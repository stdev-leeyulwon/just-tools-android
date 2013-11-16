package net.shiftstudios.just.rev3.counter;

import java.lang.reflect.Field;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import net.shiftstudios.just.rev3.counter.R;

public class CountActivity extends Activity {

	public long i = 0;
	public double currentTime1 = 0;
	public TextView in, in2;
	public EditText ui1;
	public TranslateAnimation a, b, c, d;
	public AlphaAnimation x, y;
	public AnimationSet a1, a2, b1, b2;
	public int dur = 167;
	public Button p, m;
	Animation clickAnim;
	private int w;
	private int h;
	public ListView mDrawerList;
	public CounterAdapter adapter;
	public Counter[] counter_data = new Counter[100];
	public int counters = 1;
	public int curCounter = 0;
	public boolean lastActivity;
	private ActionBarDrawerToggle mDrawerToggle;
	private DrawerLayout mDrawerLayout;
	public TextWatcher watcher;
	public String curCounterName = "Counter 1";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_count);

		
		
		ActionBar ab = this.getActionBar();
		ab.setTitle("");
		ab.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.action_bar_background));
		
		getOverflowMenu();

		Typeface Helv = Typeface.createFromAsset(getAssets(),
				"fonts/Roboto-Thin.ttf");
		p = (Button) findViewById(R.id.button1);
		m = (Button) findViewById(R.id.button2);

		watcher = new TextWatcher() {

			@Override
			public void afterTextChanged(Editable arg0) {

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				curCounterName = s.toString();
				onCounterNameUpdated();
			}
		};

		in = (TextView) findViewById(R.id.textView1);
		in2 = (TextView) findViewById(R.id.textView2);
		ui1 = (EditText) findViewById(R.id.editText1);
		in.setTypeface(Helv);
		in2.setTypeface(Helv);
		ui1.setTypeface(Helv);
		ui1.addTextChangedListener(watcher);
		// in.setTextSize(50);

		getSize();

		counter_data[0] = new Counter(i, "Counter 1");

		x = new AlphaAnimation(0.5f, 1);
		x.setDuration(dur);
		y = new AlphaAnimation(1, 0.5f);
		y.setDuration(dur);

		a = new TranslateAnimation(0, 0, -h, 0);
		a.setDuration(dur);
		a.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationEnd(Animation arg0) {
				in.setText("" + i);
				in2.setText("" + i);
			}

			@Override
			public void onAnimationRepeat(Animation arg0) {

			}

			@Override
			public void onAnimationStart(Animation arg0) {

			}
		});

		b = new TranslateAnimation(0, 0, 0, h);
		b.setDuration(dur);
		b.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationEnd(Animation arg0) {
				in.setText("" + i);
				in2.setText("" + i);
			}

			@Override
			public void onAnimationRepeat(Animation arg0) {

			}

			@Override
			public void onAnimationStart(Animation arg0) {

			}
		});

		c = new TranslateAnimation(0, 0, h, 0);
		c.setDuration(dur);
		c.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationEnd(Animation arg0) {
				in.setText("" + i);
				in2.setText("" + i);
			}

			@Override
			public void onAnimationRepeat(Animation arg0) {

			}

			@Override
			public void onAnimationStart(Animation arg0) {

			}
		});

		d = new TranslateAnimation(0, 0, 0, -h);
		d.setDuration(dur);
		d.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationEnd(Animation arg0) {
				in.setText("" + i);
				in2.setText("" + i);
			}

			@Override
			public void onAnimationRepeat(Animation arg0) {

			}

			@Override
			public void onAnimationStart(Animation arg0) {

			}
		});

		a1 = new AnimationSet(false);
		a1.addAnimation(a);
		a1.addAnimation(x);

		a2 = new AnimationSet(false);
		a2.addAnimation(b);
		a2.addAnimation(y);

		b1 = new AnimationSet(false);
		b1.addAnimation(c);
		b1.addAnimation(y);

		b2 = new AnimationSet(false);
		b2.addAnimation(d);
		b2.addAnimation(x);

		p.setOnTouchListener(new RelativeLayout.OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (MotionEvent.ACTION_DOWN == event.getAction()
						&& event.getX() > w / 6) {

					i++;

					if (i == Long.MAX_VALUE) {
						in.setText("MAX");
					} else {
						in.setText("" + i);
						in2.setText("" + (i - 1));
					}
					;
					in.startAnimation(a1);
					in2.startAnimation(a2);
					onCounterUpdate();
					lastActivity = true;
				}
				return true;
			}
		});

		m.setOnTouchListener(new RelativeLayout.OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (MotionEvent.ACTION_DOWN == event.getAction()
						&& event.getX() > w / 6) {

					i--;

					if (i == Long.MAX_VALUE) {
						in.setText("MAX");
					} else {
						in.setText("" + i);
						in2.setText("" + (i + 1));
					}
					;
					in.startAnimation(b1);
					in2.startAnimation(b2);
					onCounterUpdate();
					lastActivity = false;
				}
				return true;
			}
		});

		adapter = new CounterAdapter(this, R.layout.drawer_item_row,
				counter_data);

		View header = (View) getLayoutInflater().inflate(
				R.layout.drawer_header, null);

		mDrawerList = (ListView) findViewById(R.id.left_drawer);
		mDrawerList.addHeaderView(header);

		// Set the adapter for the list view
		mDrawerList.setAdapter(adapter);
		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

		mDrawerToggle = new ActionBarDrawerToggle(this, /* host Activity */
		mDrawerLayout, /* DrawerLayout object */
		R.drawable.ic_drawer /* nav drawer image to replace 'Up' caret */,
				R.string.app_name, R.string.app_name) {
			public void onDrawerClosed(View view) {
				invalidateOptionsMenu(); // creates call to
											// onPrepareOptionsMenu()
			}

			public void onDrawerOpened(View drawerView) {
				invalidateOptionsMenu();
				in.setText("" + i);
				in2.setText("" + i);
				onCounterUpdate();
				initDrawer();
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);

		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);
		
		onCounterNameUpdated();

	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		mDrawerToggle.onConfigurationChanged(newConfig);
	}


	public void getSize() {
		Display display = getWindowManager().getDefaultDisplay();
		w = display.getWidth(); // deprecated
		h = display.getHeight(); // deprecated
	}

	public void onCounterUpdate() {
		if (curCounterName.equals("Counter " + (curCounter + 1))) {
			counter_data[curCounter] = new Counter(i, "Counter "
					+ (curCounter + 1));
		} else {
			counter_data[curCounter] = new Counter(i, curCounterName);
		}
	}

	public void initDrawer() {
		Counter[] counterData = new Counter[counters];

		for (int i = 0; i < counters; i++) {
			counterData[i] = counter_data[i];
		}

		adapter = new CounterAdapter(this, R.layout.drawer_item_row,
				counterData);
		mDrawerList.setAdapter(adapter);
	}

	private class DrawerItemClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView parent, View view, int position,
				long id) {
			if (position == 0) {
				if (counters < 100) {
					counters++;
					counter_data[counters - 1] = new Counter(0, "Counter "
							+ counters);
				} else {
					Toast.makeText(getApplicationContext(),
							"Total amount exceeded!", Toast.LENGTH_SHORT)
							.show();
				}
			} else {
				curCounter = position - 1;
				i = counter_data[position - 1].counts;
				in.setText("" + i);
				in2.setText("" + i);
				ui1.removeTextChangedListener(watcher);
				ui1.setText(counter_data[position - 1].title);
				curCounterName = counter_data[position - 1].title;
				ui1.addTextChangedListener(watcher);
				onCounterNameUpdated();
			}
			
			initDrawer();
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		menu.add(0, 0, 0, getResources().getString(R.string.reset));
		menu.add(0, 1, 1, getResources().getString(R.string.delete));
		menu.add(0, 2, 2, getResources().getString(R.string.contact));
		menu.add(0, 3, 3, getResources().getString(R.string.about));
		
		menu.getItem(1).setEnabled(counters <= 1? false: true);
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onPrepareOptionsMenu(Menu menu){
		super.onPrepareOptionsMenu(menu);
		menu.clear();
		
		menu.add(0, 0, 0, getResources().getString(R.string.reset));
		menu.add(0, 1, 1, getResources().getString(R.string.delete));
		menu.add(0, 2, 2, getResources().getString(R.string.contact));
		menu.add(0, 3, 3, getResources().getString(R.string.about));
		
		menu.getItem(1).setEnabled(counters <= 1? false: true);
		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		
		if (item != null) {
			switch (item.getItemId()) {
			case 0:
				i = 0;
				in.setText("0");
				in2.setText("0");
				onCounterUpdate();
				break;
			case 1:
				for (int k = curCounter; k < counters - 1; k++) {
					counter_data[k] = counter_data[k + 1];
				}
				
				counters--;
				curCounter--;
				
				if (curCounter <= 0) {
					curCounter = 1;
				}
				
				long l = counter_data[curCounter].counts;
				in.setText("" + l);
				in2.setText("" + l);
				ui1.removeTextChangedListener(watcher);
				ui1.setText(counter_data[curCounter].title);
				curCounterName = counter_data[curCounter].title;
				ui1.addTextChangedListener(watcher);
				onCounterNameUpdated();
				initDrawer();
				break;
			case 2:
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
			case 3:
				Intent inte = new Intent(this, AboutActivity.class);
				startActivity(inte);
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
	
	public void onCounterNameUpdated(){
		getActionBar().setTitle(Html.fromHtml("<small> - " + curCounterName));
		if (curCounterName.length() == 0) {
			ui1.setHint("Untitled");
		}
	}
}
