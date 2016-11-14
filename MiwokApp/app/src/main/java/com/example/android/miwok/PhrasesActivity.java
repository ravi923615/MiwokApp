package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class PhrasesActivity extends AppCompatActivity {
    private MediaPlayer audioToPlay;
    private AudioManager mAudioManager;

    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };

    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChange = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if(focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||focusChange ==  AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
                audioToPlay.pause();
                audioToPlay.seekTo(0);
            }else if(focusChange == AudioManager.AUDIOFOCUS_GAIN){
                audioToPlay.start();
            }else if(focusChange == AudioManager.AUDIOFOCUS_LOSS){
                audioToPlay.stop();
            }
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
        setContentView(R.layout.activity_phrases);

        final ArrayList<Word> families = new ArrayList<Word>();
        families.add(new Word("Where are you going?","minto wuksus",R.raw.phrase_where_are_you_going));
        families.add(new Word("What is your name?","tinnә oyaase'nә",R.raw.phrase_what_is_your_name));
        families.add(new Word("My name is...","oyaaset...",R.raw.phrase_my_name_is));
        families.add(new Word("How are you feeling?","michәksәs?",R.raw.phrase_how_are_you_feeling));
        families.add(new Word("I’m feeling good.","kuchi achit",R.raw.phrase_im_feeling_good));
        families.add(new Word("Are you coming?","әәnәs'aa?",R.raw.phrase_are_you_coming));
        families.add(new Word("Yes, I’m coming.","hәә’ әәnәm",R.raw.phrase_yes_im_coming));
        families.add(new Word("I’m coming.","әәnәm",R.raw.phrase_im_coming));
        families.add(new Word("Let’s go.","yoowutis",R.raw.phrase_lets_go));
        families.add(new Word("Come here.","әnni'nem",R.raw.phrase_come_here));

        WordAdapter phrasesAdapter = new WordAdapter(this,families, R.color.category_phrases);

        final ListView listViewFam = (ListView)findViewById(R.id.listView_phrases);
        listViewFam.setAdapter(phrasesAdapter);

        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        listViewFam.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Word currentWord = families.get(i);
                releaseMediaPlayer();
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChange,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN);
                if(result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    audioToPlay = MediaPlayer.create(PhrasesActivity.this, currentWord.getAudioAttached());
                    audioToPlay.start();
                    audioToPlay.setOnCompletionListener(mCompletionListener);
                }
                mAudioManager.abandonAudioFocus(mOnAudioFocusChange);
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }
}
