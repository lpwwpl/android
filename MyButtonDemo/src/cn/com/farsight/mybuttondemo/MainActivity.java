package cn.com.farsight.mybuttondemo;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

//第五种：Activity本身作为事件监听器
public class MainActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button button2 = (Button) findViewById(R.id.button2);
		Button button3 = (Button) findViewById(R.id.button3);
		Button button4 = (Button) findViewById(R.id.button4);
		Button button5 = (Button) findViewById(R.id.button5);
		
		MyButtonListener listener = new MyButtonListener();
		button2.setOnClickListener(listener);
		button3.setOnClickListener(listener);
		button4.setOnClickListener(listener);
		button5.setOnClickListener(listener);

	/*	button2.setOnClickListener(new MyButtonListener());
		button3.setOnClickListener(new MyButtonListener03());
		button5.setOnClickListener(this);
		//第四种：匿名内部类方式
		button4.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, "第四次被点击", Toast.LENGTH_SHORT)
						.show();
			}
		});*/
	}

	// 第一种：直接通过xml当中的属性进行绑定事件监听
	public void MyListen(View v) {
		Toast.makeText(MainActivity.this, "第一次被点击", Toast.LENGTH_SHORT).show();
	}

	// 第二种：采用内部类的方式

	class MyButtonListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			//v.getId()
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.button2:
				Toast.makeText(MainActivity.this, "button2被点击", Toast.LENGTH_SHORT)
				.show();
				break;
			case R.id.button3:
				Toast.makeText(MainActivity.this, "button3被点击", Toast.LENGTH_SHORT)
				.show();
				break;
			case R.id.button4:
				Toast.makeText(MainActivity.this, "button4被点击", Toast.LENGTH_SHORT)
				.show();
				break;
			case R.id.button5:
				Toast.makeText(MainActivity.this, "button5被点击", Toast.LENGTH_SHORT)
				.show();
				break;
			default:
				break;
			}
			
		}

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Toast.makeText(MainActivity.this, "第五次被点击", Toast.LENGTH_SHORT).show();
	}

	// 第三种：外部类方式,这时就需要构建一个外部类对象
}
