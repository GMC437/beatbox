package com.wrightcontrol.beatbox.app.controller.beatBox;

import android.support.v4.app.Fragment;
import com.wrightcontrol.beatbox.app.R;
import com.wrightcontrol.beatbox.app.controller.abstractMain.SingleFragmentActivity;

public class BeatBoxActivity extends SingleFragmentActivity {

    @Override
    protected int getLayout() {
        return R.layout.fragment_container;
    }

    @Override
    protected int getContainerID() {
        return R.id.fragment_container;
    }

    @Override
    protected Fragment createFragment() {
        return BeatBoxFragment.newInstance();
    }

}
