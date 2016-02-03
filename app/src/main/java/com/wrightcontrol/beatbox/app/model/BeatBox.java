package com.wrightcontrol.beatbox.app.model;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Grant on 02/02/2016.
 */
public class BeatBox {

    private static final String TAG = "BeatBox";

    private static final String SOUNDS_FOLDER = "sample_sounds";
    private static final int MAX_SOUNDS = 5;

    private AssetManager mAssets;
    private List<Sound> mSounds = new ArrayList<Sound>();
    private SoundPool mSoundPool;

    private float leftVol = 1.0f;
    private float rightVol = 1.0f;
    private int priority = 1;
    private int loop = 0;
    private float rate = 1.0f;

    public BeatBox(Context context) {
        mAssets = context.getAssets();
        //This old constructor is deprecated, but we need it for compatibility.
        mSoundPool = new SoundPool(MAX_SOUNDS, AudioManager.STREAM_MUSIC, 0);
        loadSounds();
    }

    private void loadSounds() {
        String[] soundNames;
        try {
            soundNames = mAssets.list(SOUNDS_FOLDER);
            Log.i(TAG, "Found " + soundNames.length + " sounds");
        } catch (IOException ioe) {
            Log.e(TAG, "Could not list assets", ioe);
            return;
        }

       // mSounds = new ArrayList<Sound>();

        for (String filename : soundNames) {
            try {
                String assetPath = SOUNDS_FOLDER + "/" + filename;
                Sound sound = new Sound(assetPath);
                load(sound);
                mSounds.add(sound);
            } catch (IOException ioe) {
                Log.e(TAG, "Could not load sound " + filename, ioe);
            }

        }
    }

    private void load(Sound sound) throws IOException {

        AssetFileDescriptor afd = mAssets.openFd(sound.getAssetPath());
        int soundId = mSoundPool.load(afd, 1);
        sound.setSoundId(soundId);
    }

    public void play(Sound sound) {

        Integer soundId = sound.getSoundId();
        if (soundId == null) {
            return;
        }
        mSoundPool.play(soundId, leftVol, rightVol, priority, loop, rate);
    }

    public void release(){
        mSoundPool.release();
    }

    public List<Sound> getSounds() {
        return mSounds;
    }

}
