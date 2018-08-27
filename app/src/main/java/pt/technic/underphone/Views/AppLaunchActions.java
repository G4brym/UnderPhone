package pt.technic.underphone.Views;

import android.content.Intent;
import android.view.View;

import pt.technic.underphone.DrawerActivity;
import pt.technic.underphone.MainActivity;

public class AppLaunchActions implements View.OnClickListener, View.OnLongClickListener {
    @Override
    public void onClick(View v) {
        AppLaunchBase appLaunchBase = (AppLaunchBase)v;

        if(appLaunchBase.getApp() == null) {
            getAppName(v, appLaunchBase);
            return;
        }

        MainActivity context = (MainActivity) v.getContext();
        Intent launchIntent = context.getPackageManager().getLaunchIntentForPackage(appLaunchBase.getApp());
        context.startActivity(launchIntent);
    }

    @Override
    public boolean onLongClick(View v) {
        AppLaunchBase appLaunchBase = (AppLaunchBase)v;

        getAppName(v, appLaunchBase);
        return true;
    }

    private void getAppName(View v, AppLaunchBase appLaunchBase){
        MainActivity context = (MainActivity) v.getContext();

        Intent intent = new Intent(context, DrawerActivity.class);
        intent.putExtra("id", appLaunchBase.getIdLauncher());
        intent.putExtra("action", DrawerActivity.RETURN_APP);
        context.startActivityForResult(intent, DrawerActivity.RETURN_APP);
    }
}
