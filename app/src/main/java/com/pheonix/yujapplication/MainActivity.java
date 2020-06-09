package com.pheonix.yujapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RequestQueue mQueue;
    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonParse = findViewById(R.id.button_parse);
        mQueue = Volley.newRequestQueue(this);
        buttonParse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonParse();
            }
        });
    }

    private void jsonParse() {
        String url = "http://dummy.restapiexample.com/api/v1/employees";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray jsonArray = response.getJSONArray("data");

                            String id, firstName, salary, age, profile_image = "";

                            List<String> displayList = new ArrayList<String>();

                            String firstNameList [] = new String[jsonArray.length()];
                            String salaryList [] = new String[jsonArray.length()];
                            String ageList [] = new String[jsonArray.length()];

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject employee = jsonArray.getJSONObject(i);


                                id = String.valueOf(employee.getInt("id"));
                                firstName = employee.getString("employee_name");
                                salary = String.valueOf(employee.getInt("employee_salary"));
                                age = String.valueOf(employee.getInt("employee_age"));
                                profile_image = employee.getString("profile_image");

                                displayList.add("\t" + firstName + "\t" + salary + "\t " + age + "\n");

                                firstNameList[i] = firstName;
                                salaryList[i] = salary;
                                ageList[i] = age;
                            }

                            gridView = findViewById(R.id.grid_view);

                            EmployeeAdapter employeeAdapterNew = new EmployeeAdapter(MainActivity.this, firstNameList, salaryList, ageList);
                            gridView.setAdapter(employeeAdapterNew);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueue.add(request);
    }
}
