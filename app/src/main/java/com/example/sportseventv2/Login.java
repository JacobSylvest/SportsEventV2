package com.example.sportseventv2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

public class Login extends AppCompatActivity {

    Button callSignUp, login_btn;
    ImageView image;
    TextView logoText, sloganText;
    TextInputLayout username, password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this hides status bar on screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        callSignUp = findViewById(R.id.ny_bruger);
        image = findViewById(R.id.logo_login);
        logoText = findViewById(R.id.velkommen_besked);
        sloganText = findViewById(R.id.log_ind_besked);
        username = findViewById(R.id.brugernavn);
        password = findViewById(R.id.password);
        login_btn = findViewById(R.id.fortsæt_login);

        callSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, SignUp.class);

                Pair[] pairs = new Pair[7];
                pairs[0] = new Pair<View, String>(image, "logo_image");
                pairs[1] = new Pair<View, String>(logoText, "slogan_text");
                pairs[2] = new Pair<View, String>(sloganText, "log_ind_besked");
                pairs[3] = new Pair<View, String>(username, "brugernavn_slot");
                pairs[4] = new Pair<View, String>(password, "password_slot");
                pairs[5] = new Pair<View, String>(login_btn, "fortsæt_knap");
                pairs[6] = new Pair<View, String>(callSignUp, "logind_signind_knap");

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Login.this, pairs);
                startActivity(intent, options.toBundle());

            }
        });
    }
}