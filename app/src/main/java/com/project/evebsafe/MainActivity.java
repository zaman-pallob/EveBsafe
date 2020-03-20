package com.project.evebsafe;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.project.evebsafe.Databases.SharedPreference;
import com.project.evebsafe.DialogBoxes.AlreadyRegistered;
import com.project.evebsafe.DialogBoxes.PatternDialog;
import com.project.evebsafe.Interfaces.ActivityListener;
import com.project.evebsafe.Model.Result;
import com.project.evebsafe.Network.ApiClient;
import com.project.evebsafe.Network.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener , ActivityListener {

    String Name, Number, Email, Address;
    Button Save, Cancel;
    EditText EditName, EditNumber, EditEmail, EditAddress;
    TextView AlradyRegister;
    SharedPreference preference;
    PatternDialog patternDialog;
    AlreadyRegistered registered;
    ActivityListener listener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preference=new SharedPreference(this);
        listener=this;


        if (preference.isRegistered()) {

            if (preference.isLocked()) {
              patternDialog=new PatternDialog(this);
            }

            setContentView(R.layout.activity_main);
            TextView textView=findViewById(R.id.tv);
            textView.setText(preference.getNames()+"\n"+preference.getNumber()+"\n"+preference.getEmail() +"\n"+preference.getAddress());


        } else {
            setContentView(R.layout.registration);
            EditName = findViewById(R.id.name);
            EditNumber = findViewById(R.id.number);
            EditEmail = findViewById(R.id.email);
            EditAddress = findViewById(R.id.address);
            Save = findViewById(R.id.save);
            Cancel = findViewById(R.id.cancel);
            AlradyRegister = findViewById(R.id.alreadyregisterd);
            Save.setOnClickListener(this);
            Cancel.setOnClickListener(this);
            AlradyRegister.setOnClickListener(this);
        }


    }



    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.save:
                if (hasConnection()){

                    SaveInformation();

                }else {
                    Toast.makeText(this, "No internet connections", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.cancel:

                finish();

                break;

            case R.id.alreadyregisterd:
                   registered=new AlreadyRegistered(this,listener,preference);
                break;

        }

    }

    public boolean hasConnection(){
        NetworkInfo activeNetworkInfo = ((ConnectivityManager)getSystemService(CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }

    public void SaveInformation() {
        Name = EditName.getText().toString();
        Number = EditNumber.getText().toString();
        Email = EditEmail.getText().toString();
        Address = EditAddress.getText().toString();
        postToServer(Name,Number,Email,Address);
    }


    public void postToServer(String name, String number, String email, String address) {

        ApiService apiService= ApiClient.getClient().create(ApiService.class);
        Call<Result>call=apiService.register(number,name,email,address);


        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {

                switch (response.body().getResponse()){

                    case "0":
                        Toast.makeText(MainActivity.this, "Failed to register", Toast.LENGTH_SHORT).show();
                        break;
                    case "1":
                        Toast.makeText(MainActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                        preference.saveUser(Name,Number,Email,Address);

                        restart();
                        break;
                    case "2":
                        Toast.makeText(MainActivity.this, "User Exists!!!", Toast.LENGTH_SHORT).show();
                        preference.saveUser(Name,Number,Email,Address);
                        restart();
                        break;

                }

            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failed To Register", Toast.LENGTH_SHORT).show();
            }
        });


    }


    @Override
    public void Event() {
            restart();
    }

    public void restart(){
        Intent intent=new Intent(MainActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }

}
