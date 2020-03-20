package com.project.evebsafe.Databases;


import android.content.Context;
import android.content.SharedPreferences;

import com.project.evebsafe.Model.Constants;

public class SharedPreference  {

    Context context;

    public SharedPreference(Context context) {
        this.context=context;
    }

    public void savePattern(String pattern){
        SharedPreferences preferences = context.getSharedPreferences(Constants.APPS_PREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString(Constants.PATTERN, pattern);
        editor.commit();
    }

   public String getPattern(){
       SharedPreferences preferences = context.getSharedPreferences(Constants.APPS_PREFERENCE, Context.MODE_PRIVATE);
       return preferences.getString(Constants.PATTERN,"");
   }

   public void setLock(boolean var){
       SharedPreferences preferences = context.getSharedPreferences(Constants.APPS_PREFERENCE, Context.MODE_PRIVATE);
       SharedPreferences.Editor editor=preferences.edit();
       editor.putBoolean(Constants.LOCKSTATE, var);
       editor.commit();
   }

   public boolean isLocked(){
       SharedPreferences preferences = context.getSharedPreferences(Constants.APPS_PREFERENCE, Context.MODE_PRIVATE);
       return preferences.getBoolean(Constants.LOCKSTATE,false);

   }

   public void saveUser(String number,String name,String email,String address){
       SharedPreferences preferences = context.getSharedPreferences(Constants.APPS_PREFERENCE, Context.MODE_PRIVATE);
       SharedPreferences.Editor editor=preferences.edit();
       editor.putString(Constants.NUMBER,number);
       editor.putString(Constants.USERNAME,name);
       editor.putString(Constants.EMAIL,email);
       editor.putString(Constants.ADDRESS,address);
       editor.commit();

   }

   public String getNumber(){
       SharedPreferences preferences = context.getSharedPreferences(Constants.APPS_PREFERENCE, Context.MODE_PRIVATE);
       return preferences.getString(Constants.NUMBER,"");
   }
   public String getNames(){
       SharedPreferences preferences = context.getSharedPreferences(Constants.APPS_PREFERENCE, Context.MODE_PRIVATE);
       return preferences.getString(Constants.USERNAME,"");
   }

    public String getEmail(){
        SharedPreferences preferences = context.getSharedPreferences(Constants.APPS_PREFERENCE, Context.MODE_PRIVATE);
        return preferences.getString(Constants.EMAIL,"");
    }
    public String getAddress(){
        SharedPreferences preferences = context.getSharedPreferences(Constants.APPS_PREFERENCE, Context.MODE_PRIVATE);
        return preferences.getString(Constants.ADDRESS,"");
    }

    public boolean isRegistered(){
        return !(getNames().equals("") & getNumber().equals(""));

    }


}
