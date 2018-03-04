package cn.com.farsight.myactivitylanuchmode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		TextView textView = (TextView) findViewById(R.id.tv);

		textView.setText("这是第一个界面 "+MainActivity.this.toString()+"栈ID："+getTaskId());

		// 第一步：根据Button的id，找到对象

		Button button = (Button) findViewById(R.id.button1);

		// 第二步：为我们的Button绑定事件监听器

		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// 第三步：跳转页面
				// Intent（意图）主要是解决Android应用的各项组件之间的通讯。
				// 显式Intent
				Intent intent = new Intent(MainActivity.this,
						SecondActivity.class);

				// 光有意图或者想法是不行的，必须付诸行动，女神才是你的
				startActivity(intent);
			}
		});

	}

}
