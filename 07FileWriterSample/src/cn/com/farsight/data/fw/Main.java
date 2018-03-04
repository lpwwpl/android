package cn.com.farsight.data.fw;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;

public class Main extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		try {
			// ����FileWriter���󣬲���д��λ���趨ΪSD���е�output.txt
			FileWriter fw = new FileWriter("/mnt/sdcard/output.txt", false);
			// ����fw��Output Buffer
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("Hello, Android");
			bw.newLine();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}