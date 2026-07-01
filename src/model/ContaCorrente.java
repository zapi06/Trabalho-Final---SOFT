package model;

public class ContaCorrente extends Conta {
    private double limite;

    public ContaCorrente(String numero, double limite) {
        super(numero);
        this.limite = limite;
    }

    public double getLimite() { return limite; }

    @Override
    public String getTipo() { return "Corrente"; }
}
