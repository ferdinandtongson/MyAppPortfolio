package me.makeachoice.myappportfolio.controller;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.ListAdapter;

import java.util.ArrayList;
import java.util.HashMap;

import me.makeachoice.myappportfolio.R;
import me.makeachoice.myappportfolio.adapter.TitleAdapter;
import me.makeachoice.myappportfolio.adapter.item.TitleItem;
import me.makeachoice.myappportfolio.controller.butler.AppDemoButler;
import me.makeachoice.myappportfolio.fragment.list.SimpleListFragment;
import me.makeachoice.myappportfolio.model.AppDemoModel;

/**
 * Boss is the "boss", main controller of the app and interfaces with the View and Model. Boss
 * tries to adhere to the MVP (Model-View-Presenter) model so Model-View communication is
 * prevented; in MVC (Model-View-Controller) model, the Model and View can communicate
 */
public class Boss extends Application implements SimpleListFragment.Bridge{
    public final static String KEY_MAIN_SCREEN = "Main";
    public final static String KEY_MAIN_CONTAINER = "Main Container";
    public final static String KEY_LIST_FRAG = "List Fragment";
    public final static String KEY_INFO_FRAG = "Information Fragment";

    private final static int LAYOUT_MAIN = R.layout.activity_main;
    private final static int LAYOUT_MAIN_CONTAINER = R.id.fragment_container;
    private final static int LAYOUT_LIST_FRAG = R.layout.list_fragment;

    Context mActivityContext;
    public void setActivityContext(Context ctx){
        mActivityContext = ctx;
        createLayoutMap();
    }

    HashMap<String,Integer> mLayoutMap = new HashMap<String,Integer>();
    private void createLayoutMap(){
        mLayoutMap.put(KEY_MAIN_SCREEN, LAYOUT_MAIN);
        mLayoutMap.put(KEY_MAIN_CONTAINER, LAYOUT_MAIN_CONTAINER);
        mLayoutMap.put(KEY_LIST_FRAG, LAYOUT_LIST_FRAG);
        mLayoutMap.put(KEY_INFO_FRAG, -1);
    }

    public int getLayout(String key){
        return mLayoutMap.get(key);
    }


    private ListAdapter mListAdapter;
    public ListAdapter getListAdapter(){
        Log.d("SimpleListFragment", "Boss.getListAdapter");

        if(mListAdapter == null){
            AppDemoButler butler = new AppDemoButler(mActivityContext);
            Log.d("SimpleListFragment", "     adapter is null");
            mListAdapter = butler.getListAdapter();
            Log.d("SimpleListFragment", "          size: " + mListAdapter.getCount());

        }
        Log.d("SimpleListFragment", "     adapter: " + mListAdapter.toString());

        return mListAdapter;
    }

    public void onSimpleListItemClick(int position){
        Log.d("SimpleListFragment", "Boss.onListItemClick");
        //Log.d("SimpleListFragment", "     info: " + mAppModel.getApp(position).getDescription());
    }

}
