package cn.com.farsight.myimageview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainActivity extends Activity {

	int[] images = { R.drawable.p0404, R.drawable.p0405, R.drawable.p0406,
			R.drawable.p0407, R.drawable.p0408 };
	private ImageView imageView;
	private ImageButton pre;
	private ImageButton next;
	public int i;
	private int currentImage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		imageView = (ImageView) findViewById(R.id.imageView1);
		pre = (ImageButton) findViewById(R.id.pre);
		next = (ImageButton) findViewById(R.id.next);

		MyImageListener listener = new MyImageListener();

		pre.setOnClickListener(listener);

		next.setOnClickListener(listener);
	}

	class MyImageListener implements OnClickListener {

		

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.pre:
				currentImage = (currentImage + 1 + images.length) % images.length;
				imageView.setImageDrawable(getResources().getDrawable(images[currentImage]));
				
				break;
			case R.id.next:
				currentImage = (currentImage - 1 + images.length) % images.length;
				imageView.setImageDrawable(getResources().getDrawable(images[currentImage]));
				break;

			default:
				break;
			}

		}

	}

}
