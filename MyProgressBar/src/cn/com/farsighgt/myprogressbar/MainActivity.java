package cn.com.farsighgt.myprogressbar;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar1);
		TextView current = (TextView) findViewById(R.id.current);
		TextView total = (TextView) findViewById(R.id.total);
		
		
		progressBar.setMax(200);
		progressBar.setProgress(20);
		
		
		current.setText("当前进度："+progressBar.getProgress());
		total.setText("总进度："+progressBar.getMax());
	}

	

}
