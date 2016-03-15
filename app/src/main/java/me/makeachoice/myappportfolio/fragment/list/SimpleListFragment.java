package me.makeachoice.myappportfolio.fragment.list;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import me.makeachoice.myappportfolio.R;
import me.makeachoice.myappportfolio.fragment.MyFragmentInterface;


/**
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
            Log.d(DEBUG_STR, "     bundle NOT null");
            mLayoutId = savedInstanceState.getInt(SimpleListFragmentContract.Value.BUNDLE_KEY_LAYOUT_PRT);
        }
        else{
            Log.d(DEBUG_STR, "     bundle null");
            Toast.makeText(this.getContext(), "SimpleListFragment.onCreate - bundle Null",
                    Toast.LENGTH_LONG).show();
            //saveInstanceState.putInt()
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.d(DEBUG_STR, "onCreateView");
        if(savedInstanceState != null){
            mLayoutId = savedInstanceState.getInt(SimpleListFragmentContract.Value.BUNDLE_KEY_LAYOUT_PRT);
            Log.d(DEBUG_STR, "     layout: " + mLayoutId);

        }
        Log.d(DEBUG_STR, "     container: " + container.toString());
        View view = inflater.inflate(mLayoutId, container, false);


        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        /*String[] items = new String[3];
        items[0] = "test1";
        items[1] = "test2";
        items[2] = "test3";*/
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, items);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(), R.array.app_planets, android.R.layout.simple_list_item_1);
        setListAdapter(adapter);
    }

    private int mLayoutId;
    public void setLayout(int aId){
        Log.d(DEBUG_STR, "SimpleListFragment.setLayout: " + aId);
        mLayoutId = aId;
    }

    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        Log.d(DEBUG_STR, "SimpleListFragment - save instance state");
        Log.d(DEBUG_STR, "     layout: " + mLayoutId);
        outState.putInt(SimpleListFragmentContract.Value.BUNDLE_KEY_LAYOUT_PRT, mLayoutId);
    }

}
