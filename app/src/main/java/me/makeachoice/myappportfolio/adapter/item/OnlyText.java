package me.makeachoice.myappportfolio.adapter.item;

/**
 * OnlyText - is a RowItem class used in conjunction a ListAdapter. Is for only on text item
 */
public class OnlyText {
    private String mTitle;

    public OnlyText(String aTitle){
        this.mTitle = aTitle;
    }

    public String getTitle(){
        return mTitle;
    }

    public void setTitle(String aTitle){
        this.mTitle = aTitle;
    }
}
