package com.wrightcontrol.beatbox.app.controller.beatBox;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.wrightcontrol.beatbox.app.model.BeatBox;
import com.wrightcontrol.beatbox.app.R;
import com.wrightcontrol.beatbox.app.model.Sound;

import java.util.List;

/**
 * Created by Grant on 02/02/2016.
 */
public class BeatBoxFragment extends Fragment {

    private BeatBox mBeatBox;
    private Sound mSound;

    public static BeatBoxFragment newInstance() {
        return new BeatBoxFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBeatBox = new BeatBox(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_beat_box, container, false);
        instantiateWidgets(view);
        return view;
    }

    private void instantiateWidgets(View view) {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.beat_box_recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        recyclerView.setAdapter(new SoundAdapter(mBeatBox.getSounds()));
    }

    private class SoundHolder extends RecyclerView.ViewHolder {

        private Button mButton;

        public SoundHolder(LayoutInflater inflater, ViewGroup container) {
            super(inflater.inflate(R.layout.list_item_sound, container, false));

            mButton = (Button) itemView.findViewById(R.id.list_item_sound_button);
        }

        public void bindSounds(Sound sound) {
            mSound = sound;
            mButton.setText(mSound.getName());
        }
    }

    private class SoundAdapter extends RecyclerView.Adapter<SoundHolder> {

        private List<Sound> mSounds;

        public SoundAdapter(List<Sound> sounds) {
            mSounds = sounds;
        }

        @Override
        public SoundHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            return new SoundHolder(inflater, parent);
        }

        @Override
        public void onBindViewHolder(SoundHolder soundHolder, int position) {

            Sound sound = mSounds.get(position);
            soundHolder.bindSounds(sound);
        }

        @Override
        public int getItemCount() {
            return mSounds.size();
        }
    }

}
