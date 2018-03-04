package cn.com.farsight.myimagebutton;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class MainActivity extends Activity {

	private ImageButton imageButton;
	protected boolean isClick;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		imageButton = (ImageButton) findViewById(R.id.imageButton1);

		imageButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (!isClick) {
					imageButton.setImageDrawable(getResources().getDrawable(
							R.drawable.pause));
					isClick = true;
				} else {
					imageButton.setImageDrawable(getResources().getDrawable(
							R.drawable.play));
					isClick = false;
				}
			}
		});
	}

}
