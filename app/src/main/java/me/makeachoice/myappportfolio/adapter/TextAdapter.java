package me.makeachoice.myappportfolio.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import me.makeachoice.myappportfolio.R;
import me.makeachoice.myappportfolio.adapter.item.OnlyText;

/**
 * Created by Usuario on 3/15/2016.
 */
public class TextAdapter extends BaseAdapter {
    private Context mContext;
    List<OnlyText> mRowItem;

    public TextAdapter(Context c, List<OnlyText> rowItem) {
        Log.d("SimpleListFragment", "TextAdapter: " + c.toString());
        mContext = c;
        mRowItem = rowItem;
    }

    @Override
    public int getCount() {
        return mRowItem.size();
    }

    @Override
    public Object getItem(int position) {
        return mRowItem.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mRowItem.indexOf(getItem(position));
    }

    // create a new ImageView for each item referenced by the Adapter
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d("SimpleListFragment", "TextAdapter.getView");
        TextView textView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            textView = new TextView(mContext);
            textView.setLayoutParams(new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
            //textView.setPadding(8, 8, 8, 8);
        } else {
            textView = (TextView) convertView;
        }


        OnlyText row_pos = mRowItem.get(position);
        textView.setText(row_pos.getTitle());
        return textView;
    }


}
