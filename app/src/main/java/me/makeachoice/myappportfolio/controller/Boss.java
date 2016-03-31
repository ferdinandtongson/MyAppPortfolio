package me.makeachoice.myappportfolio.controller;

import android.app.Application;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.ListAdapter;

import java.util.ArrayList;
import java.util.HashMap;

import me.makeachoice.myappportfolio.R;
import me.makeachoice.myappportfolio.adapter.ImageAdapter;
import me.makeachoice.myappportfolio.adapter.TitleSimpleAdapter;
import me.makeachoice.myappportfolio.adapter.item.TitleItem;
import me.makeachoice.myappportfolio.controller.butler.AppDemoButler;
import me.makeachoice.myappportfolio.controller.housekeeper.HouseKeeper;
import me.makeachoice.myappportfolio.controller.housekeeper.MainKeeper;
import me.makeachoice.myappportfolio.controller.maid.AppSelectMaid;
import me.makeachoice.myappportfolio.controller.maid.Maid;
import me.makeachoice.myappportfolio.model.AppDemoModel;

/**
 * Boss is the "boss", main controller of the app and interfaces with the View and Model. Boss
 * tries to adhere to the MVP (Model-View-Presenter) model so Model-View communication is
 * prevented; in MVC (Model-View-Controller) model, the Model and View can communicate
 */
public class Boss extends Application{
    public final static String KEY_LIST_FRAG = "List Fragment";
    public final static String KEY_INFO_FRAG = "Information Fragment";


    Context mActivityContext;
    private int mAppListTypeId;

    AppDemoButler mButler;
    public void setActivityContext(Context ctx){
        mActivityContext = ctx;
        mButler = new AppDemoButler(mActivityContext);

        createLayoutMap();
    }

    public AppDemoModel getModel(){
        return mButler.getModel();
    }

/**************************************************************************************************/

    private final static int LAYOUT_APP_LIST_FRAGMENT = R.layout.list_fragment;
    //private final static int LAYOUT_APP_LIST_ITEM_ID = R.layout.item_titleicon;
    //private final static int LAYOUT_APP_LIST_ITEM_TITLE_ID = R.id.item_title;

    private final static int LAYOUT_APP_GRID_FRAGMENT = R.layout.grid_fragment;

    AppSelectMaid mAppListMaid;

    public interface AppListBridge{
        //Interface are methods the Maid has to implement but it is a one-way
        //communication.
        Fragment getFragment();

        void setListAdapter(ListAdapter adapter);
        void setFragmentType(int fragmentType);
    }


    private int mListItemId;
    private int mListItemTitleId;



/**
 * ListAdapter initTitleAdapter(model) used to initialize a Title type adapter typically used with
 * a ListView. Uses the AppDemo data model
 * @param model - data model for the application demos demoed in this app
 * @return ListAdapter - will return a reference to the Title adapter create with the data model
 */
    private ListAdapter initTitleAdapter(AppDemoModel model, int layoutId, int childViewId){
        //create an ArrayList to hold the list items to be consumed by the ListAdapter
        ArrayList<TitleItem> itemList = new ArrayList<>();

        //number of AppDemo data models
        int count = model.getAppCount();

        //loop through the data models
        for(int i = 0; i < count; i++){
            //initialize TitleItem with name of app taken from the model
            TitleItem item = new TitleItem(model.getApp(i).getName());

            //add item into array list
            itemList.add(item);
        }

        //instantiate TitleSimpleAdapter with layout id found in res/layout and the child
        TitleSimpleAdapter adapter = new TitleSimpleAdapter(mActivityContext, itemList,
                layoutId, childViewId);
        //adapter.setOnClickListener(mAppListOnClickListener);

        return adapter;
    }

    private ListAdapter initIconAdapter(AppDemoModel model, int layoutId, int childViewId){

        return new ImageAdapter(mActivityContext);
    }

/**************************************************************************************************/



    HashMap<String,Integer> mLayoutMap = new HashMap<>();
    private void createLayoutMap(){
        mLayoutMap.put(KEY_LIST_FRAG, LAYOUT_APP_LIST_FRAGMENT);
        mLayoutMap.put(KEY_INFO_FRAG, -1);
    }

    public int getLayout(String key){
        return mLayoutMap.get(key);
    }



    private HashMap<String, Maid> mMaidRegistry = new HashMap<>();
    public void registerMaid(String key, Maid maid){
        mMaidRegistry.put(key, maid);
    }

    public Maid getMaid(String key){
        return mMaidRegistry.get(key);
    }


    private HashMap<String, HouseKeeper> mHouseKeeperRegistry = new HashMap<>();
    public void registerHouseKeeper(String key, HouseKeeper keeper) {
        mHouseKeeperRegistry.put(key, keeper);
    }

}
