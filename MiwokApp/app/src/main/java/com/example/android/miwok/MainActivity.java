/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.miwok;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.activity_main);

        //Get the View to apply the click listener
        TextView numbersView = (TextView) findViewById(R.id.numbers);

        //Associate the clicklistener object to onClickListener method of the view.
        numbersView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Create a new intent to open the numbers activity.
                Intent numbersList = new Intent(MainActivity.this,NumbersActivity.class);
                startActivity(numbersList);
            }
        });

        //Get the All TextViews
        TextView colorView = (TextView) findViewById(R.id.colors);
        TextView familyView= (TextView) findViewById(R.id.family);
        TextView phrasesView = (TextView) findViewById(R.id.phrases);

        //Create OnclickListener event on this text view.
        colorView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent colorActivity = new Intent(MainActivity.this,ColorsActivity.class);
                startActivity(colorActivity);
            }
        });

        familyView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent familyActivity = new Intent(MainActivity.this,FamilyActivity.class);
                startActivity(familyActivity);
            }
        });

        phrasesView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent phrasesActivity = new Intent(MainActivity.this,PhrasesActivity.class);
                startActivity(phrasesActivity);
            }
        });
    }
}
