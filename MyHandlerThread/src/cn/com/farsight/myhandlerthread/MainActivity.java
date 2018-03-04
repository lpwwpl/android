package cn.com.farsight.myhandlerthread;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView textView;
	private Handler handler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Log.v("tag", Thread.currentThread().getName());

		textView = (TextView) findViewById(R.id.tv);
		Button button = (Button) findViewById(R.id.button1);

		handler = new Handler() {

			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				if (msg.what == 0x12345) {
					Log.v("tag", "收到消息的what值是" + msg.what);
					textView.setText("" + msg.what);
				}
			}

		};

		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new MyThread().start();
			}
		});
	}

	class MyThread extends Thread {

		private int count;

		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			while (true) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				Message message = new Message();
				message.what = 0x12345;
				handler.sendEmptyMessage(message.what);

				Log.v("tag", MyThread.this.getName());

			}
		}

	}
}
