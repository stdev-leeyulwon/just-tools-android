package net.shiftstudios.just.scoreboard;

import net.shiftstudios.just.scoreboard.R;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Point;
import android.graphics.Typeface;

import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.view.animation.Animation.AnimationListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

@SuppressLint("NewApi")
public class MainActivity extends Activity {
	
	public long i, j = 0;
	public double currentTime1 = 0;
	Animation clickAnim;

	public TextView in11, in12, in21, in22;

	private int w;
	private int h;

	public TranslateAnimation a1, b1, c1, d1, a2, b2, c2, d2;
	public AlphaAnimation x1, y1, x2, y2;
	public AnimationSet a11, a12, b11, b12, a21, a22, b21, b22;

	public int dur = 167;

	public Button p1, m1, p2, m2;


		
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			this.requestWindowFeature(Window.FEATURE_NO_TITLE);
			setContentView(R.layout.activity_main);

	        Typeface Helv = Typeface.createFromAsset(getAssets(), "fonts/HelveticaNeue-Black.otf");
	        p1 = (Button) findViewById(R.id.button11);
	        m1 = (Button) findViewById(R.id.button12);	
	        p2 = (Button) findViewById(R.id.button21);
	        m2 = (Button) findViewById(R.id.button22);	
			
			in11 = (TextView) findViewById(R.id.textView11);
			in12 = (TextView) findViewById(R.id.textView12);
			in11.setTypeface(Helv);
			in12.setTypeface(Helv);
			
			in21 = (TextView) findViewById(R.id.textView21);
			in22 = (TextView) findViewById(R.id.textView22);
			in21.setTypeface(Helv);
			in22.setTypeface(Helv);
			//in.setTextSize(50);
			
			Display display = getWindowManager().getDefaultDisplay(); 
			w = display.getWidth();  // deprecated
			h = display.getHeight();  // deprecated
			
			x1 = new AlphaAnimation(0.5f, 1);
			x1.setDuration(dur);
			y1 = new AlphaAnimation(1, 0.5f);
			y1.setDuration(dur);
			x2 = new AlphaAnimation(0.5f, 1);
			x2.setDuration(dur);
			y2 = new AlphaAnimation(1, 0.5f);
			y2.setDuration(dur);
			
			a1 = new TranslateAnimation(0, 0, -h, 0);
			a1.setDuration(dur);
			a1.setAnimationListener(new AnimationListener(){

				@Override
				public void onAnimationEnd(Animation arg0) {
					in11.setText(""+i);
					in12.setText(""+i);
				}

				@Override
				public void onAnimationRepeat(Animation arg0) {
					
				}

				@Override
				public void onAnimationStart(Animation arg0) {
					
				}});
			
			b1 = new TranslateAnimation(0, 0, 0, h);
			b1.setDuration(dur);
			b1.setAnimationListener(new AnimationListener(){

				@Override
				public void onAnimationEnd(Animation arg0) {
					in11.setText(""+i);
					in12.setText(""+i);
				}

				@Override
				public void onAnimationRepeat(Animation arg0) {
					
				}

				@Override
				public void onAnimationStart(Animation arg0) {
					
				}});
			
			c1 = new TranslateAnimation(0, 0, h, 0);
			c1.setDuration(dur);
			c1.setAnimationListener(new AnimationListener(){

				@Override
				public void onAnimationEnd(Animation arg0) {
					in11.setText(""+i);
					in12.setText(""+i);
				}

				@Override
				public void onAnimationRepeat(Animation arg0) {
					
				}

				@Override
				public void onAnimationStart(Animation arg0) {
					
				}});
			
			d1 = new TranslateAnimation(0, 0, 0, -h);
			d1.setDuration(dur);
			d1.setAnimationListener(new AnimationListener(){

				@Override
				public void onAnimationEnd(Animation arg0) {
					in11.setText(""+i);
					in12.setText(""+i);
				}

				@Override
				public void onAnimationRepeat(Animation arg0) {
					
				}

				@Override
				public void onAnimationStart(Animation arg0) {
					
				}});
			
			a2 = new TranslateAnimation(0, 0, -h, 0);
			a2.setDuration(dur);
			a2.setAnimationListener(new AnimationListener(){

				@Override
				public void onAnimationEnd(Animation arg0) {
					in21.setText(""+j);
					in22.setText(""+j);
				}

				@Override
				public void onAnimationRepeat(Animation arg0) {
					
				}

				@Override
				public void onAnimationStart(Animation arg0) {
					
				}});
			
			b2 = new TranslateAnimation(0, 0, 0, h);
			b2.setDuration(dur);
			b2.setAnimationListener(new AnimationListener(){

				@Override
				public void onAnimationEnd(Animation arg0) {
					in21.setText(""+j);
					in22.setText(""+j);
				}

				@Override
				public void onAnimationRepeat(Animation arg0) {
					
				}

				@Override
				public void onAnimationStart(Animation arg0) {
					
				}});
			
			c2 = new TranslateAnimation(0, 0, h, 0);
			c2.setDuration(dur);
			c2.setAnimationListener(new AnimationListener(){

				@Override
				public void onAnimationEnd(Animation arg0) {
					in21.setText(""+j);
					in22.setText(""+j);
				}

				@Override
				public void onAnimationRepeat(Animation arg0) {
					
				}

				@Override
				public void onAnimationStart(Animation arg0) {
					
				}});
			
			d2 = new TranslateAnimation(0, 0, 0, -h);
			d2.setDuration(dur);
			d2.setAnimationListener(new AnimationListener(){

				@Override
				public void onAnimationEnd(Animation arg0) {
					in21.setText(""+j);
					in22.setText(""+j);
				}

				@Override
				public void onAnimationRepeat(Animation arg0) {
					
				}

				@Override
				public void onAnimationStart(Animation arg0) {
					
				}});
			
			a11 = new AnimationSet(false);
			a11.addAnimation(a1);
			a11.addAnimation(x1);
			
			a12 = new AnimationSet(false);
			a12.addAnimation(b1);
			a12.addAnimation(y1);
			
			b11 = new AnimationSet(false);
			b11.addAnimation(c1);
			b11.addAnimation(y1);
			
			b12 = new AnimationSet(false);
			b12.addAnimation(d1);
			b12.addAnimation(x1);
			
			a21 = new AnimationSet(false);
			a21.addAnimation(a2);
			a21.addAnimation(x2);
			
			a22 = new AnimationSet(false);
			a22.addAnimation(b2);
			a22.addAnimation(y2);
			
			b21 = new AnimationSet(false);
			b21.addAnimation(c2);
			b21.addAnimation(y2);
			
			b22 = new AnimationSet(false);
			b22.addAnimation(d2);
			b22.addAnimation(x2);


			p1.setOnTouchListener(new RelativeLayout.OnTouchListener(){
				public boolean onTouch(View v, MotionEvent event) {
					if (MotionEvent.ACTION_DOWN == event.getAction()) {
		    	
			    	i++;
			    	
			    	if (i == Long.MAX_VALUE){
			    		in11.setText("MAX");
			    	} else {
			    		in11.setText(""+i);
			    		in12.setText(""+(i-1));
			    	};	
			    	in11.startAnimation(a11);
			    	in12.startAnimation(a12);
					}
					return true;
				}
				});
			
			m1.setOnTouchListener(new RelativeLayout.OnTouchListener(){
				public boolean onTouch(View v, MotionEvent event) {
					if (MotionEvent.ACTION_DOWN == event.getAction()) {
		    	
			    	i--;
			    	
			    	if (i == Long.MAX_VALUE){
			    		in11.setText("MAX");
			    	} else {
			    		in11.setText(""+i);
			    		in12.setText(""+(i+1));
			    	};	
			    	in11.startAnimation(b11);
			    	in12.startAnimation(b12);
					}
					return true;
				}
				});
			
			p2.setOnTouchListener(new RelativeLayout.OnTouchListener(){
				public boolean onTouch(View v, MotionEvent event) {
					if (MotionEvent.ACTION_DOWN == event.getAction()) {
		    	
			    	j++;
			    	
			    	if (j == Long.MAX_VALUE){
			    		in21.setText("MAX");
			    	} else {
			    		in21.setText(""+j);
			    		in22.setText(""+(j-1));
			    	};	
			    	in21.startAnimation(a21);
			    	in22.startAnimation(a22);
					}
					return true;
				}
				});
			
			m2.setOnTouchListener(new RelativeLayout.OnTouchListener(){
				public boolean onTouch(View v, MotionEvent event) {
					if (MotionEvent.ACTION_DOWN == event.getAction()) {
		    	
			    	j--;
			    	
			    	if (j == Long.MAX_VALUE){
			    		in21.setText("MAX");
			    	} else {
			    		in21.setText(""+j);
			    		in22.setText(""+(j+1));
			    	};	
			    	in21.startAnimation(b21);
			    	in22.startAnimation(b22);
					}
					return true;
				}
				});
		}
		
		
		@Override
		public boolean onOptionsItemSelected(MenuItem item) {		
			
		    // Handle item selection
		    switch (item.getItemId()) {

		        case R.id.menu_clear:
		            i = 0;
		            j = 0;
		            in11.setText("0");
		            in12.setText("0");
		            in21.setText("0");
		            in22.setText("0");
		    }
			return false;
		}
		
		
		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			// Inflate the menu; this adds items to the action bar if it is present.
			getMenuInflater().inflate(R.menu.activity_main, menu);
			return true;
		}

}
