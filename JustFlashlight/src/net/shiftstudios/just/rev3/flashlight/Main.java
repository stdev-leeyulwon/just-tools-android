package net.shiftstudios.just.rev3.flashlight;

import java.lang.reflect.Field;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

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

	public boolean blinkmode = false;

	public TextView uiState;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getOverflowMenu();

		ActionBar ab = this.getActionBar();
		ab.setTitle("");
		ab.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.action_bar_background));

		uiState = (TextView) findViewById(R.id.textView1);
		uiState.setText(R.string.on);
		uiState.setShadowLayer(20, 0, 0, 0xa0ffffff);

		SeekBar seekbar2 = (SeekBar) findViewById(R.id.seekBar2);
		seekbar2.setOnSeekBarChangeListener(change2);

		pr = (TextView) findViewById(R.id.textView3);
		flight = (ImageView) findViewById(R.id.imageView1);
		flight.setImageResource(R.drawable.flight);

		flash = getPackageManager().hasSystemFeature(
				PackageManager.FEATURE_CAMERA_FLASH);
		cam = Camera.open();

		blinkDelay = 50; // Delay in ms

		flashToggle(null);
	}

	public void flashToggle(View v) {
		if (isToggled) {
			isToggled = false;
			flight.setImageResource(R.drawable.button);
			// The toggle is disabled
			Parameters p = cam.getParameters();
			p.setFlashMode(Parameters.FLASH_MODE_OFF);
			cam.setParameters(p);
			cam.startPreview();
			uiState.setText(R.string.off);
			uiState.setShadowLayer(10, 0, 0, 0x00ffffff);
			on = false;
			blinkmode = false;

			myString = "11";
		} else {
			isToggled = true;
			flight.setImageResource(R.drawable.button_glow);

			// The toggle is enabled
			p = cam.getParameters();
			p.setFlashMode(Parameters.FLASH_MODE_TORCH);
			cam.setParameters(p);
			cam.startPreview();
			uiState.setText(R.string.on);
			uiState.setShadowLayer(20, 0, 0, 0xa0ffffff);

			CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox1);

			background = new Thread(new Runnable() {
				@Override
				public void run() {
					while (blinkmode) {
						myString = "01";

						for (int i = 0; blinkmode; i++) {
							if (myString.charAt(i % 2) == '0') {
								p.setFlashMode(Parameters.FLASH_MODE_TORCH);
								runOnUiThread(new Runnable() {
									@Override
									public void run() {
										uiState.setText(R.string.blink);
										uiState.setShadowLayer(20, 0, 0,
												0xa0ffffff);
									}
								});
							} else {
								p.setFlashMode(Parameters.FLASH_MODE_OFF);
								runOnUiThread(new Runnable() {
									@Override
									public void run() {
										uiState.setText(R.string.blink);
										uiState.setShadowLayer(10, 0, 0,
												0x00ffffff);
									}
								});
							}
							cam.setParameters(p);
							cam.startPreview();
							try {
								Thread.sleep(blinkDelay);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							if (i == 2) {
								i = 0;
							}
						}

					}
				}
			});

			if (checkBox.isChecked()) {
				background.start();
				blinkmode = true;
			} else {
				myString = "00";
				blinkmode = false;
			}

			on = true;

		}
	}

	SeekBar.OnSeekBarChangeListener change2 = new OnSeekBarChangeListener() {

		@Override
		public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
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

		}

		@Override
		public void onStopTrackingTouch(SeekBar arg0) {

		}

	};

	public void onPause() {
		super.onPause();
		if (on) {
			Notification.Builder mBuilder = new Notification.Builder(this)
					.setSmallIcon(R.drawable.flashlight_noti)
					.setContentTitle(
							getResources().getString(R.string.noti_top))
					.setContentText(
							getResources().getString(R.string.noti_bottom));
			// Creates an explicit intent for an Activity in your app
			Intent resultIntent = new Intent(this, ResultActivity.class);

			// This ensures that navigating backward from the Activity leads out
			// of
			// your application to the Home screen.
			TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
			// Adds the back stack for the Intent (but not the Intent itself)
			stackBuilder.addParentStack(ResultActivity.class);
			// Adds the Intent that starts the Activity to the top of the stack
			stackBuilder.addNextIntent(resultIntent);
			PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(
					0, PendingIntent.FLAG_UPDATE_CURRENT);
			mBuilder.setContentIntent(resultPendingIntent);
			NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
			// mId allows you to update the notification later on.
			Notification notification = mBuilder.build();
			notification.flags |= Notification.FLAG_AUTO_CANCEL
					| Notification.FLAG_NO_CLEAR;
			mNotificationManager.notify(1, notification);

		}
	}

	public void onDestroy() {
		super.onDestroy();
		cam.stopPreview();
		cam.release();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		menu.add(0, 0, 0, getResources().getString(R.string.contact));
		menu.add(0, 1, 1, getResources().getString(R.string.about));
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item != null) {
			switch (item.getItemId()) {
			case 0:
				String version = "";
				try {
					PackageInfo i = getApplicationContext()
							.getPackageManager()
							.getPackageInfo(
									getApplicationContext().getPackageName(), 0);
					version = i.versionName;
				} catch (NameNotFoundException e) {
				}

				Intent i = new Intent(Intent.ACTION_SEND);
				i.setType("message/rfc822"); // use from live device
				i.putExtra(Intent.EXTRA_EMAIL,
						new String[] { "arom303@gmail.com" });
				i.putExtra(Intent.EXTRA_SUBJECT,
						"Just Flashlight " + System.currentTimeMillis());
				i.putExtra(Intent.EXTRA_TEXT, "App Version: " + version + "\n\n\n");
				startActivity(Intent.createChooser(i,
						"Select email application"));
				break;
			case 1:
				Intent in = new Intent(this, AboutActivity.class);
				startActivity(in);
				break;
			default:
				break;
			}
			return true;
		} else {
			return false;
		}
	}

	private void getOverflowMenu() {

		try {
			ViewConfiguration config = ViewConfiguration.get(this);
			Field menuKeyField = ViewConfiguration.class
					.getDeclaredField("sHasPermanentMenuKey");
			if (menuKeyField != null) {
				menuKeyField.setAccessible(true);
				menuKeyField.setBoolean(config, false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
