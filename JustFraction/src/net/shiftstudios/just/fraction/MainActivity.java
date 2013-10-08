package net.shiftstudios.just.fraction;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;

import android.view.View;
import android.view.Window;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	public Context Context = this;

	public TextView a_, b_, c_, error;
	
	public EditText decimal, errorRange;
	
	public ImageView line;
	
	public int a, b;

	public TranslateAnimation moveLefttoRight;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		
		a_ = (TextView) findViewById(R.id.textView1);
		b_ = (TextView) findViewById(R.id.textView2);
		c_ = (TextView) findViewById(R.id.textView4);
		error = (TextView) findViewById(R.id.textView5);
		
		Typeface Helv = Typeface.createFromAsset(getAssets(), "fonts/HelveticaNeue-Light.otf");
		
		a_.setTypeface(Helv);
		b_.setTypeface(Helv);
		c_.setTypeface(Helv);
		error.setTypeface(Helv);
		
		decimal = (EditText) findViewById(R.id.editText1);
		errorRange = (EditText) findViewById(R.id.editText2);
		
		decimal.setTypeface(Helv);
		errorRange.setTypeface(Helv);
		
		line = (ImageView) findViewById(R.id.imageView2);
		
		ImageButton conf = (ImageButton) findViewById(R.id.imageButton1);
		conf.setOnClickListener(calculate);
		
		moveLefttoRight = new TranslateAnimation(0, 0, -30, 0);
        moveLefttoRight.setDuration(100);
        moveLefttoRight.setFillAfter(true);
        
        c_.setVisibility(View.INVISIBLE);
	}
	
	Button.OnClickListener calculate = new Button.OnClickListener()
	{
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (Double.parseDouble(errorRange.getText().toString()) <= 0.00000001) {
		Toast.makeText(Context, "Error range is smaller than 0.00000001. Just Fraction may cause lag.", 1000).show();
		}
        makeFraction();
	}};
	
	public void makeFraction() {
		double input = Double.parseDouble(decimal.getText().toString());
		if (decimal.length() > 0) {
		
		boolean positive = input > 0;
	    int numPart = 0; 
	    int num = 1; 
	    int den = 1;
	    double fraction = num / den;
	    double range = Double.parseDouble(errorRange.getText().toString());
	    while (Math.abs(fraction - input) > range)
	    {
	        if (fraction < input)
	        {
	            num++;
	        }
	        else
	        {
	            den++;
	            num = (int)Math.round(input * den);
	        }

	        fraction = num / (double)den;
	    }

	    if (num < 0) num = num * -1;

	    while (num >= den)
	    {
	        numPart += 1;
	        num -= den;
	    }

	    //return new Fraction(positive, numPart, num, den);
	    double errorVal = Math.abs(Math.abs(input) - (numPart + ((double)num/(double)den)));
	    
	    
		
		if (positive) {
			if (numPart != 0) {
				if (numPart == input) {
					a_.setText((int) input + "");
					b_.setText("");
					c_.setText("");
					a_.startAnimation(moveLefttoRight);
					a_.setVisibility(View.INVISIBLE);
					b_.setVisibility(View.INVISIBLE);
					c_.setVisibility(View.INVISIBLE);
					line.setVisibility(View.INVISIBLE);
					
					error.setText("Error = "+errorVal);
					error.startAnimation(moveLefttoRight);
				} else {
					a_.setText(num + "");
					b_.setText(den + "");
					c_.setText(numPart + "");
					c_.startAnimation(moveLefttoRight);
					b_.startAnimation(moveLefttoRight);
					line.startAnimation(moveLefttoRight);
					a_.startAnimation(moveLefttoRight);
					
					error.setText("Error = "+errorVal);
					error.startAnimation(moveLefttoRight);
				}
				} else {
					a_.setText(num + "");
					b_.setText(den + "");
					c_.setText("");
					c_.setVisibility(View.INVISIBLE);
					b_.startAnimation(moveLefttoRight);
					line.startAnimation(moveLefttoRight);
					a_.startAnimation(moveLefttoRight);
					
					error.setText("Error = "+errorVal);
					error.startAnimation(moveLefttoRight);
				}
		} else {
			if (numPart != 0) {
				if (numPart == -input) {
					a_.setText((int) input + "");
					b_.setText("");
					c_.setText("");
					a_.startAnimation(moveLefttoRight);
					a_.setVisibility(View.INVISIBLE);
					b_.setVisibility(View.INVISIBLE);
					c_.setVisibility(View.INVISIBLE);
					line.setVisibility(View.INVISIBLE);
					
					error.setText("Error = "+errorVal);
					error.startAnimation(moveLefttoRight);
				} else {
					a_.setText(num + "");
					b_.setText(den + "");
					c_.setText(-numPart + "");
					c_.startAnimation(moveLefttoRight);
					b_.startAnimation(moveLefttoRight);
					line.startAnimation(moveLefttoRight);
					a_.startAnimation(moveLefttoRight);
					
					error.setText("Error = "+errorVal);
					error.startAnimation(moveLefttoRight);
				}
				
				} else {
					a_.setText(num + "");
					b_.setText(den + "");
					c_.setText("-");
					c_.startAnimation(moveLefttoRight);
					b_.startAnimation(moveLefttoRight);
					line.startAnimation(moveLefttoRight);
					a_.startAnimation(moveLefttoRight);
					
					error.setText("Error = "+errorVal);
					error.startAnimation(moveLefttoRight);
				}
			
		}
		}
		
	}
}

