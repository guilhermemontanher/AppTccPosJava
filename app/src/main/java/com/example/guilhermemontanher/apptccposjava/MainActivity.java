package com.example.guilhermemontanher.apptccposjava;

import android.databinding.DataBindingUtil;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.example.guilhermemontanher.apptccposjava.listener.RecyclerViewButtonClick;
import com.example.guilhermemontanher.apptccposjava.model.Param;
import com.example.guilhermemontanher.apptccposjava.view.adapter.RecyclerAdapterParams;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerViewButtonClick {

    private ActivityMainBinding binding;

    private List<Param> paramList = new ArrayList<>();

    private RecyclerAdapterParams recyclerAdapterParams;

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

        binding.buttonAddParam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!binding.editTextParamName.getText().toString().isEmpty()){
                    paramList.add(
                            new Param(binding.editTextParamName.getText().toString(),
                                    binding.editTextParamValue.getText().toString())
                    );
                    recyclerAdapterParams.notifyDataSetChanged();

                } else {
                    binding.editTextParamName.setError("Required field");
                }
            }
        });

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.recyclerViewParam.setLayoutManager(layoutManager);
        binding.recyclerViewParam.setItemAnimator(new DefaultItemAnimator());
        binding.recyclerViewParam.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        recyclerAdapterParams = new RecyclerAdapterParams(this, paramList);
        binding.recyclerViewParam.setAdapter(recyclerAdapterParams);
        recyclerAdapterParams.setOnClickRecyclerViewButtonClick(this);
    }

    @Override
    public void onClick(int position) {
        paramList.remove(position);
        //Teste
        if(true){}
        recyclerAdapterParams.notifyDataSetChanged();
    }
}
