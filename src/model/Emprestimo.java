package model;

import java.util.ArrayList;

public class Emprestimo {
    private double valorTotal;
    private int qtdParcelas;
    private String status; // pendente, aprovado, recusado, quitado
    private ArrayList<Parcela> parcelas;

    private static final double TAXA_JUROS = 0.05; // 5% ao mês

    public Emprestimo(double valorTotal, int qtdParcelas) {
        if (valorTotal <= 0) throw new IllegalArgumentException("Valor deve ser positivo.");
        if (qtdParcelas <= 0) throw new IllegalArgumentException("Parcelas deve ser positivo.");
        this.valorTotal = valorTotal;
        this.qtdParcelas = qtdParcelas;
        this.status = "pendente";
        this.parcelas = new ArrayList<>();
    }

    public double calcularJuros() {
        return valorTotal * TAXA_JUROS * qtdParcelas;
    }

    public double calcularValorParcela() {
        return (valorTotal + calcularJuros()) / qtdParcelas;
    }

    public void aprovar() {
        this.status = "aprovado";
        gerarParcelas();
    }

    public void recusar() {
        this.status = "recusado";
    }

    private void gerarParcelas() {
        parcelas.clear();
        double valorParcela = calcularValorParcela();
        for (int i = 1; i <= qtdParcelas; i++) {
            parcelas.add(new Parcela(valorParcela, i));
        }
    }

    public void pagarParcela(int numero) {
        for (Parcela p : parcelas) {
            if (p.getNumero() == numero && !p.isPago()) {
                p.pagar(); // verificar e atualizar o saldo e possivelmente atualizar o observer;
                verificarQuitacao();
                return;
            }
        }
        throw new IllegalArgumentException("Parcela não encontrada ou já paga.");
    }

    private void verificarQuitacao() {
        for (Parcela p : parcelas) {
            if (!p.isPago()) return;
        }
        this.status = "quitado";
    }

    public double getValorTotal() { return valorTotal; }
    public int getQtdParcelas() { return qtdParcelas; }
    public String getStatus() { return status; }
    public ArrayList<Parcela> getParcelas() { return parcelas; }
}
