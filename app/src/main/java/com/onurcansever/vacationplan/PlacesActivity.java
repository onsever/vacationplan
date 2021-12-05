package com.onurcansever.vacationplan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class PlacesActivity extends AppCompatActivity {

    private ListView addedPlacesListView;
    private Button goBackToButton, calculateButton;
    private TextView yourBudgetText, totalNoPeople, totalCostText;
    private ArrayList<Place> chosenPlaceItems = MainActivity.chosenPlaceItems;
    private Budget calculatedBudget = MainActivity.calculatedBudget;
    private double totalCost = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places);

        addedPlacesListView = findViewById(R.id.addedPlacesListView);
        goBackToButton = findViewById(R.id.goBackToButton);
        calculateButton = findViewById(R.id.calculateButton);
        yourBudgetText = findViewById(R.id.yourBudgetText);
        totalNoPeople = findViewById(R.id.totalNoPeople);
        totalCostText = findViewById(R.id.totalCostText);

        yourBudgetText.setVisibility(View.INVISIBLE);
        totalNoPeople.setVisibility(View.INVISIBLE);
        totalCostText.setVisibility(View.INVISIBLE);

        addedPlacesListView.setAdapter(new ChosenPlaceAdapter(this, chosenPlaceItems));

        goBackToButton.setOnClickListener(view -> {
            Intent intent = new Intent(PlacesActivity.this, MainActivity.class);
            startActivity(intent);
        });

        for (Place place: chosenPlaceItems) {
            totalCost += place.getChargeAmount();
        }

        calculateButton.setOnClickListener(view -> {
            yourBudgetText.setVisibility(View.VISIBLE);
            totalNoPeople.setVisibility(View.VISIBLE);
            totalCostText.setVisibility(View.VISIBLE);

            yourBudgetText.setText(String.valueOf(calculatedBudget.getCurrentBudget()));
            totalNoPeople.setText(String.valueOf(MainActivity.numberOfPeople));
            totalCostText.setText(String.valueOf(totalCost * MainActivity.numberOfPeople));
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        totalCostText.setText(String.valueOf(totalCost * MainActivity.numberOfPeople));

    }
}