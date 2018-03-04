package cn.com.farsight.mybroadcastreceiverdemo02;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MyService extends Service {

	public boolean isStop;
	private MyThread myThread;


	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
		myThread = new MyThread();
		myThread.start();
	}

	
	class MyThread extends Thread
	{
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

				System.out.println("º∆ ±:" + (count++) + "√Î");
			}
		}

		public void setStop() {
			// TODO Auto-generated method stub
			isStop = true;
		}
		
	}
	
}
