package tw.edu.fcu.vegetablemaster;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import tw.edu.fcu.vegetablemaster.firebase.FirebaseFunction;
import tw.edu.fcu.vegetablemaster.firebase.Vegetable;

public class SuggestVeg extends AppCompatActivity {

    private static final String TAG = "SuggestVeg";
    private TextView sugTop1;
    private TextView sugTop2;
    private TextView sugTop3;

    private ImageView topImage1;
    private ImageView topImage2;
    private ImageView topImage3;

    private ArrayList<Vegetable> todayVeg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggest_veg);

        sugTop1 = (TextView) findViewById(R.id.top3_1);
        sugTop2 = (TextView) findViewById(R.id.top3_2);
        sugTop3 = (TextView) findViewById(R.id.top3_3);

        topImage1 = (ImageView) findViewById(R.id.topImage01);
        topImage2 = (ImageView) findViewById(R.id.topImage02);
        topImage3 = (ImageView) findViewById(R.id.topImage03);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference todayRef = database.getReference("vegetables/today");
        todayRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                ArrayList<Vegetable> list = new ArrayList<Vegetable>();
                for (DataSnapshot vegSnapshot : dataSnapshot.getChildren()) {
                    list.add(vegSnapshot.getValue(Vegetable.class));
                }
                todayVeg = list;

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

                        for(Vegetable veg : todayVeg) {
                            if(veg.name.equals(top1))
                                sugTop1.setText("1. " + top1 + " NT$" + veg.avg_price + "/台斤");
                            if(veg.name.equals(top2))
                                sugTop2.setText("2. " + top2 + " NT$" + veg.avg_price + "/台斤");
                            if(veg.name.equals(top3))
                                sugTop3.setText("3. " + top3 + " NT$" + veg.avg_price +  "/台斤");
                        }

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

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    private int getImageId(String name) {

        switch (name) {

            case "芹菜":
                return R.drawable.celery;
            case "高麗菜":
                return R.drawable.cabbage;
            case "白菜":
                return R.drawable.chinese_cabbage;
            case "空心菜-小葉":
                return R.drawable.spinach_1;
            case "空心菜-大葉":
                return R.drawable.spinach_1;
            case "空心菜-水空心菜":
                return R.drawable.spinach_1;
            case "空心菜":
                return R.drawable.spinach_1;
            case "菠菜":
                return R.drawable.spinach_2;
            case "花椰菜":
                return R.drawable.cauliflower;
            case "青花椰菜":
                return R.drawable.broccoli;
            case "杏鮑菇":
                return R.drawable.pleurotus;
            case "青蔥-北蔥":
                return R.drawable.shallot;
            case "青蔥-粉蔥":
                return R.drawable.shallot;
            case "青蔥":
                return R.drawable.shallot;
            case "皇宮菜-大葉":
                return R.drawable.palacedishes;
            case "皇宮菜-小葉":
                return R.drawable.palacedishes;
            case "胡蘿蔔":
                return R.drawable.carrot_1;
            case "白蘿蔔":
                return R.drawable.carrot_2;
            case "青江菜":
                return R.drawable.qingjiangcuisine;
        }
        return R.drawable.nonameveg;
    }

}
