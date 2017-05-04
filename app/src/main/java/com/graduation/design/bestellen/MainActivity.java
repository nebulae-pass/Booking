package com.graduation.design.bestellen;

import android.os.Bundle;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void initViews() {
        setTitle("main page");
    }

    @Override
    public void initListener() {

    }
}
