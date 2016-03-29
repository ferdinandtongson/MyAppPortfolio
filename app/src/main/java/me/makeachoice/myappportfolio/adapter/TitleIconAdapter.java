package me.makeachoice.myappportfolio.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import me.makeachoice.myappportfolio.R;
import me.makeachoice.myappportfolio.adapter.item.TitleItem;
import me.makeachoice.myappportfolio.adapter.util.ViewHolder;

/**
 * Created by Usuario on 3/28/2016.
 */
public class TitleIconAdapter extends TitleAdapter{
/**************************************************************************************************/
    /**
     * TitleAdapter - constructor, uses default values for Layout and TextView (for Title)
     * @param c - activity context
     * @param items - array list of TitleItem objects; data used to populate view objects
     */
    public TitleIconAdapter(Context c, ArrayList<TitleItem> items) {
        super(c, items);
        //initialize class variables
        mContext = c;
        mInflater = LayoutInflater.from(c);
        mItems = items;

        //use default values for Layout and TextView to display the Title
        mItemLayoutId = DEFAULT_LAYOUT_ID;
        mTitleViewId = DEFAULT_TITLE_VIEW_ID;
    }

    /**
     * TitleAdapter - constructor, accept Presenter values for Layout and TextView (for Title)
     * @param c - activity context
     * @param items - array list of TitleItem objects; data used to populate view objects
     * @param layoutId - list item layout id
     * @param titleViewId - child view of list item layout, textView for "title" value
     */
    public TitleIconAdapter(Context c, ArrayList<TitleItem> items, int layoutId, int titleViewId){
        super(c, items, layoutId, titleViewId);
        //initialize class varialbes
        mContext = c;
        mInflater = LayoutInflater.from(c);
        mItems = items;

        //use Presenter values for Layout and TextView to display the Title
        mItemLayoutId = layoutId;
        mTitleViewId = titleViewId;
    }

/**************************************************************************************************/
    /**
     * updateView(int, View) - get object from ArrayList<TitleItem> and update TextView with Title
     * @param position - list item position
     * @param convertView - layout of list item view
     */
    protected void updateView(int position, View convertView){
        super.updateView(position, convertView);

        updateIconView(position, convertView);
        /*get bean for view - encapsulated data
        TitleItem item = mItems.get(position);

        //get child view using ViewHolder class
        TextView txtTitle = ViewHolder.get(convertView, mTitleViewId);
        if(txtTitle == null){
            txtTitle = (TextView) convertView.findViewById(mTitleViewId);
        }
        //update child view data - title
        txtTitle.setText(item.getTitle());

        //put item object i
        txtTitle.setTag(item);*/
    }


    private void updateIconView(int position, View convertView){
        TitleItem item = mItems.get(position);

        //get child view using ViewHolder class
        ImageView imgIcon = ViewHolder.get(convertView, R.id.item_icon);
        if(imgIcon == null){
            imgIcon = (ImageView) convertView.findViewById(R.id.item_icon);
        }
        //update child view data - title
        imgIcon.setImageResource(R.drawable.sample_2);




    }
/**************************************************************************************************/

}
