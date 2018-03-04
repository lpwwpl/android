package cn.com.farsight.mycountservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

	
	public boolean isStop;
	private MyThread myThread;
	private MyThread02 myThread02;
	private Thread thread;

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();

		Log.v("SERVICE", "MyService onCreate");

		myThread02 = new MyThread02();
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.v("SERVICE", "MyService onDestroy");
		//myThread.setStop();
		myThread02.setStop();
	}

	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
		Log.v("SERVICE", "MyService onStart");
		
		
		thread = new Thread(myThread02);
		thread.start();

		
		/*myThread = new MyThread();
		myThread.start();*/
	}

	// 线程的第一种构建方式
	class MyThread extends Thread {
		public int count;
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

				System.out.println("计时:" + (count++) + "秒");
			}
		}

		public void setStop() {
			// TODO Auto-generated method stub
			isStop = true;
		}

	}

	// 线程的第二种构建方式

	class MyThread02 implements Runnable {
		public int count;
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

				System.out.println("计时:" + (count++) + "秒");
			}
		}

		public void setStop() {
			// TODO Auto-generated method stub
			isStop = true;
		}
	}

	
	
}
