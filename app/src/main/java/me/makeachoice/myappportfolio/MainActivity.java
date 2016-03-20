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
import me.makeachoice.myappportfolio.fragment.list.SimpleListFragment;


public class MainActivity extends AppCompatActivity {

    private OrientationEventListener myOrientationEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //start Boss
        final Boss mBoss = (Boss)getApplicationContext();
        mBoss.setActivityContext(this);

        //Note setContent must happen before toolbar
        setContentView(mBoss.getLayout(mBoss.KEY_MAIN_SCREEN));

        // Check that the activity is using the layout version with
        // the fragment_container FrameLayout
        if (findViewById(mBoss.getLayout(mBoss.KEY_MAIN_CONTAINER)) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }



            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(mBoss.getLayout(mBoss.KEY_MAIN_CONTAINER), mBoss.getListFragment()).commit();
        }

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



}
