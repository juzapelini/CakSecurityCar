package mobile.example.opet.caksecuritycar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LogInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
    }
    public void signUpView(View v) {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }
    public void mainActivityView(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
