package model;

public class ContaPoupanca extends Conta {
    private double taxaRendimento;

    public ContaPoupanca(String numero, double taxaRendimento) {
        super(numero);
        this.taxaRendimento = taxaRendimento;
    }

    public double getTaxaRendimento() { return taxaRendimento; }

    public double calcularRendimento() {
        return getSaldo() * taxaRendimento;
    }

    @Override
    public String getTipo() { return "Poupança"; }
}
