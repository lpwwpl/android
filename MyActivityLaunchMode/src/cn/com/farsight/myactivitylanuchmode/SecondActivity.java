package cn.com.farsight.myactivitylanuchmode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.second);

	

		TextView textView = (TextView) findViewById(R.id.textview);

		textView.setText("这是第二个界面 " + SecondActivity.this.toString()+"栈ID："+getTaskId());

		Button button = (Button) findViewById(R.id.second);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent intent = new Intent(SecondActivity.this,
						ThirdActivity.class);

				startActivity(intent);
			}
		});
	}

}
