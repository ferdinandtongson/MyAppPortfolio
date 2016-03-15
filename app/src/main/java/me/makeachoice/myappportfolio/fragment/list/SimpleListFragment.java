package me.makeachoice.myappportfolio.fragment.list;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import me.makeachoice.myappportfolio.R;
import me.makeachoice.myappportfolio.adapter.TextAdapter;
import me.makeachoice.myappportfolio.adapter.item.OnlyText;
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

    private static String DEBUG_STR = "SimpleListFragment";

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

        Log.d(DEBUG_STR, "onCreate");
        if (savedInstanceState != null){
            mLayoutId = savedInstanceState.getInt(SimpleListFragmentContract.Value.BUNDLE_KEY_LAYOUT_PRT);
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

        Log.d(DEBUG_STR, "onCreateView");
        if(savedInstanceState != null){
            mLayoutId = savedInstanceState.getInt(SimpleListFragmentContract.Value.BUNDLE_KEY_LAYOUT_PRT);
        }

        View view = inflater.inflate(mLayoutId, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(DEBUG_STR,"SimpleListFragment.onActivityCreated");
        /*String[] items = new String[3];
        items[0] = "test1";
        items[1] = "test2";
        items[2] = "test3";*/
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, items);
        /*ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.app_planets, android.R.layout.simple_list_item_1);*/
        // references to our images
        String[] mTest_array = {
                "Test1", "Test2",
                "Test3", "Test4",
                "Test5", "Test6",
                "Test7", "Test8",
                "Test9", "Test0"
        };

        ArrayList<OnlyText> rowItems = new ArrayList<OnlyText>();
        for(int i = 0; i < mTest_array.length; i++){
            Log.d(DEBUG_STR, "     row: " + mTest_array[i]);
            OnlyText item = new OnlyText(mTest_array[i]);
            rowItems.add(item);
        }

        TextAdapter adapter = new TextAdapter(getContext(), rowItems,
                R.layout.item_onlytext, R.id.item_onlytext_title);

        setListAdapter(adapter);
    }

    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        Log.d(DEBUG_STR, "SimpleListFragment - save instance state");
        Log.d(DEBUG_STR, "     layout: " + mLayoutId);
        outState.putInt(SimpleListFragmentContract.Value.BUNDLE_KEY_LAYOUT_PRT, mLayoutId);
    }

    private int mLayoutId;
    public void setLayout(int aId){
        Log.d(DEBUG_STR, "SimpleListFragment.setLayout: " + aId);
        mLayoutId = aId;
    }
}
