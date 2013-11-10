package net.shiftstudios.just.rev3.flashlight;

import android.app.ActionBar;
import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.text.Html;
import android.view.MenuItem;
import android.widget.TextView;

public class AboutActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);
		
		ActionBar ab = this.getActionBar();
		ab.setTitle("");
		ab.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.action_bar_background));
		ab.setDisplayHomeAsUpEnabled(true);
		TextView tv = (TextView) findViewById(R.id.textView1);
		
		String version = "";
		try {
			PackageInfo i = getApplicationContext()
					.getPackageManager()
					.getPackageInfo(
							getApplicationContext().getPackageName(), 0);
			version = i.versionName;
		} catch (NameNotFoundException e) {
		}
		
		tv.setText(Html.fromHtml(
				"<br><b>Just Flashlight</b> " + version + "<br><br>" +
		"original idea by <b>Shift</b>(Soohyun, Park), <b>Agelast</b>(Jaehyun, Ok)<br><br>" +
		"lead developed by <b>Shift</b>(Soohyun, Park)<br><br>" + 
		"developed by <b>SS Innovation Studio / Division 1</b><br><br>" + 
		"asset designed by <b>Shift</b>(Soohyun, Park), <b>C0RT3X</b>(Sangwoon, Yu)<br><br>" +
		"ui designed by <b>SS Design Lab / Control UX Division"
				));
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	    // Respond to the action bar's Up/Home button
	    case android.R.id.home:
	        finish();
	        return true;
	    }
	    return super.onOptionsItemSelected(item);
	}

}
