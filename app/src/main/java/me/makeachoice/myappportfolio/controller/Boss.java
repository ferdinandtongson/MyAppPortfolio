package me.makeachoice.myappportfolio.controller;

import android.app.Application;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.ListAdapter;

import java.util.ArrayList;
import java.util.HashMap;

import me.makeachoice.myappportfolio.R;
import me.makeachoice.myappportfolio.adapter.TitleAdapter;
import me.makeachoice.myappportfolio.adapter.item.TitleItem;
import me.makeachoice.myappportfolio.controller.butler.AppDemoButler;
import me.makeachoice.myappportfolio.controller.maid.AppListMaid;
import me.makeachoice.myappportfolio.fragment.list.SimpleListFragment;
import me.makeachoice.myappportfolio.model.AppDemoModel;

/**
 * Boss is the "boss", main controller of the app and interfaces with the View and Model. Boss
 * tries to adhere to the MVP (Model-View-Presenter) model so Model-View communication is
 * prevented; in MVC (Model-View-Controller) model, the Model and View can communicate
 */
public class Boss extends Application{
    public final static String KEY_MAIN_SCREEN = "Main";
    public final static String KEY_MAIN_CONTAINER = "Main Container";
    public final static String KEY_LIST_FRAG = "List Fragment";
    public final static String KEY_INFO_FRAG = "Information Fragment";

    private final static int LAYOUT_MAIN = R.layout.activity_main;
    private final static int LAYOUT_MAIN_CONTAINER = R.id.fragment_container;
    private final static int LAYOUT_LIST_FRAG = R.layout.list_fragment;

    Context mActivityContext;
    AppListMaid mMaid;
    AppDemoButler mButler;
    public void setActivityContext(Context ctx){
        mActivityContext = ctx;
        mMaid = new AppListMaid(mActivityContext);
        mButler = new AppDemoButler(mActivityContext);

        mMaid.setModel(mButler.getModel());
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

    public Fragment getListFragment(){
        return mMaid.getFragment();
    }

    public AppListMaid getMaid(){
        return mMaid;
    }


}
