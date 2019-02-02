package com.example.guilhermemontanher.apptccposjava;

import android.databinding.DataBindingUtil;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.guilhermemontanher.apptccposjava.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.imageButtonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                String url = binding.editTextUrl.getText().toString();
                int method = binding.spinnerMethod.getSelectedItemPosition();

                StringRequest stringRequest = new StringRequest(method, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                binding.textViewOutPut.setText(response);
                                Snackbar.make(binding.rootView, "Successful requisition!", Snackbar.LENGTH_LONG).show();
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Snackbar.make(binding.rootView, "That didn't work!", Snackbar.LENGTH_LONG).show();;
                    }
                });

                queue.add(stringRequest);
            }
        });

    }
}
