package ro.pub.cs.systems.eim.Colocviu1_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class Colocviu1_1SecondaryActivity extends AppCompatActivity {

    TextView currentInputs;
    Button buttonRegister;
    Button buttonCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colocviu1_1_secondary);

        currentInputs = findViewById(R.id.current_inputs);
        buttonRegister = findViewById(R.id.button_register);
        buttonCancel = findViewById(R.id.button_cancel);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                currentInputs.setText("No data");
            } else {
                currentInputs.setText(extras.getString(Constants.PRESSED));
            }
        } else {
            currentInputs.setText(savedInstanceState.getString(Constants.PRESSED));
        }

        buttonRegister.setOnClickListener(it -> {
            setResult(RESULT_OK, null);
            finish();
        });

        buttonCancel.setOnClickListener(it -> {
            setResult(RESULT_CANCELED, null);
            finish();
        });
    }
}