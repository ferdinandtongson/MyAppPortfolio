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
import java.util.List;

import me.makeachoice.myappportfolio.R;
import me.makeachoice.myappportfolio.adapter.ImageAdapter;
import me.makeachoice.myappportfolio.adapter.TitleAdapter;
import me.makeachoice.myappportfolio.adapter.item.TitleItem;
import me.makeachoice.myappportfolio.controller.butler.AppDemoButler;
import me.makeachoice.myappportfolio.controller.maid.AppListMaid;
import me.makeachoice.myappportfolio.controller.maid.Maid;
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

        mAppListMaid = initializeAppListMaid();

        //createLayoutMap();
    }


/**************************************************************************************************/

    private final static int LAYOUT_APP_LIST_FRAGMENT = R.layout.list_fragment;
    //private final static int LAYOUT_APP_LIST_ITEM_ID = R.layout.item_titleicon;
    //private final static int LAYOUT_APP_LIST_ITEM_TITLE_ID = R.id.item_title;

    private final static int LAYOUT_APP_GRID_FRAGMENT = R.layout.grid_fragment;

    AppListMaid mAppListMaid;

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
        TextView txtTitle = (TextView) v.findViewById(R.id.item_title);
        Log.d("SimpleListFragment", "Boss.onClickHere");
        TitleItem item = (TitleItem)txtTitle.getTag();
        Log.d("SimpleListFragment", "     title: " + item.getTitle());
    }

    private int mListItemId;
    private int mListItemTitleId;

    public ListAdapter getAppListAdapter(){
        ListAdapter adapter = initializeAppListAdapter(mButler.getModel(), mListItemId,
                mListItemTitleId);

        return adapter;
    }

/**
 * AppListMaid initializeAppListMaid()initializes the Maid class taking care of the AppList view
 * fragment defined in the design of this application. It sets the view type of how the list of
 * demo apps will be displayed, prepares and sets the list adapter to be consumed by the fragment.
 * @return AppListMaid - return a reference to the AppListMaid
 */
    private AppListMaid initializeAppListMaid(){
        mAppListTypeId = AppListMaid.TYPE_LIST_FRAGMENT;

        AppListMaid maid = new AppListMaid(this, mAppListTypeId);

        mListItemId = R.layout.item_titleicon;
        mListItemTitleId = R.id.item_title;

        ListAdapter adapter = initializeAppListAdapter(mButler.getModel(), mListItemId,
                mListItemTitleId);

        maid.setListAdapter(adapter);

        //TODO - debug to see if this is redundant, fragment type is set when Maid is instantiated
        maid.setFragmentType(mAppListTypeId);

        return maid;
    }

/**
 * ListAdapter initializeAppListAdapter() initializes the ListAdapter that will be used by the
 * AppList fragment. Currently it will initialize a Title type adapter or an Icon type adapter.
 * @return ListAdapter - will return a reference to the ListAdapter to be consumed
 */
    public ListAdapter initializeAppListAdapter(AppDemoModel model, int layoutId,
                                                int childViewId){
        Log.d("SimpleListFragment", "Boss.createAppListAdapter");

        //ListAdapter variable to be return
        ListAdapter adapter;

        //check type of fragment being used by the AppList Maid
        if(mAppListTypeId == AppListMaid.TYPE_LIST_FRAGMENT){
            //ListView fragment, initialize a Title type adapter using the data model for the apps
            adapter = initTitleAdapter(model, layoutId, childViewId);
        }
        else if(mAppListTypeId == AppListMaid.TYPE_GRID_FRAGMENT){
            //GridView fragment, initialize an Icon type adapter using the data model for the apps
            adapter = initIconAdapter(model, layoutId, childViewId);
        }
        else{
            adapter = initTitleAdapter(model, layoutId, childViewId);
        }

        return adapter;
    }

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

        //instantiate TitleAdapter with layout id found in res/layout and the child
        TitleAdapter adapter = new TitleAdapter(mActivityContext, itemList,
                layoutId, childViewId);
        adapter.setOnClickListener(mAppListOnClickListener);

        return adapter;
    }

    private ListAdapter initIconAdapter(AppDemoModel model, int layoutId, int childViewId){
        ImageAdapter adapter = new ImageAdapter(mActivityContext);

        return adapter;
    }

/**************************************************************************************************/



    HashMap<String,Integer> mLayoutMap = new HashMap<>();
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

    public Maid getMaid(String name){

        Maid maid;
        if(name.equals(AppListMaid.MAID_NAME)){
            if (mAppListMaid == null){
                mAppListMaid = initializeAppListMaid();
            }
            maid = mAppListMaid;
        }
        else{
            return null;
        }

        return maid;
    }

}
