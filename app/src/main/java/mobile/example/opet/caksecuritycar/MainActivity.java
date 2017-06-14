package mobile.example.opet.caksecuritycar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    View view;
    EditText txtPlaca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void buscarCarView(View v) {

        Intent intent = new Intent(MainActivity.this, CarActivity.class);
        txtPlaca = (EditText) findViewById(R.id.txtPlaca);
        String placa  = txtPlaca.getText().toString();
        Bundle bundle = new Bundle();

        bundle.putString("placa", placa);
        intent.putExtras(bundle);

        startActivity(intent);
    }
    public void cadastrarCarView(View v) {
        Intent intent = new Intent(this, CarActivity.class);
        startActivity(intent);
    }


}