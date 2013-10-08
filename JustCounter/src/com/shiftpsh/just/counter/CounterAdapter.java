package com.shiftpsh.just.counter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CounterAdapter extends ArrayAdapter<Counter>{

    Context context; 
    int layoutResourceId;    
    Counter[] data = null;
    
    public CounterAdapter(Context context, int layoutResourceId, Counter[] data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        CounterHolder holder = null;
        
        if(row == null)
        {
        	LayoutInflater inflater = ((Activity)context).getLayoutInflater();       		
        	row = inflater.inflate(layoutResourceId, parent, false);
    		holder = new CounterHolder();
    		holder.title = (TextView)row.findViewById(R.id.textView1);
    		holder.counts = (TextView)row.findViewById(R.id.textView2);
        
    		row.setTag(holder);
        }
        else
        {
            holder = (CounterHolder)row.getTag();
        }
        
        Counter counter = data[position];
        if (counter != null){
        holder.counts.setText(counter.counts + "");
        holder.title.setText(counter.title);
        } else {
        	holder.counts.setText("");
        	holder.counts.setVisibility(View.GONE);
            holder.title.setText("");
            holder.title.setVisibility(View.GONE);
        }
        return row;
    }
    
    static class CounterHolder
    {
        TextView counts;
        TextView title;
    }
}