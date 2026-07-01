package model;

public class ContaPoupancaFactory implements ContaFactory {

    private double taxaRendimento;

    public ContaPoupancaFactory(double taxaRendimento) {
        this.taxaRendimento = taxaRendimento;
    }

    @Override
    public Conta criarConta(String numero) {
        return new ContaPoupanca(numero, taxaRendimento);
    }
}
