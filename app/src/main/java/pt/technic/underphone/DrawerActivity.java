package pt.technic.underphone;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

import pt.technic.underphone.utils.AppInfo;
import pt.technic.underphone.utils.RAdapter;

public class DrawerActivity extends AppCompatActivity {
    public static int RETURN_APP = 1;
    public static int OPEN_APP = 2;
    public static int RESULT_OK = 3;
    public static int RESULT_ERROR = 4;

    RecyclerView recyclerView;
    RAdapter radapter;
    public int action;
    public int idLauncher = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_drawer);
        hide();

        // Argument
        Intent intent = getIntent();
        this.idLauncher = intent.getIntExtra("id", 0);
        this.action = intent.getIntExtra("action", 0);

        radapter = new RAdapter(this);

        recyclerView = findViewById(R.id.RView);
        recyclerView.setAdapter(radapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        new AppsReader().execute();
    }

    public class AppsReader extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... Params) {

            PackageManager pm = getPackageManager();
            radapter.appsList = new ArrayList<>();

            Intent i = new Intent(Intent.ACTION_MAIN, null);
            i.addCategory(Intent.CATEGORY_LAUNCHER);

            List<ResolveInfo> allApps = pm.queryIntentActivities(i, 0);
            for(ResolveInfo ri:allApps) {
                AppInfo app = new AppInfo();
                app.label = ri.loadLabel(pm);
                app.packageName = ri.activityInfo.packageName;
                app.icon = ri.activityInfo.loadIcon(pm);
                radapter.appsList.add(app);
            }
            return "Success";

        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            updateStuff();
        }
    }

    public void updateStuff() {
        radapter.notifyItemInserted(radapter.getItemCount()-1);

    }

    private void hide() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }
}
