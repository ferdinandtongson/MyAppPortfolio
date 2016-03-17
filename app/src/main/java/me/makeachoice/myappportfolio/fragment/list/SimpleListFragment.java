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

import java.util.ArrayList;

import me.makeachoice.myappportfolio.MainActivity;
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

/**************************************************************************************************/

    private static String DEBUG = "SimpleListFragment";

/**************************************************************************************************/
/** onCreate(...) is called to do initial creation of a fragment. This is called after
 * onAttach(...) and before onCreateView(...)
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

        //Retain Instance true will retain the fragment instance across Activity re-creation
        //setRetainInstance(true);
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
    View _rootView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.d(DEBUG, "onCreateView");
        if(savedInstanceState != null){
            Log.d(DEBUG, " bundle not Null");
            mLayoutId = savedInstanceState.getInt(SimpleListFragmentContract.Value.BUNDLE_LAYOUT);
            Log.d(DEBUG, "     bundle: " + mLayoutId);
        }
        else{
            Log.d(DEBUG, "     bundle null");
        }

        if(_rootView == null){
            Log.d(DEBUG, "     rootView = null");
            _rootView = inflater.inflate(mLayoutId, container, false);
        }

        return _rootView;
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        Log.d(DEBUG, "onAttach");
        mActivity = (MainActivity)getActivity();
        Log.d(DEBUG, "     activity: " + mActivity.toString());
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(DEBUG, "onActivityCreated");
        Log.d(DEBUG,"     activity: " + mActivity.toString());
        if(savedInstanceState != null){
            Log.d(DEBUG, "     bundle NOT null");
        }
        else{
            Log.d(DEBUG, "     bundle null");
        }
        /*String[] items = new String[3];
        items[0] = "test1";
        items[1] = "test2";
        items[2] = "test3";*/
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, items);
        /*ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.app_planets, android.R.layout.simple_list_item_1);*/
        // references to our images

        ListAdapter list = mActivity.getListAdapter();
        Log.d(DEBUG, "     in Frag - list: " + list.toString());
        this.setListAdapter(list);

    }

    @Override
    public void onViewStateRestored(Bundle inState){
        super.onViewStateRestored(inState);
        Log.d(DEBUG, "onViewStateRestored");
    }

    @Override
    public void onStart(){
        super.onStart();
        Log.d(DEBUG, "onStart");
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.d(DEBUG, "onResume");
    }

    @Override
    public void onPause(){
        super.onPause();
        Log.d(DEBUG, "onPause");
    }

    @Override
    public void onStop(){
        super.onStop();
        Log.d(DEBUG, "onStop");
    }

    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        Log.d(DEBUG, "onSaveInstanceState");
        Log.d(DEBUG, "     layout: " + mLayoutId);
        outState.putInt(SimpleListFragmentContract.Value.BUNDLE_LAYOUT, mLayoutId);

    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        Log.d(DEBUG, "onDestroyView");
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d(DEBUG, "onDestroy");
    }

    @Override
    public void onDetach(){
        super.onDetach();
        Log.d(DEBUG, "onDetach");
    }

    private int mLayoutId;

    public void setLayout(int aId){
        Log.d(DEBUG, "SimpleListFragment.setLayout: " + aId);
        //mAdapter = adapter;
        mLayoutId = aId;
    }

    private MainActivity mActivity;


}
