package com.example.taruc.configurationlanguage;

import android.icu.text.DecimalFormat;
import android.icu.text.NumberFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner spinnerAge;
    private RadioGroup radioGroupGender;
    private RadioButton radioButtonMale, radioButtonFemale;
    private CheckBox checkboxSmoker;
    private TextView textViewPremium;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Linking UI to program
        spinnerAge = (Spinner)findViewById(R.id.spinnerAge);
        radioGroupGender = (RadioGroup)findViewById(R.id.radioGroupGender);
        radioButtonMale = (RadioButton)findViewById(R.id.radioButtonMale);
        radioButtonFemale = (RadioButton)findViewById(R.id.radioButtonFemale);
        checkboxSmoker = (CheckBox)findViewById(R.id.checkBoxSmoker);
        textViewPremium = (TextView)findViewById(R.id.textViewPremium);

        //Create an adapter for spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.age_group,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAge.setAdapter(adapter);
        spinnerAge.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getApplicationContext(),"Position: " + position,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void calculatePremium(View view){
        double premium = 0;
        int pos,indexGender;
        pos = spinnerAge.getSelectedItemPosition();
        //TODO: determine the basic premium
        switch(pos) {
            case 0:
                premium+=50;
                break;
            case 1:
                premium+=55;
                break;
            case 2:
                premium+=60;
                break;
            case 3:
                premium+=70;
                break;
            case 4:
                premium+=120;
                break;
            case 5:
                premium+=160;
                break;
            case 6:
                premium+=200;
                break;
            case 7:
                premium+=250;
                break;

        }

        indexGender = radioGroupGender.getCheckedRadioButtonId();
        if(indexGender == R.id.radioButtonMale){
            //TODO: calculate premium of male
            switch(pos) {
                case 2:
                    premium+=50;
                    break;
                case 3:
                    premium+=100;
                    break;
                case 4:
                    premium+=100;
                    break;
                case 5:
                    premium+=50;
                    break;
            }
        }

        if(checkboxSmoker.isChecked()){
            //TODO: calculate premium of smoker
            switch(pos) {
                case 3:
                    premium+=100;
                    break;
                case 4:
                    premium+=150;
                    break;
                case 5:
                    premium+=150;
                    break;
                case 6:
                    premium+=250;
                    break;
                case 7:
                    premium+=250;
                    break;
            }
        }
        DecimalFormat numberFormat = new DecimalFormat("$ ####.00");
        textViewPremium.setText(getString(R.string.premium) + ": " + numberFormat.format(premium));
    }

    public void resetPremium(View view){
        //TODO: clear UI and reset the premium value
        spinnerAge.setSelection(0);
        radioButtonMale.setChecked(true);
        checkboxSmoker.setChecked(false);
        textViewPremium.setText(getString(R.string.premium));
    }

}
