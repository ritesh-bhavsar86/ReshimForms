package com.riteshbhavsar.reshimforms;

import android.app.Application;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import java.io.PrintWriter;
import java.io.StringWriter;

import io.realm.Realm;

import static java.lang.Character.LINE_SEPARATOR;

/**
 * Created by ritesh.bhavsar on 06-07-2017.
 */

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // Initialize Realm. Should only be done once when the application starts.
        Realm.init(this);

        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {

            @Override
            public void uncaughtException(Thread thread, Throwable ex) {
                StringWriter stackTrace = new StringWriter();
                ex.printStackTrace(new PrintWriter(stackTrace));
                StringBuilder errorReport = new StringBuilder();
                errorReport.append("************ CAUSE OF ERROR ************\n\n");
                errorReport.append(stackTrace.toString());

                errorReport.append("\n************ DEVICE INFORMATION ***********\n");
                errorReport.append("Brand: ");
                errorReport.append(Build.BRAND);
                errorReport.append(LINE_SEPARATOR);
                errorReport.append("Device: ");
                errorReport.append(Build.DEVICE);
                errorReport.append(LINE_SEPARATOR);
                errorReport.append("Model: ");
                errorReport.append(Build.MODEL);
                errorReport.append(LINE_SEPARATOR);
                errorReport.append("Id: ");
                errorReport.append(Build.ID);
                errorReport.append(LINE_SEPARATOR);
                errorReport.append("Product: ");
                errorReport.append(Build.PRODUCT);
                errorReport.append(LINE_SEPARATOR);
                errorReport.append("\n************ FIRMWARE ************\n");
                errorReport.append("SDK: ");
                errorReport.append(Build.VERSION.SDK);
                errorReport.append(LINE_SEPARATOR);
                errorReport.append("Release: ");
                errorReport.append(Build.VERSION.RELEASE);
                errorReport.append(LINE_SEPARATOR);
                errorReport.append("Incremental: ");
                errorReport.append(Build.VERSION.INCREMENTAL);
                errorReport.append(LINE_SEPARATOR);

                Intent intent = new Intent(getApplicationContext(), ExceptionActivity.class);
                intent.putExtra("error", errorReport.toString());
                intent.setFlags (Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(intent);

                android.os.Process.killProcess(android.os.Process.myPid());




                Log.e("MyApplication", ex.getMessage());
                System.exit(1);

            }
        });

    }


}
