package com.example.learnaboutcountries.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.learnaboutcountries.model.CountriesService;
import com.example.learnaboutcountries.model.CountryModel;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class CountryListViewModel extends ViewModel {
    public MutableLiveData<List<CountryModel>> countryList = new MutableLiveData<>();
    public MutableLiveData<Boolean> countryLoadError = new MutableLiveData<>();
    public MutableLiveData<Boolean> loading = new MutableLiveData<>();

    private CountriesService countriesService = CountriesService.getInstance();

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public void refresh() {
        fetchCountries();
    }

    private void fetchCountries(){
        loading.setValue(true);

        compositeDisposable.add(
                countriesService.getCountries()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<CountryModel>>() {
                    @Override
                    public void onSuccess(@NonNull List<CountryModel> countryModels) {
                        countryList.setValue(countryModels);
                        countryLoadError.setValue(false);
                        loading.setValue(false);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        countryLoadError.setValue(true);
                        loading.setValue(false);
                        e.printStackTrace();
                    }
                })
        );
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }
}
