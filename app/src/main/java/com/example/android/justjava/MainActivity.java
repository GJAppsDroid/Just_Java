package com.example.android.justjava;

import java.text.NumberFormat;
import java.util.Locale;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.attr.checked;
import static android.R.attr.x;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    /**
     * This method is called when the order button is clicked.
     */
    int qty = 0;
    boolean haschecked, hascheckedChocolate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void submitOrderplus(View view) {
        if (qty < 100)
            display(qty += 1);
        else
            Toast.makeText(this, "You have exceeded the limit", Toast.LENGTH_SHORT).show();
    }

    public void submitOrderminus(View v) {
        if (qty >= 1)
            display(qty -= 1);
        else
            Toast.makeText(this, "You have 0 cups", Toast.LENGTH_SHORT).show();
    }

    public void submitOrder(View v) {

        pricedisplay(calculatePrice(), orderSummery());

    }

    //checkbox Whipped creamclass
    public void checker(View v) {
        CheckBox checkBox = (CheckBox) findViewById(R.id.checkbox);
        haschecked = checkBox.isChecked();
    }

    //checkbox Chocolate
    public void checkerChocolate(View v) {
        CheckBox checkBox = (CheckBox) findViewById(R.id.checkboxChocolate);
        hascheckedChocolate = checkBox.isChecked();
    }

    //Set the name of customer
    public String namer() {
        EditText name = (EditText) findViewById(R.id.editName);
        String cutomer = name.getText().toString();
        return cutomer;
    }


    private int calculatePrice() {
        if (hascheckedChocolate && haschecked == true) {
            return qty * 8;
        }
        if (haschecked)
            return qty * 6;
        if (hascheckedChocolate)
            return qty * 7;
        return qty * 5;
    }

    private String orderSummery() {

        String message = getString(R.string.CustName,namer()) + "\n" + getString(R.string.whippedCreamQUE,haschecked) + "\n"+getString(R.string.chocolateQUE,hascheckedChocolate)+ "\n"+getString(R.string.Quatity,qty) + "\n";
        return message;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.qty);
        quantityTextView.setText("" + number);
    }

    private void pricedisplay(int num, String str) {
        String x=NumberFormat.getCurrencyInstance(new Locale("en", "in")).format(num);
        str = str + getString(R.string.Total,NumberFormat.getCurrencyInstance().format(num)) + getText(R.string.Thank_You);
        composeEmail( str);
    }

    public void composeEmail( String str) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:default@recipient.com")); // only email apps should handle this
        //intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java Order : " + namer());
        intent.putExtra(intent.EXTRA_TEXT, str);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

}
