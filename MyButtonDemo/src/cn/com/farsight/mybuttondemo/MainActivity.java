package cn.com.farsight.mybuttondemo;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

//�����֣�Activity������Ϊ�¼�������
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
		//�����֣������ڲ��෽ʽ
		button4.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, "���Ĵα����", Toast.LENGTH_SHORT)
						.show();
			}
		});*/
	}

	// ��һ�֣�ֱ��ͨ��xml���е����Խ��а��¼�����
	public void MyListen(View v) {
		Toast.makeText(MainActivity.this, "��һ�α����", Toast.LENGTH_SHORT).show();
	}

	// �ڶ��֣������ڲ���ķ�ʽ

	class MyButtonListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			//v.getId()
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.button2:
				Toast.makeText(MainActivity.this, "button2�����", Toast.LENGTH_SHORT)
				.show();
				break;
			case R.id.button3:
				Toast.makeText(MainActivity.this, "button3�����", Toast.LENGTH_SHORT)
				.show();
				break;
			case R.id.button4:
				Toast.makeText(MainActivity.this, "button4�����", Toast.LENGTH_SHORT)
				.show();
				break;
			case R.id.button5:
				Toast.makeText(MainActivity.this, "button5�����", Toast.LENGTH_SHORT)
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
		Toast.makeText(MainActivity.this, "����α����", Toast.LENGTH_SHORT).show();
	}

	// �����֣��ⲿ�෽ʽ,��ʱ����Ҫ����һ���ⲿ�����
}
