package net.shiftstudios.just.text;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;

import net.shiftstudios.just.text.R;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	public EditText			uiInput;
	public TextView			uiOutLength, uiOutBytes;
	public TextWatcher		watcher;
	public Button			uiSaveAs;
	public DecimalFormat	df;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		uiInput			= (EditText)	findViewById(R.id.editText1);
		uiOutLength		= (TextView)	findViewById(R.id.textView1);
		uiOutBytes		= (TextView)	findViewById(R.id.textView3);
		uiSaveAs		= (Button)		findViewById(R.id.button1);
		
		df = new DecimalFormat("#,##0");
		
		watcher = new TextWatcher(){

			@Override
			public void afterTextChanged(Editable arg0) {
				update();
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				update();
			}};
			
		uiInput.addTextChangedListener(watcher);
		
		final Dialog dialog = new Dialog(this);
			dialog.setContentView(R.layout.dialog_save);
			dialog.setTitle("Save As...");
			Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
			
			TextView rootLoc = (TextView) dialog.findViewById(R.id.textView2);
			rootLoc.setText(Environment.getExternalStorageDirectory() + "/just-text/");
			
			// if button is clicked, close the custom dialog
			
			dialogButton.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					try {
						
						
						File directory = new File(Environment.getExternalStorageDirectory()+File.separator+"just-text");
						directory.mkdirs();
						
						EditText location = (EditText) dialog.findViewById(R.id.editText1);
						
						String loc = Environment.getExternalStorageDirectory() + File.separator + "just-text" + File.separator + location.getText() + ".txt";
						File myFile = new File(loc);
						myFile.createNewFile();
						FileOutputStream fOut = new FileOutputStream(myFile);
						OutputStreamWriter myOutWriter = 
												new OutputStreamWriter(fOut);
						myOutWriter.append(uiInput.getText());
						myOutWriter.close();
						fOut.close();
						Toast.makeText(getBaseContext(),
								"Saved file as " + loc,
								Toast.LENGTH_SHORT).show();
					} catch (Exception e) {
						Toast.makeText(getBaseContext(), e.getMessage(),
								Toast.LENGTH_SHORT).show();
					}
					dialog.dismiss();
				}
			});
			
			uiSaveAs.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					dialog.show();
				}
				
			});
	}
	
	public void update(){
		uiOutLength.setText(df.format(uiInput.length()));
		
		byte[] bytetext;
		
		try {
			  bytetext = uiInput.getText().toString().getBytes("UTF-8");
			  double bytearr = bytetext.length;
			  int bytearra = bytetext.length;
			  
			  if ( bytetext.length > 1000) {
				  
				  uiOutBytes.setText(Math.floor(( bytearr / 1024 ) * 1000) / 1000+"kB");
				  
				  if ( bytetext.length > 1024000) {
					  if ( bytetext.length > 1048576000) {
						  uiOutBytes.setText(( Math.floor(( bytearr / 1073741824 ) * 1000) / 1000 )+"GB");
					  } else {
						  uiOutBytes.setText(( Math.floor(( bytearr / 1048576 ) * 1000) / 1000 )+"MB");
					  };
				  } else {
				  
				  };
			  } else {
				  uiOutBytes.setText(bytearra + "B");
			  };
			  
		  } catch (UnsupportedEncodingException e) {
		  	  // TODO Auto-generated catch block
		  	  e.printStackTrace();
		  }
	}
}
