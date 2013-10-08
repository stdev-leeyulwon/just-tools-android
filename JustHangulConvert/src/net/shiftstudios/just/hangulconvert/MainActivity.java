package net.shiftstudios.just.hangulconvert;

import net.shiftstudios.just.hangulconvert.util.Hangul;
import android.os.Bundle;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.*;

public class MainActivity extends Activity {
	
	public EditText korText;
	public TextView result;
	public Button copy;
	
	public String resultString;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		
		korText = (EditText) findViewById(R.id.editText1);
		result = (TextView) findViewById(R.id.textView1);
		copy = (Button) findViewById(R.id.button1);
		resultString = "";
		
		korText.addTextChangedListener(new TextWatcher() {

			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub
				if(!TextUtils.isEmpty(korText.getText())) {
                    result.setText(Hangul.convertToEnglishforSingleChar(Hangul.convertToEnglish(korText.getText().toString())));
                    
                    resultString = Hangul.convertToEnglishforSingleChar(Hangul.convertToEnglish(korText.getText().toString()));
                } else {
                    result.setText("");
                    resultString = "";
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
		
		copy.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				try {
				ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE); 
				ClipData clip = ClipData.newPlainText("label", resultString);
				clipboard.setPrimaryClip(clip);
				} catch (Exception e) {
					android.text.ClipboardManager clipboard = (android.text.ClipboardManager)getSystemService(CLIPBOARD_SERVICE); 
				    clipboard.setText(resultString);
				}
				Toast.makeText(getBaseContext(), "Copied text to clipboard!", 1000).show();
			}
			
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	

}
