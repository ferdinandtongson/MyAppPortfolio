package me.makeachoice.myappportfolio.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import me.makeachoice.myappportfolio.R;
import me.makeachoice.myappportfolio.adapter.item.OnlyText;

/**
 * Created by Usuario on 3/15/2016.
 */
public class TextAdapter extends BaseAdapter {
    Context mContext;
    LayoutInflater mInflator;
    List<OnlyText> mRowItem;

    public TextAdapter(Context c, List<OnlyText> rowItem) {
        Log.d("SimpleListFragment", "TextAdapter");
        Log.d("SimpleListFragment", "     row: " + rowItem.size());
        mContext = c;
        mInflator = LayoutInflater.from(c);
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
        View aView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            aView = mInflator.inflate(R.layout.item_onlytext, null);
            /*textView.setLayoutParams(new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT));*/

            Log.d("SimpleListFragment", "     createTextView: " + aView.toString());
            //textView.setPadding(8, 8, 8, 8);
        } else {
            aView = (View)convertView;
        }

        TextView txtTitle = (TextView)aView.findViewById(R.id.item_onlytext_title);

        OnlyText row_pos = mRowItem.get(position);
        txtTitle.setText(row_pos.getTitle());
        return aView;
    }


}
