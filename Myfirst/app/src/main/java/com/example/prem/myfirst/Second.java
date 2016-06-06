package com.example.prem.myfirst;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Prem $ on 6/2/2016.
 */
public class Second extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);

        TextView t1 = (TextView) findViewById(R.id.textView1);
        TextView t2 = (TextView) findViewById(R.id.textView2);
        TextView t3 = (TextView) findViewById(R.id.textView3);
        TextView t4 = (TextView) findViewById(R.id.textView4);
        t4.setText("Hello " + getIntent().getExtras().getString("name") + ", your details are:");
        t1.setText(getIntent().getExtras().getString("name"));
        t2.setText(getIntent().getExtras().getString("email"));
        t3.setText(getIntent().getExtras().getString("phone"));
        Button b1 = (Button) findViewById(R.id.button2);

        b1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
