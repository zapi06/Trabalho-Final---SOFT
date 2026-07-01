package model;

public abstract class Usuario {
    protected String login;
    protected String senha;
    protected String perfil;

    public Usuario(String login, String senha, String perfil) {
        this.login = login;
        this.senha = senha;
        this.perfil = perfil;
    }

    public String getLogin() { return login; }
    public String getPerfil() { return perfil; }

    public boolean autenticar(String senha) {
        return this.senha.equals(senha);
    }
}
