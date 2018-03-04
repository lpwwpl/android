package cn.com.farsight.linearlayoutdemo02;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

	private EditText name;
	private EditText paswd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		name = (EditText) findViewById(R.id.name);
		paswd = (EditText) findViewById(R.id.passwd);
		Button login  =(Button) findViewById(R.id.login);
		
		login.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				String names = name.getText().toString();
				String pwd = paswd.getText().toString();
				
				if(names.equals("admin") && pwd.equals("admin"))
				{
					Log.v("TAG",names+"登陆成功");
				}
				else
				{
					Log.v("TAG","用户名或密码错误");
					
				}
			}
		});
	}

	

}
