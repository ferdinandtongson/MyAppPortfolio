package me.makeachoice.myappportfolio.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import me.makeachoice.myappportfolio.R;
import me.makeachoice.myappportfolio.adapter.item.TitleItem;
import me.makeachoice.myappportfolio.adapter.util.ViewHolder;

/**
 * TitleAdapter extends MyBaseAdapter base class and is used as a List Adapter for the Android
 * ListView widget. This adapter creates and updates the Views contained in the ListView.
 *
 * Default Item class: TitleItem
 * Default Layout: item_onlytitle.xml
 *
 * Variables from MyBaseAdapter:
 *      Context mContext
 *      Inflater mInflator
 *
 * Methods from MyBaseAdapter:
 *      - None -
 *
 * In your Presenter, in onActivityCreated(...) for Fragments or in onCreate for Activities and
 * after populating your ArrayList<TitleItem> with data:
 *      TitleAdapter adapter = new TitleAdapter(Context, ArrayList<TitleItem>)
 *      setListAdapter(adapter)
 *
 */
public class TitleAdapter extends MyBaseAdapter {
    //Default layout id found in project Resources res/layout
    public int DEFAULT_LAYOUT_ID = R.layout.item_onlytitle;
    //Default TextView id found in the default layout
    public int DEFAULT_TITLE_VIEW_ID = R.id.item_onlytext_title;

    //mItemLayoutId - id of the layout to be inflated
    int mItemLayoutId;
    //mTitleViewId - id of TextView to display Title
    int mTitleViewId;

    //mItems - ArrayList of TitleItem objects holding data to be displayed
    ArrayList<TitleItem> mItems;

/**************************************************************************************************/
/**
 * TitleAdapter - constructor, uses default values for Layout and TextView (for Title)
 * @param c - activity context
 * @param items - array list of TitleItem objects; data used to populate view objects
 */
    public TitleAdapter(Context c, ArrayList<TitleItem> items) {
        //initialize class variables
        mContext = c;
        mInflator = LayoutInflater.from(c);
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
    public TitleAdapter(Context c, ArrayList<TitleItem> items, int layoutId, int titleViewId){
        //initialize class varialbes
        mContext = c;
        mInflator = LayoutInflater.from(c);
        mItems = items;

        //use Presenter values for Layout and TextView to display the Title
        mItemLayoutId = layoutId;
        mTitleViewId = titleViewId;
    }

/**************************************************************************************************/
/**
 * getCount() - returns the size of the ArrayList<TitleItem>
 * @return int - returns number of items in list
 */
    @Override
    public int getCount() {
        return mItems.size();
    }

/**
 * getItem(int) - returns the object in the ArrayList<TitleItem> position; TitleItem Object
 * @param position - position of list item
 * @return Object - item object at that given position
 */
    @Override
    public Object getItem(int position) {
        return mItems.get(position);
    }

/**
 * getItemId(int) - returns index of the object in the ArrayList<TitleItem></TitleItem>
 * @param position - position of list item
 * @return int - id of list item
 */
    @Override
    public long getItemId(int position) {
        return mItems.indexOf(getItem(position));
    }

/**
 * getView(int, View, ViewGroup) - create or recylce View object then update and return View
 * @param position - position of view
 * @param convertView - view object
 * @param parent - parent of view, list view object
 * @return View - return updated item view of list
 */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //Check if we can recycle old view object
        if (convertView == null) {
            // if not recycled, inflate layout of new view object
            convertView = mInflator.inflate(mItemLayoutId, null);
        }

        //updateView with data
        updateView(position, convertView);

        return convertView;
    }

/**************************************************************************************************/
/**
 * updateView(int, View) - get object from ArrayList<TitleItem> and update TextView with Title
 * @param position - list item position
 * @param convertView - layout of list item view
 */
    protected void updateView(int position, View convertView){
        //get bean for view - encapsulated data
        TitleItem item = mItems.get(position);

        //get child view using ViewHolder class
        TextView txtTitle = ViewHolder.get(convertView, mTitleViewId);
        if(txtTitle == null){
            txtTitle = (TextView) convertView.findViewById(mTitleViewId);
        }
        //update child view
        txtTitle.setText(item.getTitle());
    }
/**************************************************************************************************/
}
