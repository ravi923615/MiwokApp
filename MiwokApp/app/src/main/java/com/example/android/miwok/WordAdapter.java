package com.example.android.miwok;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by kailash on 11/3/2016.
 */
public class WordAdapter extends ArrayAdapter<Word> {

    private int mColorofActivity;


    public WordAdapter(Context context, ArrayList<Word> objects, int colorofActivity) {
        super(context,0,objects);
        mColorofActivity = colorofActivity;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //get the current word position
        Word currentWord = getItem(position);

        //Get the List View to view object.
        View listItemView = convertView;
        if(listItemView==null){
            listItemView = LayoutInflater.from(getContext())
                    .inflate(R.layout.list_view_item,parent,false);
        }

        //Get the textview to view default translation
        TextView currentDefaultTranslation = (TextView) listItemView.findViewById(R.id.default_text_view);
        currentDefaultTranslation.setText(currentWord.getmDefaultTranslation());

        //Get the textview to view miwok translation
        TextView currentMiwokTranslation = (TextView) listItemView.findViewById(R.id.miwok_text_view);
        currentMiwokTranslation.setText(currentWord.getmMiwokTransalation());

        //Get the Image to view the image
        ImageView imageView = (ImageView) listItemView.findViewById(R.id.attach_image);
        if(currentWord.getImagePresence()) {
            imageView.setImageResource(currentWord.getImageResourceId());
        }else{
            imageView.setVisibility(View.GONE);
        }

        //Find the theme of the app.
        View textViewContainer = listItemView.findViewById(R.id.text_view_container);

        //Find the color that resource id maps to.
        int color = ContextCompat.getColor(getContext(),mColorofActivity);

        //Set the background color.
        textViewContainer.setBackgroundColor(color);

        return listItemView;
    }
}
