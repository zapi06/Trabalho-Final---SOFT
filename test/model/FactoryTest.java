package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FactoryTest {

    @Test
    public void contaCorrenteFactory_criaConta_tipoCorrente() {
        ContaFactory factory = new ContaCorrenteFactory(1000.0);
        Conta conta = factory.criarConta("1001");
        assertEquals("Corrente", conta.getTipo());
    }

    @Test
    public void contaCorrenteFactory_limiteCorreto() {
        ContaCorrenteFactory factory = new ContaCorrenteFactory(500.0);
        ContaCorrente conta = (ContaCorrente) factory.criarConta("1002");
        assertEquals(500.0, conta.getLimite());
    }

    @Test
    public void contaPoupancaFactory_criaConta_tipoPoupanca() {
        ContaFactory factory = new ContaPoupancaFactory(0.005);
        Conta conta = factory.criarConta("2001");
        assertEquals("Poupança", conta.getTipo());
    }

    @Test
    public void contaPoupancaFactory_taxaCorreta() {
        ContaPoupancaFactory factory = new ContaPoupancaFactory(0.008);
        ContaPoupanca conta = (ContaPoupanca) factory.criarConta("2002");
        assertEquals(0.008, conta.getTaxaRendimento());
    }

    @Test
    public void factory_contasCriadasComNumerosDistintos() {
        ContaFactory factory = new ContaCorrenteFactory(1000.0);
        Conta c1 = factory.criarConta("1001");
        Conta c2 = factory.criarConta("1002");
        assertNotEquals(c1.getNumero(), c2.getNumero());
    }
}
