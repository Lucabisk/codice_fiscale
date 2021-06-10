package com.lucap.codicefiscale;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

import com.lucap.codicefiscale.database.*;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText etFirst, etSecond, etDay, etMonth, etYear,etCom, etProv;
    private RadioGroup rgSex;
    private RadioButton rbSex;
    private ImageButton ibGo;
    private String sex = "";

    private ComuniDatabase db;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etFirst = findViewById(R.id.etFirstName);
        etSecond = findViewById(R.id.etSecondName);
        etDay = findViewById(R.id.etDay);
        etMonth = findViewById(R.id.etMonth);
        etYear = findViewById(R.id.etYear);
        etCom = findViewById(R.id.etCom);
        etProv = findViewById(R.id.etProv);

        rgSex = findViewById(R.id.radioGroup);
        ibGo = findViewById(R.id.ibGo);
        ibGo.setOnClickListener(this);

        createDB();
    }

    private void createDB() {

        db = Room.databaseBuilder(getApplicationContext(), ComuniDatabase.class,"comuni.db")
                .createFromFile(new File("comuni.db"))
                .allowMainThreadQueries()
                .build();
    }


    @Override
    protected void onResume() {
        super.onResume();
        //
    }

    @Override
    public void onBackPressed() {
        //
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.ibGo){
            int selectedIdSex = rgSex.getCheckedRadioButtonId();
            rbSex = findViewById(selectedIdSex);
            if (selectedIdSex == R.id.rbF)
                sex += "f";
            else sex += "m";
            main();
        }
    }


    private void main(){
        String s,f,d,c,sc, CF;
        s = secondName(etSecond.getText().toString());
        f = firstName(etFirst.getText().toString());
        d = date(etDay.getText().toString(), etMonth.getText().toString(), etYear.getText().toString());
        c = country(etCom.getText().toString(), etProv.getText().toString());
        sc = specialCharacters(s+f+d+c);
        CF = s+f+d+c+sc;
        //call new activity to show the cf
        Intent intent = new Intent(MainActivity.this, showActivity.class);
        intent.putExtra("cf", CF);
        startActivity(intent);
    }

    private String secondName(String S){
        String s = "";
        char c = 'x';
        int i;
        for(i=0; i<S.length(); i++) {
            if (s.length() < 3) {
                c = S.charAt(i);
                if (c != 'a' && c != 'e' && c != 'i' && c != 'o' && c != 'u' && c != ' ')
                    s += c;
            }
        }
        if(s.length() != 3){
            for(i=0; i<S.length(); i++){
                if (s.length() < 3) {
                    c = S.charAt(i);
                    if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u')
                        s += c;
                }
            }
        }
        if(s.length() != 3){
            s+='x';
        }

        return s;
    }

    private String firstName(String S){
        String f = "";
        int i, countForCons = 0;
        int countCons = ausFirstName(S);
        char c='x';

        if(countCons < 4)
            f = secondName(S);

        else{
            for(i=0; i<S.length(); i++) {
                if (f.length() < 3) {
                    c = S.charAt(i);
                    if (c != 'a' && c != 'e' && c != 'i' && c != 'o' && c != 'u' && c != ' '){
                        if(f.length() != 1 || countForCons == 0) {
                            f += c;
                            countForCons += 1;
                        }
                        else countForCons = 0;
                    }
                }
            }
        }

        return f;
    }

    private int ausFirstName(String S){
        int count = 0, i;
        char c='x';

        for(i=0; i<S.length(); i++){
            c = S.charAt(i);
            if (c != 'a' && c != 'e' && c != 'i' && c != 'o' && c != 'u' && c != ' ')
                count += 1;
        }

        return count;
    }

    private String date(String day, String month, String year){
        String d = "";

        d += year.substring(2);
        d += ausDate(month);
        if(sex.equals("m"))
            d += day;
        else{
            d += String.valueOf(Integer.parseInt(day) + 40);
        }

        return d;
    }

    private String ausDate(String month){
        String c="x";

        if(month.equals("01"))
            c = "a";
        if(month.equals("02"))
            c = "b";
        if(month.equals("03"))
            c = "c";
        if(month.equals("04"))
            c = "d";
        if(month.equals("05"))
            c = "e";
        if(month.equals("06"))
            c = "h";
        if(month.equals("07"))
            c = "l";
        if(month.equals("08"))
            c = "m";
        if(month.equals("09"))
            c = "p";
        if(month.equals("10"))
            c = "r";
        if(month.equals("11"))
            c = "s";
        if(month.equals("12"))
            c = "t";

        return c;
    }


    private String country(String com, String prov){
        return db.comuniDAO().getCc(com);
    }

    private String specialCharacters(String S){
        String sc = "";
        //
        return "W";
    }
}