package tw.edu.fcu.vegetablemaster;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import tw.edu.fcu.vegetablemaster.firebase.FirebaseFunction;
import tw.edu.fcu.vegetablemaster.firebase.Vegetable;

public class HomePage extends AppCompatActivity {

    private static final String TAG = "HomePage";
    Button compareBtn, forecastBtn, historyBtn, suggestBtn, recipeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       // compareBtn = (Button) findViewById(R.id.compareveg);
       // forecastBtn = (Button) findViewById(R.id.forecastveg);
        historyBtn = (Button) findViewById(R.id.historyveg);
        suggestBtn = (Button) findViewById(R.id.suggestveg);
      //  recipeBtn = (Button) findViewById(R.id.recipe);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("vegetables/today");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                ArrayList<Vegetable> list = new ArrayList<Vegetable>();
                for(DataSnapshot vegSnapshot: dataSnapshot.getChildren()) {
                    list.add(vegSnapshot.getValue(Vegetable.class));
                }
                for(Vegetable veg: list) {
                    Log.d(TAG, "onDataChange: "+ veg.name + " " + veg.avg_price);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
/*
        compareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(HomePage.this, CompareVeg.class);
                startActivity(intent);
            }
        });

        forecastBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(HomePage.this, ForecastVeg.class);
                startActivity(intent);
            }
        });
*/
        historyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(HomePage.this, HistoryVeg.class);
                startActivity(intent);
            }
        });

        suggestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(HomePage.this, SuggestVeg.class);
                startActivity(intent);
            }
        });
/*
        recipeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(HomePage.this, Recipe.class);
                startActivity(intent);
            }
        });
*/
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
