package c2.bmr_bmi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import java.util.Calendar;

import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.app.DatePickerDialog;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class ResultActivity extends Activity {

    private TextView tHeight, tWeight, tGender, tName, tBMI, tBMR, tAge;
    private int current_year,current_month, current_day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_main);
        getViews();
        current_date();
        showResult();
    }

    public void getViews(){
        tHeight = findViewById(R.id.height);
        tWeight = findViewById(R.id.weight);
        tGender = findViewById(R.id.gender);
        tName = findViewById(R.id.name);
        tAge = findViewById(R.id.age);
        tBMI = findViewById(R.id.bmi);
        tBMR = findViewById(R.id.bmr);
    }

    public void current_date(){
        Calendar calendar = Calendar.getInstance();
        current_year = calendar.get(Calendar.YEAR);
        current_month = calendar.get(Calendar.MONTH) + 1;
        current_day = calendar.get(Calendar.DAY_OF_MONTH);

    }

    public void showResult(){
        Bundle bundle = this.getIntent().getExtras();
        double height = bundle.getDouble("height");
        double weight = bundle.getDouble("weight");
        String name = bundle.getString("name");
        String gender = bundle.getString("gender");
        String birthday = bundle.getString("birthday");
        double bmrValue;
        int age;
        String[] birth = birthday.split("-");
        int birth_year = Integer.parseInt(birth[0]);
        int birth_month = Integer.parseInt(birth[1]);
        int birth_day = Integer.parseInt(birth[2]);
        if(current_month < birth_month)
            age = current_year - birth_year -1;
        else if(current_month > birth_day)
            age = current_year - birth_year;
        else
            if(current_day < birth_day)
                age = current_year - birth_year -1;
            else
                age = current_year - birth_year;

        double bmiValue = weight / ((height / 100) * (height / 100));
        if(gender.equals("Male"))
            bmrValue = 66 + (13.7 * weight) + (5.0 * height) - (6.8 * age);
        else
            bmrValue =  655 + (9.6 * weight) + (1.8 * height) - (4.7 * age);

        tHeight.setText(String.valueOf(height));
        tWeight.setText(String.valueOf(weight));
        tName.setText(name);
        tGender.setText(gender);
        tAge.setText(String.valueOf(age));
        tBMI.setText(String.format("%.2f", bmiValue));
        tBMR.setText(String.format("%.2f", bmrValue));

    }
    public void onBackClick(View view) {
        this.finish();
    }
}

