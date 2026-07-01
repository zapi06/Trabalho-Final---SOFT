package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EmprestimoTest {

    private Emprestimo emprestimo;

    @BeforeEach
    public void setUp() {
        // R$1000 em 2 parcelas, juros = 5% * 2 meses = 10% => total R$1100 => parcela R$550
        emprestimo = new Emprestimo(1000.0, 2);
    }

    // --- CRIAÇÃO ---

    @Test
    public void criacao_statusInicial_pendente() {
        assertEquals("pendente", emprestimo.getStatus());
    }

    // --- CÁLCULO ---

    @Test
    public void calcularJuros_correto() {
        // 1000 * 0.05 * 2 = 100
        assertEquals(100.0, emprestimo.calcularJuros());
    }

    @Test
    public void calcularValorParcela_correto() {
        // (1000 + 100) / 2 = 550
        assertEquals(550.0, emprestimo.calcularValorParcela());
    }

    // --- APROVAÇÃO ---

    @Test
    public void aprovar_mudaStatusParaAprovado() {
        emprestimo.aprovar();
        assertEquals("aprovado", emprestimo.getStatus());
    }

    @Test
    public void aprovar_geraParcelasCorretas() {
        emprestimo.aprovar();
        assertEquals(2, emprestimo.getParcelas().size());
    }

    // --- RECUSA ---

    @Test
    public void recusar_mudaStatusParaRecusado() {
        emprestimo.recusar();
        assertEquals("recusado", emprestimo.getStatus());
    }

    // --- PAGAMENTO DE PARCELAS ---

    @Test
    public void pagarParcela_marcaComoPago() {
        emprestimo.aprovar();
        emprestimo.pagarParcela(1);
        assertTrue(emprestimo.getParcelas().get(0).isPago());
    }

    @Test
    public void pagarTodasParcelas_statusQuitado() {
        emprestimo.aprovar();
        emprestimo.pagarParcela(1);
        emprestimo.pagarParcela(2);
        assertEquals("quitado", emprestimo.getStatus());
    }
}
