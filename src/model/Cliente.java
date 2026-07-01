package model;

import java.util.ArrayList;

public abstract class Cliente extends Usuario {
    protected String nome;
    protected String telefone;
    protected String email;
    private ArrayList<Conta> contas;

    public Cliente(String login, String senha, String nome, String telefone, String email) {
        super(login, senha, "CLIENTE");
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.contas = new ArrayList<>();
    }

    public String getNome() { return nome; }
    public String getTelefone() { return telefone; }
    public String getEmail() { return email; }
    public ArrayList<Conta> getContas() { return contas; }

    public void adicionarConta(Conta conta) {
        contas.add(conta);
    }

    public double consultarSaldo(String numeroConta) {
        for (Conta c : contas) {
            if (c.getNumero().equals(numeroConta)) return c.getSaldo();
        }
        throw new IllegalArgumentException("Conta não encontrada.");
    }
}
