package ro.pub.cs.systems.eim.Colocviu1_1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Colocviu1_1MainActivity extends AppCompatActivity {
    Button buttonNorth;
    Button buttonSouth;
    Button buttonEast;
    Button buttonWest;
    TextView textView;
    Button buttonNavigate;
    List<String> pressed = new ArrayList<>();
    Integer pressedCount = 0;

    public static class MessageBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(android.content.Context context, Intent intent) {
            String message = intent.getStringExtra(Constants.BROADCAST_RECEIVER_EXTRA);
            Log.d(Constants.BROADCAST_RECEIVER_EXTRA, message);
        }
    }
    private final MessageBroadcastReceiver messageBroadcastReceiver = new MessageBroadcastReceiver();

    private class MyButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            pressed.add(((Button)view).getText().toString());
            pressedCount++;
            Log.d(Constants.PRESSED_COUNT_TAG, "Pressed count: " + pressedCount);
            textView.setText(String.join(", ", pressed));
            startMyService();
        }
    }

    private void startMyService() {
        if (pressed.size() == Constants.TARGET) {
            Intent intent = new Intent(getApplicationContext(), Colocviu1_1Service.class);
            intent.putExtra(Constants.PRESSED, String.join(", ", pressed));
            getApplicationContext().startService(intent);
        }
    }

    private final MyButtonClickListener buttonClickListener = new MyButtonClickListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colocviu1_1_main);

        buttonNorth = findViewById(R.id.button_north);
        buttonSouth = findViewById(R.id.button_south);
        buttonEast = findViewById(R.id.button_east);
        buttonWest = findViewById(R.id.button_west);
        textView = findViewById(R.id.text_view);
        buttonNavigate = findViewById(R.id.button_navigate);

        buttonNorth.setOnClickListener(buttonClickListener);
        buttonSouth.setOnClickListener(buttonClickListener);
        buttonEast.setOnClickListener(buttonClickListener);
        buttonWest.setOnClickListener(buttonClickListener);
        buttonNavigate.setOnClickListener(it -> {
            Intent intent = new Intent(getApplicationContext(), Colocviu1_1SecondaryActivity.class);
            intent.putExtra(Constants.PRESSED, String.join(", ", pressed));
            intent.putExtra(Constants.PRESSED_COUNT, pressedCount);
            pressed.clear();
            pressedCount = 0;
            startActivityForResult(intent, Constants.REQUEST_CODE);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Constants.BROADCAST_ACTION);
        registerReceiver(messageBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(messageBroadcastReceiver);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(Constants.PRESSED_COUNT, pressedCount);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if (savedInstanceState.containsKey(Constants.PRESSED_COUNT)) {
            pressedCount = savedInstanceState.getInt(Constants.PRESSED_COUNT);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Constants.REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "Result Register", Toast.LENGTH_SHORT).show();
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Result Cancel", Toast.LENGTH_SHORT).show();
            }
        }
    }
}