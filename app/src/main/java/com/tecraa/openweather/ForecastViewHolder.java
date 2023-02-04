package com.tecraa.openweather;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ForecastViewHolder extends RecyclerView.ViewHolder {
    ImageView itemListIcon;
    TextView itemListTemperatureTV, itemListWeekNameTV, itemListDateTV;

    public ForecastViewHolder(@NonNull View itemView) {
        super(itemView);

        itemListIcon = itemView.findViewById(R.id.itemListIcon);
        itemListTemperatureTV = itemView.findViewById(R.id.itemListTemperatureTV);
        itemListWeekNameTV = itemView.findViewById(R.id.itemListWeekNameTV);
        itemListDateTV = itemView.findViewById(R.id.itemListDateTV);

    }
}
