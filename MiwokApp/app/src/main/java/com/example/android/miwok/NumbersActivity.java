package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Objects;

public class NumbersActivity extends AppCompatActivity {
    private  MediaPlayer audioToPlay;
    private AudioManager mAudioManager;

    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChange = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                if (audioToPlay != null) {
                    audioToPlay.pause();
                    audioToPlay.seekTo(0);
                }
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                if (audioToPlay != null) {
                    releaseMediaPlayer();
                }
            }else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                audioToPlay.start();
            }
        }
    };

    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener(){

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
        setContentView(R.layout.activity_numbers);

        //Create array of string word
        final ArrayList<Word> wordNumber = new ArrayList<Word>();
        wordNumber.add(new Word("One","Lutti",R.drawable.number_one,R.raw.number_one));
        wordNumber.add(new Word("Two","Otiiko",R.drawable.number_two,R.raw.number_two));
        wordNumber.add(new Word("Three","Tolookosu",R.drawable.number_three,R.raw.number_three));
        wordNumber.add(new Word("Four","Oyyisa",R.drawable.number_four,R.raw.number_four));
        wordNumber.add(new Word("Five","Massokka",R.drawable.number_five,R.raw.number_five));
        wordNumber.add(new Word("Six","Temmokka",R.drawable.number_six,R.raw.number_six));
        wordNumber.add(new Word("Seven","Kenekaku",R.drawable.number_seven,R.raw.number_seven));
        wordNumber.add(new Word("Eight","Kawinta",R.drawable.number_eight,R.raw.number_eight));
        wordNumber.add(new Word("Nine","wo'e",R.drawable.number_nine,R.raw.number_nine));
        wordNumber.add(new Word("Ten","na'aacha",R.drawable.number_ten,R.raw.number_ten));

        WordAdapter adapter = new WordAdapter(this,wordNumber, R.color.category_numbers);

        //Create a list view
        final ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(adapter);

        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Word currentWord = wordNumber.get(i);
                releaseMediaPlayer();
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChange,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN);
                if(result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    audioToPlay = MediaPlayer.create(NumbersActivity.this, currentWord.getAudioAttached());
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
