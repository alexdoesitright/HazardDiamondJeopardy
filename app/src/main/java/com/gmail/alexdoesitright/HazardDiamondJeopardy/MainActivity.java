package com.gmail.alexdoesitright.HazardDiamondJeopardy;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.transition.Fade;
import android.transition.Slide;
import android.transition.TransitionManager;
import android.util.AttributeSet;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

public class MainActivity extends AppCompatActivity
{

    Fade      crossFader;
    Slide     slide;
    ViewGroup parentView;// The root view the animation will take place on
    CardView cardView;

    // Field to hold the roll result text
    TextView tvAnswerText;

    // Field to hold the score
    int score;

    // Field to hold the score text
    TextView scoreText;

    // declare image view to hold hazard diamond image
    ImageView ivHazDiamond;
    // Field to hold random number generator
    Random    rand;

    // Fields to hold the die values

    int imgIndex;


    private String[] msg;

    public MainActivity()
    {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(com.gmail.alexdoesitright.HazardDiamondJeopardy.R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(com.gmail.alexdoesitright.HazardDiamondJeopardy.R.id.toolbar);
        setSupportActionBar(toolbar);
        cardView = findViewById(R.id.targetLayoutView);
        // Set initial score
        score = 0;


        // Link instances to widgets in the activity view
        tvAnswerText = (TextView) findViewById(com.gmail.alexdoesitright.HazardDiamondJeopardy.R.id.tvAnswerText);// returns android.view.View object
        scoreText = (TextView) findViewById(com.gmail.alexdoesitright.HazardDiamondJeopardy.R.id.tvAnswerText);

        // Initialize the random number generator (For choosing a random image/question out of the answers array)
        rand = new Random();


        // Access the ImageView widget
        ivHazDiamond = (ImageView) findViewById(com.gmail.alexdoesitright.HazardDiamondJeopardy.R.id.diamond2Image);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(com.gmail.alexdoesitright.HazardDiamondJeopardy.R.menu.menu_main, menu);
        return true;
    }


    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs)
    {
        return super.onCreateView(name, context, attrs);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP) @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        switch (id)
        {
            case R.id.action_show_answer:
                //tvAnswerText.setTextColor(getResources().getColor(R.color.primaryLightColor));
                ObjectAnimator animatior = ObjectAnimator.ofFloat(cardView, "rotationY", 360f) // 360 degrees
                                                        .setDuration(1000); // 1s
                animatior.start();

                    tvAnswerText.setVisibility(View.VISIBLE);// Show the correct answer

                break;
            case R.id.action_quit:
                // code to exit app
                System.exit(0);
                break;
            case R.id.action_next:
                //crossFader = new Fade(Fade.IN);// A Fade animation, fade in then fade out


                // hide the correct answer
                tvAnswerText.setVisibility(View.INVISIBLE);

                // choose a random question from the array.
                imgIndex = rand.nextInt(20) + 1;


                // change firehazard img

                String imageName = "hazardDiamond_" + imgIndex + ".png";

                try
                {
                    InputStream stream = getAssets().open(imageName);
                    Drawable    d      = Drawable.createFromStream(stream, imageName);
                    ivHazDiamond.setImageDrawable(d);
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }


                // Array to hold the answers corresponding to each image in assets folder
                msg = new String[]{"Fire Hazard, Will not burn.", // red square 0
                        "Fire Hazard, Will ignite(catch fire) at Above 200 degrees F.",//red square 1
                        "Fire Hazard, Will ignite(catch fire) at Above 100 degrees F.",//red square 2
                        "Fire Hazard, Will ignite(catch fire) at Below 100 degrees F.",//red square 3
                        "Fire Hazard, Will ignite(catch fire) at Below 73 degrees F.",//red square 3
                        "Specific Hazard, Radioactive",//white square - radioactive
                        "Health Hazard, Deadly",//blue square 4 deadly
                        "Health Hazard, Extreme Danger.",//blue square 3
                        "Health Hazard, Hazardous.",//blue square 2
                        "Health Hazard, Slightly Hazardous.",//blue square 1
                        "Health Hazard, Normal Material.",//blue square 1
                        "Reactivity Hazard, May Detonate.",//blue square 1
                        "Reactivity Hazard, Shock and Heat may detonate.",//blue square 1
                        "Reactivity Hazard, Violent Chemical Change.",//Yellow square 1
                        "Reactivity Hazard, Unstable if Heated.", //blue square 1
                        "Specific Hazard, Use no water.", //blue square 1
                        "Specific Hazard, Corrosive.",//blue square 1
                        "Specific Hazard, Alkali.",//blue square 1
                        "Specific Hazard, Acid.",//blue square 1
                        "Specific Hazard, Oxidizer."};//blue square 1


                // Update tvAnswerText to display the text corresponding to the displayed image.
                tvAnswerText.setText(msg[imgIndex - 1] + "");
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }
}
