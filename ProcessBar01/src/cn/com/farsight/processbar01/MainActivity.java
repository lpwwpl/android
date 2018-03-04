package cn.com.farsight.processbar01;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity {

	private ProgressBar progressBar;
	private boolean isClick;
	private ImageButton imageButton;
	private TextView current,total;
	private Intent intent;
	private MyReceiver receiver;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		IntentFilter filter = new IntentFilter();
		filter.addAction("COUNT");
		receiver = new MyReceiver();
		registerReceiver(receiver, filter);
		
		
		progressBar = (ProgressBar) findViewById(R.id.progressBar1);
		imageButton = (ImageButton) findViewById(R.id.imageButton1);
		current = (TextView) findViewById(R.id.current);
		total = (TextView) findViewById(R.id.total);
		
		progressBar.setMax(100);
		total.setText("总进度："+progressBar.getMax());
		current.setText("当前进度："+progressBar.getProgress()+"秒");
		
		intent = new Intent(MainActivity.this, MyCastService.class);
		
		imageButton.setOnClickListener(new OnClickListener() {
			

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				
				if(!isClick)
				{
					//System.out.println("1234567890");
					imageButton.setImageDrawable(getResources().getDrawable(R.drawable.forward_focused));
					
					
					startService(intent);
					isClick = true;
				}
				
				else if(isClick)
				{
					//System.out.println("4567890");
					
					imageButton.setImageDrawable(getResources().getDrawable(R.drawable.backward_default));
					isClick = false;
					Intent  intent = new Intent("PAUSE");
					sendBroadcast(intent);
				}
				
				
				
				
			}
		});
		
		//progressBar.setMax(100);
		//progressBar.setProgress(10);
	
		
	}

	

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		
		unregisterReceiver(receiver);
	}



	class MyReceiver extends BroadcastReceiver
	{

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			
			progressBar.setProgress(intent.getIntExtra("key", 0));
			current.setText("当前进度："+intent.getIntExtra("key", 0)+"秒");
			
		}
		
		
	}
}
