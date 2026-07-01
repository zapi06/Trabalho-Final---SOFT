package model;

public class PessoaJuridica extends Cliente {
    private String cnpj;
    private String razaoSocial;

    public PessoaJuridica(String cnpj, String senha, String razaoSocial, String telefone, String email) {
        super(cnpj, senha, razaoSocial, telefone, email);
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
    }

    public String getCnpj() { return cnpj; }
    public String getRazaoSocial() { return razaoSocial; }
}
