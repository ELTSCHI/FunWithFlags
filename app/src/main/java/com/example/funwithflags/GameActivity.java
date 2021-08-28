package com.example.funwithflags;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.opencsv.CSVReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    //Attribute festlegen
    private ImageView flagge;
    private TextView dropdown;
    private ArrayList<String> arrayList;
    private Button submit;
    private Random ran;
    private String id = "";
    private String land = "";
    private String iso = "";
    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //Attribute initialisieren:
        flagge = findViewById(R.id.test);
        dropdown = findViewById(R.id.dropdown);
        arrayList = new ArrayList<>();
        submit = findViewById(R.id.submit);
        ran = new Random();
        count = 0;

        //set array list
        setArrayList();

        //flagge anzeigen
        sucheIsozuInt(1 + ran.nextInt(249));


    }



    //sucht die Flagge zu einem Integer
    public void sucheIsozuInt(int iso_id){
        try{
            CSVReader reader = new CSVReader(new InputStreamReader(getResources().openRawResource(R.raw.laender)));
            String [] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                // nextLine[] is an array of values from the line
                id = nextLine[0];
                land = nextLine[1];
                iso = nextLine[2];
                if(id.equals("" + iso_id)){
                    break;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        int imageID =getResources().getIdentifier(iso, "drawable", getPackageName());
        flagge.setImageResource(imageID);
    }

    //sucht die Flage zu einem Landesnamen
    public String sucheIsozuName(String name){
        try{
            CSVReader reader = new CSVReader(new InputStreamReader(getResources().openRawResource(R.raw.laender)));
            String [] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                // nextLine[] is an array of values from the line
                id = nextLine[0];
                land = nextLine[1];
                iso = nextLine[2];
                if(land.equals(name)){
                    break;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return iso;
    }

    //arrayliste mit Ländernamen füllen
    public void setArrayList(){
        try{
            CSVReader reader = new CSVReader(new InputStreamReader(getResources().openRawResource(R.raw.laender)));
            String [] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                // nextLine[] is an array of values from the line
                id = nextLine[0];
                land = nextLine[1];
                iso = nextLine[2];
                arrayList.add(land);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    //aktiviere Dropdown on Click
    public void activateDropdown(View v){
        //init dialog
        Dialog dialog = new Dialog(GameActivity.this);
        dialog.setContentView(R.layout.dialog_searchable_spinner);
        dialog.getWindow().setLayout(800, 1200);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

        EditText editText = dialog.findViewById(R.id.edit_text);
        ListView listView = dialog.findViewById(R.id.list_view);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(GameActivity.this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(adapter);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                adapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                dropdown.setText(adapter.getItem(i));
                dialog.dismiss();
            }
        });
    }


    //checkt die Richtigkeit der Antwort, zeigt dies an und lädt ein neues Level
    public void doAfterSubmit(View v){
        //checkt die Antwort und verfährt dann richtig
        if(check()){
            count ++;
            sucheIsozuInt(1 + ran.nextInt(249));
            dropdown.setText("Select Country");
        }
        else{
            Intent i = new Intent(GameActivity.this, WrongActivity.class);
            i.putExtra("land", land);
            i.putExtra("iso", iso);
            i.putExtra("count", count);
            i.putExtra("answer", dropdown.getText().toString());
            i.putExtra("answer_iso", sucheIsozuName(dropdown.getText().toString()));
            startActivity(i);
            this.finish();
        }

    }

    public boolean check(){
        if(dropdown.getText().equals(land)){
            return true;
        }
        return false;
    }
}