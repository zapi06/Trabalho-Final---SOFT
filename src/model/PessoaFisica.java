package model;

import java.time.LocalDate;

public class PessoaFisica extends Cliente {
    private String cpf;
    private LocalDate dataNasc;

    public PessoaFisica(String cpf, String senha, String nome, String telefone, String email, LocalDate dataNasc) {
        super(cpf, senha, nome, telefone, email);
        this.cpf = cpf;
        this.dataNasc = dataNasc;
    }

    public String getCpf() { return cpf; }
    public LocalDate getDataNasc() { return dataNasc; }
}
