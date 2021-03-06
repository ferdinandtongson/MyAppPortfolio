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

        mAppModel.addApp(mActivityContext.getString(R.string.app_spotify),
                "This button will launch my Spotify Streamer App",
                R.drawable.sample_1);
        mAppModel.addApp(mActivityContext.getString(R.string.app_scores),
                "This button will launch my Scores App",
                R.drawable.sample_2);
        mAppModel.addApp(mActivityContext.getString(R.string.app_library),
                "This button will launch my Library App",
                R.drawable.sample_3);
        mAppModel.addApp(mActivityContext.getString(R.string.app_bigger),
                "This button will launch my Build It Bigger App",
                R.drawable.sample_4);
        mAppModel.addApp(mActivityContext.getString(R.string.app_reader),
                "This button will launch my XYZ Reader App",
                R.drawable.sample_7);
        mAppModel.addApp(mActivityContext.getString(R.string.app_capstone),
                "This button will launch my CAPSTONE App",
                R.drawable.sample_6);
    }



    public AppDemoModel getModel( ){
        return mAppModel;
    }

}
