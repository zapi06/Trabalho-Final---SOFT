package model;

public class Parcela {
    private double valorParcela;
    private int numero;
    private boolean pago;

    public Parcela(double valorParcela, int numero) {
        this.valorParcela = valorParcela;
        this.numero = numero;
        this.pago = false;
    }

    public void pagar() { this.pago = true; }

    public double getValorParcela() { return valorParcela; }
    public int getNumero() { return numero; }
    public boolean isPago() { return pago; }
}
