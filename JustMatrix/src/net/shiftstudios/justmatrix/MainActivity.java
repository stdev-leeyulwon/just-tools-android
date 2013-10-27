package net.shiftstudios.justmatrix;

import net.shiftstudios.justmatrix.MainActivity;
import net.shiftstudios.justmatrix.R;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		
	    RelativeLayout layout = (RelativeLayout)findViewById(R.id.layout);
	    RelativeLayout.LayoutParams adsParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
	    adsParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
	    adsParams.addRule(RelativeLayout.CENTER_IN_PARENT);

		
		addListenerOnButton();
	}
	
	public void addListenerOnButton() {
		 
		ImageButton button1 = (ImageButton)findViewById(R.id.imageButton2);
		

 
		button1.setOnClickListener(new OnClickListener() {
 
			public void onClick(View arg0) {
 
				EditText a_11 = (EditText)findViewById(R.id.a11);
				EditText a_12 = (EditText)findViewById(R.id.a12);
				EditText a_21 = (EditText)findViewById(R.id.a21);
				EditText a_22 = (EditText)findViewById(R.id.a22);
				
				EditText b_11 = (EditText)findViewById(R.id.b11);
				EditText b_12 = (EditText)findViewById(R.id.b12);
				EditText b_21 = (EditText)findViewById(R.id.b21);
				EditText b_22 = (EditText)findViewById(R.id.b22);
				
				TextView c_11 = (TextView)findViewById(R.id.c11);
				TextView c_12 = (TextView)findViewById(R.id.c12);
				TextView c_21 = (TextView)findViewById(R.id.c21);
				TextView c_22 = (TextView)findViewById(R.id.c22);
				
				String aaa11 = a_11.getText().toString();
				String aaa12 = a_12.getText().toString();
				String aaa21 = a_21.getText().toString();
				String aaa22 = a_22.getText().toString();
				
				String bbb11 = b_11.getText().toString();
				String bbb12 = b_12.getText().toString();
				String bbb21 = b_21.getText().toString();
				String bbb22 = b_22.getText().toString();
				
				if (aaa11.length() != 0) {
					if (aaa12.length() != 0) {
						if (aaa21.length() != 0) {
							if (aaa22.length() != 0) {
								if (bbb11.length() != 0) {
									if (bbb12.length() != 0) {
										if (bbb21.length() != 0) {
											if (bbb22.length() != 0) {
												
												double aa11 = Double.parseDouble(a_11.getText().toString());
												double aa12 = Double.parseDouble(a_12.getText().toString());
												double aa21 = Double.parseDouble(a_21.getText().toString());
												double aa22 = Double.parseDouble(a_22.getText().toString());
												
												double bb11 = Double.parseDouble(b_11.getText().toString());
												double bb12 = Double.parseDouble(b_12.getText().toString());
												double bb21 = Double.parseDouble(b_21.getText().toString());
												double bb22 = Double.parseDouble(b_22.getText().toString());
												
												c_11.setText(((aa11 * bb11) + (aa12 * bb21)) + "");
												c_12.setText(((aa11 * bb12) + (aa12 * bb22)) + "");
												c_21.setText(((aa12 * bb11) + (aa22 * bb21)) + "");
												c_22.setText(((aa21 * bb12) + (aa22 * bb22)) + "");
												
											} else {
												/* b22 = 0 */
												Toast.makeText(MainActivity.this, R.string.b22, 3000).show();
											};
										} else {
											/* b21 = 0 */
											Toast.makeText(MainActivity.this, R.string.b21, 3000).show();
										};
									} else {
										/* b12 = 0 */
										Toast.makeText(MainActivity.this, R.string.b12, 3000).show();
									};
								} else {
									/* b11 = 0 */
									Toast.makeText(MainActivity.this, R.string.b11, 3000).show();
								};
							} else {
								/* a22 = 0 */
								Toast.makeText(MainActivity.this, R.string.a22, 3000).show();
							};
						} else {
							/* a21 = 0 */
							Toast.makeText(MainActivity.this, R.string.a21, 3000).show();
						};
					} else {
						/* a12 = 0 */
						Toast.makeText(MainActivity.this, R.string.a12, 3000).show();
					};
				} else {
					/* a11 = 0 */
					Toast.makeText(MainActivity.this, R.string.a11, 3000).show();
				};
			};


 
		});
		
		
 
	}
	


}
