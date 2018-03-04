package cn.com.farsight.mybadthread;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView textView;



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		textView = (TextView) findViewById(R.id.tv);
		
		new MyThread().start();
	}


	
	class MyThread extends Thread
	{

		private int count;

		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			while(true)
			{
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				textView.setText("º∆ ±"+(count++)+"√Î");
				//count++;
				
			}
		}
		
		
	}
}
