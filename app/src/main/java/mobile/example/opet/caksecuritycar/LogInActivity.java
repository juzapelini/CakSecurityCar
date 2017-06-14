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

import mobile.example.opet.caksecuritycar.VO.Usuario;

import static mobile.example.opet.caksecuritycar.R.id.txtNome;

public class LogInActivity extends AppCompatActivity {

    EditText txtIdUsuario;
    EditText txtSenha;
    EditText txtTeste;
    String idUsuario;
    String senha;
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        txtIdUsuario = (EditText) findViewById(R.id.txtIdUsuario);
        txtSenha = (EditText) findViewById(R.id.txtSenha);
        txtTeste = (EditText) findViewById(R.id.txtTeste);
    }
    public void signUpView(View v) {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }
    public void mainActivityView(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    ////////////////////////// GET USUARIO /////////////////////

    public void startAPIConsultaUsuario(View v){
        view = v;
        idUsuario = txtIdUsuario.getText().toString();
        senha = txtSenha.getText().toString();
        new LogInActivity.ConnectionAPIConsultaUsuario().execute();
    }

    private class ConnectionAPIConsultaUsuario extends AsyncTask<Void, Void, Usuario> {

        @Override
        protected Usuario doInBackground (Void...params){
            HttpURLConnection con = null;

            try {

                URL url = new URL("http://carcakapi.azurewebsites.net/v1/usuario/" + idUsuario );
                con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                con.setDoInput(true);

                String resposta = Util.toString(con.getInputStream());
                Usuario usuario = Util.JSONToUsuario(resposta);

                return usuario;
            }catch(Exception e){
                Log.e("ERROR", e.getMessage());
            }finally {
                con.disconnect();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Usuario usuario){
            if(senha.equals(usuario.getSenha())){
                txtTeste.setText("senha ok");
                mainActivityView(view);
            }else{
                txtTeste.setText("Senha inválida");
            }
        }

    }
}
