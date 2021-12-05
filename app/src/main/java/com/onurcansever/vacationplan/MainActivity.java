package com.onurcansever.vacationplan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText budgetTextField;
    private Spinner countrySpinner;
    private ListView placesListView;
    private SeekBar numberOfPeopleBar;
    private TextView numberOfPeopleText, shortDescriptionText;
    private Button addButton, showMoreButton;

    private ArrayList<Place> places = new ArrayList<>();
    private String[] countryNames = {"Turkey", "Italy", "France", "Spain", "USA"};

    public static ArrayList<Place> tempPlaces = new ArrayList<>();
    public static Place chosenPlace = null;
    public static ArrayList<Place> chosenPlaceItems = new ArrayList<>();
    public static double currentBudget = 0.0;
    public static int numberOfPeople = 0;
    public static double totalCost = 0.0;
    public static Budget calculatedBudget;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connectLayouts();
        fillData();
        configureSpinner();
        configureListView();
        configureSeekBar();
    }

    @Override
    protected void onResume() {
        super.onResume();
        float storedBudget = sharedPreferences.getFloat("budget", 0);
        budgetTextField.setText(String.valueOf(storedBudget));
    }

    private void connectLayouts() {
        budgetTextField = findViewById(R.id.budgetTextField);
        countrySpinner = findViewById(R.id.countrySpinner);
        placesListView = findViewById(R.id.placesListView);
        numberOfPeopleBar = findViewById(R.id.numberOfPeopleBar);
        numberOfPeopleText = findViewById(R.id.numberOfPeopleText);
        shortDescriptionText = findViewById(R.id.shortDescriptionText);
        addButton = findViewById(R.id.addButton);
        showMoreButton = findViewById(R.id.showMoreButton);

        addButton.setOnClickListener(this);
        showMoreButton.setOnClickListener(this);

        sharedPreferences = this.getSharedPreferences("com.onurcansever.vacationplan", Context.MODE_PRIVATE);
        
    }

    private void fillData() {
        places.add(new Place("Turkey", "Hagia Sophia", 1250, new int[] {R.drawable.hagiasophia, R.drawable.hagiasophia1, R.drawable.hagiasophia2}, "Built by the Byzantine Emperor Justinian in 537 CE.", "Renowned as one of the most beautiful buildings in the world, the spellbinding Byzantine glory of the Hagia Sophia Mosque (Aya Sofya) is not only one of the top things to do in Istanbul, but also in Turkey.\n" +
                "\n" +
                "Built by the Byzantine Emperor Justinian in 537 CE, it is renowned as the Byzantine Empire's greatest architectural achievement and has remained the world's largest church for 1,000 years.\n" +
                "\n" +
                "The staggering bulk of its exterior is rimmed by the delicate minarets added after the Ottoman conquest, while the sumptuous and cavernous frescoed interior is a grand reminder of old Constantinople's might and power."));

        places.add(new Place("Turkey", "Cappadocia", 2000, new int[] {R.drawable.cappadocia, R.drawable.cappadocia1}, "Cliff ridges and hill crests are home to rippling panoramas of wave-like rock.", "In particular, the multiple cave-churches of Göreme Open-Air Museum and Ihlara Valley are home to some of the best examples of surviving mid-Byzantine-era religious art in the world.\n" +
                "\n" +
                "Cappadocia's villages, half hewn into the hillsides, where travelers base themselves to explore the surrounding countryside, are also an attraction in themselves, with their boutique hotels that allow you to bed down in a cave with full contemporary comforts."));

        places.add(new Place("Turkey", "Ephesus", 1900, new int[] {R.drawable.ephesus, R.drawable.ephesus1}, "Not to be missed, the mighty ruin of Ephesus is a city of colossal monuments and marble-columned roads. ", "One of the most complete, still-standing famed cities of antiquity in the Mediterranean region, this is the place to experience what life must have been like during the golden age of the Roman Empire.\n" +
                "\n" +
                "The city's history dates back to the 10th century BCE, but the major monuments you see today all date from its Roman era when it was a thriving commercial center."));

        places.add(new Place("Turkey", "Topkapi Palace", 2000, new int[] {R.drawable.topkapi, R.drawable.topkapi1}, "Sumptuous beyond belief, Istanbul's Topkapı Palace takes you into the fantastical, opulent world of the sultans. ", "In particular, don't miss the Imperial Council building, where the empire's business was conducted by the Grand Vizier; the arms collection displayed in the Imperial Treasury; the world-class collection of miniature paintings; and the dazzling Harem rooms, which were designed by the famed Ottoman architect Sinan."));

        places.add(new Place("Italy", "Grand Canal", 1750, new int[] {R.drawable.grandcanal, R.drawable.grandcanal1}, "A gondola ride through the canals of Venice is a tradition that travelers have been enjoying for centuries.", "The Grand Canal is the largest and most famous of these waterways, cutting a wide S-shaped route through the city. Along its sides are the grandest of the palaces once owned by the wealthiest and most powerful families of the Venetian Republic. The best way to see many of the grand palaces, whose fronts face the water, is from a Vaporetto ride along the Grand Canal."));

        places.add(new Place("Italy", "Santa Maria", 3000, new int[] {R.drawable.santamaria}, "Regarded as one of the finest cathedrals in the world, the Duomo Santa Maria del Fiore, or the Cathedral of Santa Maria del Fiore.", "Regarded as one of the finest cathedrals in the world, the Duomo Santa Maria del Fiore, or the Cathedral of Santa Maria del Fiore, dominates the Florence skyline. The cathedral was built between the 13th and 15th centuries, with the most famous piece being the extraordinary dome, completed by Filippo Brunelleschi in 1434."));

        places.add(new Place("Italy", "Colosseum", 2500, new int[] {R.drawable.colosseum, R.drawable.colosseum1}, "This huge amphitheater, the largest of its kind ever built by the Roman Empire.", "This huge amphitheater, the largest of its kind ever built by the Roman Empire and the largest of their constructions to survive, remained a model for sports facilities right up to modern times. Built by Vespasian in AD 72 and enlarged by the addition of a fourth story by his son, Titus, it was a venue for public spectacles and shows – even mock sea battles."));

        places.add(new Place("France", "Eiffel Tower", 4000, new int[] {R.drawable.eiffeltower, R.drawable.eiffeltower1}, "The symbol of Paris, the Eiffel Tower is a feat of ingenuity as much as it is a famous landmark.", "The symbol of Paris, the Eiffel Tower is a feat of ingenuity as much as it is a famous landmark. This structure of 8,000 metallic parts was designed by Gustave Eiffel as a temporary exhibit for the World Fair of 1889. Originally loathed by critics, the 320-meter-high tower is now a beloved and irreplaceable fixture of the Paris skyline."));

        places.add(new Place("France", "Mont Saint-Michel", 2000, new int[] {R.drawable.montsaint}, "Rising dramatically from a rocky islet off the Normandy coast.", "Rising dramatically from a rocky islet off the Normandy coast, the UNESCO-listed Mont Saint-Michel is one of France's most striking landmarks. This \"Pyramid of the Seas\" is a mystical sight, perched 80 meters above the bay and surrounded by imposing defensive walls and bastions."));

        places.add(new Place("Spain", "The Alhambra Gardens", 2400, new int[] {R.drawable.alhambra, R.drawable.alhambra1}, "The Alhambra complex includes several buildings, towers, walls, gardens, and a mosque.", "No matter how much you have read or how many pictures you have seen of Granada's Alhambra palaces, this Moorish pleasure palace will still take your breath away. The Nasrid dynasty's royal palace is the artistic highlight of Spain's Islamic period, when Al-Andalus – as they called Andalucía – represented the epitome of culture and civilization in Europe's Middle Ages."));

        places.add(new Place("Spain", "Barcelona's Sagrada Sites", 3000, new int[] {R.drawable.sagrada, R.drawable.sagrada1}, "Antoni Gaudi took the architectural style known as Art Nouveau.", "Antoni Gaudi took the architectural style known as Art Nouveau a step further, even, some have argued, into absurdity. The fanciful and outrageous buildings he created in Barcelona have become landmarks, the signature attractions of this Catalan city. "));

        places.add(new Place("Spain", "The Great Mosque", 1500, new int[] {R.drawable.greatmosque, R.drawable.greatmosque1}, "Once the principal mosque of western Islam and still known as the Mezquita.", "Once the principal mosque of western Islam and still known as the Mezquita, Cordoba's mosque is one of the largest in the world and the finest achievement of Moorish architecture in Spain. In spite of later alterations that carved out its center to build a Catholic cathedral at its heart, the Great Mosque ranks with the Alhambra in Granada as one of the two most splendid examples of Islamic art and architecture in western Europe."));

        places.add(new Place("USA", "The Grand Canyon", 5000, new int[] {R.drawable.grandcanyon, R.drawable.grandcanyon1, R.drawable.grandcanyon2}, "The Grand Canyon is one of those must-see, bucket-list destinations that have been attracting visitors for generations.", "Visiting the Grand Canyon can easily be done on a day trip from Las Vegas or Phoenix, and from some smaller cities in the vicinity, including Sedona or Flagstaff. Another option is to incorporate a visit into a larger driving trip through Arizona and surrounding states. A train trip from Williams, AZ is another delightful way to experience the canyon."));

    }

    private void configureSpinner() {
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, countryNames);
        countrySpinner.setAdapter(arrayAdapter);

        countrySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                fillTempList(countryNames[i]);
                placesListView.setAdapter(new PlaceAdapter(MainActivity.this, tempPlaces));
                shortDescriptionText.setText("");
                numberOfPeopleBar.setProgress(1);
                numberOfPeopleText.setText(String.valueOf(numberOfPeopleBar.getProgress()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void configureListView() {
        placesListView.setOnItemClickListener((adapterView, view, i, l) -> {
            shortDescriptionText.setText(tempPlaces.get(i).getShortDesc());

            if (budgetTextField.getText().toString().equals("")) {
                Toast.makeText(getBaseContext(), "Please enter your budget to proceed!", Toast.LENGTH_SHORT).show();
            }

            chosenPlace = tempPlaces.get(i);
            
        });
    }

    private void configureSeekBar() {
        numberOfPeopleBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                numberOfPeopleText.setText(String.valueOf(seekBar.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void fillTempList(String country) {
        tempPlaces.clear();
        for (Place place: places) {
            if (place.getCountryName().equalsIgnoreCase(country)) {
                tempPlaces.add(place);
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.addButton:
                numberOfPeople = Integer.parseInt(numberOfPeopleText.getText().toString());
                currentBudget = Double.parseDouble(budgetTextField.getText().toString());
                sharedPreferences.edit().putFloat("budget", (float) currentBudget).apply();

                if (currentBudget == 0) break;

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


                Intent intent1 = new Intent(MainActivity.this, PlacesActivity.class);
                startActivity(intent1);

                break;
                
            case R.id.showMoreButton:
                if (chosenPlace == null) {
                    Toast.makeText(getBaseContext(), "Please choose a place to show it's details!", Toast.LENGTH_LONG).show();
                }
                else {
                    Intent intent2 = new Intent(MainActivity.this, DetailsActivity.class);
                    startActivity(intent2);
                }

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

/*
if (chosenPlaceItems.contains(chosenPlace)) {

                }
                else if (totalCost > currentBudget) {
                    Toast.makeText(getBaseContext(), "You can't exceed your budget!", Toast.LENGTH_SHORT).show();
                }
                else {

                }
 */

/*
if (isAdded()) {

                    for (Place place: chosenPlaceItems) {
                        totalCost += place.getChargeAmount();
                    }
                }
                else {
                    chosenPlaceItems.add(chosenPlace);
                }
 */