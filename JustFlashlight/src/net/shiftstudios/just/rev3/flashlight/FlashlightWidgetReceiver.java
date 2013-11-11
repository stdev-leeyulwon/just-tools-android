package net.shiftstudios.just.rev3.flashlight;

import android.appwidget.AppWidgetManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.widget.RemoteViews;

public class FlashlightWidgetReceiver extends BroadcastReceiver {
	private static boolean isLightOn = false;
	static Camera camera;

	@Override
	public void onReceive(Context context, Intent intent) {
		RemoteViews views = new RemoteViews(context.getPackageName(),
				R.layout.new_app_widget);
		
		try {
			Main.cam.release();
		} catch (Exception e){
			
		}
		
		if (isLightOn) {
			views.setImageViewResource(R.id.imageView1,
					R.drawable.widget_button_off);
		} else {
			views.setImageViewResource(R.id.imageView1,
					R.drawable.widget_button_on);
		}

		AppWidgetManager appWidgetManager = AppWidgetManager
				.getInstance(context);
		appWidgetManager.updateAppWidget(new ComponentName(context,
				FlashlightWidget.class), views);

		if (isLightOn) {
			if (camera != null) {
				camera.stopPreview();
				camera.release();
				camera = null;
				isLightOn = false;
			}

		} else {
			// Open the default i.e. the first rear facing camera.
			
			camera = Camera.open();

			if (camera == null) {
			} else {
				// Set the torch flash mode
				Parameters param = camera.getParameters();
				param.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
				try {
					camera.setParameters(param);
					camera.startPreview();
					isLightOn = true;
				} catch (Exception e) {
				}
			}
		}
	}
}