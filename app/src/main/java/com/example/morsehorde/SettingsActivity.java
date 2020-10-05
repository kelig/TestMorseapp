package com.example.morsehorde;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;



public class SettingsActivity extends AppCompatActivity {


    private CheckBox check;
    private Button button;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_main);


        check = (CheckBox) findViewById(R.id.checkBoxbutton);
        button =(Button) findViewById(R.id.Savebutton);

        sharedPreferences = getSharedPreferences("com.example.morsehorde.sharedpref", this.MODE_PRIVATE);
        editor = sharedPreferences.edit();


        checkSharedPref();

        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(check.isChecked()){
                    editor.putBoolean("buttonShow",true);
                    editor.commit();
                }else{
                    editor.putBoolean("buttonShow",false);
                    editor.commit();
                }
            }
        });

    }

    private void checkSharedPref(){
        Boolean ischecked = sharedPreferences.getBoolean("buttonShow",false);

        check.setChecked(ischecked);

    }

}

