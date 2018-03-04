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
				// 发送seekbar的当前进度
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

	// 接收歌曲进度
	class ProgressReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			System.out.println("aaaaaa");
			int progress = intent.getIntExtra("pro", 0);
			seekBar.setProgress(progress);
			progressView.setText(format(progress));
		}
	}

	// 接收歌曲长度
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

	// 将毫秒转换成00:00这种格式
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
			// 发送启动MediaPlayer的消息
			Intent intent = new Intent();
			intent.setAction("control");
			intent.putExtra("ctrl", 1);
			sendBroadcast(intent);
		}
	}

	public void pause(View view) {
		// 发送启动MediaPlayer的消息
		Intent intent = new Intent();
		intent.setAction("control");
		intent.putExtra("ctrl", 2);
		sendBroadcast(intent);
	}

	public void previous(View view) {
		// 发送启动MediaPlayer的消息
		Intent intent = new Intent();
		intent.setAction("control");
		intent.putExtra("ctrl", 3);
		sendBroadcast(intent);
	}

	public void next(View view) {
		// 发送启动MediaPlayer的消息
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
						// 将路径放入到ArrayList中
						musics.add(files[i].getPath());
						titles.add(getTitleByPath(files[i].getPath()));
					}
				} else if (files[i].isDirectory()
						&& files[i].getPath().indexOf("/.") == -1) {
					if (isSubDir) { // 递归遍历子目录
						getFiles(files[i].getPath(), extension, isSubDir);
					}
				}
			}
		}
	}

	// 根据文件路径取出文件名作为歌名
	// 根据文件路径得到音频文件名称，当作歌曲名
	private String getTitleByPath(String path) {
		String title = null;
		int lastSlash = path.lastIndexOf("/");
		int lastDot = path.lastIndexOf(".");
		title = path.substring(lastSlash + 1, lastDot);
		return title;
	}
}