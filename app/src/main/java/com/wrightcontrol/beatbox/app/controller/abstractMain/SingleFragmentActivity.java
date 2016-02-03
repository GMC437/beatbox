package com.wrightcontrol.beatbox.app.controller.abstractMain;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Grant on 02/02/2016.
 */
public abstract class SingleFragmentActivity extends AppCompatActivity {
    protected abstract int getLayout();

    protected abstract int getContainerID();

    protected abstract Fragment createFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());

        FragmentManager fragManager = getSupportFragmentManager();
        FragmentTransaction fragTransaction = fragManager.beginTransaction();
        Fragment frag = fragManager.findFragmentById(getContainerID());

        if (frag == null) {

            frag = createFragment();
            fragTransaction.add(getContainerID(), frag);
            fragTransaction.commit();

        }
    }

}
