package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContaPoupancaTest {

    @Test
    public void calcularRendimento_correto() {
        ContaPoupanca cp = new ContaPoupanca("2001", 0.005);
        cp.depositar(1000.0);
        assertEquals(5.0, cp.calcularRendimento());
    }

    @Test
    public void calcularRendimento_contaVazia_retornaZero() {
        ContaPoupanca cp = new ContaPoupanca("2002", 0.005);
        assertEquals(0.0, cp.calcularRendimento());
    }

    @Test
    public void getTipo_retornaPoupanca() {
        ContaPoupanca cp = new ContaPoupanca("2003", 0.005);
        assertEquals("Poupança", cp.getTipo());
    }

    @Test
    public void contaCorrente_getTipo_retornaCorrente() {
        ContaCorrente cc = new ContaCorrente("1001", 1000.0);
        assertEquals("Corrente", cc.getTipo());
    }
}
