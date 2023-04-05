package ro.pub.cs.systems.eim.Colocviu1_1;

import android.content.Context;
import android.content.Intent;

import java.util.Date;

public class ProcessingThread extends Thread {
    private Context context;
    private String pressed;

    public ProcessingThread(Context context, String pressed) {
        this.context = context;
        this.pressed = pressed;
    }

    @Override
    public void run() {
        sleep();
        sendMessage();
    }

    private void sendMessage() {
        Intent intent = new Intent();
        intent.setAction(Constants.BROADCAST_ACTION);
        intent.putExtra(Constants.BROADCAST_RECEIVER_EXTRA, new Date(System.currentTimeMillis()) + " " + pressed);
        context.sendBroadcast(intent);
    }

    private void sleep() {
        try {
            Thread.sleep(Constants.BROADCAST_INTERVAL);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }
}
