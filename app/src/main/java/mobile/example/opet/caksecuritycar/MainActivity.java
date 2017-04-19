package mobile.example.opet.caksecuritycar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void buscarCarView(View v) {
        Intent intent = new Intent(this, CarActivity.class);
        startActivity(intent);
    }
    public void cadastrarCarView(View v) {
        Intent intent = new Intent(this, CarActivity.class);
        startActivity(intent);
    }
}