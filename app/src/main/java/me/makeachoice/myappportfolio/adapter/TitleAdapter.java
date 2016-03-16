package me.makeachoice.myappportfolio.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import me.makeachoice.myappportfolio.R;
import me.makeachoice.myappportfolio.adapter.item.TitleItem;

/**
 * Created by Usuario on 3/15/2016.
 */
public class TitleAdapter extends BaseAdapter {
    Context mContext;
    LayoutInflater mInflator;
    List<TitleItem> mRowItem;
    int mItemLayoutId;
    int mTitleViewId;

    public TitleAdapter(Context c, List<TitleItem> rowItem) {
        Log.d("SimpleListFragment", "TitleAdapter");
        Log.d("SimpleListFragment", "     row: " + rowItem.size());
        mContext = c;
        mInflator = LayoutInflater.from(c);
        mRowItem = rowItem;
        mItemLayoutId = R.layout.item_onlytitle;
        mTitleViewId = R.id.item_onlytext_title;
    }

    public TitleAdapter(Context c, List<TitleItem> rowItem, int layoutId, int titleViewId){
        Log.d("SimpleListFragment", "TitleAdapter");
        Log.d("SimpleListFragment", "     row: " + rowItem.size());
        Log.d("SimpleListFragment", "     dynamic layout and titleView");
        mContext = c;
        mInflator = LayoutInflater.from(c);
        mRowItem = rowItem;
        mItemLayoutId = layoutId;
        mTitleViewId = titleViewId;
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
        Log.d("SimpleListFragment", "TitleAdapter.getView");
        View aView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            aView = mInflator.inflate(mItemLayoutId, null);
            /*textView.setLayoutParams(new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT));*/

            Log.d("SimpleListFragment", "     createTextView: " + aView.toString());

        } else {
            aView = (View)convertView;
        }

        TextView txtTitle = (TextView)aView.findViewById(mTitleViewId);

        TitleItem row_pos = mRowItem.get(position);
        txtTitle.setText(row_pos.getTitle());
        return aView;
    }

}
