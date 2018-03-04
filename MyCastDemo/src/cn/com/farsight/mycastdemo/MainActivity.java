package cn.com.farsight.mycastdemo;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	private MyCastReceiver receiver;
	private MyCountReceiver countReceiver;
	private TextView time;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//第一步：构建广播接收器对象，准备好收音机
		receiver = new MyCastReceiver();
		//第二步:构建一个IntentFilter对象，相当于，为我们的收音机准备一个调频按钮,
		IntentFilter filter = new IntentFilter();
		//使用调频按钮，可以调到指定频道
		filter.addAction("99.7HZ");
		//第三步：将调频按钮绑定到你的收音机上面，也就是完成广播接收器的注册
		registerReceiver(receiver, filter);
		
		//注册第二个广播接收器
		countReceiver = new MyCountReceiver();
		IntentFilter filter2 = new IntentFilter();
		filter2.addAction("COUNT");
		registerReceiver(countReceiver, filter2);
		
		time = (TextView) findViewById(R.id.tv);
		Button start = (Button) findViewById(R.id.button1);
		Button pause = (Button) findViewById(R.id.button2);
		
		start.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent("99.7HZ");
				intent.putExtra("key", "来自调频99.7HZ的广播消息");
				sendBroadcast(intent);
				
			}
		});
		
		pause.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent  intent = new Intent();
				intent.setAction("PAUSE");
				sendBroadcast(intent);
			}
		});
		
	}

	//第一步：构建一个类，继承于广播接收器,重写onReceive方法
	class MyCastReceiver extends BroadcastReceiver
	{

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			Log.v("CATS", "接收的消息是："+intent.getStringExtra("key"));
			Intent intent2 = new Intent(MainActivity.this, MyCastService.class);
			startService(intent2);
			
		}
		
		
	}
	
	//第二个广播接收器，负责接收和响应服务中线程发送过来的数据
	class MyCountReceiver extends BroadcastReceiver
	{

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			time.setText("计时："+intent.getIntExtra("key", 0)+"秒");
		}
		
		
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		unregisterReceiver(receiver);
		unregisterReceiver(countReceiver);
	}
	

	
}
