package com.example.learnaboutcountries.view;

import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.RelativeSizeSpan;
import android.text.style.SuperscriptSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.learnaboutcountries.R;
import com.example.learnaboutcountries.model.CountryModel;

import java.util.List;

public class CountryListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<CountryModel> countryModelList;

    private int currentViewType = ViewType.NORMAL;

    public CountryListAdapter(List<CountryModel> countryModelList) {
        this.countryModelList = countryModelList;
    }

    public void updateCountryList(List<CountryModel> newCountries) {
        this.countryModelList.clear();
        this.countryModelList.addAll(newCountries);
        notifyDataSetChanged();
    }

    public void showPopulation() {
        currentViewType = ViewType.POPULATION;
        notifyDataSetChanged();
    }

    public void showArea() {
        currentViewType = ViewType.AREA;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.normal_view, parent, false);
        switch (viewType) {
            case ViewType.POPULATION:
                return new ShowPopulationViewHolder(view);
                case ViewType.AREA:
                    return new ShowAreaViewHolder(view);
            default:
                return new NormalStateViewHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case ViewType.POPULATION:
                ((ShowPopulationViewHolder)holder).bind(countryModelList.get(position));
                break;
            case ViewType.AREA:
                ((ShowAreaViewHolder)holder).bind(countryModelList.get(position));
                break;
            default:
                ((NormalStateViewHolder)holder).bind(countryModelList.get(position));
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return currentViewType;
    }

    @Override
    public int getItemCount() {
        return countryModelList.size();
    }

    public class NormalStateViewHolder extends RecyclerView.ViewHolder{

        private TextView countryNameView;
        private TextView regionView;
        private TextView capitalNameView;
        private ImageView countryImageView;

        public NormalStateViewHolder(@NonNull View itemView) {
            super(itemView);
            countryNameView = itemView.findViewById(R.id.country_name_view);
            regionView = itemView.findViewById(R.id.second_text_view);
            capitalNameView = itemView.findViewById(R.id.third_text_view);
            countryImageView = itemView.findViewById(R.id.country_flag_image);
        }

        public void bind(CountryModel country) {
            countryNameView.setText(country.getCountryName());
            regionView.setText("( " + country.getRegion()+" )");
            capitalNameView.setText("Capital: " + country.getCapitalName());
            ImageLoadUtil.loadImage(countryImageView, country.getFlag(), ImageLoadUtil.getDrawable(countryImageView.getContext()));
        }
    }

    public class ShowPopulationViewHolder extends RecyclerView.ViewHolder{

        private TextView countryNameView;
        private TextView regionView;
        private TextView populationView;
        private ImageView countryImageView;

        public ShowPopulationViewHolder(@NonNull View itemView) {
            super(itemView);
            countryNameView = itemView.findViewById(R.id.country_name_view);
            regionView = itemView.findViewById(R.id.second_text_view);
            populationView = itemView.findViewById(R.id.third_text_view);
            countryImageView = itemView.findViewById(R.id.country_flag_image);
        }

        public void bind(CountryModel country) {
            countryNameView.setText(country.getCountryName());
            regionView.setText("( " + country.getRegion()+" )");
            String populationText = "Population: " + country.getPopulation();
            populationView.setText(populationText);
            ImageLoadUtil.loadImage(countryImageView, country.getFlag(), ImageLoadUtil.getDrawable(countryImageView.getContext()));
        }
    }

    public class ShowAreaViewHolder extends RecyclerView.ViewHolder{

        private TextView countryNameView;
        private TextView regionView;
        private TextView areaView;
        private ImageView countryImageView;

        public ShowAreaViewHolder(@NonNull View itemView) {
            super(itemView);
            countryNameView = itemView.findViewById(R.id.country_name_view);
            regionView = itemView.findViewById(R.id.second_text_view);
            areaView = itemView.findViewById(R.id.third_text_view);
            countryImageView = itemView.findViewById(R.id.country_flag_image);
        }

        public void bind(CountryModel country) {
            countryNameView.setText(country.getCountryName());
            regionView.setText("( " + country.getRegion()+" )");

            SpannableStringBuilder areaText = new SpannableStringBuilder("Area: " + country.getArea() + " km2");
            areaText.setSpan(new SuperscriptSpan(), areaText.length() - 1, areaText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            areaText.setSpan(new RelativeSizeSpan(0.75f), areaText.length() - 1, areaText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            areaView.setText(areaText);

            ImageLoadUtil.loadImage(countryImageView, country.getFlag(), ImageLoadUtil.getDrawable(countryImageView.getContext()));
        }
    }

    public final class ViewType {
        public static final int NORMAL = 0;
        public static final int POPULATION = 1;
        public static final int AREA = 2;

        private ViewType() {}
    }
}
