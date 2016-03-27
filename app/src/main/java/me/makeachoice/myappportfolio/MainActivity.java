package me.makeachoice.myappportfolio;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import me.makeachoice.myappportfolio.controller.Boss;
import me.makeachoice.myappportfolio.controller.housekeeper.MainKeeper;

/**
 * MainActivity is the main activity of this application.
 */
public class MainActivity extends AppCompatActivity {

    //HouseKeeper class that handles the initialization of the MainActivity and Fragments as well
    //as the communication between UI and the Boss
    private MainKeeper mHouseKeeper;

    //Toolbar object used by the MainActivity
    private Toolbar mToolbar;
    //Floating Action Button used by the MainActivity
    private FloatingActionButton mFloatButton;

    public interface Bridge{
        //Interface are methods the Maid has to implement but it is a one-way
        //communication.
        int getActivityLayoutId();
        int getFragmentContainerId();
        int getToolbarId();
        int getFloatingActionButtonId();

        void onOptionsItemSelected(MenuItem item);
        View.OnClickListener getFABOnClickListener();
    }



/**************************************************************************************************/
/**
 * void onCreate() is called when the Activity is first being created or during a configuration
 * change (i.e. orientation change). Creates Boss and HouseKeeper class, inflates the Activity
 * layout and the toolbar and floating action button (if any).
 *
 * If onCreate is being called because of a configuration change, savedInstanceState will not be
 * null.
 * @param savedInstanceState - saved instance states saved before the fragment was detached.
 */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("SimpleListFragment", "MainActivity.onCreate");
        //start Boss
        final Boss mBoss = (Boss)getApplicationContext();
        //register Activity context with Boss
        mBoss.setActivityContext(this);

        //start HouseKeeper for this Activity
        mHouseKeeper = new MainKeeper(mBoss, this);

        //Note setContent must happen before toolbar
        setContentView(mHouseKeeper.getActivityLayoutId());

        // Check that the activity is using the layout version with
        // the fragment_container FrameLayout
        if (findViewById(mHouseKeeper.getFragmentContainerId()) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if(savedInstanceState != null){
                //Fragment is being reconstituted, need to recreate toolbar and float button
                initToolbar();
                initFloatButton();
                return;
            }

            // Add the fragment to the 'fragment_container' FrameLayout
            mHouseKeeper.setFragmentManager(getSupportFragmentManager());
        }

        //Create toolbar with creation of Activity
        initToolbar();

        //Create floating action button for Activity
        initFloatButton();
    }
/**************************************************************************************************/

/**************************************************************************************************/
/**
 * boolean onCreateOptionsMenu(Menu) is called if an action bar is present
 * @param menu - action bar menu object
 * @return boolean - by default returns true
 */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d("SimpleListFragment", "Main.onCreateOptionsMenu");
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(mHouseKeeper.getMenuId(), menu);
        return true;
    }

/**
 * boolean onOptionItemSelected(MenuItem) is called when a menu item is clicked on by the user
 * @param item - menu item clicked on
 * @return boolean - by default returns false
 */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //HouseKeeper handles menu item click event
        mHouseKeeper.onOptionsItemSelected(item);


        return super.onOptionsItemSelected(item);
    }
/**************************************************************************************************/

/**************************************************************************************************/
/**
 * void initToolbar() inflates the toolbar from the layout and then sets it into the Activity.
 */
    private void initToolbar(){
        //check if toolbar is already inflated
        if(mToolbar == null){
            //if null, inflate the toolbar
            mToolbar = (Toolbar)findViewById(mHouseKeeper.getToolbarId());
            //set support for toolbar, onCreateOptionsMenu() will be called
            setSupportActionBar(mToolbar);
        }
    }

/**
 * void initFloatButton() inflates the floating action button layout and sets Event Listeners
 */
    private void initFloatButton(){

        if(mFloatButton == null){
            mFloatButton = (FloatingActionButton)findViewById
                    (mHouseKeeper.getFloatingActionButtonId());
            mFloatButton.setOnClickListener(mHouseKeeper.getFABOnClickListener());
        }
    }
/**************************************************************************************************/
}
