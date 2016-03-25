package me.makeachoice.myappportfolio.controller.maid;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.ListAdapter;

import me.makeachoice.myappportfolio.R;
import me.makeachoice.myappportfolio.controller.Boss;
import me.makeachoice.myappportfolio.fragment.SimpleGridFragment;
import me.makeachoice.myappportfolio.fragment.list.SimpleListFragment;

/**
 * AppSelectMaid initializes and takes care of the Fragment details that hold the Portfolio of
 * Apps that the user can select from. The Maid also takes care of the communication between the
 * Fragment and the Boss.
 *
 * The AppSelectMaid is only aware of the Fragment and the views at the fragment level. It is NOT
 * aware of the view above it (the Activity containing the Fragment) or below it (the View Items in
 * a ListView or GridView, for example).
 *
 * Variables from Maid:
 *      Boss mBoss
 *
 * Implements Boss.AppListBridge Methods:
 *      Fragment getFragment( ) - returns fragment being managed by the Maid
 *      void setFragmentType(int) - receives the Fragment type from the Boss to be displayed
 *      void setListAdapter(ListAdapter) - receives the ListAdapter from Boss to be displayed
 *
 * Implements SimpleListFragment.Bridge
 *      int getLayoutId() - Fragments' access to the layout id used by the Fragment
 *      ListAdapter getListAdapter() - Fragments' access to the ListAdapter
 *      void onItemClick() - list item click event
 */
public class AppListMaid extends Maid implements SimpleListFragment.Bridge,
        SimpleGridFragment.Bridge, Boss.AppListBridge{

    public final static String MAID_NAME = "app_list_maid";
    public final static int TYPE_LIST_FRAGMENT = R.layout.list_fragment;
    public final static int TYPE_GRID_FRAGMENT = R.layout.grid_fragment;
    public final static int GRID_FRAG_CHILD_GRID = R.id.gridview;

    private ListAdapter mListAdapter;
    private Fragment mFragment;
    private int mFragmentType = -1;


    public AppListMaid(Boss boss, int fragmentType){
        mBoss = boss;
        mFragmentType = fragmentType;
        mFragment = initFragment(fragmentType);
    }

/**************************************************************************************************/
/**
 * Fragment initFragment(int) - initialize the type of Fragment being requested.
 * @param fragmentType - fragment type
 * @return Fragment - returns an initialized and ready fragment
 */
    private Fragment initFragment(int fragmentType){
        Fragment fragment;

        //check fragment type being requested
        if(fragmentType == TYPE_LIST_FRAGMENT){
            //create Fragment with a ListView, class extends ListFragment
            fragment = new SimpleListFragment();

            //set layout id to use to inflate fragment
            ((SimpleListFragment)fragment).setLayout(fragmentType);

            //set communication bridge between Maid and fragment
            ((SimpleListFragment)fragment).setMaidName(MAID_NAME);
        }
        else if(fragmentType == TYPE_GRID_FRAGMENT){
            //create Fragment with GridView, class extends Fragment
            fragment = new SimpleGridFragment();

            //set layout id to use to inflate fragment
            ((SimpleGridFragment)fragment).setLayout(fragmentType);

            //set gridView id to be used in fragment
            ((SimpleGridFragment)fragment).setGridViewId(GRID_FRAG_CHILD_GRID);

            //set communication bridge between Maid and fragment
            ((SimpleGridFragment)fragment).setMaidName(MAID_NAME);
        }
        else{
            fragment = new Fragment();
        }

        return fragment;
    }

/**************************************************************************************************/
/**
 * Boss.AppListBridge interface implementation.
 *      Fragment getFragment( ) - returns fragment being managed by the Maid
 *      void setFragmentType(int) - receives the Fragment type from the Boss to be displayed
 *      void setListAdapter(ListAdapter) - receives the ListAdapter from Boss to be displayed
 */
/**************************************************************************************************/
/**
 * Fragment getFragment() - Boss can get access to the fragment created by the Maid
 * @return Fragment - returns a fragment to be displayed
 */
    public Fragment getFragment(){return mFragment;}

/**
 * void setFragment(int) - sets the Fragment type to be used, valid values are class defined static
 * int values; TYPE_XXX_FRAGMENT
 * @param fragmentType - int value for fragment type
 */
    public void setFragmentType(int fragmentType){
        //checks to see if the fragment type is the same or if the fragment exists
        if(mFragmentType != fragmentType || mFragment == null){
            //if the fragment type being set is different from previous or the fragment is null
            //set fragment type to the new type of fragment
            mFragmentType = fragmentType;

            //initialize new fragment type
            mFragment = initFragment(fragmentType);
        }
    }

/**
 * void setListAdapter(ListAdapter) - set the ListAdapter the fragment is going to use, if any.
 * @param adapter - ListAdapter to be consumed by the fragment
 */
    public void setListAdapter(ListAdapter adapter){
        Log.d("SimpleListFragment", "AppListMaid.setListAdapter");
        Log.d("SimpleListFragment", "     adapter: " + adapter.toString());
        mListAdapter = adapter;
    }

/**************************************************************************************************/

/**************************************************************************************************/
/**
 * SimpleListFragment.Bridge interface implementation.
 *      ListAdapter getListAdapter() - Fragments' access to the ListAdapter
 *      void onItemClick() - list item click event
 */
/**************************************************************************************************/
/**
 * ListAdapter getListAdapter() - Fragment can get access to the ListAdapter
 * @return ListAdapter - returns a list adapter created by the Boss
 */
    public ListAdapter getListAdapter(){
        Log.d("SimpleListFragment", "AppListMaid.getListAdapter");
        //checks if ListAdapter is null
        if(mListAdapter == null){
            Log.d("SimpleListFragment", "     adapter null");
            //if null, request ListAdapter from Boss
            mListAdapter = mBoss.getAppListAdapter();
        }

        Log.d("SimpleListFragment", "     adapter: " + mListAdapter.toString());
        //return list adapter to calling fragment
        return mListAdapter;
    }

/**
 * Bridge getBridge() - sends Maid service using the Bridge interface to calling class
 * @return Bridge - Maid class casted as Bridge
 */
    public Maid getBridge(){
        return this;
    }


/**
 * void onItemClick(int) - event listener call by the fragment when an app item has been clicked
 * @param position - list position of item clicked
 */
    public void onItemClick(int position){
        Log.d("SimpleListFragment", "Maid.onListItemClick");
        //TODO - need to connect onItemClick event to Boss
    }

/**************************************************************************************************/

}
