package com.example.android.miwok;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.media.MediaPlayer;

/**
 * Created by kailash on 11/3/2016.
 */
public class Word {
    private String mDefaultTranslation;
    private String mMiwokTransalation;
    private int NO_IMAGE_PROVIDED = -1;
    private int mImageResourceId = NO_IMAGE_PROVIDED;
    private int audioAttached;

    public Word(String defaultTranslation, String miwokTransaltion, int imageResourceId, int audioToPlay ){
        this.mDefaultTranslation = defaultTranslation;
        this.mMiwokTransalation = miwokTransaltion;
        this.mImageResourceId = imageResourceId;
        this.audioAttached = audioToPlay;
    }

    public Word(String defaultTranslation, String miwokTransaltion,int audioToPlay){
        this.mDefaultTranslation = defaultTranslation;
        this.mMiwokTransalation = miwokTransaltion;
        this.audioAttached = audioToPlay;
    }

    public String getmDefaultTranslation(){
        return this.mDefaultTranslation;
    }

    public String getmMiwokTransalation(){
        return this.mMiwokTransalation;
    }

    public int getImageResourceId(){
        return this.mImageResourceId;
    }

    public boolean getImagePresence(){ return this.mImageResourceId!=-1; }

    public int getAudioAttached(){
        return this.audioAttached;
    }
}
