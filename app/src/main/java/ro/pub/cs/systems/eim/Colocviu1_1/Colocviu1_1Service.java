package ro.pub.cs.systems.eim.Colocviu1_1;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import java.util.Date;

public class Colocviu1_1Service extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String pressed = intent.getStringExtra(Constants.PRESSED);

        ProcessingThread processingThread = new ProcessingThread(getApplicationContext(), pressed);
        processingThread.start();

        return Service.START_REDELIVER_INTENT;
    }
}
