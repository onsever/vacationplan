package com.onurcansever.vacationplan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView chosenPlaceName, chosenNoPeopleText;
    private ImageView chosenPlaceImage;
    private ListView chosenImageList;
    private EditText chosenPlaceDesc;
    private SeekBar chosenNoPeople;
    private Button goBackButton, addChosenButton;
    private ArrayList<Place> chosenPlaceItems = MainActivity.chosenPlaceItems;

    private SharedPreferences sharedPreferences;

    private Place chosenPlace = MainActivity.chosenPlace;
    private double currentBudget = MainActivity.currentBudget;
    private int numberOfPeople = MainActivity.numberOfPeople;
    private double totalCost = MainActivity.totalCost;
    private Budget calculatedBudget = MainActivity.calculatedBudget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        connectLayouts();

    }

    private void connectLayouts() {
        chosenPlaceName = findViewById(R.id.chosenPlaceName);
        chosenNoPeopleText = findViewById(R.id.chosenNoPeopleText);
        chosenPlaceImage = findViewById(R.id.chosenPlaceImage);
        chosenImageList = findViewById(R.id.chosenImageList);
        chosenPlaceDesc = findViewById(R.id.chosenPlaceDesc);
        chosenNoPeople = findViewById(R.id.chosenNoPeople);
        goBackButton = findViewById(R.id.goBackButton);
        addChosenButton = findViewById(R.id.addChosenButton);

        goBackButton.setOnClickListener(this);
        addChosenButton.setOnClickListener(this);

        chosenPlaceName.setText(chosenPlace.getPlaceName());
        chosenPlaceImage.setImageResource(chosenPlace.getImages()[0]);
        chosenPlaceDesc.setText(chosenPlace.getLongDesc());

        chosenImageList.setAdapter(new ImageAdapter(this, chosenPlace.getImages()));
        chosenImageList.setOnItemClickListener((adapterView, view, i, l) -> {
            chosenPlaceImage.setImageResource(chosenPlace.getImages()[i]);
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.addChosenButton:
                if ((totalCost * numberOfPeople) <= currentBudget) {
                    if (isAdded(chosenPlace)) {
                        Toast.makeText(getBaseContext(), "You have already added this place!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        totalCost += chosenPlace.getChargeAmount();
                        calculatedBudget = new Budget(currentBudget, totalCost);
                        chosenPlaceItems.add(chosenPlace);
                    }
                }
                else {
                    Toast.makeText(getBaseContext(), "You can't exceed your budget!", Toast.LENGTH_SHORT).show();
                }


                Intent intent1 = new Intent(DetailsActivity.this, PlacesActivity.class);
                startActivity(intent1);
                break;
            case R.id.goBackButton:
                Intent intent = new Intent(DetailsActivity.this, MainActivity.class);
                startActivity(intent);
                break;
        }
    }

    private boolean isAdded(Place chosenPlace) {
        for (Place place: chosenPlaceItems) {
            if (place.getPlaceName().equals(chosenPlace.getPlaceName())) {
                return true;
            }
        }

        return false;
    }
}