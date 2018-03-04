package cn.com.farsight.mybroadcastreceiverdemo02;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyCast extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub

		Log.v("TAG", "MyCast "+intent.getStringExtra("key"));
		
		Intent intent2 = new Intent(context, MyService.class);
		context.startService(intent2);
	}

}
