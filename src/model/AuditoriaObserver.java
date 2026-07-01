package model;

public class AuditoriaObserver implements TransacaoObserver {

    @Override
    public void atualizar(Transacao t) {
        System.out.println("[AUDITORIA] " + t.toString());
    }
}
