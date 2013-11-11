package net.shiftstudios.just.rev3.factorizer;

import java.lang.reflect.Field;
import net.shiftstudios.just.rev3.factorizer.R;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity {

	public EditText in;
	public int input;
	public long[] l = new long[100];

	public TextView out;

	public ProgressBar factorizeProgress;
	public TextView factorizing;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getOverflowMenu();

		ActionBar ab = this.getActionBar();
		ab.setTitle("");
		ab.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.action_bar_background));

		Typeface Din = Typeface.createFromAsset(getAssets(),
				"fonts/Roboto-Thin.ttf");
		Typeface DinItalic = Typeface.createFromAsset(getAssets(),
				"fonts/Roboto-ThinItalic.ttf");

		factorizing = (TextView) findViewById(R.id.textView2);
		factorizing.setVisibility(View.INVISIBLE);
		factorizeProgress = (ProgressBar) findViewById(R.id.progressBar1);
		factorizeProgress.setVisibility(View.INVISIBLE);
		in = (EditText) findViewById(R.id.editText1);
		out = (TextView) findViewById(R.id.textView1);

		in.setTypeface(Din);
		out.setTypeface(Din);
		factorizing.setTypeface(DinItalic);

		in.addTextChangedListener(new TextWatcher() {

			@Override
			public void afterTextChanged(Editable arg0) {
				if (in.getText().length() < 9) {
					factorizing.setVisibility(View.INVISIBLE);
					factorizeProgress.setVisibility(View.INVISIBLE);
					for (int i = 0; i < 100; i++) {
						l[i] = 0;
					}
					try {
						Factorize(Long.parseLong(in.getText().toString()));
						out.setText(Html.fromHtml("=" + returnStr(l)));
					} catch (Exception e) {
						out.setText("= 0");
						e.printStackTrace();
					}
				} else {
					factorizeProgress.setVisibility(View.VISIBLE);
					factorizing.setVisibility(View.VISIBLE);

					out.setText("...");
					Thread t = new Thread(new Runnable() {
						@Override
						public void run() {
							for (int i = 0; i < 100; i++) {
								l[i] = 0;
							}
							try {
								Factorize(Long.parseLong(in.getText()
										.toString()));
								runOnUiThread(new Runnable() {

									@Override
									public void run() {
										out.setText(Html.fromHtml("="
												+ returnStr(l)));
										factorizing
												.setVisibility(View.INVISIBLE);
										factorizeProgress
												.setVisibility(View.INVISIBLE);
									}
								});

							} catch (Exception e) {
								runOnUiThread(new Runnable() {

									@Override
									public void run() {
										out.setText("= 0");
										factorizing
												.setVisibility(View.INVISIBLE);
										factorizeProgress
												.setVisibility(View.INVISIBLE);
									}
								});

								e.printStackTrace();
							}
						}
					});

					t.start();
				}

			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {

			}

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {

			}

		});

	}

	public void Factorize(final long p) {
		long m = p;
		long sq = (long) Math.sqrt(m) + 5;
		long j = 2;
		long k = 0;

		for (int i = 0; i < 100; i++) {
			for (j = 2; j <= sq; j++) {
				if (j == sq && m % j != 0) {
					if (k == 0) {
						l[i] = m;
						Log.d("TAG", l[i] + "");
						k++;
						break;
					} else {
						l[i] = 0;
						break;
					}
				} else {
					if (m % j == 0) {
						l[i] = j;
						m = m / j;
						Log.d("TAG", l[i] + "");
						sq = (long) Math.sqrt(m) + 5;
						break;
					}
				}
			}

		}
	}

	public String returnStr(long[] in) {
		String s = "";
		long b = 1;
		for (int i = 0; i < 9999; i++) {
			if (l[i] != 0) {
				if (l[i] == l[i + 1]) {
					b++;
				} else if (i >= 0) {
					if (b == 1) {
						s = s + " x " + l[i];
					} else {
						s = s + " x " + l[i] + "<sup>" + b + "</sup>";
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
		return s.replaceFirst(" x ", " ").replaceAll("x", "×");
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		menu.add(0, 0, 0, getResources().getString(R.string.contact));
		menu.add(0, 1, 1, getResources().getString(R.string.about));
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item != null) {
			switch (item.getItemId()) {
			case 0:
				String version = "";
				try {
					PackageInfo i = getApplicationContext()
							.getPackageManager()
							.getPackageInfo(
									getApplicationContext().getPackageName(), 0);
					version = i.versionName;
				} catch (NameNotFoundException e) {
				}

				Intent i = new Intent(Intent.ACTION_SEND);
				i.setType("message/rfc822"); // use from live device
				i.putExtra(Intent.EXTRA_EMAIL,
						new String[] { "arom303@gmail.com" });
				i.putExtra(Intent.EXTRA_SUBJECT,
						"Just Flashlight " + System.currentTimeMillis());
				i.putExtra(Intent.EXTRA_TEXT, "App Version: " + version
						+ "\n\n\n");
				startActivity(Intent.createChooser(i,
						"Select email application"));
				break;
			case 1:
				Intent in = new Intent(this, AboutActivity.class);
				startActivity(in);
				break;
			default:
				break;
			}
			return true;
		} else {
			return false;
		}
	}

	private void getOverflowMenu() {

		try {
			ViewConfiguration config = ViewConfiguration.get(this);
			Field menuKeyField = ViewConfiguration.class
					.getDeclaredField("sHasPermanentMenuKey");
			if (menuKeyField != null) {
				menuKeyField.setAccessible(true);
				menuKeyField.setBoolean(config, false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
