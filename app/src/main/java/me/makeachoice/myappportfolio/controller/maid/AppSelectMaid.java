package me.makeachoice.myappportfolio.controller.maid;

import android.util.Log;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;

import me.makeachoice.myappportfolio.fragment.SimpleGridFragment;
import me.makeachoice.myappportfolio.fragment.list.SimpleListFragment;

/**
 * AppSelectMaid initializes and takes care of communicating with the Fragment that hold the Portfolio
 * of Apps that the user can select from.
 *
 * Its main purpose is to handle events and request from the Fragment and if the Maid cannot handle
 * a request or an event, it will pass it onto the HouseKeeper.
 *
 * The AppSelectMaid is only aware of the Fragment and the views at the fragment level. It is NOT
 * aware of the view above it (the Activity containing the Fragment) or below it (the View Items in
 * a ListView or GridView, for example).
 *
 * Variables from Maid:
 *      HouseKeeper mHouseKeeper
 *
 * Implements SimpleListFragment.Bridge
 *      ListAdapter getListAdapter() - Fragments' access to the ListAdapter
 *      void setListAdapter(ListAdapter) - receives the ListAdapter from Boss to be displayed
 *      void onItemClick(...) - list item click event
 */
public class AppSelectMaid extends Maid implements SimpleListFragment.Bridge,
        SimpleGridFragment.Bridge{

    private ListAdapter mListAdapter;

    private Bridge mBridge;
    public AppSelectMaid(Bridge bridge){
        mBridge = bridge;

    }

    public interface Bridge{
        //Interface methods needed to be implemented by the instantiating class
        ListAdapter getListAdapter();
    }


/**************************************************************************************************/

/**************************************************************************************************/
/**
 * SimpleListFragment.Bridge interface implementation.
 *      ListAdapter getListAdapter() - Fragments' access to the ListAdapter
 *      void setListAdapter() - used to ensure that the Maid class uses the setListAdapter method
 *      void onItemClick() - list item click event
 */
/**************************************************************************************************/
/**
 * ListAdapter getListAdapter() - Fragment can get access to the ListAdapter
 * @return ListAdapter - returns a list adapter created by the Boss
 */
    public ListAdapter getListAdapter(){
        Log.d("SimpleListFragment", "AppSelectMaid.getListAdapter");
        //checks if ListAdapter is null
        if(mListAdapter == null){
            Log.d("SimpleListFragment", "     adapter null");
            //if null, request ListAdapter from Boss
            mListAdapter = mBridge.getListAdapter();
        }

        Log.d("SimpleListFragment", "     adapter: " + mListAdapter.toString());
        //return list adapter to calling fragment
        return mListAdapter;
    }

/**
 * void setListAdapter(ListAdapter) - set the ListAdapter the fragment is going to use, if any.
 * @param adapter - ListAdapter to be consumed by the fragment
 */
    public void setListAdapter(ListAdapter adapter){
        Log.d("SimpleListFragment", "AppSelectMaid.setListAdapter");
        Log.d("SimpleListFragment", "     adapter: " + adapter.toString());
        mListAdapter = adapter;
    }


/**
 * void onItemClick(int) - event listener call by the fragment when an app item has been clicked
 * @param position - list position of item clicked
 */
    public void onItemClick(ListView l, View v, int position, long id){
        Log.d("SimpleListFragment", "Maid.onListItemClick");
        //TODO - need to connect onItemClick event to Boss
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
