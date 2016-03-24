package me.makeachoice.myappportfolio.controller;

import android.app.Application;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import me.makeachoice.myappportfolio.R;
import me.makeachoice.myappportfolio.adapter.ImageAdapter;
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



    Context mActivityContext;
    private int mAppListTypeId;

    AppDemoButler mButler;
    public void setActivityContext(Context ctx){
        mActivityContext = ctx;
        mButler = new AppDemoButler(mActivityContext);
        mAppListTypeId = mAppListMaid.TYPE_LIST_FRAGMENT;
        mAppListMaid = getAppListMaid();

        createLayoutMap();
    }

    AppListMaid mAppListMaid;
    public void requestMaid(String key){
        if(key == KEY_MAIN_SCREEN){

        }
        else if(key == KEY_LIST_FRAG){
            mAppListMaid = getAppListMaid();
        }
    }

/**************************************************************************************************/

    private final static int LAYOUT_APP_LIST_FRAGMENT = R.layout.list_fragment;
    private final static int LAYOUT_APP_LIST_ITEM_ID = R.layout.item_titleicon;
    private final static int LAYOUT_APP_LIST_ITEM_TITLE_ID = R.id.item_title;

    private final static int LAYOUT_APP_GRID_FRAGMENT = R.layout.grid_fragment;

    public interface AppListBridge{
        //Interface are methods the Maid has to implement but it is a one-way
        //communication.
        Fragment getFragment();

        void setListAdapter(ListAdapter adapter);
        void setFragmentType(int fragmentType);
    }


    View.OnClickListener mAppListOnClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            Log.d("SimpleListFragment", "Boss.onClickListner");
            Log.d("SimpleListFragment", "     view: " + this.toString());
            onClickHere(v);
            //this.toString();
            //Log.d("SimpleListFragment", "     position: " + v.getTag().toString());
        }
    };

    public void onClickHere(View v){
        TextView txtTitle = (TextView) v.findViewById(LAYOUT_APP_LIST_ITEM_TITLE_ID);
        Log.d("SimpleListFragment", "Boss.onClickHere");
        TitleItem item = (TitleItem)txtTitle.getTag();
        Log.d("SimpleListFragment", "     title: " + item.getTitle());
    }
    private AppListMaid getAppListMaid(){
        Log.d("SimpleListFragment", "Boss.getAppListMaid");
        if(mAppListMaid == null){
            mAppListMaid = new AppListMaid(this, mAppListTypeId);

            mAppListAdapter = createAppListAdapter();

            mAppListMaid.setListAdapter(mAppListAdapter);
            mAppListMaid.setFragmentType(mAppListTypeId);
            Log.d("SimpleListFragment", "     init Frag and set ListAdapter");
        }
        return mAppListMaid;
    }

    ListAdapter mAppListAdapter;
    public ListAdapter createAppListAdapter(){
        Log.d("SimpleListFragment", "Boss.createAppListAdapter");
        if(mAppListAdapter == null){

            if(mAppListTypeId == mAppListMaid.TYPE_LIST_FRAGMENT){
                mAppListAdapter = initTitleAdapter(mButler.getModel());
            }
            else{
                Log.d("SimpleListFragment", "     icon adapter");
                mAppListAdapter = initIconAdapter();
            }
        }

        return mAppListAdapter;
    }

    private ListAdapter initTitleAdapter(AppDemoModel model){
        ArrayList<TitleItem> itemList = new ArrayList<TitleItem>();
        int count = model.getAppCount();
        for(int i = 0; i < count; i++){
            TitleItem item = new TitleItem(model.getApp(i).getName());
            itemList.add(item);
        }

        TitleAdapter adapter = new TitleAdapter(mActivityContext, itemList,
                LAYOUT_APP_LIST_ITEM_ID, LAYOUT_APP_LIST_ITEM_TITLE_ID);
        adapter.setOnClickListener(mAppListOnClickListener);

        return (ListAdapter)adapter;
    }

    private ListAdapter initIconAdapter(){
        ImageAdapter adapter = new ImageAdapter(mActivityContext);

        return (ListAdapter)adapter;
    }

    /*Fragment mAppListFragment;
    private Fragment initAppListFragment(){
        Log.d("SimpleListFragment", "Boss.initAppListFragment");
        // Create a new Fragment to be placed in the activity layout
        SimpleListFragment frag = new SimpleListFragment();

        frag.setLayout(LAYOUT_APP_LIST_FRAGMENT);
        Log.d("SimpleListFragment", "     setlayout to frag");

        return (Fragment)frag;
    }*/

/**************************************************************************************************/



    HashMap<String,Integer> mLayoutMap = new HashMap<String,Integer>();
    private void createLayoutMap(){
        mLayoutMap.put(KEY_MAIN_SCREEN, LAYOUT_MAIN);
        mLayoutMap.put(KEY_MAIN_CONTAINER, LAYOUT_MAIN_CONTAINER);
        mLayoutMap.put(KEY_LIST_FRAG, LAYOUT_APP_LIST_FRAGMENT);
        mLayoutMap.put(KEY_INFO_FRAG, -1);
    }

    public int getLayout(String key){
        return mLayoutMap.get(key);
    }

    public Fragment getListFragment(){
        return mAppListMaid.getFragment();
    }

    public AppListMaid getMaid(String name){

        if(name == mAppListMaid.MAID_NAME){
            return mAppListMaid;
        }

        return mAppListMaid;
    }

 /*String KEY_APP_LIST_LAYOUT = "AppList Fragment";
        String KEY_ITEM_VIEW_TITLE = "ListItem ViewTitle";

        HashMap<String, Integer> mLayoutMap = new HashMap<String,Integer>(){
            {
                put(KEY_APP_LIST_LAYOUT, LAYOUT_APP_LIST_ITEM_ID);
                put(KEY_ITEM_VIEW_TITLE, LAYOUT_APP_LIST_ITEM_TITLE_ID);
            };
        };*/
}
