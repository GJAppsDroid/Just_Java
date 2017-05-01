package com.example.android.justjava;

import java.text.NumberFormat;
import java.util.Locale;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import static android.R.attr.x;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    /**
     * This method is called when the order button is clicked.
     */
    int qty = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void submitOrderplus(View view) {
        display(qty += 1);
    }

    public void submitOrderminus(View v) {
        if (qty >= 1)
            display(qty -= 1);
    }

    public void submitOrder(View v) {

        pricedisplay(calculatePrice(),orderSummery());

    }

    private int calculatePrice() {
        return qty * 5;
    }

    private String orderSummery() {
        String message = "name : Githin\n" + "Quantity : " + qty+"\n";
        return message;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.qty);
        quantityTextView.setText("" + number);
    }

    private void pricedisplay(int num,String str) {
        TextView priceview = (TextView) findViewById(R.id.price);
        priceview.setText(str+"Total = " + NumberFormat.getCurrencyInstance(new Locale("en", "in")).format(num) + "\nThank You!!!");
    }

}
