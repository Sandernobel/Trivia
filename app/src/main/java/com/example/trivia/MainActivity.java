package com.example.trivia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    private RadioGroup diff_group;
    private RadioButton difficulty;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        submit = findViewById(R.id.ready);
        submit.setOnClickListener(new onClickListener());
    }

    private class onClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            diff_group = findViewById(R.id.difficulty);
            int sel_diff = diff_group.getCheckedRadioButtonId();
            difficulty = findViewById(sel_diff);

            EditText username = findViewById(R.id.name);
            String name = username.getText().toString();
            if (name.isEmpty()) {
                name = "Anonymous";
            }

            Intent intent = new Intent(MainActivity.this, GameplayActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("difficulty", difficulty.getText().toString());
            bundle.putString("name", name);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }
}
