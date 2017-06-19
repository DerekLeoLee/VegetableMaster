package tw.edu.fcu.vegetablemaster.firebase;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by Red on 2017/6/19.
 */

@IgnoreExtraProperties
public class Vegetable {
    public Double high_price;
    public Double mid_price;
    public Double low_price;
    public Double avg_price;
    public String name;

    public Vegetable() {

    }

    public Vegetable(String name, Double high_price, Double mid_price, Double low_price, Double avg_price) {
        this.name = name;
        this.high_price = high_price;
        this.mid_price = mid_price;
        this.low_price = low_price;
        this.avg_price = avg_price;
    }
}
