package com.example.learnaboutcountries.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.learnaboutcountries.R;
import com.example.learnaboutcountries.model.CountryModel;

import java.util.Collections;
import java.util.List;

public class CountryListAdapter extends RecyclerView.Adapter<CountryListAdapter.CountryViewHolder> {

    private List<CountryModel> countryModelList;

    public CountryListAdapter(List<CountryModel> countryModelList) {
        this.countryModelList = countryModelList;
    }

    public void updateCountryList(List<CountryModel> newCountries) {
        this.countryModelList.clear();
        this.countryModelList.addAll(newCountries);
        notifyDataSetChanged();
    }

    public void sortByPopulation(boolean isDescending) {
        if(isDescending) {
            Collections.sort(countryModelList, (a, b) -> {
                return a.getPopulation() - b.getPopulation();
            });
        }
        else{
            Collections.sort(countryModelList, (a, b) -> {
                return b.getPopulation() - a.getPopulation();
            });
        }
        notifyDataSetChanged();
    }

    public void sortByArea(boolean isDescending) {
        if(isDescending) {
            Collections.sort(countryModelList, (a, b) -> {
                //TODO: See if it can be improved
                return (int) (a.getArea() - b.getArea());
            });
        }
        else{
            Collections.sort(countryModelList, (a, b) -> {
                //TODO: See if it can be improved
                return (int) (b.getArea() - a.getArea());
            });
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_country, parent, false);
        return new CountryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, int position) {
        holder.bind(countryModelList.get(position));
    }

    @Override
    public int getItemCount() {
        return countryModelList.size();
    }

    public class CountryViewHolder extends RecyclerView.ViewHolder{

        private TextView countryNameView;
        private TextView capitalNameView;
        private ImageView countryImageView;

        public CountryViewHolder(@NonNull View itemView) {
            super(itemView);
            countryNameView = itemView.findViewById(R.id.country_name_view);
            capitalNameView = itemView.findViewById(R.id.capital_name_view);
            countryImageView = itemView.findViewById(R.id.country_flag_image);
        }

        public void bind(CountryModel country) {
            countryNameView.setText(country.getCountryName());
            capitalNameView.setText(country.getCapitalName());
            ImageLoadUtil.loadImage(countryImageView, country.getFlag(), ImageLoadUtil.getDrawable(countryImageView.getContext()));
        }
    }
}
