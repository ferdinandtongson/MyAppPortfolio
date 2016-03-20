package me.makeachoice.myappportfolio.controller.butler;

import android.content.Context;
import android.util.Log;
import android.widget.ListAdapter;

import java.util.ArrayList;

import me.makeachoice.myappportfolio.adapter.item.TitleItem;
import me.makeachoice.myappportfolio.model.AppDemoModel;

/**
 * Created by Usuario on 3/19/2016.
 */
public class AppDemoButler {

    Context mActivityContext;
    public AppDemoButler(Context ctx){
        mActivityContext = ctx;
    }


    AppDemoModel mAppModel;
    public AppDemoModel getModel( ){
        Log.d("SimpleListFragment", "Boss.createListItems()");
        mAppModel = new AppDemoModel();
        Log.d("SimpleListFragment", "     created appModel object");

        mAppModel.addApp("App1", "Info1");
        mAppModel.addApp("App2", "Info2");
        mAppModel.addApp("App3", "Info3");
        mAppModel.addApp("App4", "Info4");
        mAppModel.addApp("App5", "Info5");

        return mAppModel;
    }


}
