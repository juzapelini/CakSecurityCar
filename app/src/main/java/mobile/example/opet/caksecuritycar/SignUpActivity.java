package mobile.example.opet.caksecuritycar;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;
import mobile.example.opet.caksecuritycar.VO.Usuario;


public class SignUpActivity extends Activity {

    EditText txtNome;
    EditText txtTelefone;
    EditText txtEmail;
    EditText txtSenha1;
    EditText txtSenha2;
    TextView txtMsg;
    String nomeView;
    String telefoneView;
    String emailView;
    String senha1View;
    String senha2View;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        txtNome = (EditText) findViewById(R.id.txtNome);
        txtTelefone = (EditText) findViewById(R.id.txtTelefone);
        txtEmail = (EditText) findViewById(R.id.txtIdUsuario);
        txtSenha1 = (EditText) findViewById(R.id.txtSenha1);
        txtSenha2 = (EditText) findViewById(R.id.txtSenha2);
        txtMsg = (TextView) findViewById(R.id.txtMsg);

        //startAPIConsultaUsuario();
    }

    ////////////////////////// GET USUARIO /////////////////////

    public void startAPIConsultaUsuario(){

        new ConnectionAPIConsultaUsuario().execute();
    }

    private class ConnectionAPIConsultaUsuario extends AsyncTask<Void, Void, Usuario>{

        @Override
        protected Usuario doInBackground (Void...params){
            HttpURLConnection con = null;

            try {
                 //TODO ALTERAR USUARIO FIXO
                URL url = new URL("http://carcakapi.azurewebsites.net/v1/usuario/1");
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

            txtNome.setText(usuario.getNome());
            txtTelefone.setText(usuario.getTelefone());
            txtEmail.setText(usuario.getEmail());
            txtSenha1.setText(usuario.getSenha());
        }

    }

    ////////////////////////// POST USUARIO /////////////////////

    public void startAPICadastraUsuario(View v){
        nomeView = txtNome.getText().toString();
        telefoneView = txtTelefone.getText().toString();
        emailView = txtEmail.getText().toString();
        senha1View = txtSenha1.getText().toString();
        senha2View = txtSenha2.getText().toString();

        new ConnectionAPICadastraUsuario().execute();
    }

    private class ConnectionAPICadastraUsuario extends AsyncTask<Void, Void, Usuario>{

        @Override
        protected Usuario doInBackground (Void...params){
            HttpURLConnection urlConnection  = null;

            try {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("Nome", nomeView);
                jsonObject.put("Telefone", telefoneView);
                jsonObject.put("Email", emailView);
                jsonObject.put("Senha", senha1View);

                URL url = new URL("http://carcakapi.azurewebsites.net/v1/usuario");
                urlConnection = (HttpURLConnection) url.openConnection();
                //InputStream in = new BufferedInputStream(urlConnection.getInputStream());


                //urlConnection.setDoInput(true);
                urlConnection.setRequestMethod("POST");
                //urlConnection.setRequestProperty("Content-Type", "application/json");

                urlConnection.connect();

                DataOutputStream wr = new DataOutputStream(urlConnection.getOutputStream());
                wr.writeBytes(jsonObject.toString());
                wr.flush();
                wr.close();

                return null;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }finally {
                urlConnection.disconnect();
            }

            return null;
        }

       // @Override
        //protected void onPostExecute(Usuario usuario){

           // txtMsg.setText("DEU BOA =))) post blz");
       // }

    }


}
