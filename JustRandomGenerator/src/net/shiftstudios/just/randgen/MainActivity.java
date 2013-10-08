package net.shiftstudios.just.randgen;

import java.util.Random;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	
	public EditText uiSource, uiLength, uiOutput;
	public Button uiPress;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		
		uiSource	= (EditText)	findViewById(R.id.editText1);
		uiLength	= (EditText)	findViewById(R.id.editText2);
		uiOutput	= (EditText)	findViewById(R.id.editText3);
		uiPress		= (Button)		findViewById(R.id.button1);
		
		uiPress.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				String source = uiSource.getText().toString();
				int length = Integer.parseInt(uiLength.getText().toString());
				
				uiOutput.setText(randGen(source, length));
			}});
	}
	
	public String randGen(String source, int length){
		String s = "";
		
		if (source.length() == 0) {
			return "";
		}
		
		if (length == 0) {
			return "";
		}
		
		Random r = new Random();
		
		for(int i = 0; i < length; i++){
			int j = r.nextInt(source.length());
			s = s + source.charAt(j);
		}
		
		return s;
	}

}
