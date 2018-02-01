package com.example.user.getinstalledapp;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private PackageManager packageManager;
    private List<AppInfo> list;
    private ListView listView;
    private CustomAdapter customAdapter;
    private ActivityManager activityManager;
    private List<ActivityManager.RunningTaskInfo> taskInfo;
    private ComponentName componentName;
    private String appName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();
        getAllInstalledApps();
        customAdapter = new CustomAdapter(MainActivity.this,list);
        listView.setAdapter(customAdapter);

        componentName = taskInfo.get(0).topActivity;
        try {
            appName = (String)packageManager.getApplicationLabel(packageManager.getApplicationInfo(componentName.getPackageName(),PackageManager.GET_META_DATA));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        Toast.makeText(MainActivity.this,appName,Toast.LENGTH_SHORT).show();
    }

    //Ilklendirme islemlerinin gerceklestirildigi kisim
    public void initialize()
    {
        activityManager = (ActivityManager)getSystemService(ACTIVITY_SERVICE);
        taskInfo = activityManager.getRunningTasks(1);
        listView = (ListView)findViewById(R.id.listView);
        packageManager = getPackageManager();
    }

    public void getAllInstalledApps()
    {
        List<PackageInfo> packageInfoList = packageManager.getInstalledPackages(0);
        list = new ArrayList<AppInfo>();

        for (int i = 0; i < packageInfoList.size(); i++)
        {
            PackageInfo packageInfo = packageInfoList.get(i);
            if ((isSystemPackage(packageInfo) == false)) {
                String appName = packageInfo.applicationInfo.loadLabel(getPackageManager()).toString();
                Drawable icon = packageInfo.applicationInfo.loadIcon(getPackageManager());
                list.add(new AppInfo(appName, icon));
            }
        }
    }

    private boolean isSystemPackage(PackageInfo packageInfo)
    {
        if((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
