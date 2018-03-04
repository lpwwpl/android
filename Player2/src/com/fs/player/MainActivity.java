package com.fs.player;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.database.Cursor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class MainActivity extends Activity {

	private MediaPlayer mediaPlayer = null;
	private ListView song_listview;
	private TextView song_name;
	private TextView total_process_tv;
	private TextView now_process_tv;
	private boolean isChanging;
	private Thread thread;
	private int flag = 0;
	private int isThreadStart = 0;
	/**
	 * 定义查找音乐信息数组，1.标题 2.音乐时间 3.艺术家 4.音乐id 5.显示名字 6.数据路径
	 */
	private String[] media_music_info = { MediaStore.Audio.Media.TITLE,
			MediaStore.Audio.Media.DURATION, MediaStore.Audio.Media.ARTIST,
			MediaStore.Audio.Media._ID, MediaStore.Audio.Media.DISPLAY_NAME,
			MediaStore.Audio.Media.DATA, MediaStore.Audio.Media.ALBUM_ID };;

	private long[] song_ids;
	private String[] song_titles;
	private String[] song_artists;
	private String[] song_path;
	private long song_duration[];
	private ImageButton play_bn;
	private ImageButton next_bn;
	private ImageButton prev_bn;
	private SeekBar seekbar;
	private List<Map<String, Object>> list;
	protected boolean isClick_play;
	private int current_position;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		current_position = 0;
		mediaPlayer = new MediaPlayer();
		mediaPlayer.reset();
		song_ids = new long[100];
		song_titles = new String[100];
		song_artists = new String[100];
		song_path = new String[100];
		song_duration = new long[100];
		song_listview = (ListView) findViewById(R.id.song_listview);
		play_bn = (ImageButton) findViewById(R.id.play_bn);
		next_bn = (ImageButton) findViewById(R.id.next_bn);
		prev_bn = (ImageButton) findViewById(R.id.prev_bn);
		seekbar = (SeekBar) findViewById(R.id.seekbar);
		song_name = (TextView) findViewById(R.id.song_name);
		total_process_tv = (TextView) findViewById(R.id.total_process_tv);
		now_process_tv = (TextView) findViewById(R.id.now_process_tv);

		list = new ArrayList<Map<String, Object>>();
		Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
		Cursor cursor = getContentResolver().query(uri, media_music_info, null,
				null, MediaStore.Audio.Media.DEFAULT_SORT_ORDER);

		Map<String, Object> map = new HashMap<String, Object>();
		for (int i = 0; i < cursor.getCount(); i++) {
			cursor.moveToNext();
			song_ids[i] = cursor.getLong(cursor
					.getColumnIndex(MediaStore.Audio.Media._ID));
			song_titles[i] = cursor.getString(cursor
					.getColumnIndex(MediaStore.Audio.Media.TITLE));
			song_artists[i] = cursor.getString(cursor
					.getColumnIndex(MediaStore.Audio.Media.ARTIST));
			song_path[i] = cursor.getString(cursor
					.getColumnIndex(MediaStore.Audio.Media.DATA));
			song_duration[i] = cursor.getLong(cursor
					.getColumnIndex(MediaStore.Audio.Media.DURATION));
			map.put("id", song_ids[i]);
			map.put("name", song_artists[i]);
			map.put("song", song_titles[i]);
			map.put("path", song_path[i]);
			map.put("duration", song_duration[i]);
			list.add(map);
		}

		SimpleAdapter simpleAdapter = new SimpleAdapter(MainActivity.this,
				list, R.layout.songlist, new String[] { "id", "name", "song" },
				new int[] { R.id.idview, R.id.nameview, R.id.songview });

		song_listview.setAdapter(simpleAdapter);

		PlayerSongListViewClickHandler playerSongListViewClickHandler = new PlayerSongListViewClickHandler();
		song_listview.setOnItemClickListener(playerSongListViewClickHandler);

		PlayerButtonClickHandler playerButtonClickHandler = new PlayerButtonClickHandler();
		play_bn.setOnClickListener(playerButtonClickHandler);
		prev_bn.setOnClickListener(playerButtonClickHandler);
		next_bn.setOnClickListener(playerButtonClickHandler);

		PlayerSeekBarChangeHandler playerSeekBarChangeHandler = new PlayerSeekBarChangeHandler();
		seekbar.setOnSeekBarChangeListener(playerSeekBarChangeHandler);

	}

	public void prepareMediaPlayer(String path) {
		try {

			mediaPlayer.setDataSource(path);
			mediaPlayer.setAudioStreamType(AudioManager.STREAM_RING);
			mediaPlayer.prepare();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void resumeMediaPlayer() {
		mediaPlayer.start();
		thread.resume();
	}

	public void startMediaPlayer() {
		mediaPlayer.start();
		thread.start();
	}

	public void pauseMediaPlayer() {
		mediaPlayer.pause();
		thread.suspend();
	}

	public void stopMediaPlayer() {
		mediaPlayer.stop();
	}

	class PlayerSongListViewClickHandler implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> adapterview, View view,
				int position, long id) {
			mediaPlayer.reset();
			current_position = position;
			String path = (String) list.get(position).get("path");
			prepareMediaPlayer(path);
			song_name.setText((String) list.get(current_position).get("song"));
			seekbar.setMax(mediaPlayer.getDuration());
			total_process_tv.setText(format(((Long) list.get(current_position)
					.get("duration"))));
			play_bn.setImageDrawable(getResources().getDrawable(
					R.drawable.pause));
			isClick_play = true;
			startMediaPlayer();
		}

		public String format(long time) {
			SimpleDateFormat format = new SimpleDateFormat("mm:ss");
			String hmsString = format.format(time);
			return hmsString;
		}
	}

	public String format(long time) {
		SimpleDateFormat format = new SimpleDateFormat("mm:ss");
		String hmsString = format.format(time);
		return hmsString;
	}

	class PlayerButtonClickHandler implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if (v.getId() == play_bn.getId()) {
				if (!isClick_play) {
					play_bn.setImageDrawable(getResources().getDrawable(
							R.drawable.pause));
					isClick_play = true;
					resumeMediaPlayer();
				} else {
					play_bn.setImageDrawable(getResources().getDrawable(
							R.drawable.play));
					isClick_play = false;
					pauseMediaPlayer();
				}
			} else if (v.getId() == prev_bn.getId()) {
				mediaPlayer.reset();
				current_position = (current_position - 1 + list.size())
						% list.size();
				song_name.setText((String) list.get(current_position).get(
						"song"));
				prepareMediaPlayer((String) list.get(current_position).get(
						"path"));
				startMediaPlayer();
			} else if (v.getId() == next_bn.getId()) {
				mediaPlayer.reset();
				current_position = (current_position + 1 + list.size())
						% list.size();
				song_name.setText((String) list.get(current_position).get(
						"song"));
				prepareMediaPlayer((String) list.get(current_position).get(
						"path"));
				startMediaPlayer();
			}
		}
	}

	class PlayerSeekBarChangeHandler implements OnSeekBarChangeListener {

		@Override
		public void onProgressChanged(SeekBar seekBar, int progress,
				boolean fromUser) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {
			// TODO Auto-generated method stub
			isChanging = true;
		}

		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
			// TODO Auto-generated method stub
			mediaPlayer.seekTo(seekbar.getProgress());
			isChanging = false;
		}
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if (mediaPlayer.isPlaying())
			stopMediaPlayer();
		mediaPlayer.release();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
