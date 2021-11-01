package com.example.learnaboutcountries.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.learnaboutcountries.R;
import com.example.learnaboutcountries.model.CountryModel;
import com.example.learnaboutcountries.viewmodel.CountryListViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView errorText;
    private ProgressBar loadingBar;
    private RecyclerView countryRecyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;

    private CountryListViewModel countryListViewModel;
    private CountryListAdapter countryListAdapter = new CountryListAdapter(new ArrayList<>());

    //TODO: move to custom view, plz plz plz
    private Button sortByPopulationButton;
    private Button sortByAreaButton;
    private boolean isPopulationDescending = true;
    private boolean isAreaDescending = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);
        errorText = findViewById(R.id.counties_loading_error);
        loadingBar = findViewById(R.id.country_loading_view);
        countryRecyclerView = findViewById(R.id.countries_list_view);

        //TODO: move to custom view, plz plz plz
        sortByPopulationButton = findViewById(R.id.sort_by_population_button);
        sortByAreaButton = findViewById(R.id.sort_by_area_button);

        countryListViewModel = new ViewModelProvider(this).get(CountryListViewModel.class);

        observeViewModel();

        countryListViewModel.refresh();

        countryRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        countryRecyclerView.setAdapter(countryListAdapter);

        sortByPopulationButton.setOnClickListener(v -> {
            countryListAdapter.sortByPopulation(isPopulationDescending);
            isPopulationDescending = !isPopulationDescending;
        });

        sortByAreaButton.setOnClickListener(v -> {
            countryListAdapter.sortByArea(isAreaDescending);
            isAreaDescending = !isAreaDescending;
        });

        swipeRefreshLayout.setOnRefreshListener(() -> {
            countryListViewModel.refresh();
            swipeRefreshLayout.setRefreshing(false);
        });
    }

    private void observeViewModel() {
        countryListViewModel.countryList.observe(this, countryModels -> {
            if(countryModels != null) {
                countryListAdapter.updateCountryList(countryModels);
                countryRecyclerView.setVisibility(View.VISIBLE);
            }
        });

        countryListViewModel.countryLoadError.observe(this, isError -> {
            if(isError != null) {
                errorText.setVisibility(isError ? View.VISIBLE : View.GONE);
            }
        });

        countryListViewModel.loading.observe(this, isLoading -> {
            if(isLoading != null) {
                loadingBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
                if(isLoading) {
                    countryRecyclerView.setVisibility(View.GONE);
                    errorText.setVisibility(View.GONE);
                }
            }
        });
    }
}