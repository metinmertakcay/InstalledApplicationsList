package com.example.user.getinstalledapp;

import android.graphics.drawable.Drawable;

public class AppInfo
{
    private String appName;
    private Drawable drawable;

    public AppInfo(String appName, Drawable drawable)
    {
        this.appName = appName;
        this.drawable = drawable;
    }

    public String getAppName()
    {
        return appName;
    }

    public Drawable getDrawable()
    {
        return drawable;
    }
}
