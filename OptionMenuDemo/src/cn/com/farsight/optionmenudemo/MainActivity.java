package cn.com.farsight.optionmenudemo;

import com.example.optionmenudemo.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		menu.clear();
		menu.add(0, 0, 10, "关于手机");
		menu.add(0, 1, 1, "手机设置").setIcon(android.R.drawable.ic_dialog_email);
		menu.add(0, 2, 0, "手机设置1");
		menu.add(0, 3, 3, "手机设置2");
		menu.add(0, 4, 4, "手机设置3");
		menu.add(0, 5, 5, "手机设置4");
		menu.add(0, 6, 6, "手机设置5");
		menu.add(0, 7, 7, "手机设置6");
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		
		int i = item.getItemId();
		Toast.makeText(this, i+"", 0).show();		
		return super.onOptionsItemSelected(item);
	}
}
