package com.rsjms.daftar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.rsjms.daftar.R;
import com.google.android.material.card.MaterialCardView;

public class informasi extends AppCompatActivity {
    RelativeLayout line1, line2,line3,line4,line5,line6;
    ImageButton showButton,showButton2,showButton3,showButton6,showButton5,showButton7;
    LinearLayout hidden,hidden2,hidden3,hidden6,hidden5,hidden7;
    CardView cardView,cardView2,cardView3,cardView6,cardView5,cardView7;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informasi);

        line1 = findViewById(R.id.relativ1);
        line2 = findViewById(R.id.relativ2);
        line3 = findViewById(R.id.relativ3);
        line4 = findViewById(R.id.relativ4);
        line5 = findViewById(R.id.relativ5);
        line6 = findViewById(R.id.relativ6);


        line1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(hidden.getVisibility() == View.VISIBLE){
                    TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
                    hidden.setVisibility(View.GONE);
                    showButton.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24);

                }else{
                    TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
                    hidden.setVisibility(View.VISIBLE);
                    showButton.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24);

                }

            }
        });

        line2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(hidden2.getVisibility() == View.VISIBLE){
                    TransitionManager.beginDelayedTransition(cardView2, new AutoTransition());
                    hidden2.setVisibility(View.GONE);
                    showButton2.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24);

                }else{
                    TransitionManager.beginDelayedTransition(cardView2, new AutoTransition());
                    hidden2.setVisibility(View.VISIBLE);
                    showButton2.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24);

                }

            }
        });
        line3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(hidden3.getVisibility() == View.VISIBLE){
                    TransitionManager.beginDelayedTransition(cardView3, new AutoTransition());
                    hidden3.setVisibility(View.GONE);
                    showButton3.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24);

                }else{
                    TransitionManager.beginDelayedTransition(cardView3, new AutoTransition());
                    hidden3.setVisibility(View.VISIBLE);
                    showButton3.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24);

                }

            }
        });

        line4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(hidden5.getVisibility() == View.VISIBLE){
                    TransitionManager.beginDelayedTransition(cardView5, new AutoTransition());
                    hidden5.setVisibility(View.GONE);
                    showButton5.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24);

                }else{
                    TransitionManager.beginDelayedTransition(cardView5, new AutoTransition());
                    hidden5.setVisibility(View.VISIBLE);
                    showButton5.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24);

                }

            }
        });


        line5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(hidden7.getVisibility() == View.VISIBLE){
                    TransitionManager.beginDelayedTransition(cardView7, new AutoTransition());
                    hidden7.setVisibility(View.GONE);
                    showButton7.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24);

                }else{
                    TransitionManager.beginDelayedTransition(cardView7, new AutoTransition());
                    hidden7.setVisibility(View.VISIBLE);
                    showButton7.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24);

                }

            }
        });

        line6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(hidden6.getVisibility() == View.VISIBLE){
                    TransitionManager.beginDelayedTransition(cardView6, new AutoTransition());
                    hidden6.setVisibility(View.GONE);
                    showButton6.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24);

                }else{
                    TransitionManager.beginDelayedTransition(cardView6, new AutoTransition());
                    hidden6.setVisibility(View.VISIBLE);
                    showButton6.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24);

                }

            }
        });


        showButton = findViewById(R.id.menu1);
        hidden = findViewById(R.id.lineInfo1);
        cardView = findViewById(R.id.cardLayout1);

        showButton.setOnClickListener(view -> {
            if(hidden.getVisibility() == View.VISIBLE){
                TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
                hidden.setVisibility(View.GONE);
                showButton.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24);

            }else{
                TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
                hidden.setVisibility(View.VISIBLE);
                showButton.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24);

            }
        });

        showButton2 = findViewById(R.id.menu2);
        hidden2 = findViewById(R.id.lineInfo2);
        cardView2 = findViewById(R.id.cardLayout2);

        showButton2.setOnClickListener(view -> {
            if(hidden2.getVisibility() == View.VISIBLE){
                TransitionManager.beginDelayedTransition(cardView2, new AutoTransition());
                hidden2.setVisibility(View.GONE);
                showButton2.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24);

            }else{
                TransitionManager.beginDelayedTransition(cardView2, new AutoTransition());
                hidden2.setVisibility(View.VISIBLE);
                showButton2.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24);

            }
        });

        showButton3 = findViewById(R.id.menu3);
        hidden3 = findViewById(R.id.lineInfo3);
        cardView3 = findViewById(R.id.cardLayout3);

        showButton3.setOnClickListener(view -> {
            if(hidden3.getVisibility() == View.VISIBLE){
                TransitionManager.beginDelayedTransition(cardView3, new AutoTransition());
                hidden3.setVisibility(View.GONE);
                showButton3.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24);

            }else{
                TransitionManager.beginDelayedTransition(cardView3, new AutoTransition());
                hidden3.setVisibility(View.VISIBLE);
                showButton3.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24);

            }
        });



        showButton5 = findViewById(R.id.menu4);
        hidden5 = findViewById(R.id.lineInfo4);
        cardView5 = findViewById(R.id.cardLayout4);


        showButton5.setOnClickListener(view -> {
            if(hidden5.getVisibility() == View.VISIBLE){
                TransitionManager.beginDelayedTransition(cardView5, new AutoTransition());
                hidden5.setVisibility(View.GONE);
                showButton5.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24);

            }else{
                TransitionManager.beginDelayedTransition(cardView5, new AutoTransition());
                hidden5.setVisibility(View.VISIBLE);
                showButton5.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24);

            }
        });

        showButton6 = findViewById(R.id.menu6);
        hidden6 = findViewById(R.id.lineInfo6);
        cardView6 = findViewById(R.id.cardLayout6);

        showButton6.setOnClickListener(view -> {
            if(hidden6.getVisibility() == View.VISIBLE){
                TransitionManager.beginDelayedTransition(cardView6, new AutoTransition());
                hidden6.setVisibility(View.GONE);
                showButton6.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24);

            }else{
                TransitionManager.beginDelayedTransition(cardView6, new AutoTransition());
                hidden6.setVisibility(View.VISIBLE);
                showButton6.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24);

            }
        });

        showButton7 = findViewById(R.id.menu5);
        hidden7 = findViewById(R.id.lineInfo5);
        cardView7 = findViewById(R.id.cardLayout5);

        showButton7.setOnClickListener(view -> {
            if(hidden7.getVisibility() == View.VISIBLE){
                TransitionManager.beginDelayedTransition(cardView7, new AutoTransition());
                hidden7.setVisibility(View.GONE);
                showButton7.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24);

            }else{
                TransitionManager.beginDelayedTransition(cardView7, new AutoTransition());
                hidden7.setVisibility(View.VISIBLE);
                showButton7.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24);

            }
        });
    }
}