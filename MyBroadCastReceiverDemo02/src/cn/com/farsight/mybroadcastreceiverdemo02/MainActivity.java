package cn.com.farsight.mybroadcastreceiverdemo02;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button button = (Button) findViewById(R.id.button1);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//Intent intent = new Intent(MainActivity.this, MyCast.class);
				Intent intent = new Intent();
				intent.setAction("99.7HZ");
				intent.putExtra("key", "来自99.7HZ的交通广播");
				sendBroadcast(intent);
			}
		});
	}

	

}
