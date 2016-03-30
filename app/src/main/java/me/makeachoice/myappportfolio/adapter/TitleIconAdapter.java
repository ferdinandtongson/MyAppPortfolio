package me.makeachoice.myappportfolio.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import me.makeachoice.myappportfolio.R;
import me.makeachoice.myappportfolio.adapter.item.TitleIconItem;
import me.makeachoice.myappportfolio.adapter.item.TitleItem;
import me.makeachoice.myappportfolio.adapter.util.ViewHolder;

/**
 * TitleIconAdapter extends TitleSimpleAdapter class and is used as a List Adapter for the Android
 * ListView widget. This adapter creates and updates the Views contained in the ListView.
 *
 * Default Item class: TitleItem
 * Default Layout: item_titlesimplele.xml
 *
 * Variables from MyBaseAdapter:
 *      Context mContext
 *      Inflater mInflator
 *
 * Variables from from TitleSimpleAdapter:
 *      DEFAULT_LAYOUT_ID
 *      DEFAULT_TITLE_VIEW_ID
 *
 *      mItemLayoutId
 *      mTitleViewId
 *
 * In your Presenter, in onActivityCreated(...) for Fragments or in onCreate for Activities and
 * after populating your ArrayList<TitleItem> with data:
 *      TitleSimpleAdapter adapter = new TitleSimpleAdapter(Context, ArrayList<TitleItem>)
 *      setListAdapter(adapter)
 *
 */
public class TitleIconAdapter extends MyBaseAdapter{

/**************************************************************************************************/
    //Default layout has been modifed from TitleSimpleAdapter to use item_titleicon instead
    protected int DEFAULT_LAYOUT_ID = R.layout.item_titleicon;
    //Default TextView id found in the default layout
    protected int DEFAULT_TITLE_VIEW_ID = R.id.item_title;
    //Default ImageView id found in the default layout
    protected int DEFAULT_ICON_VIEW_ID = R.id.item_icon;

    //mItemLayoutId - id of the layout to be inflated
    protected int mItemLayoutId;
    //mTitleViewId - id of TextView to display Title
    protected int mTitleViewId;
    //mIconView id - id of ImageView to display Icon
    protected int mIconViewId;

    private ArrayList<TitleIconItem> mItems;

/**************************************************************************************************/
/**
 * TitleIconAdapter - constructor, uses default values for Layout, TextView and ImageView
 * @param c - activity context
 * @param items - array list of TitleItem objects; data used to populate view objects
 */
    public TitleIconAdapter(Context c, ArrayList<TitleIconItem> items) {
        //initialize class variables
        mContext = c;
        mInflater = LayoutInflater.from(c);
        mItems = items;

        //use default values for Layout and TextView to display the Title
        mItemLayoutId = DEFAULT_LAYOUT_ID;
        mTitleViewId = DEFAULT_TITLE_VIEW_ID;
        mIconViewId = DEFAULT_ICON_VIEW_ID;
    }

    /**
     * TitleSimpleAdapter - constructor, accept Presenter values for Layout and TextView (for Title)
     * @param c - activity context
     * @param items - array list of TitleItem objects; data used to populate view objects
     * @param layoutId - list item layout id
     * @param titleViewId - child view of list item layout, textView for "title" value
     */
    public TitleIconAdapter(Context c, ArrayList<TitleIconItem> items, int layoutId, int titleViewId,
        int iconViewId){
        //initialize class varialbes
        mContext = c;
        mInflater = LayoutInflater.from(c);
        mItems = items;

        //use Presenter values for Layout and TextView to display the Title
        mItemLayoutId = layoutId;
        mTitleViewId = titleViewId;
        mIconViewId = iconViewId;
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
            convertView = mInflater.inflate(mItemLayoutId, null);
        }

        //updateView with data
        updateView(position, convertView);

        return convertView;
    }

/**************************************************************************************************/
    /**
     * updateView(int, View) - get object from ArrayList<TitleIconItem> and update TextView with Title
     * @param position - list item position
     * @param convertView - layout of list item view
     */
    protected void updateView(int position, View convertView){
        TitleIconItem item = mItems.get(position);

        updateTitleView(item, convertView);
        updateIconView(item, convertView);
    }

    private void updateTitleView(TitleIconItem item, View convertView){
        //get child view using ViewHolder class
        TextView txtTitle = ViewHolder.get(convertView, mTitleViewId);
        if(txtTitle == null){
            txtTitle = (TextView) convertView.findViewById(mTitleViewId);
        }
        //update child view data - title
        txtTitle.setText(item.getTitle());

        //put item object i
        txtTitle.setTag(item);
    }

    private void updateIconView(TitleIconItem item, View convertView){

        //get child view using ViewHolder class
        ImageView imgIcon = ViewHolder.get(convertView, mIconViewId);
        if(imgIcon == null){
            imgIcon = (ImageView) convertView.findViewById(mIconViewId);
        }
        //update child view data - title
        imgIcon.setImageResource(item.getIcon());




    }
/**************************************************************************************************/

}
