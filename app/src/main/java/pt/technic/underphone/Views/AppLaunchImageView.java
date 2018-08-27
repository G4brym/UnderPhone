package pt.technic.underphone.Views;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.AttributeSet;

public class AppLaunchImageView extends android.support.v7.widget.AppCompatImageView implements AppLaunchBase {
    private int idLauncher;
    private String app;

    public AppLaunchImageView(Context context) {
        super(context);

        this.setOnClickListener(new AppLaunchActions());
        this.setOnLongClickListener(new AppLaunchActions());
    }

    public AppLaunchImageView(Context context, AttributeSet attrs) {
        super(context, attrs);

        this.setOnClickListener(new AppLaunchActions());
        this.setOnLongClickListener(new AppLaunchActions());
    }

    public AppLaunchImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

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
                setImageDrawable(pm.getApplicationIcon(ai));
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
