package mobile.example.opet.caksecuritycar;

/**
 * Created by Juliano Zapelini on 31/05/2017.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import mobile.example.opet.caksecuritycar.VO.Carro;
import mobile.example.opet.caksecuritycar.VO.Usuario;

public class Util {
    public static String toString(InputStream is) throws IOException {
        BufferedReader br;
        StringBuilder sb;
        String line;

        br = new BufferedReader(new InputStreamReader(is));
        sb = new StringBuilder();

        while((line = br.readLine()) != null){
            sb.append(line);
        }
        br.close();
        return sb.toString();
    }


    public static Usuario JSONToUsuario(String jsonFile){
        JSONObject mainObj;
        Usuario usuario = new Usuario();
        try {
            mainObj = new JSONObject(jsonFile);

            String json_idUsuario = mainObj.getString("IdUsuario");
            String json_nome = mainObj.getString("Nome");
            String json_telefone = mainObj.getString("Telefone");
            String json_email = mainObj.getString("Email");
            String json_senha = mainObj.getString("Senha");


            usuario.setIdUsuario(Integer.parseInt(json_idUsuario));
            usuario.setNome(json_nome);
            usuario.setTelefone(json_telefone);
            usuario.setEmail(json_email);
            usuario.setSenha(json_senha);

        } catch (JSONException e) {
            e.printStackTrace();
        }finally {
            return usuario;
        }
    }

    public static Usuario UsuarioToJSON(String jsonFile){
        JSONObject mainObj;
        Usuario usuario = new Usuario();
        try {
            mainObj = new JSONObject(jsonFile);

            String json_idUsuario = mainObj.getString("IdUsuario");
            String json_Nome = mainObj.getString("Nome");
            String json_Telefone = mainObj.getString("Telefone");
            String json_Email = mainObj.getString("Email");
            String json_Senha = mainObj.getString("Senha");


            usuario.setIdUsuario(Integer.parseInt(json_idUsuario));
            usuario.setNome(json_Nome);
            usuario.setTelefone(json_Telefone);
            usuario.setEmail(json_Email);
            usuario.setSenha(json_Senha);

        } catch (JSONException e) {
            e.printStackTrace();
        }finally {
            return usuario;
        }
    }


    public static Carro JSONToCarro(String jsonFile){
        JSONObject mainObj;
        Carro carro = new Carro();
        try {
            mainObj = new JSONObject(jsonFile);

            String json_IdCarro = mainObj.getString("IdCarro");
            String json_IdUsuario = mainObj.getString("IdUsuario");
            String json_Marca = mainObj.getString("Marca");
            String json_Modelo = mainObj.getString("Modelo");
            String json_Placa = mainObj.getString("Placa");


            carro.setIdCarro(Integer.parseInt(json_IdCarro));
            carro.setIdUsuario(Integer.parseInt(json_IdUsuario));
            carro.setMarca(json_Marca);
            carro.setModelo(json_Modelo);
            carro.setPlaca(json_Placa);

        } catch (JSONException e) {
            e.printStackTrace();
        }finally {
            return carro;
        }
    }

}
