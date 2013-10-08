package com.shiftpsh.jf;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.ToggleButton;


public class Main extends Activity {
	
	public TextView br;
	public TextView pr;
	public boolean isToggled = false;;
	public boolean flash, isClicked;
	public Camera cam;
	public boolean on;
	public Parameters p;
	public Thread background;
	public boolean isBlinkStateOn;
	public String myString;
	public int blinkDelay;
	public ImageView flight;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
         setContentView(R.layout.activity_main);
  		 
  		SeekBar seekbar2 = (SeekBar) findViewById(R.id.seekBar2);
 		 seekbar2.setOnSeekBarChangeListener(change2);
         
         pr = (TextView) findViewById(R.id.textView3);
         flight = (ImageView) findViewById(R.id.imageView1);
         flight.setImageResource(R.drawable.flight);
         
         flash = getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
         cam = Camera.open();
         
         blinkDelay = 1000; //Delay in ms
         
         flashToggle(null);
	}
	
	public void flashToggle(View v){
		if (isToggled) {
			isToggled = false;
			flight.setImageResource(R.drawable.flight);
			// The toggle is disabled 
        	Parameters p = cam.getParameters();
        	p.setFlashMode(Parameters.FLASH_MODE_OFF);
        	cam.setParameters(p);
        	cam.startPreview();
        	on = false;
        	background.interrupt();

        	myString = "11";
		} else {
			isToggled = true;
			flight.setImageResource(R.drawable.flighton);
			   // The toggle is enabled
        	background = null;
        	p = cam.getParameters();
        	p.setFlashMode(Parameters.FLASH_MODE_TORCH);
        	cam.setParameters(p);
        	cam.startPreview();
        	
        	CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox1);

        	background = new Thread(new Runnable() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					while (!background.isInterrupted()) {
        	        	myString = "01";
        	        	
        	        	for (int i = 0; i < 2147400000; i++) {
        	        	   if (myString.charAt(i % 2) == '0') {
        	        	      p.setFlashMode(Parameters.FLASH_MODE_TORCH);
        	        	   } else {
        	        	      p.setFlashMode(Parameters.FLASH_MODE_OFF);
        	        	   }
        	        	   cam.setParameters(p);
           	           	   cam.startPreview();
        	        	   try {
        	        	      Thread.sleep(blinkDelay);
        	        	   } catch (InterruptedException e) {
        	        	      e.printStackTrace();
        	        	   }
        	        	   if (i == 2){
        	        		   i = 0;
        	        	   }
        	        	}
					
				}
        		}
        	});
        	
        	
        	
        	if (checkBox.isChecked()) {
        		background.start();
			} else {
				myString = "00";
			}
        
        	on = true;
        
		}
	}
    
    SeekBar.OnSeekBarChangeListener change2 = new OnSeekBarChangeListener() {

		@Override
		public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
			// TODO Auto-generated method stub
			
			if (arg1 < 50) {
			blinkDelay = (int) (arg1 + 1) * 1;
			pr.setText(blinkDelay + "ms");
			} else {
				blinkDelay = (int) (arg1 - 40) * 5;
				pr.setText(blinkDelay + "ms");
			}
		}

		@Override
		public void onStartTrackingTouch(SeekBar arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onStopTrackingTouch(SeekBar arg0) {
			// TODO Auto-generated method stub
			
		}
    	
    };

    public void onPause() {
        super.onPause();
        if (on){
        	NotificationCompat.Builder mBuilder =
        	        new NotificationCompat.Builder(this)
        	        .setSmallIcon(R.drawable.ic_launcher)
        	        .setContentTitle("Camera Flash Still On")
        	        .setContentText("Click here to disable");
        	// Creates an explicit intent for an Activity in your app
        	Intent resultIntent = new Intent(this, ResultActivity.class);

        	// This ensures that navigating backward from the Activity leads out of
        	// your application to the Home screen.
        	TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        	// Adds the back stack for the Intent (but not the Intent itself)
        	stackBuilder.addParentStack(ResultActivity.class);
        	// Adds the Intent that starts the Activity to the top of the stack
        	stackBuilder.addNextIntent(resultIntent);
        	PendingIntent resultPendingIntent =
        	        stackBuilder.getPendingIntent(
        	            0,
        	            PendingIntent.FLAG_UPDATE_CURRENT
        	        );
        	        		mBuilder.setContentIntent(resultPendingIntent);
        	NotificationManager mNotificationManager =
        	    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        	// mId allows you to update the notification later on.
        	Notification notification = mBuilder.build();
        	notification.flags |= Notification.FLAG_AUTO_CANCEL | Notification.FLAG_NO_CLEAR;
        	mNotificationManager.notify(1, notification);
        	
        }
    }
    
    public void onDestroy() {
        super.onDestroy();
        cam.stopPreview();
        cam.release();
    }

    

}
