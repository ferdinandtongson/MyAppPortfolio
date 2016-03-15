package me.makeachoice.myappportfolio.adapter.item;

/**
 * OnlyTitleItem - is a RowItem class used in conjunction a ListAdapter. Is for only on text item
 */
public class OnlyTitleItem {
    private String mTitle;

    public OnlyTitleItem(String aTitle){
        this.mTitle = aTitle;
    }

    public String getTitle(){
        return mTitle;
    }

    public void setTitle(String aTitle){
        this.mTitle = aTitle;
    }
}
