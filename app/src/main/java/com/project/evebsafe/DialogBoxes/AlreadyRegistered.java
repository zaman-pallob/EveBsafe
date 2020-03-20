package com.project.evebsafe.DialogBoxes;

import android.app.Dialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.project.evebsafe.Databases.SharedPreference;
import com.project.evebsafe.Interfaces.ActivityListener;
import com.project.evebsafe.Model.Restore;
import com.project.evebsafe.Model.Result;
import com.project.evebsafe.Network.ApiClient;
import com.project.evebsafe.Network.ApiService;
import com.project.evebsafe.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.CONNECTIVITY_SERVICE;

public class AlreadyRegistered extends Dialog implements View.OnClickListener {
    Button Okay,Cancel;
    EditText EditName, EditNumber;
    String Name,Number;
    Context context;
    ActivityListener listener;
    SharedPreference preference;
    public AlreadyRegistered(Context context, ActivityListener listener, SharedPreference preference) {
        super(context);
        this.context=context;
        this.listener=listener;
        this.preference=preference;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.alreadyregistereddialog);
        setCancelable(true);
        show();
        Okay=findViewById(R.id.dialogOk);
        Cancel=findViewById(R.id.dialogCancel);
        EditName=findViewById(R.id.dialogName);
        EditNumber=findViewById(R.id.dialogNumber);
        Okay.setOnClickListener(this);
        Cancel.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.dialogOk:

                if (hasConnection()){

                    checkRegistration();

                }else {
                    Toast.makeText(context, "No internet connection", Toast.LENGTH_SHORT).show();
                }

                break;

            case R.id.dialogCancel:

                dismiss();

                break;
        }

    }

    public void checkRegistration() {
        Name=EditName.getText().toString();
        Number=EditNumber.getText().toString();

        ApiService apiService=ApiClient.getClient().create(ApiService.class);
        Call<ArrayList<Restore>>call=apiService.registrationCheck(Number);

        call.enqueue(new Callback<ArrayList<Restore>>() {
            @Override
            public void onResponse(Call<ArrayList<Restore>> call, Response<ArrayList<Restore>> response) {
                Restore object=response.body().get(0);
                preference.saveUser(object.getNumber(),object.getName(),object.getEmail(),object.getAddress());
                dismiss();
                listener.Event();
            }

            @Override
            public void onFailure(Call<ArrayList<Restore>> call, Throwable t) {
                Toast.makeText(context, "Failed to connect with server", Toast.LENGTH_SHORT).show();
            }
        });


    }
    public boolean hasConnection(){
        NetworkInfo activeNetworkInfo = ((ConnectivityManager)context.getSystemService(CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }

}
