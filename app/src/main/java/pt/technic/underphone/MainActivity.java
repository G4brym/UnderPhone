package pt.technic.underphone;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import pt.technic.underphone.Views.AppLaunchBase;
import pt.technic.underphone.utils.Utilities;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        loadAppLaunch(R.id.textClock);

        loadAppLaunch(R.id.appLaunch1);
        loadAppLaunch(R.id.appLaunch2);
        loadAppLaunch(R.id.appLaunch3);
        loadAppLaunch(R.id.appLaunch4);
        loadAppLaunch(R.id.appLaunch5);
        loadAppLaunch(R.id.appLaunch6);

        loadAppLaunch(R.id.imageView1);
        loadAppLaunch(R.id.imageView2);
        loadAppLaunch(R.id.imageView3);
    }

    protected void loadAppLaunch(int id){
        String app  = Utilities.getSetting(this, Integer.toString(id), null);

        AppLaunchBase appLaunchBase = findViewById(id);
        appLaunchBase.init(id, app);
    }

    protected void setAppLaunch(int id, String name){
        Utilities.saveSetting(this, Integer.toString(id), name);

        AppLaunchBase appLaunchBase = findViewById(id);
        appLaunchBase.init(id, name);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        hide();
    }

    private void hide() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == DrawerActivity.RESULT_OK) {
            int id = data.getIntExtra("id", 0);
            String name = data.getStringExtra("name");

            setAppLaunch(id, name);
        }
    }
}
