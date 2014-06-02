package com.calcul.diabetif.commun.adapter;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;


public class KeyValueAdapter
    extends BaseAdapter
    implements SpinnerAdapter
{

	private static final String TAG = KeyValueAdapter.class.getSimpleName();

    private final Context context;
    private final Map<String, String> data;
    private final List<String> keys;


    public KeyValueAdapter(Context context, int textViewResourceId, Map<String, String> objects)
    {
    	Log.v(TAG, "KeyValueAdapter(Context context, int textViewResourceId, Map<String, String> objects)");
        this.context = context;
        data = objects;
        
        keys = new LinkedList<String>();
        keys.addAll(objects.keySet());
    }


    public int getCount()
    {
        return data.size();
    }


    public int getPositionFromKey(String searchKey)
    {
    	return keys.indexOf(searchKey);
    }


    public Object getItem(int position)
    {
        return keys.get(position);
    }


    public long getItemId(int position)
    {
       return position;
    }


    public View getView(int position, View view, ViewGroup parent)
    {
        final TextView text = new TextView(context);
        text.setText(data.get(keys.get(position)));

        return text;
    }
}