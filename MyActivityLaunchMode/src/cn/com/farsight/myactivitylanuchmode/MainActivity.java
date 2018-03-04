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

		textView.setText("���ǵ�һ������ "+MainActivity.this.toString()+"ջID��"+getTaskId());

		// ��һ��������Button��id���ҵ�����

		Button button = (Button) findViewById(R.id.button1);

		// �ڶ�����Ϊ���ǵ�Button���¼�������

		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// ����������תҳ��
				// Intent����ͼ����Ҫ�ǽ��AndroidӦ�õĸ������֮���ͨѶ��
				// ��ʽIntent
				Intent intent = new Intent(MainActivity.this,
						SecondActivity.class);

				// ������ͼ�����뷨�ǲ��еģ����븶���ж���Ů��������
				startActivity(intent);
			}
		});

	}

}
