package model;

import java.time.LocalDateTime;

public class Transacao {
    private String tipo;
    private double valor;
    private LocalDateTime dataHora;

    public Transacao(String tipo, double valor) {
        this.tipo = tipo;
        this.valor = valor;
        this.dataHora = LocalDateTime.now().withNano(0); // retira milissegundos
    }

    public String getTipo() { return tipo; }
    public double getValor() { return valor; }
    public LocalDateTime getDataHora() { return dataHora; }

    @Override
    public String toString() {
        return "[" + dataHora + "] " + tipo + " - R$ " + String.format("%.2f", valor);
    }
}
