package com.example.droidcafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class OrderActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        Intent intent = getIntent();
        String order = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        String message = "Order: " + order;

        TextView textView = findViewById(R.id.order_textview);
        textView.setText(message);

        Spinner spinner = findViewById(R.id.label_spinner);

        if (spinner != null) {
            spinner.setOnItemSelectedListener(this);
        }

        // Create ArrayAdapter using the string array and default spinner layout.
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.labels_array, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears.
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        if (spinner != null) {
            spinner.setAdapter(adapter);
        }

        EditText editText = findViewById(R.id.phone_text);

        if (editText != null) {
            editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                    boolean handled = false;

                    if (i == EditorInfo.IME_ACTION_SEND) {
                        dialNumber();
                        handled = true;
                    }

                    return handled;
                }
            });
        }
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.sameday:
                if (checked) {
                    displayToast(getString(R.string.same_day_messenger_service));
                }
                break;

            case R.id.nextday:
                if (checked) {
                    displayToast(getString(R.string.next_day_ground_delivery));
                }
                break;

            case R.id.pickup:
                if (checked) {
                    displayToast(getString(R.string.pick_up));
                }
                break;
        }
    }

    public void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String spinnerLabel = adapterView.getItemAtPosition(i).toString();
        displayToast(spinnerLabel);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void dialNumber() {
        EditText editText = findViewById(R.id.phone_text);
        String phoneNum = null;

        if (editText != null) {
            phoneNum = "tel:" + editText.getText().toString();
        }

        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse(phoneNum));

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Log.d(OrderActivity.class.getSimpleName(), "Can't handle this!");
        }
    }
}