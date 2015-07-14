package org.lunding.deliciousclock.register;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.lunding.deliciousclock.LoginActivity;
import org.lunding.deliciousclock.R;

public class WelcomeFragment extends Fragment {

    private static final String TAG = WelcomeFragment.class.getSimpleName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_welcome, container, false);

        Button registerButton = (Button) rootView.findViewById(R.id.register_button);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Continue to register screens");
                Intent intent = new Intent(getActivity(), MealSelectionActivity.class);
                intent.putExtra(SignupObject.SIGNOP_OBJECT_TAG, new SignupObject());
                startActivity(intent);
            }
        });

        TextView loginButton = (TextView) rootView.findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Continue to login screen");
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                intent.addFlags(LoginActivity.LOGIN_FLAG);
                startActivity(intent);
            }
        });

        return rootView;

    }


}
