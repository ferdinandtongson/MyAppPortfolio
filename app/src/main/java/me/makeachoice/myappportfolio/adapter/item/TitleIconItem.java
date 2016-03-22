package me.makeachoice.myappportfolio.adapter.item;

/**
 * TitleIconItem extends TitleItem class and is used by a BaseAdapter to populate a ListView.
 * This class is associated with item_titleicon.xml layout resource file and encapsulates the View
 * created by item_titleicon.xml.
 *
 * TitleIconItem will be populated in the Presenter taking data from the Model.
 *
 * In your Presenter, adding data to your adapter before calling setListAdapter():
 *      ArrayList<TitleIconItem> rowItems = new ArrayList<TitleIconItem>();
 *      for(int i = 0; i < count; i++){
 *          MyBaseRowItem item = new TitleIconItem(title_array[i]);
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
 *      ....
 *
 */
public class TitleIconItem extends TitleItem{

    // mIconId - holds the resource Id number for the icon
    private int mIconId;

/**************************************************************************************************/
/**
 * TitleIconItem(String) - constructor, accepts string value for title and int value for icon id.
 *
 * Variables from TitleItem:
 *      String mTitle - holds the string value of the title
 *
 * Methods from TitleItem:
 *      String getTitle() - getter method for title
 *      void setTitle(String title) - setter method for title
 */
    public TitleIconItem(){}

    public TitleIconItem(String title, int iconId){
        mTitle = title;
        mIconId = iconId;
    }

/**************************************************************************************************/
    /**
     * Getter and Setter for text value Title
     * getIcon() returns the Icon; int
     * setIcon(String) sets the int value of the icon id
     */
    public int getIcon(){
        return mIconId;
    }

    public void setIcon(int iconId){
        mIconId = iconId;
    }
/**************************************************************************************************/

}
