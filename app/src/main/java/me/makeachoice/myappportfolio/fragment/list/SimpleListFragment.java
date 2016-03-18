package me.makeachoice.myappportfolio.fragment.list;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import me.makeachoice.myappportfolio.fragment.MyFragmentInterface;

/**************************************************************************************************/
/*
 * SimpleListFragment - a simple list view fragment
 */
public class SimpleListFragment extends ListFragment implements MyFragmentInterface {
/**
 *  Fragment subclasses require an empty default constructor. If you don't provide one but
 *  specify a non-empty constructor, Lint will give you an error.
 *  Android may destroy and later re-create an activity and all its associated fragments when
 *  the app goes into the background. When the activity comes back, its FragmentManager starts
 *  re-creating fragments by using the empty default constructor. If it cannot find one, you
 *  get an exception
 */
    public static SimpleListFragment newInstance(){
        return new SimpleListFragment();
    }

    public SimpleListFragment(){}


    //Container Activity must implement this interface
    public interface OnSimpleListFragmentListener{
        void onSimpleListItemClick(int position);
        ListAdapter getListAdapter();
    }

/**************************************************************************************************/

    private static String DEBUG = "SimpleListFragment";
    private OnSimpleListFragmentListener mCallback;
    private int mLayoutId;

/**************************************************************************************************/
/**
 * onAttach(...) called once the fragment is associated with its activity. Fragments are usually
 * created in the Activities onCreate( ) method.
 *
 * This is where you can check if the container activity has implemented a bridge interface. If
 * not, it throws an exception. The bridge interface acts as a bridge to communicate with the
 * implementing activity.
 *
 * The reference to the bridge needs to be removed when onDetach is called to prevent a leak.
 * @param context - activity context
 */
    @Override
    public void onAttach(Context context){
        super.onAttach(context);

        Activity activity = getActivity();
        try{
            mCallback = (OnSimpleListFragmentListener)activity;
        }catch(ClassCastException e){
            throw new ClassCastException(activity.toString() +
                " must implement OnSimpleListListener");
        }

    }

/** onCreate(...) is called to do initial creation of a fragment. This is called after
 * onAttach(...)
 *
 * You should initialize essential components of the fragment that you want to retain when
 * the fragment is paused or stopped, then resumed.
 *
 * Note that this can be called while the fragment's activity is still in the process of being
 * created. As such, you can not rely on things like the activity's content view hierarchy being
 * initialized at this point; use onActivityCreated(...)
 */
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Log.d(DEBUG, "onCreate");

        if (savedInstanceState != null){
            mLayoutId = savedInstanceState.getInt(SimpleListFragmentContract.Value.BUNDLE_LAYOUT);
        }
    }

/** onCreateView(...) is called when it's time for the fragment to draw its UI for the first
 * time. To draw a UI for your fragment, you must return a View from this method that is the
 * root of your fragment's layout. You can return null if the fragment does not provide a UI.
 *
 * This is called between onCreate(...) and onActivityCreated(...). If you return a View from
 * here, you will later be called in onDestroyView() when the view is being released.
 */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(DEBUG, "onCreateView");

        //check if bundle has been sent/saved
        if(savedInstanceState != null){
            //get layout id to inflate root view
            mLayoutId = savedInstanceState.getInt(SimpleListFragmentContract.Value.BUNDLE_LAYOUT);
        }

        //create and return fragment layout view from file found in res/layout/xxx.xml,
        // use R.layout.xxx (mLayoutId)
        return inflater.inflate(mLayoutId, container, false);
    }

/**
 * onActivityCreated(...) is called when the activity the fragment is attached to completed its
 * own Activity.onCreate( )
 * @param savedInstanceState - bundle object containing saved instance states
 */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(DEBUG, "onActivityCreated");

        //create the list by setting the list adapter
        setListAdapter(mCallback.getListAdapter());

    }

/**
 * OnViewStateRestored(...) tells the fragment that all of the saved state of its view hierarchy
 * has been restored. Basically, the fragment is ready to be seen.
 * @param savedInstanceState - bundle object containing saved instance states
 */
    @Override
    public void onViewStateRestored(Bundle savedInstanceState){
        super.onViewStateRestored(savedInstanceState);
        Log.d(DEBUG, "onViewStateRestored");
    }

/**
 * onStart( ) makes the fragment visible to the user (based on its containing activity being
 * started.
 */
    @Override
    public void onStart(){
        super.onStart();
        Log.d(DEBUG, "onStart");
    }

/**
 * onResume( ) the fragment is visible and is interacting with the user
 */
    @Override
    public void onResume(){
        super.onResume();
        Log.d(DEBUG, "onResume");
    }

/**
 * onPause( ) is when the fragment is no longer interacting with the user either because its
 * activity is being paused or a fragment operation is modifying it in the activity
 */
    @Override
    public void onPause(){
        super.onPause();
        Log.d(DEBUG, "onPause");
    }

/**
 * onSaveInstanceState(...) is called any time before onDestroy( ) and is where you can save
 * instance states by placing them into a bundle
 * @param saveState - bundle object used to save any instance states
 */
    public void onSaveInstanceState(Bundle saveState){
        super.onSaveInstanceState(saveState);
        Log.d(DEBUG, "onSaveInstanceState");
        saveState.putInt(SimpleListFragmentContract.Value.BUNDLE_LAYOUT, mLayoutId);

    }

/**
 * onStop( ) is called when the fragment is no longer visible to the user either because its
 * activity is being stopped or a fragment operation is modifying it in the activity.
 */
    @Override
    public void onStop(){
        super.onStop();
        Log.d(DEBUG, "onStop");
    }

/**
 * onDestroyView( ) allows the fragment to clean up resources associated with its View
 */
    @Override
    public void onDestroyView(){
        super.onDestroyView();
        Log.d(DEBUG, "onDestroyView");
    }

/**
 * onDestroy( ) is called to do final clean up of the fragment's state.
 *
 */
    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d(DEBUG, "onDestroy");
    }

/**
 * onDetach( ) is called immediately prior to the fragment no longer being associated with its
 * activity. This is where you remove any bridge references created with the activity.
 */
    @Override
    public void onDetach(){
        super.onDetach();
        Log.d(DEBUG, "onDetach");
        mCallback = null;
    }

/**************************************************************************************************/
/**
 * setLayout(...) allows the layout id for the fragment to be dynamically added
 * @param id  - resource layout id
 */
    public void setLayout(int id){
        Log.d(DEBUG, "setLayout");

        //save layout id to an instance variable
        mLayoutId = id;
    }

/**
 * onListItemClick(...) is called when the user clicks on a list item
 * @param l - ListView containing view item
 * @param v - View item that was clicked by the user
 * @param position - position of the item; position is zero based (0 - x)
 * @param id - id of item clicked
 */
    @Override
    public void onListItemClick(ListView l, View v, int position, long id){

        //sends the click event across the bridge for the activity to handle
        mCallback.onSimpleListItemClick(position);
    }
/**************************************************************************************************/

}
