package me.makeachoice.myappportfolio.controller;

import android.app.Application;
import android.util.Log;
import android.widget.ListAdapter;

import java.util.ArrayList;

import me.makeachoice.myappportfolio.R;
import me.makeachoice.myappportfolio.adapter.TitleAdapter;
import me.makeachoice.myappportfolio.adapter.item.TitleItem;
import me.makeachoice.myappportfolio.fragment.list.SimpleListFragment;
import me.makeachoice.myappportfolio.model.AppModel;

/**
 * Boss is the "boss", main controller of the app and interfaces with the View and Model. Boss
 * tries to adhere to the MVP (Model-View-Presenter) model so Model-View communication is
 * prevented; in MVC (Model-View-Controller) model, the Model and View can communicate
 */
public class Boss extends Application implements SimpleListFragment.Bridge{
    private final static String KEY_MAIN_SCREEN = "Main";
    private final static String KEY_MAIN_CONTAINER = "Main Container";
    private final static String KEY_LIST_FRAG = "List Fragment";
    private final static String KEY_INFO_FRAG = "Information Fragment";

    private final static int LAYOUT_MAIN = R.layout.activity_main;
    private final static int LAYOUT_MAIN_CONTAINER = R.id.fragment_container;
    private final static int LAYOUT_LIST_FRAG = R.layout.list_fragment;

    private ListAdapter mListAdapter;
    public ListAdapter getListAdapter(){
        Log.d("SimpleListFragment", "Boss.getListAdapter");

        if(mListAdapter == null){
            Log.d("SimpleListFragment", "     adapter is null");
            mListAdapter = new TitleAdapter(this, createListItems(),
                    R.layout.item_onlytitle, R.id.item_onlytext_title);
            Log.d("SimpleListFragment", "          size: " + mListAdapter.getCount());

        }
        Log.d("SimpleListFragment", "     adapter: " + mListAdapter.toString());

        return mListAdapter;
    }

    AppModel mAppModel;
    private ArrayList<TitleItem> createListItems( ){
        Log.d("SimpleListFragment", "Boss.createListItems()");
        mAppModel = new AppModel();
        Log.d("SimpleListFragment", "     created appModel object");

        mAppModel.addApp("App1", "Info1");
        mAppModel.addApp("App2", "Info2");
        mAppModel.addApp("App3", "Info3");
        mAppModel.addApp("App4", "Info4");
        mAppModel.addApp("App5", "Info5");


        ArrayList<TitleItem> itemList = new ArrayList<TitleItem>();
        int count = mAppModel.getAppCount();
        for(int i = 0; i < count; i++){
            TitleItem item = new TitleItem(mAppModel.getApp(i).getAppName());
            itemList.add(item);
        }

        return itemList;
    }

    public void onSimpleListItemClick(int position){
        Log.d("SimpleListFragment", "Boss.onListItemClick");
        Log.d("SimpleListFragment", "     info: " + mAppModel.getApp(position).getmAppInfo());
    }

}
