package pt.technic.underphone.Views;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.AttributeSet;
import android.widget.TextClock;

public class AppLaunchClockView extends TextClock implements AppLaunchBase {
    private int idLauncher;
    private String app;

    public AppLaunchClockView(Context context) {
        super(context);

        this.setOnClickListener(new AppLaunchActions());
        this.setOnLongClickListener(new AppLaunchActions());
    }

    public AppLaunchClockView(Context context, AttributeSet attrs) {
        super(context, attrs);

        this.setOnClickListener(new AppLaunchActions());
        this.setOnLongClickListener(new AppLaunchActions());
    }

    public AppLaunchClockView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        this.setOnClickListener(new AppLaunchActions());
        this.setOnLongClickListener(new AppLaunchActions());
    }

    public AppLaunchClockView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        this.setOnClickListener(new AppLaunchActions());
        this.setOnLongClickListener(new AppLaunchActions());
    }

    public void init(int id, String app){
        this.idLauncher = id;
        this.app = app;

        if(app != null){
            final PackageManager pm = getContext().getPackageManager();
            ApplicationInfo ai;
            try {
                ai = pm.getApplicationInfo(app, 0);
//                setText(pm.getApplicationLabel(ai));
            } catch (final PackageManager.NameNotFoundException e) {
                this.app = null;
            }
        }
    }

    public String getApp() {
        return app;
    }

    public int getIdLauncher() {
        return idLauncher;
    }
}
