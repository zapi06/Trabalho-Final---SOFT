package model;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class UsuarioTest {

    @Test
    public void autenticar_senhaCorreta_retornaTrue() {
        PessoaFisica pf = new PessoaFisica("12345678900", "senha123",
                "joaoth", "47999999999", "joaoth9@gmail.com", LocalDate.of(1995, 1, 1));
        assertTrue(pf.autenticar("senha123"));
    }

    @Test
    public void autenticar_senhaErrada_retornaFalse() {
        PessoaFisica pf = new PessoaFisica("12345678900", "senha321",
                "Dudu", "47999999999", "dudu@gmail.com", LocalDate.of(1990, 1, 1));
        assertFalse(pf.autenticar("errada"));
    }

    @Test
    public void autenticar_senhaCaseSensitive() {
        PessoaFisica pf = new PessoaFisica("12345678900", "Senha999",
                "lugas", "47999999999", "lugas@gmail.com", LocalDate.of(2005, 10, 10));
        assertFalse(pf.autenticar("senha999")); // maiúscula diferente
    }

    @Test
    public void pessoaFisica_perfilCliente() {
        PessoaFisica pf = new PessoaFisica("12345678900", "petren",
                "Chuck", "47967676767", "reidelas@gmail.com", LocalDate.of(2005, 6, 25));
        assertEquals("CLIENTE", pf.getPerfil());
    }

    @Test
    public void usuarioInterno_perfilGerente() {
        UsuarioInterno ui = new UsuarioInterno("gerente01", "senha", "GERENTE", "MAT001", "Gerente");
        assertEquals("GERENTE", ui.getPerfil());
    }

    @Test
    public void pessoaJuridica_dadosCorretos() {
        PessoaJuridica pj = new PessoaJuridica("12345678000195", "senha",
                "Empresa XYZ", "47333333333", "contatoXYZ@gmail.com");
        assertEquals("12345678000195", pj.getCnpj());
        assertEquals("Empresa XYZ", pj.getRazaoSocial());
    }
}
