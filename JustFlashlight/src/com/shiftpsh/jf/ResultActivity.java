package com.shiftpsh.jf;

import android.hardware.Camera;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class ResultActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Camera cam;
		cam = Camera.open();
		cam.stopPreview();
        cam.release();
		finish();
	}

}
