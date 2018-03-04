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

		// ����Preferences�ļ����ʹ�ģʽ
		sp = getSharedPreferences("values", Context.MODE_WORLD_READABLE);
		// ��ͼ�������ļ���ȡ����
		String username = sp.getString("username", null);
		// ��������ݣ�����ʾ�������
		if (username != null) {
			// ��EditText����������Ϊ��һ���˳�ʱ������Preferences�ļ��е��ַ���
			edit.setText(username);
		}else{
			edit.setText("�������û���");
		}
	}

	@Override
	public void onDestroy() {
		// ��onDestroy()�����У�ʵ���˽���ǰ
		// EditText�е��ַ����洢��Preferences�ļ�
		Editor editor = sp.edit();
		
		editor.putString("username", String.valueOf(edit.getText()));
		editor.commit();
		super.onDestroy();
	}
}