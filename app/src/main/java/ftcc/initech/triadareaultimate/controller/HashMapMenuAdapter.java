package ftcc.initech.triadareaultimate.controller;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Map;

import ftcc.initech.triadareaultimate.R;

/**
 * Created by grturner on 3/17/17.
 */

public class HashMapMenuAdapter extends BaseAdapter {
    private final ArrayList mData;

    public HashMapMenuAdapter(Map<String, Integer> map) {
        mData = new ArrayList();
        mData.addAll(map.entrySet());
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Map.Entry<String, Integer> getItem(int position) {
        return (Map.Entry) mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO: 3/17/17 fix this
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View result;

        if (convertView == null) {
            result = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_list_item,
                    parent, false);
        } else {
            result = convertView;
        }

        Map.Entry<String, Integer> item = getItem(position);
        ((TextView) result.findViewById(R.id.menu_text)).setText(item.getKey());
        ((ImageView) result.findViewById(R.id.menu_icon)).setImageResource(item.getValue());
        return result;
    }
}
