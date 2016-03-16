package me.makeachoice.myappportfolio.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import me.makeachoice.myappportfolio.R;
import me.makeachoice.myappportfolio.adapter.item.TitleItem;

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
 * @param c
 * @param items
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
 * @param c
 * @param items
 * @param layoutId
 * @param titleViewId
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
 * @return int
 */
    @Override
    public int getCount() {
        return mItems.size();
    }

/**
 * getItem(int) - returns the object in the ArrayList<TitleItem> position; TitleItem Object
 * @param position
 * @return Object
 */
    @Override
    public Object getItem(int position) {
        return mItems.get(position);
    }

/**
 * getItemId(int) - returns index of the object in the ArrayList<TitleItem></TitleItem>
 * @param position
 * @return int
 */
    @Override
    public long getItemId(int position) {
        return mItems.indexOf(getItem(position));
    }

/**
 * getView(int, View, ViewGroup) - create or recylce View object then update and return View
 * @param position
 * @param convertView
 * @param parent
 * @return View
 */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //view object return to be used in ListView
        View view;

        //Check if we can recycle old view object
        if (convertView == null) {
            // if not recycled, inflate layout of new view object
            view = mInflator.inflate(mItemLayoutId, null);

        } else {
            //recycle old view object
            view = (View)convertView;
        }

        //updateView with data
        updateView(position, view);

        return view;
    }

/**************************************************************************************************/
/**
 * updateView(int, View) - get object from ArrayList<TitleItem> and update TextView with Title
 * @param position
 * @param view
 */
    protected void updateView(int position, View view){

        TitleItem item = mItems.get(position);

        TextView txtTitle = (TextView)view.findViewById(mTitleViewId);
        txtTitle.setText(item.getTitle());
    }
/**************************************************************************************************/
}
