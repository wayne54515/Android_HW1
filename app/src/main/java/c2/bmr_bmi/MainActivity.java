package c2.bmr_bmi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import java.util.Calendar;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.app.DatePickerDialog;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends Activity {

    private EditText birthday, eHeight, eWeight, eName;
    private RadioButton rMale, rFemale;
    private String gender;
    private TextView tError;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getViews();
    }

    private void getViews(){
        birthday = findViewById(R.id.birthday);
        eName = findViewById(R.id.name);
        eWeight = findViewById(R.id.weight);
        eHeight = findViewById(R.id.height);
        rMale = findViewById(R.id.male);
        rFemale = findViewById(R.id.female);
        tError = findViewById(R.id.error);
    }

    public void datePicker(View v){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        new DatePickerDialog(v.getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                String dateTime = String.valueOf(year)+"-"+String.valueOf(month)+"-"+String.valueOf(day);
                birthday.setText(dateTime);
            }
        }, year, month, day).show();
    }

    public void Submit(View v){
        double height = 0, weight = 0;
        String str_height = eHeight.getText().toString();
        String str_weight = eWeight.getText().toString();

        if((!TextUtils.isEmpty(str_height)) & (!TextUtils.isEmpty(str_weight))){
            height = Double.parseDouble(str_height);
            weight = Double.parseDouble(str_weight);
        }

        String name = eName.getText().toString();
        String birth = birthday.getText().toString();

        if(rMale.isChecked())
            gender = "Male";
        else if(rFemale.isChecked())
            gender = "Female";

        if((height > 0) & (weight > 0) & (!TextUtils.isEmpty(name)) & (!TextUtils.isEmpty(gender)) & (!TextUtils.isEmpty(birth))){
            Bundle bundle = new Bundle();
            // 存物件必須實作序列化
            bundle.putDouble("height", height);
            bundle.putDouble("weight", weight);
            bundle.putString("name", name);
            bundle.putString("gender", gender);
            bundle.putString("birthday", birth);

            // Activity必須在manifest檔案內宣告
            Intent intent = new Intent(this, ResultActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
            tError.setText("");
        }
        else
            tError.setText("有欄位輸入錯誤或未填寫");

    }

}
