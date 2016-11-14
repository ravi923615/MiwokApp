package com.example.android.miwok;

import android.media.MediaPlayer;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ColorsActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_colors);

        //Create Color Object array and store the values
        final ArrayList<Word> colorsList = new ArrayList<Word>();
        colorsList.add(new Word("red","weṭeṭṭi",R.drawable.color_red,R.raw.color_red));
        colorsList.add(new Word("green","chokokki",R.drawable.color_green,R.raw.color_green));
        colorsList.add(new Word("brown","ṭakaakki",R.drawable.color_brown,R.raw.color_brown));
        colorsList.add(new Word("gray","ṭopoppi",R.drawable.color_gray,R.raw.color_gray));
        colorsList.add(new Word("black","kululli",R.drawable.color_black,R.raw.color_black));
        colorsList.add(new Word("white","kelelli",R.drawable.color_white,R.raw.color_white));
        colorsList.add(new Word("dusty yellow","ṭopiisә",R.drawable.color_dusty_yellow,R.raw.color_dusty_yellow));
        colorsList.add(new Word("mustard yellow","chiwiiṭә",R.drawable.color_mustard_yellow,R.raw.color_mustard_yellow));


        //Create a ColorAdapter which store colors array and extends ArrayAdapter class
        WordAdapter colorsAdapter = new WordAdapter(this,colorsList,R.color.category_colors);

        //Get the listView to display the adapter
        final ListView listViewColor = (ListView) findViewById(R.id.listView_Colors);

        //Set the adapter to display the texts
        listViewColor.setAdapter(colorsAdapter);

        listViewColor.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Word currentWord = colorsList.get(i);
                releaseMediaPlayer();
                audioToPlay = MediaPlayer.create(ColorsActivity.this,currentWord.getAudioAttached());
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
