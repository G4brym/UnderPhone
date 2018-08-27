package pt.technic.underphone.Views;

public interface AppLaunchBase {
    String getApp();
    int getIdLauncher();
    void init(int id, String app);
}
