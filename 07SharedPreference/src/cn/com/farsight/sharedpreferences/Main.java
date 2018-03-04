package cn.com.farsight.sharedpreferences;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.widget.EditText;

public class Main extends Activity {
	EditText edit;
	SharedPreferences sp;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		edit = (EditText) findViewById(R.id.editText1);

		// 传入Preferences文件名和打开模式
		sp = getSharedPreferences("values", Context.MODE_WORLD_READABLE);
		// 试图从配置文件读取数据
		String username = sp.getString("username", null);
		// 如果有数据，则显示到输入框
		if (username != null) {
			// 将EditText的内容设置为上一次退出时保存在Preferences文件中的字符串
			edit.setText(username);
		}else{
			edit.setText("请输入用户名");
		}
	}

	@Override
	public void onDestroy() {
		// 在onDestroy()方法中，实现了将当前
		// EditText中的字符串存储到Preferences文件
		Editor editor = sp.edit();
		
		editor.putString("username", String.valueOf(edit.getText()));
		editor.commit();
		super.onDestroy();
	}
}