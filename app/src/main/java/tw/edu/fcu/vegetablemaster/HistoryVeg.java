package tw.edu.fcu.vegetablemaster;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import tw.edu.fcu.vegetablemaster.firebase.Vegetable;

public class HistoryVeg extends AppCompatActivity {
    private static final String TAG = "AppCompatActivity";
    Spinner spinner;
    TextView vegName;
    TextView todayPrice;
    TextView yesterdayPrice;
    ImageView yesterdayPic;
    ImageView todayPic;
    ArrayList<Vegetable> yesterdayVeg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_veg);
        spinner = (Spinner) findViewById(R.id.spinner);
        vegName = (TextView) findViewById(R.id.showhisveg_1);
        todayPrice = (TextView) findViewById(R.id.showhisveg_3);
        yesterdayPrice = (TextView) findViewById(R.id.showhisveg_2);
        todayPic = (ImageView) findViewById(R.id.showhisvegpic_2);
        yesterdayPic = (ImageView) findViewById(R.id.showhisvegpic_1);

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference yesterdayRef = database.getReference("vegetables/yesterday");
        yesterdayRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                ArrayList<Vegetable> list = new ArrayList<Vegetable>();
                for (DataSnapshot vegSnapshot : dataSnapshot.getChildren()) {
                    list.add(vegSnapshot.getValue(Vegetable.class));
                }
                yesterdayVeg = list;
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

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
                ArrayAdapter<Vegetable> adapter = new ArrayAdapter<Vegetable>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, list);
                spinner.setAdapter(adapter);
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        Vegetable vegSelected = (Vegetable) (parent.getItemAtPosition(position));
                        vegName.setText(vegSelected.toString());
                        todayPrice.setText("現在 " + vegSelected.avg_price.toString() + " 台幣/台斤");
                        for (Vegetable veg : yesterdayVeg) {
                            if (veg.name.equals(vegSelected.name)) {
                                yesterdayPrice.setText("過去 " + veg.avg_price.toString() + " 台幣/台斤");
                            }
                        }
                        todayPic.setImageResource(getImageId(vegSelected.name));
                        yesterdayPic.setImageResource(getImageId(vegSelected.name));
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
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
                return  R.drawable.spinach_2;
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
