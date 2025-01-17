package com.example.listycity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    EditText addCityText;
    ConstraintLayout addCityLayout;
    public int selected_pos = -1;
    public View lastSelectedView = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addCityText = findViewById(R.id.addCityText);
        addCityLayout = findViewById(R.id.addCityLayout);
        cityList = findViewById(R.id.city_list);
        Button button = findViewById(R.id.button);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button4);
        String []cities = {"Edmonton", "Vancouver", "Paris"};
        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));
        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);
        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                if (lastSelectedView != null) {
                    lastSelectedView.setBackgroundColor(Color.WHITE);
                }
                selected_pos = pos;
                view.setBackgroundColor(Color.LTGRAY);
                lastSelectedView = view;
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCityText.setText("");
                addCityLayout.setVisibility(View.VISIBLE);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selected_pos >= 0 && selected_pos < dataList.size()) {
                    String removedCity = dataList.get(selected_pos);
                    dataList.remove(selected_pos);
                    cityAdapter.notifyDataSetChanged();
                    selected_pos = -1; // reset selection

                }
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cityName = addCityText.getText().toString().trim();
                if (!cityName.isEmpty()) {
                    dataList.add(cityName);
                    cityAdapter.notifyDataSetChanged();
                }
                addCityLayout.setVisibility(View.GONE);
            }
        });
    }
}