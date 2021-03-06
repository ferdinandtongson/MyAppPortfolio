package me.makeachoice.myappportfolio.adapter.item;

/**
 * TitleItem extends MyBaseRowItem base class is used by a BaseAdapter to populate a ListView. This
 * class is associated with item_titlesimple.xmlml layout resource file and encapsulates the View
 * created by item_titlesimplele.xml.
 *
 * TitleItem will be populated in the Presenter taking data from the Model.
 *
 * In your Presenter, adding data to your adapter before calling setListAdapter():
 *      ArrayList<TitleItem> rowItems = new ArrayList<TitleItem>();
 *      for(int i = 0; i < count; i++){
 *          MyBaseRowItem item = new TitleItem(title_array[i]);
 *          rowItems.add(item);
 *      }
 *
 *      [X]Adapter adapter = new [X]Adapter(context, rowItems);
 *      setListAdapter(adapter);
 *
 *  In getView(...) in your Adapter, get view and corresponding data for view:
 *      TextView txtTitle = (TextView)itemView.findViewById(R.id.item_title_txtTitle)
 *
 *      TitleItem item = mRowItem.get(position);
 *      txtTitle.setText(item.getTitle());
 *
 */
public class TitleItem extends MyBaseRowItem {
    //mTitle - holds the text value for Title
    protected String mTitle;

/**************************************************************************************************/
/**
 * TitleItem(String) - constructor, accepts string value for title
 */
public TitleItem(){}

public TitleItem(String title){
        mTitle = title;
    }

/**************************************************************************************************/
/**
 * Getter and Setter for text value Title
 * getTitle() returns the Title; String
 * setTitle(String) sets the string value of the Title
 */
    public String getTitle(){
        return mTitle;
    }

    public void setTitle(String title){
        mTitle = title;
    }
/**************************************************************************************************/
}
