package me.makeachoice.myappportfolio.controller.housekeeper;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListAdapter;

import java.util.ArrayList;
import java.util.HashMap;

import me.makeachoice.myappportfolio.MainActivity;
import me.makeachoice.myappportfolio.R;
import me.makeachoice.myappportfolio.adapter.ImageAdapter;
import me.makeachoice.myappportfolio.adapter.TitleSimpleAdapter;
import me.makeachoice.myappportfolio.adapter.TitleIconAdapter;
import me.makeachoice.myappportfolio.adapter.item.TitleIconItem;
import me.makeachoice.myappportfolio.adapter.item.TitleItem;
import me.makeachoice.myappportfolio.controller.Boss;
import me.makeachoice.myappportfolio.controller.maid.AppSelectMaid;
import me.makeachoice.myappportfolio.fragment.SimpleGridFragment;
import me.makeachoice.myappportfolio.fragment.list.SimpleListFragment;
import me.makeachoice.myappportfolio.model.AppDemoModel;

/**
 * MainKeeper is the HouseKeeper class for MainActivity. It's primary responsibility is to
 * initializes and takes care of the MainActivity resource details.
 *
 * //TODO - need to dynamically set the Menus in the ActionBar, now currently hardcoded in xml file
 * //TODO - initFrag is getting called twice after setListAdapter is called on the Maid
 *
 * It also handles the loading and unloading of Fragments and manages the Maid classes responsible
 * for the upkeep of the Fragments and the communication between the Maids, Activity and Boss.
 *
 * Finally, it directly communicates with the Boss to get all the necessary data for the Views.
 */
public class MainKeeper extends HouseKeeper implements MainActivity.Bridge,
        AppSelectMaid.Bridge{
/**
 * MainKeeper will be able to display the following fragments:
 *      AppList
 *      AppListIcon
 *      AppGrid
 *      AppGridInfo
 *      AppInfo
 *
 * Variables from HouseKeeper:
 *      Boss mBoss
 *
 * Implements Boss.xxxBridge Methods:
 *
 *
 * Implements MainActivity.Bridge Methods:
 *      int getActivityLayoutId();
 *      int getFragmentContainerId();
 *      int getToolbarId();
 *      int getFloatingActionButtonId();
 *
 *      int getFragmentType(String);
 *      void setFragmentType(String, int);
 *
 *      void createFragment(Boolean);
 *
 *      void onOptionsItemSelected(MenuItem item);
 *      void setFABOnClickListener();
 *
 */
/**************************************************************************************************/
    //NAME - unique name of the HouseKeeper
    public final static String NAME = "MainKeeper";

    //mAppSelectFrag - application demo selection fragment, where user can select app to look at
    private Fragment mAppSelectFrag;
    //mAppInfoFrag - application demo infromation fragment, where user can see info about app
    private Fragment mAppInfoFrag;

    //mAppSelectType - type of app selection fragment
    public final static String FRAG_APP_SELECT = "AppSelectFragment";

    public final static int SELECT_TYPE_LIST_SIMPLE = 0;
    public final static int SELECT_TYPE_LIST_ICON = 1;
    public final static int SELECT_TYPE_GRID_SIMPLE = 2;
    public final static int SELECT_TYPE_LIST_COMPLEX = 3;
    public final static int DEFAULT_SELECT_TYPE = SELECT_TYPE_LIST_SIMPLE;

    //mAppInfoType - type of information fragments
    private int mAppInfoType;
    public final static String FRAG_APP_INFO = "AppInfoFragment";

    public final static int INFO_TYPE_SIMPLE = 0;

/**************************************************************************************************/

    public MainKeeper(Boss boss, Context ctx, FragmentManager manager){
        mBoss = boss;
        mActivityContext = ctx;
        mFragmentManager = manager;

        mBoss.registerHouseKeeper(NAME, this);
        //TODO - need to be able to save user default fragment select type
        mMapFragmentType = new HashMap<>();

        initHouseKeeping();
    }

/**************************************************************************************************/
/**
 * AppSelectMaid is in charge of taking care of all the different types of demo application
 * selection fragments. It will maintain all events or requests called by the fragment and will push
 * these events or requests up to the HouseKeeper if the Maid cannot handle it.
 *
 * AppInfoMaid is in charge of taking care of all the different types of demo information fragments.
 * It will maintain all events or requests called by the fragment and will push these events or
 * requests up to the HouseKeeper if the Maid cannot handle it.
 */
/**************************************************************************************************/
    //mAppSelectMaid - maid in charge of taking care of demo application selection fragments
    private AppSelectMaid mAppSelectMaid;
    //mAppInfoMaid - maid in charge of taking care of demo application info fragments
    //private AppInfoMaid mAppInfoMaid //TODO - need to create AppInfoMaid class

    //NAME_APP_SELECT_MAID - name registered to the Boss for AppSelectMaid
    private final static String NAME_APP_SELECT_MAID = "AppSelectMaid";
    //NAME_APP_INFO_MAID - name registered to the Boss for AppInfoMaid
    private final static String NAME_APP_INFO_MAID = "AppInfoMaid";

    //mAppSelectAdapter - list adapter of demo applications
    ListAdapter mAppSelectAdapter;

/**************************************************************************************************/
/**
 * void initHouseKeeping() - initializes the Maids that will take care of the fragments that
 * will be added to MainKeepers' Activity.
 *
 * HouseKeeping:
 *      AppSelectMaid
 *      AppInfoMaid
 */
    private void initHouseKeeping(){
        //initialize AppSelect Maid
        initAppSelectMaid();

        //TODO - need to create initialization methods for AppInfoMaid
        initAppInfoMaid();
    }

/**
 * void initAppSelectMaid() - initialize AppSelectMaid and register Maid to Boss
 */
    private void initAppSelectMaid(){
        //initialize and register AppSelectMaid
        mAppSelectMaid = new AppSelectMaid(this);
        mBoss.registerMaid(NAME_APP_SELECT_MAID, mAppSelectMaid);
    }

    private void createAppSelectAdapter(){
        //initialize and send ListAdapter to Maid
        mAppSelectAdapter = initAppSelectAdapter(mBoss.getModel(),
                mMapFragmentType.get(FRAG_APP_SELECT));
        mAppSelectMaid.setListAdapter(mAppSelectAdapter);
    }

/**
 * void intitAppInfoMaid() - initialize AppInfoMaid and register Maid to Boss
 */
    private void initAppInfoMaid(){
        //initialize and register AppInfoMaid
        //mAppInfoMaid = new AppInfoMaid(this);
        //mBoss.registerMaid(NAME_APP_INFO_MAID, mAppInfoMaid);

    }

/**************************************************************************************************/
/**
 * TitleSimpleAdapter - ListAdapter which only displays the name of the Application Demo. Adapter
 * uses the following resource ids:
 *      LAYOUT_ITEM_TITLESIMPLE
 *      ITEM_TITLE_CHILD_TITLE_VIEW
 *
 * TitleIconAdapter - ListAdapter which displays the name and the icon of the Application Demo.
 * Adapter uses the following resource ids:
 *      LAYOUT_ITEM_TITLEICON
 *      ITEM_TITLE_CHILD_TITLE_VIEW
 *      ITEM_TITLE_CHILD_ICON_VIEW
 */
/**************************************************************************************************/
    //LAYOUT_ITEM_TITLESIMPLE - item layout id used by TitleSimpleAdapter
    private final static int LAYOUT_ITEM_TITLESIMPLE = R.layout.item_titlesimple;
    //LAYOUT_ITEM_TITLEICON - item layout id used by TitleIconAdapter
    private final static int LAYOUT_ITEM_TITLEICON = R.layout.item_titleicon;

    //Child View ids from Item Layouts above
    private final static int ITEM_TITLE_CHILD_TITLE_VIEW = R.id.item_title;
    private final static int ITEM_TITLE_CHILD_ICON_VIEW = R.id.item_icon;

/**************************************************************************************************/

    /**
 * ListAdapter initializeAppListAdapter() initializes the ListAdapter that will be used by the
 * AppList fragment. Currently it will initialize a Title type adapter or an Icon type adapter.
 * @return ListAdapter - will return a reference to the ListAdapter to be consumed
 */
    public ListAdapter initAppSelectAdapter(AppDemoModel model, int selectType){
        Log.d("SimpleListFragment", "MainKeeper.initAppSelectAdapter: " + selectType);

        //ListAdapter variable to be return
        ListAdapter adapter;

        //check type of fragment being used by the AppList Maid
        if(selectType == SELECT_TYPE_LIST_SIMPLE){
            //ListView fragment, initialize a Title type adapter using the data model for the apps
            adapter = initTitleAdapter(model, LAYOUT_ITEM_TITLESIMPLE, ITEM_TITLE_CHILD_TITLE_VIEW);
        }
        else if(selectType == SELECT_TYPE_LIST_ICON){
            //GridView fragment, initialize an Icon type adapter using the data model for the apps
            adapter = initTitleIconAdapter(model, LAYOUT_ITEM_TITLEICON,
                    ITEM_TITLE_CHILD_TITLE_VIEW, ITEM_TITLE_CHILD_ICON_VIEW);
        }
        else{
            adapter = initTitleAdapter(model, LAYOUT_ITEM_TITLESIMPLE, ITEM_TITLE_CHILD_TITLE_VIEW);
        }

        return adapter;
    }

/**
 * ListAdapter initTitleAdapter(model) used to initialize a Title type adapter typically used with
 * a ListView. Uses the AppDemo data model
 * @param model - data model for the application demos demoed in this app
 * @return ListAdapter - will return a reference to the Title adapter create with the data model
 */
    private ListAdapter initTitleAdapter(AppDemoModel model, int layoutId, int titleViewId){
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
                layoutId, titleViewId);
        //TODO - setOnClickListener
        //adapter.setOnClickListener(mAppListOnClickListener);

        return adapter;
    }

    private ListAdapter initTitleIconAdapter(AppDemoModel model, int layoutId, int titleViewId,
                                             int iconViewId){
        ArrayList<TitleIconItem> itemList = new ArrayList<>();

        int count = model.getAppCount();

        for(int i = 0; i < count; i++){
            TitleIconItem item = new TitleIconItem(model.getApp(i).getName(),
                    model.getApp(i).getIconId());
            itemList.add(item);
        }

        TitleIconAdapter adapter = new TitleIconAdapter(mActivityContext, itemList,
                layoutId, titleViewId, iconViewId);

        return adapter;
    }

    private ListAdapter initIconAdapter(AppDemoModel model, int layoutId, int childViewId){
        Log.d("SimpleListFragment", "MainKeeper.initIconAdapter");
        return new ImageAdapter(mActivityContext);
    }

    public ListAdapter getListAdapter(){
        /*if(mListAdapter == null){
            return initAppListAdapter(mBoss.getModel());
        }*/

        return null;
    }


/**************************************************************************************************/
/**
 * Variables used for MainActivity
 */
    //Activity Layout Id
    private int LAYOUT_MAIN = R.layout.activity_main;
    //Fragment Container Id found in activity_main.xml layout file
    private int LAYOUT_MAIN_CONTAINER = R.id.fragment_container;

    //Toolbar Id found in toolbar.xml layout file
    private int TOOLBAR_MAIN = R.id.toolbar;
    //Menu for Toolbar found in menu_main menu file
    private int MENU_MAIN = R.menu.menu_main;
    //Item id from Menu
    private int MENU_ITEM01 = R.id.action_bar_item01;
    private int MENU_ITEM02 = R.id.action_bar_item02;
    private int MENU_ITEM03 = R.id.action_bar_item03;

    //FloatingActionButton Id found in float_button.xml layout file
    private int FLOATING_ACTION_BUTTON = R.id.fab;

    //OnClickListener used with FloatingActionButton
    private View.OnClickListener mFABOnClickListener =
        new View.OnClickListener() {
            @Override
            public void onClick(View view){
                onFABOnClickListener(view);
        }
    };
/**************************************************************************************************/

/**************************************************************************************************/
/**
 * Implements MainActivity.Bridge Methods:
 *      int getActivityLayoutId();
 *      int getFragmentContainerId();
 *      int getToolbarId();
 *      int getMenuId();
 *      int getFloatingActionButtonId();
 *
 *      int getFragmentType(String);
 *      void setFragmentType(String, int);
 *
 *      OnClickListener getFABOnClickListener();
 *      void onOptionsItemSelected(MenuItem item)
 *
 */
/**************************************************************************************************/
/**
 * int getActivityLayoutId() - get layout id used for Main Activity
 * @return int - layout id value
 */
    public int getActivityLayoutId(){
        return LAYOUT_MAIN;
    }

/**
 * int getFragmentContainerId() - get fragment container id, a framelayout found inside the xml
 * layout file used by the Activity to be used as a container to load fragments
 * @return int - framelayout id,
 */
    public int getFragmentContainerId(){
        return LAYOUT_MAIN_CONTAINER;
    }

/**
 * int getToolbarId() - get the toolbar id, a toolbar object found inside the xml layout file used
 * by the Activity.
 * @return int - toolbar id
 */
    public int getToolbarId(){
        return TOOLBAR_MAIN;
    }

/**
 * int getMenuId - get Id of menu file from res/menu to be used in the Toolbar
 * @return int - xml menu id
 */
    public int getMenuId(){
        return MENU_MAIN;
    }

/**
 * int getFloatingActionButtonId() - get the floating action button id found inside the xml
 * layoutfile used by the Activity
 * @return int - floating action button id
 */
    public int getFloatingActionButtonId(){
        return FLOATING_ACTION_BUTTON;
    }

/**
 * void setFragmentType(String, int) - put the key and fragment type into a hashmap
 * @param fragmentType - variation of the fragment type to display
 */
    public void setFragmentType(String key, int fragmentType){
        Log.d("Simple", "MainKeeper.setFragmentType: " + fragmentType);
        //put fragment type and the variation of that time to a hashmap
        mMapFragmentType.put(key, fragmentType);
    }

/**
 * int getFragmentType(String) - returns the variation of a particular fragment type
 * @param key - fragment type (list or info)
 * @return int - variation of the fragment tpe
 */
    public int getFragmentType(String key){
        return mMapFragmentType.get(key);
    }

/**
 * void prepareFragment - createFragment to be displayed
 * @param shouldAdd - used to determine if it should be added to the Fragment manager
 */
    public void prepareFragment(Boolean shouldAdd){
        Log.d("Simple", "Keeper.createFragment");
        //create the ListAdapter to be displayed by the AppSelect Fragment
        createAppSelectAdapter();

        //initialize the AppSelectFragment
        mAppSelectFrag = initFragment(mMapFragmentType.get(FRAG_APP_SELECT));

        //check if Fragment needs to be added to the Fragment manager
        if(shouldAdd){
            //add fragment to manager
            addFragmentToManager(mAppSelectFrag);
        }

    }

/**
 * OnClickListener getFABOnClickListener() - get the OnClick Listener for the Floating Action
 * Button object.
 * @return - View.OnClickListener object
 */
    public View.OnClickListener getFABOnClickListener(){
        return mFABOnClickListener;
    }

/**
 * void onOptionsItemSelected(MenuItem) - listens for an onOptionsItemSelected event from the
 * menu list contained in the toolbar view
 * @param item - menu item selected in the toolbar
 */
    public void onOptionsItemSelected(MenuItem item){
        //get id of item selected from ActionBar
        int id = item.getItemId();

        //create variable to hold fragment type selected
        int fragmentType;

        //get fragment type selected
        if (id == MENU_ITEM01) {
            //simple fragment list display
            fragmentType = SELECT_TYPE_LIST_SIMPLE;
        }
        else if (id == MENU_ITEM02) {
            //fragment list display with icons
            fragmentType = SELECT_TYPE_LIST_ICON;
        }
        else if (id == MENU_ITEM03) {
            //fragment grid display of icons
            fragmentType = SELECT_TYPE_GRID_SIMPLE;
        }
        else {
            fragmentType = SELECT_TYPE_LIST_SIMPLE;
        }

        //check if selected display is different from current display
        if(mMapFragmentType.get(FRAG_APP_SELECT) != fragmentType){
            //fragment display selected is different from current display, save new fragment id
            mMapFragmentType.put(FRAG_APP_SELECT, fragmentType);

            replaceFragmentInManager();
        }

    }

/**
 * void onFABOnClickListener(View) - listens for an onClick event happening with the Floating
 * Action Button object.
 * @param view - floating action button view that registered the onClick event
 */
    private void onFABOnClickListener(View view){
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();

    }

/**************************************************************************************************/

/**************************************************************************************************/
/**
 * Variables used for initializing Fragments
 */
    //simple list fragment layout
    private final static int LAYOUT_LIST_SIMPLE = R.layout.list_fragment;

    //simple grid fragment layout
    private final static int LAYOUT_GRID_SIMPLE = R.layout.grid_fragment;
    //child view in grid fragment layout, gridView child
    private final static int GRID_CHILD_GRID_VIEW = R.id.gridview;

/**************************************************************************************************/

/**************************************************************************************************/
/**
 * Fragment initFragment(int) - initialize the type of Fragment being requested.
 * @param fragmentType - fragment type
 * @return Fragment - returns an initialized and ready fragment
 */
    private Fragment initFragment(int fragmentType){
        Fragment fragment;

        //check fragment type being requested
        if(fragmentType == SELECT_TYPE_LIST_SIMPLE || fragmentType == SELECT_TYPE_LIST_ICON) {
            //create Fragment with a ListView, class extends ListFragment
            fragment = new SimpleListFragment();

            //set layout id to use to inflate fragment
            ((SimpleListFragment)fragment).setLayout(LAYOUT_LIST_SIMPLE);

            //set Maid name to fragment
            ((SimpleListFragment)fragment).setServiceName(NAME_APP_SELECT_MAID);
        }
        else if(fragmentType == SELECT_TYPE_GRID_SIMPLE){
            //create Fragment with GridView, class extends Fragment
            fragment = new SimpleGridFragment();

            //set layout id to use to inflate fragment
            ((SimpleGridFragment)fragment).setLayout(LAYOUT_GRID_SIMPLE);

            //set gridView id to be used in fragment
            ((SimpleGridFragment)fragment).setGridViewId(GRID_CHILD_GRID_VIEW);

            //setMaid name to fragment
            ((SimpleGridFragment)fragment).setServiceName(NAME_APP_SELECT_MAID);

        }
        else if(fragmentType == SELECT_TYPE_LIST_COMPLEX){
            //TODO - create fragment for TYPE_LIST_COMPLEX
            fragment = new Fragment();
        }
        else if(fragmentType == INFO_TYPE_SIMPLE){
            //TODO - create fragment for TYPE_INFO_SIMPLE
            fragment = new Fragment();
        }
        else{
            fragment = new Fragment();
        }

        return fragment;
    }

/**
 * void addFragmentToManager(Fragment) - adds fragment to FragmentManager and commit to activity
 * @param fragment - fragment object to be added
 */
    private void addFragmentToManager(Fragment fragment){
        //begin fragment transaction
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        //TODO - need to add animation for Fragment transistion

        //add fragment to the fragment container
        ft.add(LAYOUT_MAIN_CONTAINER, fragment);

        //commit fragment to activity
        ft.commit();
    }

    /**
     * void replaceFragmentInManager(int) - replaces a fragment object held by the FragmentManager
     * and commit to activity
     */
    private void replaceFragmentInManager(){
        Log.d("Simple", "MainKeeper.changeFragment");
        //prepare AppSelect fragment, do NOT add fragment to fragment manager, shouldAdd = false
        prepareFragment(false);

        //begin fragment transaction
        FragmentTransaction ft = mFragmentManager.beginTransaction();

        //replace fragment held by the FragmentManager
        ft.replace(LAYOUT_MAIN_CONTAINER, mAppSelectFrag);

        //commit fragment to activity
        ft.commit();
    }

/**************************************************************************************************/


    /**
     * void onItemClick(int) - event listener call by the fragment when an app item has been clicked
     * @param position - list position of item clicked
     */
    public void onItemClick(int position){
        Log.d("SimpleListFragment", "Maid.onListItemClick");
        //TODO - need to connect onItemClick event to Boss
    }

}
