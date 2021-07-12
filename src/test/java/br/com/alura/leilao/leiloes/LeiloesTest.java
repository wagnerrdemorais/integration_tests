package br.com.alura.leilao.leiloes;

import br.com.alura.leilao.login.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LeiloesTest {

    private LeiloesPage leiloesPage;
    private CadastroLeilaoPage cadastroLeilaoPage;

    @BeforeEach
    public void beforeEach() {
        LoginPage loginPage = new LoginPage();
        loginPage.preencheFormularioLogin("fulano", "pass");
        leiloesPage = loginPage.efetuaLogin();
        this.cadastroLeilaoPage = leiloesPage.carregarFormulario();
    }

    @AfterEach
    public void afterEach() {
        this.leiloesPage.quit();
    }

    @Test
    public void deveriaCadastrarLeilao() {
        String hoje = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String nome = "Leilao do dia " + hoje;
        String valor = "500.00";

        this.leiloesPage = cadastroLeilaoPage.cadastrarLeilao(nome, valor, hoje);
        assertTrue(leiloesPage.isLeilaoCadastrado(nome, valor, hoje));
    }

    @Test
    public void deveriaValidarCadastroDeLeilao() {
        this.leiloesPage = cadastroLeilaoPage.cadastrarLeilao("", "", "");

        assertFalse(this.cadastroLeilaoPage.isPaginaAtual());
        assertTrue(this.leiloesPage.isPaginaAtual());
        assertTrue(this.cadastroLeilaoPage.isMensagensDeValidacaoVisiveis());

    }

}
