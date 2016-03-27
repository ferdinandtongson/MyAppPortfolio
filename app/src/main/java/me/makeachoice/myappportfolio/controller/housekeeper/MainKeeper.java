package me.makeachoice.myappportfolio.controller.housekeeper;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import me.makeachoice.myappportfolio.MainActivity;
import me.makeachoice.myappportfolio.R;
import me.makeachoice.myappportfolio.controller.Boss;

/**
 * MainKeeper is the HouseKeeper class for MainActivity. It's primary responsibility is to
 * initializes and takes care of the MainActivity resource details.
 *
 * It also handles the loading and unloading of Fragments and manages the Maid classes responsible
 * for the upkeep of the Fragments and the communication between the Maids, Activity and Boss.
 *
 * Finally, it directly communicates with the Boss to get all the necessary data for the Views.
 */
public class MainKeeper extends HouseKeeper implements MainActivity.Bridge{
/**
 *
 * MainKeeper will be able to display the following fragments:
 *      AppList
 *      AppListIcon
 *      AppGrid
 *      AppGridInfo
 *      AppInfo
 *
 * Variables from HouseKeeper:
 *      Boss mBoss
 *
 * Implements Boss.xxxBridge Methods:
 *
 *
 * Implements MainActivity.Bridge Methods:
 *      int getActivityLayoutId();
 *      int getFragmentContainerId();
 *      int getToolbarId();
 *      int getFloatingActionButtonId();
 *
 *      void onOptionsItemSelected(MenuItem item)
 *      void setFABOnClickListener();
 *
 */
    private Context mActivityContext;
    public MainKeeper(Boss boss, Context ctx){
        mBoss = boss;
        mActivityContext = ctx;
    }

/**************************************************************************************************/
/**
 * Variables used for MainActivity
 */
    //Activity Layout Id
    private int LAYOUT_MAIN = R.layout.activity_main;
    //Fragment Container Id found in activity_main.xml layout file
    private int LAYOUT_MAIN_CONTAINER = R.id.fragment_container;

    //Toolbar Id found in toolbar.xml layout file
    private int TOOLBAR_MAIN = R.id.toolbar;
    //Menu for Toolbar found in menu_main menu file
    private int MENU_MAIN = R.menu.menu_main;

    //FloatingActionButton Id found in float_button.xml layout file
    private int FLOATING_ACTION_BUTTON = R.id.fab;

    //OnClickListener used with FloatingActionButton
    private View.OnClickListener mFABOnClickListener =
        new View.OnClickListener() {
            @Override
            public void onClick(View view){
                onFABOnClickListener(view);
        }
    };
/**************************************************************************************************/

/**************************************************************************************************/
/**
 * int getActivityLayoutId() - get layout id used for Main Activity
 * @return int - layout id value
 */
    public int getActivityLayoutId(){
        return LAYOUT_MAIN;
    }

/**
 * int getFragmentContainerId() - get fragment container id, a framelayout found inside the xml
 * layout file used by the Activity to be used as a container to load fragments
 * @return int - framelayout id,
 */
    public int getFragmentContainerId(){
        return LAYOUT_MAIN_CONTAINER;
    }

/**
 * int getToolbarId() - get the toolbar id, a toolbar object found inside the xml layout file used
 * by the Activity.
 * @return int - toolbar id
 */
    public int getToolbarId(){
        return TOOLBAR_MAIN;
    }

/**
 * int getMenuId - get Id of menu file from res/menu to be used in the Toolbar
 * @return int - xml menu id
 */
    public int getMenuId(){
        return MENU_MAIN;
    }

/**
 * int getFloatingActionButtonId() - get the floating action button id found inside the xml
 * layoutfile used by the Activity
 * @return int - floating action button id
 */
    public int getFloatingActionButtonId(){
        return FLOATING_ACTION_BUTTON;
    }

/**
 * OnClickListener getFABOnClickListener() - get the OnClick Listener for the Floating Action
 * Button object.
 * @return - View.OnClickListener object
 */
    public View.OnClickListener getFABOnClickListener(){
        return mFABOnClickListener;
    }

/**
 * void onOptionsItemSelected(MenuItem) - listens for an onOptionsItemSelected event from the
 * menu list contained in the toolbar view
 * @param item - menu item selected in the toolbar
 */
    public void onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        String strAction;
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_bar_main_simple) {
            strAction = mBoss.getString(R.string.action_bar_main_simple);
        }
        else if (id == R.id.action_bar_main_advance) {
            strAction = mBoss.getString(R.string.action_bar_main_advance);
        }
        else if (id == R.id.action_bar_main_grid) {
            strAction = mBoss.getString(R.string.action_bar_main_grid);
        }
        else {
            strAction = "no action selected";
        }

        Toast toast = Toast.makeText(mActivityContext, strAction, Toast.LENGTH_SHORT);
        toast.show();

    }

/**
 * void onFABOnClickListener(View) - listens for an onClick event happening with the Floating
 * Action Button object.
 * @param view - floating action button view that registered the onClick event
 */
    private void onFABOnClickListener(View view){
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();

    }

/**************************************************************************************************/




    private FragmentManager mManager;
    public void setFragmentManager(FragmentManager manager){
        mManager = manager;
        mManager.beginTransaction().add(LAYOUT_MAIN_CONTAINER, mBoss.getListFragment()).commit();
    }


/**
 * AppList will display a list of names of applications held in the portfolio. There are two types
 * that can be displayed 1)a simple list of names or 2)a list of names with a corresponding icon
 */


}
