package cn.com.farsight.logindemo;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	private SharedPreferences sp;
	private Editor editor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final EditText username = (EditText) findViewById(R.id.user);
		final EditText passwd = (EditText) findViewById(R.id.pwd);
		Button login = (Button) findViewById(R.id.login);
		Button reg = (Button) findViewById(R.id.reg);
		
		sp = getSharedPreferences("values", Context.MODE_WORLD_READABLE);
		editor = sp.edit();
		
		login.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String name = username.getText().toString().trim();
				String pwd = passwd.getText().toString().trim();
				if(name.equals(sp.getString("username", null)) && pwd.equals(sp.getString("passwd", null)))
				{
					
					Toast.makeText(MainActivity.this, "登陆成功！", Toast.LENGTH_SHORT).show();
				}
				else
				{
					Toast.makeText(MainActivity.this, "用户名或密码不正确！！", Toast.LENGTH_SHORT).show();
				}
			}
		});
		
		
		reg.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//String r_username = username.getText().toString().trim();
				//String r_passwd = pwd.getText().toString().trim();
				editor.putString("username", username.getText().toString().trim());
				editor.putString("passwd", passwd.getText().toString().trim());
				editor.commit();
				Toast.makeText(MainActivity.this, "恭喜你，成功注册！", Toast.LENGTH_SHORT).show();
			}
		});
	}

	

}
