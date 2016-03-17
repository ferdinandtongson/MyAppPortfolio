package me.makeachoice.myappportfolio;

import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.OrientationEventListener;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.Toast;

import java.util.ArrayList;

import me.makeachoice.myappportfolio.adapter.TitleAdapter;
import me.makeachoice.myappportfolio.adapter.item.TitleItem;
import me.makeachoice.myappportfolio.fragment.list.SimpleListFragment;
import me.makeachoice.myappportfolio.fragment.list.SimpleListFragmentContract;

public class MainActivity extends AppCompatActivity {

    OrientationEventListener myOrientationEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Note setContent must happen before toolbar
        setContentView(R.layout.activity_main);

        // Check that the activity is using the layout version with
        // the fragment_container FrameLayout
        if (findViewById(R.id.fragment_container) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            // Create a new Fragment to be placed in the activity layout
            SimpleListFragment listFragment = new SimpleListFragment();

            listFragment.setLayout(R.layout.list_fragment);
            createListItems();

            Log.d("SimpleListFragment", "MainActivity.onCreate:");

            // In case this activity was started with special instructions from an
            // Intent, pass the Intent's extras to the fragment as arguments
            listFragment.setArguments(getIntent().getExtras());
            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, listFragment).commit();
        }


        /*myOrientationEventListener
                = new OrientationEventListener(this, SensorManager.SENSOR_DELAY_NORMAL){

            @Override
            public void onOrientationChanged(int arg0) {

                Toast.makeText(getApplicationContext(), "Orientation: " + String.valueOf(arg0), Toast.LENGTH_LONG).show();
            }};

        if (myOrientationEventListener.canDetectOrientation()){
            Toast.makeText(this, "Can DetectOrientation", Toast.LENGTH_SHORT).show();
            myOrientationEventListener.enable();
        }
        else{
            Toast.makeText(this, "Can't DetectOrientation", Toast.LENGTH_SHORT).show();
            finish();
        }*/



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public void onStart(){
        Log.d("SimpleListFragment", "Main.onStart");
        super.onStart();

    }

    @Override
    public void onResume(){
        super.onResume();
        Log.d("SimpleListFragment", "Main.onResume");
    }

    @Override
    public void onPause(){
        super.onPause();
        Log.d("SimpleListFragment", "Main.onPause");
    }

    @Override
    public void onStop(){
        super.onStop();
        Log.d("SimpleListFragment", "Main.onStop");
    }

    @Override
    public void onRestart(){
        super.onRestart();
        Log.d("SimpleListFragment", "Main.onRestart");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        String strAction;
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_bar_main_simple) {
            strAction = this.getString(R.string.action_bar_main_simple);
        }
        else if (id == R.id.action_bar_main_advance) {
            strAction = this.getString(R.string.action_bar_main_advance);
        }
        else if (id == R.id.action_bar_main_grid) {
            strAction = this.getString(R.string.action_bar_main_grid);
        }
        else {
            strAction = "no action selected";
        }

        Toast toast = Toast.makeText(this, strAction, Toast.LENGTH_SHORT);
        toast.show();
        return super.onOptionsItemSelected(item);
    }

    private ListAdapter mListAdapter;
    public ListAdapter getListAdapter(){
        Log.d("SimpleListFragment", "Main.getListAdapter" );

        if(mListAdapter == null){
            Log.d("SimpleListFragment", "     adapter is null");
            mListAdapter = new TitleAdapter(this, createListItems(),
                    R.layout.item_onlytitle, R.id.item_onlytext_title);
            Log.d("SimpleListFragment", "          size: " + mListAdapter.getCount());

        }
        Log.d("SimpleListFragment", "     adapter: " + mListAdapter.toString());
        return mListAdapter;
    }

    String[] mTest_array = {
            "Test1", "Test2",
            "Test3", "Test4",
            "Test5", "Test6",
            "Test7", "Test8",
            "Test9", "Test0"
    };

    private ArrayList<TitleItem> createListItems( ){

        ArrayList<TitleItem> itemList = new ArrayList<TitleItem>();
        for(int i = 0; i < mTest_array.length; i++){
            TitleItem item = new TitleItem(mTest_array[i]);
            itemList.add(item);
        }

        return itemList;
    }

}
