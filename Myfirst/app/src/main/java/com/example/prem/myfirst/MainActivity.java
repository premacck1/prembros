package com.example.prem.myfirst;

import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import static com.example.prem.myfirst.R.id.button;

public class MainActivity extends AppCompatActivity {

    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*final EditText e1 = (EditText) findViewById(R.id.editText1);
        final EditText e2 = (EditText) findViewById(R.id.editText2);
        final EditText e3 = (EditText) findViewById(R.id.editText3);*/
        b = (Button) findViewById(button);

        Animation anim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.anim);
        b.startAnimation(anim);

        assert b != null;
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Display mdisp = getWindowManager().getDefaultDisplay();
                Point mdispSize = new Point();
                mdisp.getSize(mdispSize);
                float maxX = mdispSize.x;
                float maxY = mdispSize.y;
                System.out.println(maxX + " / " + maxY);
                Animation anim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.anim);
                b.startAnimation(anim);
                /*Intent intent1 = new Intent(MainActivity.this, Second.class);
                intent1.putExtra("name", e1.getText().toString());
                intent1.putExtra("email", e2.getText().toString());
                intent1.putExtra("phone", e3.getText().toString());
                startActivity(intent1);*/
            }
        });
    }
}