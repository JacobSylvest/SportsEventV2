package com.example.sportseventv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Denne side bliver ikke uddybet yderligere til dette projekt. der vil, for fremtiden, være mulighed for, at 'authenticate' brugere gennem firebase database (indbygget funktion),
 * men for vores projekt ser vi ikke dette som en overbygning der er nødvendig. Vi har allerede kald frem og tlibage fra en ekstern database, og dette opfylder kravene til
 * brugen af database.
 */

public class ForgetPassword extends AppCompatActivity {
    Button back_to_login_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        back_to_login_btn = findViewById(R.id.glemt_pass_btn);

        back_to_login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reopenLogin();
            }
        });
    }
    public void reopenLogin(){
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }
}
