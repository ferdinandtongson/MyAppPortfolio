package me.makeachoice.myappportfolio.fragment;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import me.makeachoice.myappportfolio.R;

/**
 * SimpleListFragment - a simple list view fragment
 */
public class SimpleListFragment extends ListFragment {
    /*
        Fragment subclasses require an empty default constructor. If you don't provide one but
        specify a non-empty constructor, Lint will give you an error.
        Android may destroy and later re-create an activity and all its associated fragments when
        the app goes into the background. When the activity comes back, its FragmentManager starts
        re-creating fragments by using the empty default constructor. If it cannot find one, you
        get an exception
     */
    public static SimpleListFragment newInstance(){
        return new SimpleListFragment();
    }

    public SimpleListFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment, container, false);

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


}
