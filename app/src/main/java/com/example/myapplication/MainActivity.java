package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONObject;

import Model.Api;
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void fetchData(String txt){
        /* Url */
        String URL="https://api.agify.io?name="+txt;
        RequestQueue requestQueue=Volley.newRequestQueue(this);

        JsonObjectRequest objectRequest= new JsonObjectRequest(
                Request.Method.GET,
                URL,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        TextView nom =(TextView)findViewById(R.id.nom);
                        TextView age =(TextView)findViewById(R.id.age);
                        TextView count =(TextView)findViewById(R.id.count);
                        Gson gson = new Gson();
                        Api a = gson.fromJson(String.valueOf(response), Api.class);

                        nom.setText(a.getName());
                        age.setText(a.getAge());
                        count.setText(a.getCount().toString());
                        Log.e("Rest Response", response.toString());

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Rest Response",error.toString());
                    }
                }

        );
        requestQueue.add(objectRequest);
    }

    public void afficherReponse(View view){
        EditText input   = (EditText)findViewById(R.id.input_deviner);
        String txt =input.getText().toString();
        TextView nom =(TextView)findViewById(R.id.nom);
        nom.setText(txt);
        fetchData(txt);
    }
}