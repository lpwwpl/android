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
		
		//��һ���������㲥����������׼����������
		receiver = new MyCastReceiver();
		//�ڶ���:����һ��IntentFilter�����൱�ڣ�Ϊ���ǵ�������׼��һ����Ƶ��ť,
		IntentFilter filter = new IntentFilter();
		//ʹ�õ�Ƶ��ť�����Ե���ָ��Ƶ��
		filter.addAction("99.7HZ");
		//������������Ƶ��ť�󶨵�������������棬Ҳ������ɹ㲥��������ע��
		registerReceiver(receiver, filter);
		
		//ע��ڶ����㲥������
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
				intent.putExtra("key", "���Ե�Ƶ99.7HZ�Ĺ㲥��Ϣ");
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

	//��һ��������һ���࣬�̳��ڹ㲥������,��дonReceive����
	class MyCastReceiver extends BroadcastReceiver
	{

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			Log.v("CATS", "���յ���Ϣ�ǣ�"+intent.getStringExtra("key"));
			Intent intent2 = new Intent(MainActivity.this, MyCastService.class);
			startService(intent2);
			
		}
		
		
	}
	
	//�ڶ����㲥��������������պ���Ӧ�������̷߳��͹���������
	class MyCountReceiver extends BroadcastReceiver
	{

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			time.setText("��ʱ��"+intent.getIntExtra("key", 0)+"��");
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
