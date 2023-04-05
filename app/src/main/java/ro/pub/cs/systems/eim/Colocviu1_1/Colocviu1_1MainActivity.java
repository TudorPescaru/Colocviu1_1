package ro.pub.cs.systems.eim.Colocviu1_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

    private class MyButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            pressed.add(((Button)view).getText().toString());
            pressedCount++;
            Log.d(Constants.PRESSED_COUNT_TAG, "Pressed count: " + pressedCount);
            textView.setText(String.join(", ", pressed));
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
}