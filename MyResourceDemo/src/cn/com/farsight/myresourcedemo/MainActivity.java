package cn.com.farsight.myresourcedemo;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		TextView textView = (TextView) findViewById(R.id.tv);
		textView.setText(R.string.text);
		//getResources().getColor(R.color.green);
		textView.setTextColor(getResources().getColor(R.color.green));
	}



}
