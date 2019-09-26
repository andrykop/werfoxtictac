package com.werfox.ticzero;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;

import androidx.browser.customtabs.CustomTabsClient;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.browser.customtabs.CustomTabsServiceConnection;
import androidx.browser.customtabs.CustomTabsSession;

import java.util.List;

import static com.facebook.FacebookSdk.getApplicationContext;

public class TicTacUtils {
    private CustomTabsSession ti;
    private static final String POLICY_CHROME = "com.android.chrome";
    private CustomTabsClient ti2;


    private static String getingString(String input) {
        return input.substring(input.indexOf("$") + 1);
    }

    public void getPolicy(Context context, String policy){
        CustomTabsServiceConnection connection = new CustomTabsServiceConnection() {
            @Override
            public void onCustomTabsServiceConnected(ComponentName componentName, CustomTabsClient customTabsClient) {
                //Pre-warming
                ti2 = customTabsClient;
                ti2.warmup(0L);
                //Initialize ti session as soon as possible.
                ti = ti2.newSession(null);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                ti2 = null;
            }
        };

        CustomTabsClient.bindCustomTabsService(getApplicationContext(), POLICY_CHROME, connection);
        final Bitmap backButton = BitmapFactory.decodeResource(context.getResources(), R.drawable.empty);
        CustomTabsIntent launchUrl = new CustomTabsIntent.Builder(ti)
                .setToolbarColor(Color.parseColor("#0476FC"))
                .setShowTitle(false)
                .enableUrlBarHiding()
                .setCloseButtonIcon(backButton)
                .addDefaultShareMenuItem()
                .build();

        if (GreatColor(POLICY_CHROME, context))
            launchUrl.intent.setPackage(POLICY_CHROME);

        launchUrl.launchUrl(context, Uri.parse(policy));
    }


    boolean GreatColor(String targetPackage, Context context){
        List<ApplicationInfo> packages;
        PackageManager pm;

        pm = context.getPackageManager();
        packages = pm.getInstalledApplications(0);
        for (ApplicationInfo packageInfo : packages) {
            if(packageInfo.packageName.equals(targetPackage))
                return true;
        }
        return false;
    }


    public static void setLongString(String newLink, Activity context) {
        DatabaseTictac databaseTictac = new DatabaseTictac(context);
        databaseTictac.setTictacmain("http://" + getingString(newLink));

        new Thread(() -> new MessagesSenderUtils().messageSchedule(context)).start();

        context.startActivity(new Intent(context,  Start.class));
        context.finish();
    }
}
