package tw.edu.fcu.vegetablemaster;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class homepage extends AppCompatActivity {

    Button compareBtn, forecastBtn, historyBtn, suggestBtn, recipeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        compareBtn = (Button)findViewById(R.id.compareveg);
        forecastBtn =(Button)findViewById(R.id.forecastveg);
        historyBtn = (Button)findViewById(R.id.historyveg);
        suggestBtn = (Button)findViewById(R.id.suggestveg) ;
        recipeBtn = (Button)findViewById(R.id.recipe);

        compareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(homepage.this, CompareVeg.class);
                startActivity(intent);
            }
        });

        forecastBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(homepage.this, ForecastVeg.class);
                startActivity(intent);
            }
        });

        historyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(homepage.this, HistoryVeg.class);
                startActivity(intent);
            }
        });

        suggestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(homepage.this, SuggestVeg.class);
                startActivity(intent);
            }
        });

        recipeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(homepage.this, Recipe.class);
                startActivity(intent);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
