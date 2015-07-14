package org.lunding.deliciousclock.register;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.lunding.deliciousclock.data.Meal;
import org.lunding.deliciousclock.R;

import java.util.ArrayList;

/**
 * Created by Lunding on 13/07/15.
 *
 */

public class MealAdapter extends ArrayAdapter<Meal> {

    public MealAdapter(Context context, ArrayList<Meal> meals){
        super(context, 0, meals);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Meal meal = getItem(position);

        MealViewHolder viewHolder;
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_meal, parent, false);
            viewHolder = new MealViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (MealViewHolder) convertView.getTag();
        }
        viewHolder.image.setImageResource(meal.getImage());
        viewHolder.title.setText(meal.getTitle());
        viewHolder.description.setText(meal.getDescription());

        return convertView;
    }

    public class MealViewHolder {
        protected ImageView image;
        protected TextView title;
        protected TextView description;

        public MealViewHolder(View v){
            image = (ImageView) v.findViewById(R.id.list_item_meal_image);
            title = (TextView) v.findViewById(R.id.list_item_meal_title);
            description = (TextView) v.findViewById(R.id.list_item_meal_description);
        }
    }
}
