package model;

public class ContaCorrenteFactory implements ContaFactory {

    private double limite;

    public ContaCorrenteFactory(double limite) {
        this.limite = limite;
    }

    @Override
    public Conta criarConta(String numero) {
        return new ContaCorrente(numero, limite);
    }
}
