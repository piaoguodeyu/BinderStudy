package comy.server;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

/**
 * @author zhangxiaowei 2018/12/9
 */
public class MyService extends Service {
    IMyAidlInterface.Stub binderStub = new IMyAidlInterface.Stub() {
        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {
        }

        @Override
        public int sum(int a, int b) throws RemoteException {
            return a + b;
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        Log.e(":MyService:",":onBind: [*********]=");
        return binderStub;
    }
}
