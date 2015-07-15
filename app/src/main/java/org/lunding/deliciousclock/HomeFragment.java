package org.lunding.deliciousclock;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import org.lunding.deliciousclock.data.Address;
import org.lunding.deliciousclock.data.Meal;
import org.lunding.deliciousclock.data.Time;


/**
 *
 * Created by Lunding on 14/07/15.
 */
public class HomeFragment extends Fragment {

    private static final String TAG = HomeFragment.class.getSimpleName();

    private Meal meal;
    private Time time;
    private Address address;

    private Button timeButton;
    private Button offsetButton;


    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        meal = Utilities.getMeal(getActivity());
        time = Utilities.getTime(getActivity());
        address = Utilities.getAddress(getActivity());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        ImageView mealImage = (ImageView) rootView.findViewById(R.id.homeview_meal_image);
        timeButton = (Button) rootView.findViewById(R.id.homeview_change_time_button);
        offsetButton = (Button) rootView.findViewById(R.id.homeview_change_offset_button);

        mealImage.setImageResource(meal.getHomecard());
        timeButton.setText("      " + Utilities.makeTimeString(time));
        offsetButton.setText(Utilities.makeOffsetStrign(time));

        mealImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Meal is now changed", Toast.LENGTH_SHORT).show();
            }
        });

        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utilities.showTimePicker(getActivity(), time, timeButton);
            }
        });

        offsetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utilities.showNumberPicker(getActivity(), time, offsetButton);
            }
        });


        return rootView;
    }

}
