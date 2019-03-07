package cn.dbboy.dbboyxplan;

import android.app.Application;

import cn.dbboy.dbboyxplan.utils.Density;

/**
 * Created by DB_BOY on 2019/3/5.
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Density.setDensity(this,360f);
    }
}
