package cn.com.farsight;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Main extends Activity {
	
	private RadioButton rdBtn1, rdBtn2, rdBtn3; 
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.main);
		
		
		rdBtn1 = (RadioButton) findViewById(R.id.radio0);
		rdBtn2 = (RadioButton) findViewById(R.id.radio1);
		rdBtn3 = (RadioButton) findViewById(R.id.radio2);
		
		
		rdBtn1.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked)
					Toast.makeText(Main.this, "ÄÐ", Toast.LENGTH_SHORT).show();
			}
		});
		
		
		rdBtn2.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked)
					Toast.makeText(Main.this, "Å®", Toast.LENGTH_SHORT).show();
			}
		});
		
		
		rdBtn3.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked)
					Toast.makeText(Main.this, "ÆäËü", Toast.LENGTH_SHORT).show();
			}
		});
		
		
	/*	RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroup1);
		rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				Main.this.setTitle(""
						+ ((RadioButton) group.findViewById(checkedId))
								.getText());
			}
		});*/
	}


	
}