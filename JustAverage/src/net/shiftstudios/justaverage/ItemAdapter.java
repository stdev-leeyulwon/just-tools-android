package net.shiftstudios.justaverage;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ItemAdapter extends ArrayAdapter<Item>{

    Context context; 
    int layoutResourceId;    
    Item[] data = null;
    
    public ItemAdapter(Context context, int layoutResourceId, Item[] data) {
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
        
    		row.setTag(holder);
        }
        else
        {
            holder = (CounterHolder)row.getTag();
        }
        
        Item counter = data[position];
        if (counter != null){
        holder.title.setText(counter.value + "");
        } else {
            holder.title.setText("");
            holder.title.setVisibility(View.GONE);
        }
        return row;
    }
    
    static class CounterHolder
    {
        TextView title;
    }
}