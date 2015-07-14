package org.lunding.deliciousclock;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.lunding.deliciousclock.register.MealSelectionActivity;
import org.lunding.deliciousclock.register.SignupObject;

public class LoginActivity extends AppCompatActivity {

    public static final int REGISTER_FLAG = 100001;
    public static final int LOGIN_FLAG = 100002;

    private Button facebook;
    private Button google;
    private Button email;
    private TextView loginText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginText = (TextView) findViewById(R.id.login_button);
        facebook = (Button) findViewById(R.id.facebook_button);
        google = (Button) findViewById(R.id.google_button);
        email = (Button) findViewById(R.id.email_button);

        int flag = getIntent().getFlags();
        switch (flag){
            case REGISTER_FLAG:
                prepareRegister();
                break;
            case LOGIN_FLAG:
                prepareLogin();
                break;
            default:
                prepareLogin();
        }
    }

    private void prepareRegister(){
        facebook.setText(getString(R.string.title_register_facebook));
        facebook.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15);
        google.setText(getString(R.string.title_register_google));
        google.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15);
        email.setText(getString(R.string.title_register_email));
        email.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15);
        facebook.setOnClickListener(registerListener());
        google.setOnClickListener(registerListener());
        email.setOnClickListener(registerListener());

        loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                intent.addFlags(LoginActivity.LOGIN_FLAG);
                startActivity(intent);
                finish();
            }
        });
    }

    private void prepareLogin(){
        facebook.setText(getString(R.string.title_login_facebook));
        google.setText(getString(R.string.title_login_google));
        email.setText(getString(R.string.title_login_email));
        loginText.setVisibility(View.INVISIBLE);
        facebook.setOnClickListener(loginListener());
        google.setOnClickListener(loginListener());
        email.setOnClickListener(loginListener());
    }


    private View.OnClickListener registerListener(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "You are now registered", Toast.LENGTH_SHORT).show();
            }
        };
    }

    private View.OnClickListener loginListener(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        };
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
