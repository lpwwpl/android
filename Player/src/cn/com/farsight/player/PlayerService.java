package cn.com.farsight.player;

import java.io.IOException;
import java.util.ArrayList;


import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

public class PlayerService extends Service {
	MediaPlayer player;
	ArrayList musics = new ArrayList();
	int idx, size;
	MyReceiver receiver;
	SeekbarReceiver bReceiver;
	
	Intent pIntent = new Intent();
	ProgressThread thread = new ProgressThread();

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub

		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		player = new MediaPlayer();
		player.setOnCompletionListener(new MyCompletionListener());
		receiver = new MyReceiver();
		IntentFilter filter = new IntentFilter();
		filter.addAction("control");
		this.registerReceiver(receiver, filter);
		
		bReceiver = new SeekbarReceiver();
		IntentFilter bFilter = new IntentFilter();
		bFilter.addAction("seekbar");
		this.registerReceiver(bReceiver, bFilter);
		
		
		//发送实时进度
		pIntent.setAction("progress");
		super.onCreate();
	}
	//监听一首歌是否已经播放完，如果播放完，则播放下一首
	class MyCompletionListener implements MediaPlayer.OnCompletionListener{
		public void onCompletion(MediaPlayer mp) {
			mynext();			
		}		
	}

	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		musics = intent.getStringArrayListExtra("musiclist");
		size = musics.size();
		try {
			idx = 0;
			player.reset();
			player.setDataSource((String) musics.get(0));
			
			player.prepare();
			player.start();
			sendDuration();
			thread.start();
			
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
		super.onStart(intent, startId);
	}

	//接收进度条拉动的接收器
	class SeekbarReceiver extends BroadcastReceiver{

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			int progress = intent.getIntExtra("sPro", 0);
			player.seekTo(progress);
		}		
	}
	
	// 接受按钮动作的接收器
	class MyReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			int ctrl = intent.getIntExtra("ctrl", 0);
			if (ctrl == 1) { // 播放
				myplay();
			} else if (ctrl == 2) { // 暂停
				mypause();
			} else if (ctrl == 3) { // 上一首
				myprevious();
			} else { // 下一首
				mynext();
			}

		}
	}

	private void mynext() {
		idx = ++idx % size;
		player.reset();
		try {
			player.setDataSource((String) musics.get(idx));
			player.prepare();
			player.start();
			sendDuration();
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

	private void myprevious() {
		idx = (--idx + size) % size;
		player.reset();
		try {
			player.setDataSource((String) musics.get(idx));
			player.prepare();
			player.start();
			sendDuration();
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
	//发送歌曲长度
	private void sendDuration(){
		Intent dintent = new Intent();
		dintent.setAction("duration");
		dintent.putExtra("dura", player.getDuration());
		sendBroadcast(dintent);
	}

	private void myplay() {
		if (player != null) {
			player.start();
			thread.resume();
		}
	}

	private void mypause() {
		if (player != null) {
			player.pause();
			thread.pause();
		}
	}
	
	private void sendProgress(){
		System.out.println("dd");
		pIntent.putExtra("pro", player.getCurrentPosition());
		sendBroadcast(pIntent);
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		this.unregisterReceiver(receiver);
		this.unregisterReceiver(bReceiver);
		super.onDestroy();
	}
	// 处理进度条的线程
	private class ProgressThread implements Runnable {
		private boolean running = false;
		private boolean waiting = false;
		private Thread thread;

		public ProgressThread() {
			thread = new Thread(this);
		}

		public void run() {
			for (;;) {
				try {
					synchronized (this) {
						if (!running) {
							break;
						}
						if (waiting) {
							this.wait();
						}
					}
					//PlayService.this.sendProgress();
					//每隔一秒钟发送一次进度
					sendProgress();
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		// 启动线程
		public void start() {
			running = true;

			// 如果线程处于等待状态，则唤醒，并且改标志位
			if (thread.getState() == Thread.State.WAITING) {
				synchronized (this) {
					this.running = true;
					this.waiting = false;
					this.notifyAll();
				}
			}

			// 如果线程已经被终止了，则新建线程
			if (thread.getState() == Thread.State.TERMINATED)
				thread = new Thread(this);
			// 如果线程是新建状态，则启动
			if (thread.getState() == Thread.State.NEW)
				thread.start();
		}

		// 暂停
		public void pause() {
			if (waiting) {
				return;
			}
			synchronized (this) {
				this.waiting = true;
			}
		}

		// 重新启动
		public void resume() {
			if (!waiting) {
				return;
			}
			synchronized (this) {
				this.waiting = false;
				this.notifyAll();
			}
		}

		// 停止线程
		public void stop() {
			if (!running) {
				return;
			}
			synchronized (this) {
				this.running = false;
			}
		}
	}
}
