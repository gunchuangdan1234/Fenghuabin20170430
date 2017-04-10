package com.bwie.test;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.bwie.test.view.RoundProgressBar;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //获取自定义控件对象
        final RoundProgressBar rpb= (RoundProgressBar) findViewById(R.id.rpb);

        Button  button= (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rpb.setColor(Color.parseColor("#0000ff"));
            }
        });

    }
}
