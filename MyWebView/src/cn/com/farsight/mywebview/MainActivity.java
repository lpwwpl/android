package cn.com.farsight.mywebview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

	private WebView webView;
	private Button go;
	private EditText web;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		web = (EditText) findViewById(R.id.editText1);
		go = (Button) findViewById(R.id.button1);
		webView = (WebView) findViewById(R.id.webView1);
		
		
		go.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				webView.loadUrl("http://"+web.getText().toString().trim());
			}
		});
	}



}
