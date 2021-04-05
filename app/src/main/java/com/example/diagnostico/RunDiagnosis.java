package com.example.diagnostico;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import static android.widget.AdapterView.*;

public class RunDiagnosis extends AppCompatActivity implements OnItemSelectedListener {

    Spinner spingender,spinchest;
    Button rundiagbutton;
    EditText ageet;
    private RequestQueue m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run_diagnosis);

        rundiagbutton = findViewById(R.id.rdbutton);
        ageet = findViewById(R.id.etage);
        Spinner spinner = findViewById(R.id.spingender);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.gender, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);


        Spinner spinner1 = findViewById(R.id.spinchestpain);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.chestpain, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);
        spinner1.setOnItemSelectedListener(this);

        m= Volley.newRequestQueue(this);



    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        String text = adapterView.getItemAtPosition(i).toString();
        Spinner spinner = findViewById(R.id.spingender);
        Spinner spinner1 = findViewById(R.id.spinchestpain);

        Toast.makeText(adapterView.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void rundiagnosis(View view) {
        String ageV = ageet.getText().toString();

        Spinner spin1 = findViewById(R.id.spingender);
        String genderV =spin1.getSelectedItem().toString();

        Spinner spin2 = findViewById(R.id.spinchestpain);
        String chestV =spin2.getSelectedItem().toString();

        String url1="https://api.thingspeak.com/update?api_key=NP97LAGKAEVHWK4G&field1="+ageV;
        String url2="https://api.thingspeak.com/update?api_key=NP97LAGKAEVHWK4G&field2="+genderV;
        String url3="https://api.thingspeak.com/update?api_key=NP97LAGKAEVHWK4G&field3="+chestV;

        JsonObjectRequest req=new JsonObjectRequest(Request.Method.POST, url1, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println(response);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });
        m.add(req);
    }
}










