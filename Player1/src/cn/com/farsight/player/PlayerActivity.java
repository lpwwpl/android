package cn.com.farsight.player;

import java.io.File;
import java.util.ArrayList;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

public class PlayerActivity extends Activity {
	/** Called when the activity is first created. */
	boolean serviceStarted;
	ArrayList musics = new ArrayList();
	ArrayList titles = new ArrayList();
	ListView lv;
	DurationReceiver dReceiver;
	ProgressReceiver pReceiver;

	TextView maxView, progressView;
	SeekBar seekBar;

	Intent sIntent = new Intent();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		getFiles("/mnt/sdcard", "mp3", false);
		ArrayAdapter adapter = new ArrayAdapter(this,
				android.R.layout.simple_list_item_1, titles);
		lv = (ListView) findViewById(R.id.listView1);
		lv.setAdapter(adapter);
		maxView = (TextView) findViewById(R.id.textView2);
		progressView = (TextView) findViewById(R.id.textView1);
		seekBar = (SeekBar) findViewById(R.id.seekBar1);
		seekBar.setOnSeekBarChangeListener(new MyListener());

		dReceiver = new DurationReceiver();
		IntentFilter dFilter = new IntentFilter();
		dFilter.addAction("duration");
		this.registerReceiver(dReceiver, dFilter);

		pReceiver = new ProgressReceiver();
		IntentFilter pFilter = new IntentFilter();
		pFilter.addAction("progress");
		this.registerReceiver(pReceiver, pFilter);

		sIntent.setAction("seekbar");

	}

	private void sendSeekbarProgress(int progress) {
		sIntent.putExtra("sPro", progress);
		sendBroadcast(sIntent);
	}

	class MyListener implements SeekBar.OnSeekBarChangeListener {

		public void onProgressChanged(SeekBar seekBar, int progress,
				boolean fromUser) {
			if (fromUser) {
				// ����seekbar�ĵ�ǰ����
				sendSeekbarProgress(progress);
			}
		}

		public void onStartTrackingTouch(SeekBar seekBar) {
			// TODO Auto-generated method stub

		}

		public void onStopTrackingTouch(SeekBar seekBar) {
			// TODO Auto-generated method stub

		}

	}

	// ���ո�������
	class ProgressReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			System.out.println("aaaaaa");
			int progress = intent.getIntExtra("pro", 0);
			seekBar.setProgress(progress);
			progressView.setText(format(progress));
		}
	}

	// ���ո�������
	class DurationReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			int max = intent.getIntExtra("dura", 0);
			seekBar.setMax(max);
			maxView.setText(format(max));

		}
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		this.unregisterReceiver(dReceiver);
		this.unregisterReceiver(pReceiver);
		super.onDestroy();
	}

	// ������ת����00:00���ָ�ʽ
	private String format(long ms) {
		int ss = 1000;
		int mi = ss * 60;
		int hr = ss * 60 * 60;

		long hour = ms / hr;
		long minute = (ms - hour * hr) / mi;
		long second = (ms - hour * hr - minute * mi) / ss;

		String strHour = hour < 10 ? "0" + hour : "" + hour;
		String strMinute = minute < 10 ? "0" + minute : "" + minute;
		String strSecond = second < 10 ? "0" + second : "" + second;

		return hour == 0 ? strMinute + ":" + strSecond : strHour + ":"
				+ strMinute + ":" + strSecond;
	}

	public void play(View view) {
		if (!serviceStarted) {
			Intent intent = new Intent(this, PlayerService.class);
			intent.putStringArrayListExtra("musiclist", musics);
			startService(intent);
			serviceStarted = true;
		} else {
			// ��������MediaPlayer����Ϣ
			Intent intent = new Intent();
			intent.setAction("control");
			intent.putExtra("ctrl", 1);
			sendBroadcast(intent);
		}
	}

	public void pause(View view) {
		// ��������MediaPlayer����Ϣ
		Intent intent = new Intent();
		intent.setAction("control");
		intent.putExtra("ctrl", 2);
		sendBroadcast(intent);
	}

	public void previous(View view) {
		// ��������MediaPlayer����Ϣ
		Intent intent = new Intent();
		intent.setAction("control");
		intent.putExtra("ctrl", 3);
		sendBroadcast(intent);
	}

	public void next(View view) {
		// ��������MediaPlayer����Ϣ
		Intent intent = new Intent();
		intent.setAction("control");
		intent.putExtra("ctrl", 4);
		sendBroadcast(intent);
	}

	private void getFiles(String startPath, String extension, boolean isSubDir) {
		File[] files = new File(startPath).listFiles();
		if (files != null) {
			for (int i = 0; i < files.length; i++) {
				if (files[i].isFile()) {
					if (files[i]
							.getPath()
							.substring(
									files[i].getPath().length()
											- extension.length())
							.equals(extension)) {
						// ��·�����뵽ArrayList��
						musics.add(files[i].getPath());
						titles.add(getTitleByPath(files[i].getPath()));
					}
				} else if (files[i].isDirectory()
						&& files[i].getPath().indexOf("/.") == -1) {
					if (isSubDir) { // �ݹ������Ŀ¼
						getFiles(files[i].getPath(), extension, isSubDir);
					}
				}
			}
		}
	}

	// �����ļ�·��ȡ���ļ�����Ϊ����
	// �����ļ�·���õ���Ƶ�ļ����ƣ�����������
	private String getTitleByPath(String path) {
		String title = null;
		int lastSlash = path.lastIndexOf("/");
		int lastDot = path.lastIndexOf(".");
		title = path.substring(lastSlash + 1, lastDot);
		return title;
	}
}