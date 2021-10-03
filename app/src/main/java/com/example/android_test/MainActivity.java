package com.example.android_test;

import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.clevertap.android.sdk.CleverTapAPI;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    EditText edt_name, edt_email, edt_mobno, edt_dob, edt_gender;
    Button btn_login, btn_Skip;
    String name, email, mobno, dob, gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CleverTapAPI clevertapDefaultInstance = CleverTapAPI.getDefaultInstance(getApplicationContext());
        CleverTapAPI.setDebugLevel(CleverTapAPI.LogLevel.DEBUG);

        clevertapDefaultInstance.createNotificationChannel(getApplicationContext(),"0411","Ct_Tej","Hello!! how are you?", NotificationManager.IMPORTANCE_MAX,true);

        edt_name = findViewById(R.id.edt_name);
        edt_email = findViewById(R.id.edt_email);
        edt_mobno = findViewById(R.id.edt_mobno);
        edt_dob =  findViewById(R.id.edt_dob);
        edt_gender = findViewById(R.id.edt_gender);

        btn_login = findViewById(R.id.btn_login);
        btn_Skip = findViewById(R.id.btn_Skip);


        btn_login.setOnClickListener(view -> {

            name = edt_name.getText().toString();
            email = edt_email.getText().toString();
            mobno = edt_mobno.getText().toString();
            dob = edt_dob.getText().toString();
            gender = edt_gender.getText().toString();

            Log.i("CleverTap", "Oncreate: Name = " + name);
            Log.i("CleverTap", "Oncreate: Email = " + email);
            Log.i("CleverTap", "Oncreate: Mob_No = " + mobno);
            Log.i("CleverTap", "Oncreate: DoB = " + dob);
            Log.i("CleverTap", "Oncreate: Gender = " + gender);

            HashMap<String, Object> profileUpdate = new HashMap<String, Object>();
            profileUpdate.put("Name", name);
            profileUpdate.put("Email", email);
            profileUpdate.put("Mob_No", mobno);
            profileUpdate.put("DoB", dob);
            profileUpdate.put("Gender", gender);

            profileUpdate.put("MSG-email", true);
            profileUpdate.put("MSG-push", true);

            assert clevertapDefaultInstance != null;
            clevertapDefaultInstance.onUserLogin(profileUpdate);
            Toast.makeText(MainActivity.this, "Login Sucessfull", Toast.LENGTH_LONG).show();

//            CleverTapAPI.setDebugLevel(Integer.parseInt(email));
            goHome();

        });


        btn_Skip.setOnClickListener(view -> {
            goHome();

        });

    }

    private void goHome(){
        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
        startActivity(intent);
    }

}