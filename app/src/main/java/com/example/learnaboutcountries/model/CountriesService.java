package com.example.learnaboutcountries.model;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class CountriesService {
    private static final String BASE_URL = "https://raw.githubusercontent.com";
    private CountriesApi api = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(CountriesApi.class);

    private static CountriesService instance = new CountriesService();
    private CountriesService(){};

    public static CountriesService getInstance() {
        if (instance == null) {
            synchronized (instance) {
                if (instance == null) {
                    instance = new CountriesService();
                }
            }
        }
        return instance;
    }

    public Single<List<CountryModel>> getCountries() {
        return api.getCountries();
    }
}
