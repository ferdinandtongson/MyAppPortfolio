package me.makeachoice.myappportfolio;

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
import android.widget.Toast;



import me.makeachoice.myappportfolio.controller.Boss;
import me.makeachoice.myappportfolio.controller.housekeeper.MainKeeper;
import me.makeachoice.myappportfolio.fragment.list.SimpleListFragment;


public class MainActivity extends AppCompatActivity {

    private OrientationEventListener myOrientationEventListener;
    private MainKeeper mHouseKeeper;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //start Boss
        final Boss mBoss = (Boss)getApplicationContext();
        mBoss.setActivityContext(this);

        mHouseKeeper = new MainKeeper(mBoss, this);

        //Note setContent must happen before toolbar
        setContentView(mHouseKeeper.getActivityLayout());

        // Check that the activity is using the layout version with
        // the fragment_container FrameLayout
        if (findViewById(mHouseKeeper.getActivityContainer()) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            // Add the fragment to the 'fragment_container' FrameLayout
            mHouseKeeper.setFragmentManager(getSupportFragmentManager());
        }

        Log.d("SimpleListFragment", "Main.onCreate - create toolbar");
        initToolbar();

        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);
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
        super.onStart();
        Log.d("SimpleListFragment", "Main.onStart");
        if(mToolbar == null){
            initToolbar();
        }
    }










    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d("SimpleListFragment", "Main.onCreateOptionsMenu");
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(mHouseKeeper.getMenuId(), menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        mHouseKeeper.onOptionsItemSelected(item);

        return super.onOptionsItemSelected(item);
    }


    private void initToolbar(){
        mToolbar = (Toolbar)findViewById(mHouseKeeper.getToolbarId());
        setSupportActionBar(mToolbar);
    }

}
