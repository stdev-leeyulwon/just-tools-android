package net.shiftstudios.justaverage;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends Activity {
	
	public Item[] items = new Item[256];
	public double[] items2 = new double[256];
	public EditText uiInput;
	public ImageButton uiCont;
	public ListView uiList;
	public TextView uiAverage1;
	public TextView uiAverage2;
	public int counts = 0;
	public double average;
	public ItemAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		
		uiInput = (EditText) findViewById(R.id.editText1);
		uiCont = (ImageButton) findViewById(R.id.imageButton1);
		uiAverage1 = (TextView) findViewById(R.id.textView2);
		uiAverage2 = (TextView) findViewById(R.id.textView1);
		
		uiList = (ListView) findViewById(R.id.listView1);
		
		adapter = new ItemAdapter(this, 
                R.layout.listview_item_row, items);
		
		uiCont.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				try	{
					double d = Double.parseDouble(uiInput.getText().toString());
					uiInput.setText("");
					items[counts] = new Item(d);
					items2[counts] = d;
					counts++;
					double e = 0;
					for(int i = 0; i < counts; i++){
						e += items2[i];
					}
					average = e / (double)counts;
					
					uiAverage1.setText((long) average + "");
					uiAverage2.setText(getDot());
					initAdapter();
				} catch (Exception e) {
					
				}
			}});
		
		

		uiList.setAdapter(adapter);
		uiList.setOnItemClickListener(new OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {
				if(counts <= 1) {
				} else {
					for(int i = position; i < (counts - 1); i++){
						items[i] = items[i + 1];
						items2[i] = items2[i + 1];
					}
					items[counts - 1] = null;
					items2[counts - 1] = 0;
					counts--;
					
					double e = 0;
					for(int i = 0; i < counts; i++){
						e += items2[i];
					}
					
					average = e / (double)counts;
					
					uiAverage1.setText((long) average + "");
					uiAverage2.setText(getDot());
					initAdapter();
				}
			}});
	}
	
	public void initAdapter(){
		adapter = new ItemAdapter(this, 
                R.layout.listview_item_row, items);
		uiList.setAdapter(adapter);
	}
	
	public String getDot(){
		String s = Math.rint(Math.abs(average - (long)average) * 1000000) / 1000000 + "";
		return "." + s.replaceAll("0.", "");
	}
}
