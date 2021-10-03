package com.example.android_test;

import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationManager;
import android.os.Bundle;
import android.widget.Button;

import com.clevertap.android.sdk.CTInboxListener;
import com.clevertap.android.sdk.CTInboxStyleConfig;
import com.clevertap.android.sdk.CleverTapAPI;

import java.util.ArrayList;
import java.util.HashMap;

public class HomeActivity extends AppCompatActivity implements CTInboxListener {

    Button update_profile, others, app_inbox, add_to_cart, product_view, charge;
    CleverTapAPI clevertapDefaultInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        CleverTapAPI clevertapDefaultInstance = CleverTapAPI.getDefaultInstance(getApplicationContext());
        CleverTapAPI.setDebugLevel(CleverTapAPI.LogLevel.DEBUG);

        clevertapDefaultInstance.createNotificationChannel(getApplicationContext(),"0411","Ct_Tej","Hello!! how are you?", NotificationManager.IMPORTANCE_MAX,true);

        if (clevertapDefaultInstance != null) {
            //Set the Notification Inbox Listener
            clevertapDefaultInstance.setCTNotificationInboxListener(this);
            //Initialize the inbox and wait for callbacks on overridden methods
            clevertapDefaultInstance.initializeInbox();
        }

        update_profile = findViewById(R.id.btn_update);
        charge = findViewById(R.id.btn_charge);
        product_view = findViewById(R.id.btn_productview);
        add_to_cart = findViewById(R.id.btn_addtocart);
        app_inbox = findViewById(R.id.btn_appinbox);
        others = findViewById(R.id.btn_others);

        update_profile.setOnClickListener(view -> {
            HashMap<String, Object> profileUpdate = new HashMap<String, Object>();
            profileUpdate.put("Phone", 1234567890);                 // Phone (with the country code, starting with +)
            profileUpdate.put("Gender", "Male");                           // Can be either M or F
            clevertapDefaultInstance.pushProfile(profileUpdate);
        });

        charge.setOnClickListener(view -> {

            HashMap<String, Object> chargeDetails = new HashMap<String, Object>();
            chargeDetails.put("Amount", 300);
            chargeDetails.put("Payment Mode", "Credit card");
            chargeDetails.put("Charged ID", 24052013);

            HashMap<String, Object> item1 = new HashMap<String, Object>();
            item1.put("Product category", "books");
            item1.put("Book name", "The Millionaire next door");
            item1.put("Quantity", 1);

            HashMap<String, Object> item2 = new HashMap<String, Object>();
            item2.put("Product category", "books");
            item2.put("Book name", "Achieving inner zen");
            item2.put("Quantity", 1);

            HashMap<String, Object> item3 = new HashMap<String, Object>();
            item3.put("Product category", "books");
            item3.put("Book name", "Chuck it, let's do it");
            item3.put("Quantity", 5);

            ArrayList<HashMap<String, Object>> items = new ArrayList<HashMap<String, Object>>();
            items.add(item1);
            items.add(item2);
            items.add(item3);

            clevertapDefaultInstance.pushChargedEvent(chargeDetails, items);
        });

        product_view.setOnClickListener(view -> {
            HashMap<String, Object> prodViewedAction = new HashMap<String, Object>();
            prodViewedAction.put("Product Name", "Casio Chronograph Watch");
            prodViewedAction.put("Category", "Mens Accessories");
            prodViewedAction.put("Price", 59.99);
            prodViewedAction.put("Date", new java.util.Date());
            clevertapDefaultInstance.pushEvent("Product Viewed", prodViewedAction);
        });

        add_to_cart.setOnClickListener(view -> {
            HashMap<String, Object> prodViewedAction = new HashMap<String, Object>();
            prodViewedAction.put("Product Name", "Casio Chronograph Watch");
            prodViewedAction.put("Category", "Mens Accessories");
            prodViewedAction.put("Price", 59.99);
            prodViewedAction.put("Date", new java.util.Date());
            clevertapDefaultInstance.pushEvent("Add To Cart", prodViewedAction);

        });

    }

    @Override
    public void inboxDidInitialize() {
        ArrayList<String> tabs = new ArrayList<>();
        tabs.add("Promotions");
        tabs.add("Offers");

        CTInboxStyleConfig styleConfig = new CTInboxStyleConfig();
        styleConfig.setFirstTabTitle("First Tab");
        styleConfig.setTabs(tabs);//Do not use this if you don't want to use tabs
        styleConfig.setTabBackgroundColor("#FF0000");
        styleConfig.setSelectedTabIndicatorColor("#0000FF");
        styleConfig.setSelectedTabColor("#0000FF");
        styleConfig.setUnselectedTabColor("#FFFFFF");
        styleConfig.setBackButtonColor("#FF0000");
        styleConfig.setNavBarTitleColor("#FF0000");
        styleConfig.setNavBarTitle("MY INBOX");
        styleConfig.setNavBarColor("#FFFFFF");
        styleConfig.setInboxBackgroundColor("#ADD8E6");
        if (clevertapDefaultInstance != null) {
            clevertapDefaultInstance.showAppInbox(styleConfig); //With Tabs

        }

    }
    @Override
    public void inboxMessagesDidUpdate() {



    }
}