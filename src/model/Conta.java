package model;

import java.util.ArrayList;

public abstract class Conta {
    private String numero;
    protected double saldo;
    private ArrayList<Transacao> extrato;
    private ArrayList<TransacaoObserver> observers;

    public Conta(String numero) {
        this.numero = numero;
        this.saldo = 0.0;
        this.extrato = new ArrayList<>();
        this.observers = new ArrayList<>();

        // Registra os dois observers por padrão
        this.observers.add(new ExtratoObserver(extrato));
        this.observers.add(new AuditoriaObserver());
    }

    public void adicionarObserver(TransacaoObserver observer) {
        observers.add(observer);
    }

    private void notificarObservers(Transacao t) {
        for (TransacaoObserver obs : observers) {
            obs.atualizar(t);
        }
    }

    public String getNumero() { return numero; }
    public double getSaldo() { return saldo; }
    public ArrayList<Transacao> getExtrato() { return extrato; }

    public void depositar(double valor) {
        if (valor <= 0) throw new IllegalArgumentException("Valor deve ser positivo.");
        saldo += valor;
        notificarObservers(new Transacao("Depósito", valor));
    }

    public void sacar(double valor) {
        if (valor <= 0) throw new IllegalArgumentException("Valor deve ser positivo.");
        if (valor > saldo) throw new IllegalStateException("Saldo insuficiente.");
        saldo -= valor;
        notificarObservers(new Transacao("Saque", valor));
    }

    public void transferir(Conta destino, double valor) {
        if (destino == null) throw new IllegalArgumentException("Conta destino inválida.");
        if (valor <= 0) throw new IllegalArgumentException("Valor deve ser positivo.");
        if (valor > saldo) throw new IllegalStateException("Saldo insuficiente.");

        saldo -= valor;
        destino.saldo += valor;

        notificarObservers(new Transacao("Transferência enviada para " + destino.getNumero(), valor));
        destino.notificarObservers(new Transacao("Transferência recebida de " + this.numero, valor));
    }

    public abstract String getTipo();
}
