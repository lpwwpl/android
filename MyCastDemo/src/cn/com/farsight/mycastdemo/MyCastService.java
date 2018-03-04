package cn.com.farsight.mycastdemo;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

public class MyCastService extends Service {

	public boolean isStop;
	//private MyThread thread;
	public int count;
	private MySerThread serThread;
	private MyThreadCast cast;

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();

		cast = new MyThreadCast();
		IntentFilter filter = new IntentFilter();
		filter.addAction("PAUSE");
		registerReceiver(cast, filter);
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		unregisterReceiver(cast);
	}

	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
		isStop = false;
		//thread = new MyThread();
		//thread.start();
		
		serThread = new MySerThread();
		Thread thread = new Thread(serThread);
		thread.start();
	}

/*	// 线程的第一种构建方式
	class MyThread extends Thread {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();

			while (!isStop) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// System.out.println("计时:" + (count++) + "秒");
				Intent intent = new Intent("COUNT");
				intent.putExtra("key", count++);
				sendBroadcast(intent);
			}
		}

		public void setStop() {
			// TODO Auto-generated method stub
			isStop = true;
		}

	}*/

	// 线程的第二种启动方式
	class MySerThread implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			while (!isStop) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// System.out.println("计时:" + (count++) + "秒");
				Intent intent = new Intent("COUNT");
				intent.putExtra("key", count++);
				sendBroadcast(intent);
			}
		}

		public void setStop() {
			// TODO Auto-generated method stub
			isStop = true;
		}

	}

	class MyThreadCast extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			//thread.setStop();
			serThread.setStop();
		}

	}
}
