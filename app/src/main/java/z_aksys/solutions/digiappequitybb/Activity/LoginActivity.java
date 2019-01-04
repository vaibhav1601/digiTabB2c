package z_aksys.solutions.digiappequitybb.Activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import z_aksys.solutions.digiappequitybb.R;
import z_aksys.solutions.digiappequitybb.utils.CleverTapUtils;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        CleverTapUtils.getInstance(this).logCustomEvent("Login Activity Launched");

        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/Lato-Bold.ttf");


        btnLogin = (Button) findViewById(R.id.btnProceedPolicy);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });


    }
}
