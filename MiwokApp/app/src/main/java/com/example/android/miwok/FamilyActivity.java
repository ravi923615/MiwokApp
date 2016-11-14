package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity {
    private MediaPlayer audioToPlay;

    private MediaPlayer.OnCompletionListener mcompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };

    private void releaseMediaPlayer(){
        if(audioToPlay!=null){

            audioToPlay.release();

            audioToPlay = null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family);

        final ArrayList<Word> families = new ArrayList<Word>();
        families.add(new Word("father","әpә",R.drawable.family_father,R.raw.family_father));
        families.add(new Word("mother","әṭa",R.drawable.family_mother,R.raw.family_mother));
        families.add(new Word("son","angsi",R.drawable.family_son,R.raw.family_son));
        families.add(new Word("daughter","tune",R.drawable.family_daughter,R.raw.family_daughter));
        families.add(new Word("older brother","taachi",R.drawable.family_older_brother,R.raw.family_older_brother));
        families.add(new Word("younger brother","chalitti",R.drawable.family_younger_brother,R.raw.family_younger_brother));
        families.add(new Word("older sister","teṭe",R.drawable.family_older_sister,R.raw.family_older_sister));
        families.add(new Word("younger sister","kolliti",R.drawable.family_younger_sister,R.raw.family_younger_sister));
        families.add(new Word("grandmother","ama",R.drawable.family_grandmother,R.raw.family_grandmother));
        families.add(new Word("grandfather","paapa",R.drawable.family_grandfather,R.raw.family_grandfather));

        WordAdapter familyAdapter = new WordAdapter(this,families, R.color.category_family);

        final ListView listViewFam = (ListView)findViewById(R.id.listView_family);
        listViewFam.setAdapter(familyAdapter);

        listViewFam.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Word currentWord = families.get(i);
                releaseMediaPlayer();
                audioToPlay = MediaPlayer.create(FamilyActivity.this,currentWord.getAudioAttached());
                audioToPlay.start();
                audioToPlay.setOnCompletionListener(mcompletionListener);
            }
        });

    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }
}
