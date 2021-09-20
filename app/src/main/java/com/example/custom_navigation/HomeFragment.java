package com.example.custom_navigation;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class HomeFragment extends Fragment implements Recyclerclick {
    RecyclerView recyclerView, recyclerView_state;
    private final List<Data_model> data_models = new ArrayList<>();
    private final List<Data_model> data_models1 = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_home, container, false);


        recyclerView = v.findViewById(R.id.recyclerView_item);
        recyclerView_state = v.findViewById(R.id.recyclerView_state);

        ApiIntegration();

//        data_models.add(new Data_model(R.drawable.item));
//        data_models.add(new Data_model(R.drawable.item));
//        data_models.add(new Data_model(R.drawable.item));
//        data_models.add(new Data_model(R.drawable.item));
//        data_models.add(new Data_model(R.drawable.item));
//        data_models.add(new Data_model(R.drawable.item));
//        data_models.add(new Data_model(R.drawable.item));


//        data_models1.add(new Data_model(R.drawable.ic_baseline_local_dining_24));
//        data_models1.add(new Data_model(R.drawable.ic_baseline_local_dining_24));
//        data_models1.add(new Data_model(R.drawable.ic_baseline_local_dining_24));
//        data_models1.add(new Data_model(R.drawable.ic_baseline_local_dining_24));
//        data_models1.add(new Data_model(R.drawable.ic_baseline_local_dining_24));
//        data_models1.add(new Data_model(R.drawable.ic_baseline_local_dining_24));
//        data_models1.add(new Data_model(R.drawable.ic_baseline_local_dining_24));
//        data_models1.add(new Data_model(R.drawable.ic_baseline_local_dining_24));
//        data_models1.add(new Data_model(R.drawable.ic_baseline_local_dining_24));
//        data_models1.add(new Data_model(R.drawable.ic_baseline_local_dining_24));
//        data_models1.add(new Data_model(R.drawable.ic_baseline_local_dining_24));
//

        Adapter_state adapter_state = new Adapter_state(data_models1, getContext());
        LinearLayoutManager horizontalLayoutManagaer1
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView_state.setLayoutManager(horizontalLayoutManagaer1);
        recyclerView_state.setAdapter(adapter_state);

        return v;
    }

    private void ApiIntegration() {

        String url = "http://infobox.ambiguousit.com/api/getAllCategories.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);

                    JSONObject object1 = object.getJSONObject("result");
                    JSONArray array = object1.getJSONArray("catList");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject object2 = array.getJSONObject(i);
                        String name = object2.getString("imgLink");
                        data_models.add(new Data_model(name));
                    }

                    Adapter_item adapter_item = new Adapter_item(data_models, getContext()) ;
                    LinearLayoutManager horizontalLayoutManagaer
                            = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                    recyclerView.setLayoutManager(horizontalLayoutManagaer);
                    recyclerView.setAdapter(adapter_item);
                    Adapter_state adapter_state = new Adapter_state(data_models, getContext());
                    LinearLayoutManager horizontalLayoutManagaer1
                            = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                    recyclerView_state.setLayoutManager(horizontalLayoutManagaer1);
                    recyclerView_state.setAdapter(adapter_state);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("moduleKey", "indiancookingdiary");
                return map;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(request);
    }

    @Override
    public void onItemClick(int position) {
        Data_model data = data_models.get(position);
        Intent intent = new Intent(getContext(), Dish_Details.class);
        intent.putExtra("datavalue", position);
        startActivity(intent);
        Toast.makeText(getContext(), "clcik" + position, Toast.LENGTH_LONG).show();
    }
}