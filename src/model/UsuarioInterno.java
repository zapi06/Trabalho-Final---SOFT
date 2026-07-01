package model;

public class UsuarioInterno extends Usuario {
    private String matricula;
    private String cargo;

    public UsuarioInterno(String login, String senha, String perfil, String matricula, String cargo) {
        super(login, senha, perfil);
        this.matricula = matricula;
        this.cargo = cargo;
    }

    public String getMatricula() { return matricula; }
    public String getCargo() { return cargo; }
}
