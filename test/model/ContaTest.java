package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContaTest {

    private ContaCorrente conta;
    private ContaCorrente contaDestino;

    @BeforeEach
    public void setUp() {
        conta = new ContaCorrente("1001", 500.0);
        contaDestino = new ContaCorrente("1002", 500.0);
    }

    // --- DEPÓSITO ---

    @Test
    public void deposito_valorPositivo_aumentaSaldo() {
        conta.depositar(200.0);
        assertEquals(200.0, conta.getSaldo());
    }

    @Test
    public void deposito_registraTransacaoNoExtrato() {
        conta.depositar(100.0);
        assertEquals(1, conta.getExtrato().size());
        assertEquals("Depósito", conta.getExtrato().get(0).getTipo());
    }

    /*
        @Test
        public void deposito_valorZero_lancaExcecao() {
            assertThrows(IllegalArgumentException.class, new Executable() {
                @Override
                public void execute() {
                    conta.depositar(0);
                }
            });
        }
    */

    @Test
    public void deposito_valorZero_lancaExcecao() {
        assertThrows(IllegalArgumentException.class, () -> conta.depositar(0)); // utilização de função lambda
    }

    @Test
    public void deposito_valorNegativo_lancaExcecao() {
        assertThrows(IllegalArgumentException.class, () -> conta.depositar(-50));
    }

    // --- SAQUE ---

    @Test
    public void saque_saldoSuficiente_diminuiSaldo() {
        conta.depositar(300.0);
        conta.sacar(100.0);
        assertEquals(200.0, conta.getSaldo());
    }

    @Test
    public void saque_saldoInsuficiente_lancaExcecao() {
        conta.depositar(50.0);
        assertThrows(IllegalStateException.class, () -> conta.sacar(100.0));
    }

    @Test
    public void saque_valorNegativo_lancaExcecao() {
        assertThrows(IllegalArgumentException.class, () -> conta.sacar(-10));
    }

    @Test
    public void saque_contaVazia_lancaExcecao() {
        assertThrows(IllegalStateException.class, () -> conta.sacar(1.0));
    }

    @Test
    public void saque_registraTransacaoNoExtrato() {
        conta.depositar(200.0);
        conta.sacar(50.0);
        assertEquals(2, conta.getExtrato().size());
        assertEquals("Saque", conta.getExtrato().get(1).getTipo());
    }

    // --- TRANSFERÊNCIA ---

    @Test
    public void transferencia_debitaOrigem_creditaDestino() {
        conta.depositar(500.0);
        conta.transferir(contaDestino, 200.0);
        assertEquals(300.0, conta.getSaldo());
        assertEquals(200.0, contaDestino.getSaldo());
    }

    @Test
    public void transferencia_saldoInsuficiente_lancaExcecao() {
        conta.depositar(100.0);
        assertThrows(IllegalStateException.class, () -> conta.transferir(contaDestino, 200.0));
    }

    @Test
    public void transferencia_destinoNulo_lancaExcecao() {
        conta.depositar(100.0);
        assertThrows(IllegalArgumentException.class, () -> conta.transferir(null, 50.0));
    }

    @Test
    public void transferencia_valorZero_lancaExcecao() {
        conta.depositar(100.0);
        assertThrows(IllegalArgumentException.class, () -> conta.transferir(contaDestino, 0));
    }

    @Test
    public void transferencia_registraExtratoEmAmbas() {
        conta.depositar(300.0);
        conta.transferir(contaDestino, 100.0);
        assertEquals(2, conta.getExtrato().size());        // depósito + transferência enviada
        assertEquals(1, contaDestino.getExtrato().size()); // transferência recebida
    }
}
