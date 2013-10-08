package net.shiftstudios.just.factorizer;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Typeface;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	public EditText in;
	public long input;
	public long[] l = new long[100];
    public static String S0 = "⁰";
    public static String S1 = "¹";
    public static String S2 = "²";
    public static String S3 = "³";
    public static String S4 = "⁴";
    public static String S5 = "⁵";
    public static String S6 = "⁶";
    public static String S7 = "⁷";
    public static String S8 = "⁸";
    public static String S9 = "⁹";
    
    public TextView out;
    
    public Button b;
   
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		
		Typeface Din = Typeface.createFromAsset(getAssets(), "fonts/DINNextLTPro-Regular.otf");
		
		in = (EditText) findViewById(R.id.editText1);
		out = (TextView) findViewById(R.id.textView1);
		b = (Button) findViewById(R.id.button1);
		
		in.setTypeface(Din);
		out.setTypeface(Din);
		
		in.addTextChangedListener(new TextWatcher() {
			
			

			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub
				if(in.getText().length() < 9) {
					b.setVisibility(View.GONE);
					for(int i=0; i<100; i++){
						l[i] = 0;
					}
					try{
						Factorize(Long.parseLong(in.getText().toString()));
						out.setText("=" + returnStr(l));
					} catch (Exception e) {
						out.setText("= 0");
						e.printStackTrace();
					}
				} else {
					b.setVisibility(View.VISIBLE);
					out.setText("Number is large, Please press \'Calaulate\'");
					b.setOnClickListener(new OnClickListener(){

						@Override
						public void onClick(View arg0) {
							for(int i=0; i<100; i++){
								l[i] = 0;
							}
							try{
								Factorize(Long.parseLong(in.getText().toString()));
								out.setText("=" + returnStr(l));
							} catch (Exception e) {
								out.setText("= 0");
								e.printStackTrace();
							}							
						}});
				}
				
				
			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub
				
				
				
			}
			
		});
		
	}

	public void Factorize(long in){
		int sq = (int) Math.sqrt(in)+5;
		int j = 2;
		int k = 0;
		
		for(int i=0; i<100; i++){
			for(j=2; j<=sq; j++){
				if(j==sq && in%j != 0) {
					if(k == 0) {
						l[i] = in;
						Log.d("TAG", l[i] + "");
						k++;
						break;
					} else {
						l[i] = 0;
						break;
					}
				} else {
					if(in%j == 0) {
						l[i] = j;
						in = in / j;
						Log.d("TAG", l[i] + "");
						sq = (int) Math.sqrt(in)+5;
						break;
					}
				}
			}
			
			
		}
	}
	
	public String returnStr(long[] in){
		String s = "";
		int b = 1;
		for(int i=0; i<99; i++){
			if(l[i] != 0){
				if (l[i] == l[i+1]) {
					b++;
				} else if (i >= 0) {
					if (b == 1) {
						s = s + " x " + l[i];
					} else {
						s = s + " x " + l[i] + convert(b);
					}
					b = 1;
				} else {
					
				}
			} else {
				break;
			}
			Log.e("TAG", s);
		}
		s = s + "y";
		s = s.replaceAll(" x 1y", "");
		s = s.replaceAll("y", "");
		return s.replaceFirst(" x ", " ");
	}
	
	public String convert(int in){
			String j = "";
			String k = "";
			if(in >= 60) {
				j = S6;
			} else if(in >= 50) {
				j = S5;
			} else if(in >= 40) {
				j = S4;
			} else if(in >= 30) {
				j = S3;
			} else if(in >= 20) {
				j = S2;
			} else if(in >= 10) {
				j = S1;
			} else if(in >= 0) {
				j = "";
			}
			
			switch(in%10){
			case 0:
				k = S0;
				break;
			case 1:
				k = S1;
				break;
			case 2:
				k = S2;
				break;
			case 3:
				k = S3;
				break;
			case 4:
				k = S4;
				break;
			case 5:
				k = S5;
				break;
			case 6:
				k = S6;
				break;
			case 7:
				k = S7;
				break;
			case 8:
				k = S8;
				break;
			case 9:
				k = S9;
				break;
			}
		return j+k;
	}

}
