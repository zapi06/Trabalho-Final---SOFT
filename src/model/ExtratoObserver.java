package model;

import java.util.ArrayList;

public class ExtratoObserver implements TransacaoObserver {
    private ArrayList<Transacao> extrato;

    public ExtratoObserver(ArrayList<Transacao> extrato) {
        this.extrato = extrato;
    }

    @Override
    public void atualizar(Transacao t) {
        extrato.add(t);
    }
}
