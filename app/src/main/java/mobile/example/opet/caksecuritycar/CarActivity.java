package mobile.example.opet.caksecuritycar;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.net.HttpURLConnection;
import java.net.URL;

import mobile.example.opet.caksecuritycar.VO.Carro;
import mobile.example.opet.caksecuritycar.VO.Usuario;

public class CarActivity extends AppCompatActivity {

    String placa, nome, telefone;
    EditText txtMarca, txtModelo, txtPlaca, txtNome, txtTelefone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        placa = bundle.getString("placa");

        txtMarca = (EditText) findViewById(R.id.txtMarca);
        txtModelo = (EditText) findViewById(R.id.txtModelo);
        txtPlaca = (EditText) findViewById(R.id.txtPlaca);
        txtNome = (EditText) findViewById(R.id.txtNome);
        txtTelefone = (EditText) findViewById(R.id.txtTelefone);

        startAPIConsultaCarro();
    }

    public void startAPIConsultaCarro(){
        new ConnectionAPIConsultaCarro().execute();
    }

    private class ConnectionAPIConsultaCarro extends AsyncTask<Void, Void, Carro> {

        @Override
        protected Carro doInBackground (Void...params){
            HttpURLConnection con = null;

            try {

                URL url = new URL("http://carcakapi.azurewebsites.net/v1/carro/placa/" + placa );
                con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                con.setDoInput(true);

                String resposta = Util.toString(con.getInputStream());
                Carro carro = Util.JSONToCarro(resposta);

                url = new URL("http://carcakapi.azurewebsites.net/v1/usuario/" + carro.getIdUsuario());
                con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                con.setDoInput(true);

                resposta = Util.toString(con.getInputStream());
                Usuario usuario = new Usuario();
                usuario = Util.JSONToUsuario(resposta);
                nome = usuario.getNome();
                telefone = usuario.getTelefone();


                return carro;
            }catch(Exception e){
                Log.e("ERROR", e.getMessage());
            }finally {
                con.disconnect();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Carro carro){
            txtMarca.setText(carro.getMarca());
            txtModelo.setText(carro.getModelo());
            txtPlaca.setText(carro.getPlaca());
            txtNome.setText(nome);
            txtTelefone.setText(telefone);
        }

    }
}