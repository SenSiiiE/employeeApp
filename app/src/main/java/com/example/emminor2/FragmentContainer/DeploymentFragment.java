package com.example.emminor2.FragmentContainer;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.emminor2.R;
import com.google.android.material.internal.ToolbarUtils;

import org.json.JSONException;
import org.json.JSONObject;

public class DeploymentFragment extends Fragment {
    EditText satisfaction, lastevaluation, project,monthlyhour,time, accident, pramotion;
    TextView output;
    AutoCompleteTextView  Department, Salary;
    Button predict;

    public DeploymentFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @SuppressLint("CutPasteId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle("Deploymnet");

        View view= inflater.inflate(R.layout.fragment_deployment, container, false);

satisfaction = view.findViewById(R.id.satisfaction);
lastevaluation = view.findViewById(R.id.lastevaluation);
project = view.findViewById(R.id.project);
monthlyhour = view.findViewById(R.id.monthlyhour);
time= view.findViewById(R.id.time);
accident = view.findViewById(R.id.acciedent);
pramotion = view.findViewById(R.id.pramotion);
predict = view.findViewById(R.id.predict);
Department =view.findViewById(R.id.Department);
Salary = view.findViewById(R.id.Salary);
output = view.findViewById(R.id.output);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) AutoCompleteTextView autoCompleteTextView2 = view.findViewById(R.id.Department);
        String[] suggestions2 = {"RandD","technical",
                "accounting", "HR", "management", "marketing", "product_mng", "sales",
                "support","IT"
        };

        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_dropdown_item, suggestions2);
        autoCompleteTextView2.setAdapter(adapter2);
        autoCompleteTextView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedItem2 = (String) adapterView.getItemAtPosition(i);
                Toast.makeText(getContext(), "Selected: " + selectedItem2, Toast.LENGTH_SHORT).show();

            }
        });
        AutoCompleteTextView autoCompleteTextView3 = view.findViewById(R.id.Salary);

        String[] suggestions3 = {"0","1","2"
        };

        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_dropdown_item, suggestions3);
        autoCompleteTextView3.setAdapter(adapter3);
        autoCompleteTextView3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedItem3 = (String) adapterView.getItemAtPosition(i);
                Toast.makeText(getContext(), "Selected: " + selectedItem3, Toast.LENGTH_SHORT).show();

            }
        });
predict.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
if(Nullchecker()) {
    Toast.makeText(getContext(), "Please Fill the Correct value", Toast.LENGTH_SHORT).show();
}
    else{
 if(ValidatValue()){
     Toast.makeText(getContext(), "Please Fill the Valid value", Toast.LENGTH_SHORT).show();

 }
 else{

     String url ="https://vikash001.onrender.com?a1="+satisfaction.getText() +
                 "&a2="+lastevaluation.getText() +
                 "&a3="+project.getText()+
                 "&a4="+monthlyhour.getText()+
                 "&a5="+time.getText() +
                 "&a6=" +accident.getText()+
                 "&a7="+pramotion.getText() +
                 "&a8="+Department.getText()+
         "&a9="+ Salary.getText();
         RequestQueue queue = Volley.newRequestQueue(requireContext());

// Request a string response from the provided URL.
         StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                 new Response.Listener<String>() {
                     @Override
                     public void onResponse(String response) {
                         // Display the first 500 characters of the response string.
                         System.out.println(response);
                         output.setVisibility(View.VISIBLE);
                         System.out.println(output.getText());
                         if (response.contains("[0]")) {
                      output.setText("Employee Will not Leave \n Organisation"+ response);
                         } else {
                             output.setText("Employee Will  Leave \n Organisation"+ response);


                         }

                     }
                 }, new Response.ErrorListener() {
             @Override
             public void onErrorResponse(VolleyError error) {
                 output.setText("That didn't work!");
             }
         });
         queue.add(stringRequest);
     }
 }

}

});





        return view;
    }

    private boolean ValidatValue() {
        int flag = 0;


        if (Float.parseFloat(satisfaction.getText().toString()) < 0 || Float.parseFloat(satisfaction.getText().toString()) > 1) {
            satisfaction.setError("Range is {0---1} ");
            flag = 1;
        } else if (Float.parseFloat(lastevaluation.getText().toString()) < 0 || Float.parseFloat(lastevaluation.getText().toString()) > 1) {
            lastevaluation.setError("Range is {0---1} ");
            flag = 1;
        } else if (Integer.parseInt(project.getText().toString()) >= 10) {
            project.setError("Not more then 10");
            flag = 1;
        } else if (Integer.parseInt(monthlyhour.getText().toString()) < 90 || Integer.parseInt(monthlyhour.getText().toString()) > 400) {
            time.setError("Range is { 90--400}");
            flag = 1;
        }
     else if (Integer.parseInt(time.getText().toString())>8 ) {
        time.setError("Range is in 8 year");
        flag = 1;
    }
        else if (Integer.parseInt(accident.getText().toString())!=0 && Integer.parseInt(accident.getText().toString())!=1) {
            accident.setError("Either 0 or 1");
            flag=1;
        } else if (Integer.parseInt(pramotion.getText().toString())!=0 && Integer.parseInt(pramotion.getText().toString())!=1) {
            pramotion.setError("Either 0 or 1");
            flag=1;

        }
        if (flag == 1) {
            return true;
        } else {
            return false;

        }
    }

    private boolean Nullchecker() {
        int flag=0;

        if (satisfaction.getText().toString().isEmpty())
        {
            satisfaction.setError("Enter satisfaction level");
            flag =1;
        }
        else if (lastevaluation.getText().toString().isEmpty())
        {
           lastevaluation.setError("Enter Last Evaluation");
            flag=1;
        }
        else if (project.getText().toString().isEmpty())
        {
            project.setError("Enter Number of project");
            flag=1;
        } else if (monthlyhour.getText().toString().isEmpty()) {
            monthlyhour.setError("Enter Monthly Hour");
            flag=1;
        }
        else if (time.getText().toString().isEmpty()) {
            time.setError("Enter Time Spend in company");
            flag=1;
        }else if (accident.getText().toString().isEmpty()) {
            accident.setError("Enter the field");
            flag=1;
        }else if (pramotion.getText().toString().isEmpty()) {
            pramotion.setError("Enter the field");
            flag=1;
        }else if (Department.getText().toString().isEmpty()) {
            Department.setError("Enter Department");
            flag=1;
        }
        else if (Salary.getText().toString().isEmpty()) {
            Salary.setError("Enter salary Type");
            flag=1;
        }

        if (flag==1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}