package net.shiftstudios.just.metronome;

import java.util.Timer;
import java.util.TimerTask;

import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.animation.TranslateAnimation;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends Activity {

	private static Bitmap imageOriginal, imageScaled;
	private static Matrix matrix;
	private ImageView dialer;
	private int dialerHeight, dialerWidth;
	
	public double BPM = 120;
	public TextView BPM1, BPM2;
	public int delay = 1000;
	public Timer timer;
	public TimerTask tone;
	
	public short curBeat = -1;
	public short maxBeat = 4;
	
	public SoundPool sound_pool;
	public int sound_low;
	public int sound_high;
	
	public SeekBar curBeatOut;
	public TextView curBeatNum;
	
	public TranslateAnimation moveLefttoRight;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	    setContentView(R.layout.activity_main);
	    setVolumeControlStream(AudioManager.STREAM_MUSIC);
	    
	    moveLefttoRight = new TranslateAnimation(0, 0, -33, 0);
        moveLefttoRight.setDuration(100);
        moveLefttoRight.setFillAfter(true);
	    
	    BPM1 = (TextView) findViewById(R.id.textView1);
	    BPM2 = (TextView) findViewById(R.id.textView2);
	    curBeatOut = (SeekBar) findViewById(R.id.seekBar1);
	    curBeatNum = (TextView) findViewById(R.id.textView5);
	    
	    curBeatOut.setMax(maxBeat - 1);
	    
	    sound_pool = new SoundPool( 5, AudioManager.STREAM_MUSIC, 0 );
	    sound_low = sound_pool.load( getBaseContext(), R.raw.low01, 1 );
	    sound_high = sound_pool.load( getBaseContext(), R.raw.high01, 1 );
	    
	    // load the image only once
	    if (imageOriginal == null) {
	        imageOriginal = BitmapFactory.decodeResource(getResources(), R.drawable.rotater);
	    }
	    // initialize the matrix only once
	    if (matrix == null) {
	        matrix = new Matrix();
	    } else {
	        // not needed, you can also post the matrix immediately to restore the old state
	        matrix.reset();
	    }
	    dialer = (ImageView) findViewById(R.id.imageView1);
	    dialer.setOnTouchListener(new MyOnTouchListener());
	    dialer.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
	    	@Override
            public void onGlobalLayout() {
                // method called more than once, but the values only need to be initialized one time
                if (dialerHeight == 0 || dialerWidth == 0) {
                    dialerHeight = dialer.getHeight();
                    dialerWidth = dialer.getWidth();
                    // resize
                    Matrix resize = new Matrix();
                    resize.postScale((float)Math.min(dialerWidth, dialerHeight) / (float)imageOriginal.getWidth(), (float)Math.min(dialerWidth, dialerHeight) / (float)imageOriginal.getHeight());
                    imageScaled = Bitmap.createBitmap(imageOriginal, 0, 0, imageOriginal.getWidth(), imageOriginal.getHeight(), resize, false);
                    // translate to the image view's center
                    float translateX = dialerWidth / 2 - imageScaled.getWidth() / 2;
                    float translateY = dialerHeight / 2 - imageScaled.getHeight() / 2;
                    matrix.postTranslate(translateX, translateY);
                    dialer.setImageBitmap(imageScaled);
                    dialer.setImageMatrix(matrix);
                }
            }
	    });
	    
	    
	    
	    timer = new Timer("MetronomeTimer", true);
	    tone = new TimerTask(){
	         @Override
	         public void run(){
	             playSound();
	         }
	    };
	    timer.scheduleAtFixedRate(tone, delay, delay); //120 BPM. Executes every 500 ms.
	}
	
	public void playSound(){
		curBeat++;
		curBeat = (short) (curBeat % maxBeat);
		if (curBeat == 0) {
			sound_pool.play( sound_high, 1f, 1f, 0, 0, 1f );
		} else {
			sound_pool.play( sound_low, 1f, 1f, 0, 0, 1f );
		}
		runOnUiThread(new Runnable(){

			@Override
			public void run() {
				curBeatOut.setProgress(curBeat);
				curBeatNum.setText(curBeat+1 + " / " + maxBeat);
				curBeatOut.startAnimation(moveLefttoRight);
			}});
		
	}
	
	/**
	 * Simple implementation of an {@link OnTouchListener} for registering the dialer's touch events.
	 */
	private class MyOnTouchListener implements OnTouchListener {
	    private double startAngle;
	    @Override
	    public boolean onTouch(View v, MotionEvent event) {
	    	
	        switch (event.getAction()) {
	            case MotionEvent.ACTION_DOWN:
	                startAngle = getAngle(event.getX(), event.getY());
	                tone.cancel();
	                break;
	            case MotionEvent.ACTION_MOVE:
	                double currentAngle = getAngle(event.getX(), event.getY());
	                if (startAngle - currentAngle >= 0 && startAngle - currentAngle <= 300) {
	                	rotateDialer((float) (startAngle - currentAngle));
	                	Log.d("1", startAngle - currentAngle + "");
	                } else if (startAngle - currentAngle < 0 && startAngle - currentAngle >= -300) {
	                	rotateDialer((float) (startAngle - currentAngle));
	                	Log.d("2", startAngle - currentAngle + "");
	                } else if (startAngle - currentAngle > 300) {
	                	rotateDialer((float) (startAngle - currentAngle - 360));
	                	Log.d("3", startAngle - currentAngle - 360 + "");
	                } else if (startAngle - currentAngle < -300) {
	                	rotateDialer((float) (startAngle - currentAngle + 360));
	                	Log.d("4", startAngle - currentAngle + 360 + "");
	                }
	                startAngle = currentAngle;
	                break;
	            case MotionEvent.ACTION_UP:
	            	Log.d(delay + "", delay + "");
	            	tone = new TimerTask(){
	       	         @Override
	       	         public void run(){
	       	             playSound();
	       	         }
	            	};
	            	timer.scheduleAtFixedRate(tone, delay, delay);
	                break;
	        }
	        return true;
	    }
	}
	
	/**
	 * @return The angle of the unit circle with the image view's center
	 */
	private double getAngle(double xTouch, double yTouch) {
	    double x = xTouch - (dialerWidth / 2d);
	    double y = dialerHeight - yTouch - (dialerHeight / 2d);
	    switch (getQuadrant(x, y)) {
	        case 1:
	            return Math.asin(y / Math.hypot(x, y)) * 180 / Math.PI;
	        case 2:
	            return 180 - Math.asin(y / Math.hypot(x, y)) * 180 / Math.PI;
	        case 3:
	            return 180 + (-1 * Math.asin(y / Math.hypot(x, y)) * 180 / Math.PI);
	        case 4:
	            return 360 + Math.asin(y / Math.hypot(x, y)) * 180 / Math.PI;
	        default:
	            return 0;
	    }
	}
	/**
	 * @return The selected quadrant.
	 */
	private static int getQuadrant(double x, double y) {
	    if (x >= 0) {
	        return y >= 0 ? 1 : 4;
	    } else {
	        return y >= 0 ? 2 : 3;
	    }
	}
	
	/**
     * Rotate the dialer.
     *
     * @param degrees The degrees, the dialer should get rotated.
     */
    private void rotateDialer(float degrees) {
    	BPM += degrees / 10;
    	if (BPM < 40) {
    		BPM = 40;
    	} else if (BPM > 360) {
    		BPM = 360;
    	}
    	delay = (int) (((1/BPM) * 120) * 1000);
    	
	    
    	BPM1.setText((int)BPM + "");
    	BPM2.setText("." + (int)((BPM - (int)BPM) * 10));
        matrix.postRotate(degrees, dialerWidth / 2, dialerHeight / 2);
        dialer.setImageMatrix(matrix);
    }

}
