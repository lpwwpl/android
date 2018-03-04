package cn.com.farsight.mybuttondemo;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class MyButtonListener03 implements OnClickListener {

	//第三种：外部类方式
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Log.v("TAG", "第三次被点击了！");
	}

}
