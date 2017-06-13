package tw.edu.fcu.vegetablemaster;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import tw.edu.fcu.vegetablemaster.firebase.FirebaseFunction;

public class SuggestVeg extends AppCompatActivity {

    private static final String TAG = "SuggestVeg";
    private TextView sugTop1;
    private TextView sugTop2;
    private TextView sugTop3;

    private ImageView topImage1;
    private ImageView topImage2;
    private ImageView topImage3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggest_veg);

        sugTop1 = (TextView) findViewById(R.id.top3_1);
        sugTop2 = (TextView) findViewById(R.id.top3_2);
        sugTop3 = (TextView) findViewById(R.id.top3_3);

        topImage1 = (ImageView)findViewById(R.id.topImage01);
        topImage2 = (ImageView)findViewById(R.id.topImage02);
        topImage3 = (ImageView)findViewById(R.id.topImage03);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("tops");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String top1 = dataSnapshot.child("top1").getValue(String.class);
                String top2 = dataSnapshot.child("top2").getValue(String.class);
                String top3 = dataSnapshot.child("top3").getValue(String.class);
                //String[] tops = FirebaseFunction.getTops();

                sugTop1.setText("1. "+top1+" NT$50"+"/台斤");
                sugTop2.setText("2. "+top2+" NT$50"+"/台斤");
                sugTop3.setText("3. "+top3+" NT$50"+"/台斤");

                topImage1.setImageResource(getImageId(top1));
                topImage2.setImageResource(getImageId(top2));
                topImage3.setImageResource(getImageId(top3));


                Log.d(TAG, "Value is: " + top1 + top2 + top3);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }

    private int getImageId(String name){

        switch (name){
            case "白菜":
                return R.drawable.chinese_cabbage;
            case "高麗菜":
                return R.drawable.cabbage;
            case "空心菜":
                return R.drawable.spinach;
        }
        return R.drawable.spinach;
    }

}
