package revels18.in.revels18.application;

import android.app.Application;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class Revels extends Application {
    public static int searchOpen =0;

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(realmConfiguration);
        // TODO: Move below method call to the splash screen once made

    }
    private void loadData(){

    }

}
