package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ObserverTest {

    private ContaCorrente conta;

    @BeforeEach
    public void setUp() {
        conta = new ContaCorrente("1001", 1000.0);
    }

    @Test
    public void extratoObserver_deposito_registraTransacao() {
        conta.depositar(100.0);
        assertEquals(1, conta.getExtrato().size());
        assertEquals("Depósito", conta.getExtrato().get(0).getTipo());
    }

    @Test
    public void extratoObserver_saque_registraTransacao() {
        conta.depositar(200.0);
        conta.sacar(50.0);
        assertEquals(2, conta.getExtrato().size());
        assertEquals("Saque", conta.getExtrato().get(1).getTipo());
    }

    @Test
    public void extratoObserver_transferencia_registraEmAmbas() {
        ContaCorrente destino = new ContaCorrente("1002", 1000.0);
        conta.depositar(500.0);
        conta.transferir(destino, 200.0);

        // origem: depósito + transferência enviada
        assertEquals(2, conta.getExtrato().size());
        // destino: transferência recebida
        assertEquals(1, destino.getExtrato().size());
    }
}
