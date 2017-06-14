package mobile.example.opet.caksecuritycar.VO;

/**
 * Created by julia on 12/06/2017.
 */

public class Carro {

    private int idCarro;
    private int idUsuario;
    private String marca;
    private String modelo;
    private String placa;


    public Carro() {
    }

    public Carro(int idCarro, int idUsuario, String marca, String modelo, String placa) {
        this.idCarro = idCarro;
        this.idUsuario = idUsuario;
        this.marca = marca;
        this.modelo = modelo;
        this.placa = placa;
    }

    public int getIdCarro() {
        return idCarro;
    }

    public void setIdCarro(int idCarro) {
        this.idCarro = idCarro;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }
}
