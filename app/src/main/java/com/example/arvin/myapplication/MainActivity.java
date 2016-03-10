package com.example.arvin.myapplication;

//    / \   _ ____   _(_)_ __      / \   ___  ___ | | ____ _ _ __  
//   / _ \ | '__\ \ / / | '_ \    / _ \ / __|/ _ \| |/ / _` | '_ \ 
//  / ___ \| |   \ V /| | | | |  / ___ \\__ \ (_) |   < (_| | | | |
// /_/   \_\_|    \_/ |_|_| |_| /_/   \_\___/\___/|_|\_\__,_|_| |_|
//
// Code Written by Arvin Asokan
// A simple android app originally written to control the Fetch and Freight Robots 
// The app can be used to control other robots as well. 
//
// For further details contact arvinasokan@gmail.com

import android.os.Bundle;
import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends Activity implements OnTouchListener{
    RelativeLayout layout_joystick;
    ImageView image_joystick, image_border;
    TextView textView1, textView2, textView3, textView4;
    public int[] buttonpress;


    JoyStickClass js;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonpress = new int[7];
        addButtonTouchListener();
        textView1 = (TextView) findViewById(R.id.hello_world);
        textView2 = (TextView) findViewById(R.id.hello_world2);
        textView3 = (TextView) findViewById(R.id.hello_world3);
        textView4 = (TextView) findViewById(R.id.hello_world4);
        layout_joystick = (RelativeLayout) findViewById(R.id.layout_joystick);


        js = new JoyStickClass(getApplicationContext()
                , layout_joystick, R.drawable.image_button);
        js.setStickSize(150, 150);
        js.setLayoutSize(500, 500);
        js.setLayoutAlpha(150);
        js.setStickAlpha(100);
        js.setOffset(90);
        js.setMinimumDistance(10);

        layout_joystick.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                js.drawStick(event);
                if (event.getAction() == MotionEvent.ACTION_DOWN
                        || event.getAction() == MotionEvent.ACTION_MOVE) {
                    textView1.setText("X : " + String.valueOf(js.getX()));
                    textView2.setText("Y : " + String.valueOf(js.getY()));
                    textView3.setText("Angle : " + String.valueOf(js.getAngle()));

                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    textView1.setText("X :");
                    textView2.setText("Y :");
                    textView3.setText("Angle :");
                    textView4.setText(String.valueOf(0));
                    //textView4.setText("Distance :");
                    //textView5.setText("Direction :");
                }
                return true;
            }
        });
    }

    private void addButtonTouchListener() {
        Button torsoup = (Button) findViewById(R.id.torso_up);
        torsoup.setOnTouchListener((OnTouchListener) this);
        Button torsodown = (Button) findViewById(R.id.torso_down);
        torsodown.setOnTouchListener((OnTouchListener) this);
        Button armtuck = (Button) findViewById(R.id.arm_tuck);
        armtuck.setOnTouchListener((OnTouchListener) this);
        Button headtilt = (Button) findViewById(R.id.head_tilt);
        headtilt.setOnTouchListener((OnTouchListener) this);
        Button opengripper = (Button) findViewById(R.id.open_gripper);
        opengripper.setOnTouchListener((OnTouchListener) this);
        Button closegripper = (Button) findViewById(R.id.close_gripper);
        closegripper.setOnTouchListener((OnTouchListener) this);

    }

    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                if (v.getId() == R.id.torso_up) {
                    buttonpress[1] = 1;
                    textView4.setText(String.valueOf(buttonpress[1]));
                } else if (v.getId() == R.id.torso_down) {
                    buttonpress[1] = 2;
                    textView4.setText(String.valueOf(buttonpress[1]));
                } else if (v.getId() == R.id.open_gripper) {
                    buttonpress[1] = 3;
                    textView4.setText(String.valueOf(buttonpress[1]));
                } else if (v.getId() == R.id.close_gripper) {
                    buttonpress[1] = 4;
                    textView4.setText(String.valueOf(buttonpress[1]));
                } else if (v.getId() == R.id.arm_tuck) {
                    buttonpress[1] = 5;
                    textView4.setText(String.valueOf(buttonpress[1]));

                }
            }
            case MotionEvent.ACTION_UP: {
                double xx = 0;
                double yy = 0;
                buttonpress[1] = 0;
                textView4.setText(String.valueOf(buttonpress[1]));

            }
        }
        return true;
    }
}