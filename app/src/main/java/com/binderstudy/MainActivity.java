package com.binderstudy;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import comy.server.IMyAidlInterface;

public class MainActivity extends AppCompatActivity {
    String action = "comy.server.IMyAidlInterface";
    IMyAidlInterface iMyAidlInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(getApplicationContext(), IMyAidlInterface.class);
        intent.setAction(action);
        intent.setComponent(new ComponentName("comy.server", "comy.server.MyService"));
        bindService(intent, conn, BIND_AUTO_CREATE);
    }


    private ServiceConnection conn = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            iMyAidlInterface = IMyAidlInterface.Stub.asInterface(iBinder);
            try {
                int data = iMyAidlInterface.sum(5, 6);
                Log.e(":MainActivity:", ":onServiceConnected: = " + data);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        @Override
        public void onServiceDisconnected(ComponentName componentName) {
        }
    };
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(conn);
    }
}
