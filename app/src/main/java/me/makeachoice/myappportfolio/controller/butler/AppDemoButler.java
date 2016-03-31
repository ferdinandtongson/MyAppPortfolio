package me.makeachoice.myappportfolio.controller.butler;

import android.content.Context;
import android.util.Log;

import me.makeachoice.myappportfolio.R;
import me.makeachoice.myappportfolio.model.AppDemoModel;

/**
 * AppDemoButler handles the creation of the AppDemo model, taking resources from a flat file,
 * database or webservice and converting it into a model for consumption by the controller
 */
public class AppDemoButler {

    Context mActivityContext;
    public AppDemoButler(Context ctx){
        Log.d("SimpleListFragment", "AppDemoButler");
        mActivityContext = ctx;
        createModel();
    }

    AppDemoModel mAppModel;
    private void createModel(){
        //this is where the threads, database or resource access is called to create the model
        Log.d("SimpleListFragment", "AppDemoButler.createModel()");
        mAppModel = new AppDemoModel();

        mAppModel.addApp("App1", "Info1", R.drawable.sample_0);
        mAppModel.addApp("App2", "Info2", R.drawable.sample_1);
        mAppModel.addApp("App3", "Info3", R.drawable.sample_2);
        mAppModel.addApp("App4", "Info4", R.drawable.sample_3);
        mAppModel.addApp("App5", "Info5", R.drawable.sample_4);
    }



    public AppDemoModel getModel( ){
        return mAppModel;
    }

}
