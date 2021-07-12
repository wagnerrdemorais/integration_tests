package br.com.alura.leilao;

import br.com.alura.leilao.login.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {

    private LoginPage loginPage;

    @BeforeEach
    public void beforeEach() {
        this.loginPage = new LoginPage();
    }

    @AfterEach
    public void afterEach() {
        this.loginPage.quit();
    }

    @Test
    public void deveriaEfetuarLoginComDadosValidos() {
        loginPage.preencheFormularioLogin("fulano", "pass");
        loginPage.efetuaLogin();

        assertFalse(loginPage.isLoginPage());
        assertEquals("fulano", loginPage.getUsuarioLogado());
    }

    @Test
    public void naoDeveriaLogarComDadosInvalidos() {
        loginPage.preencheFormularioLogin("blabla", "eita");
        loginPage.efetuaLogin();

        assertTrue(loginPage.isLoginErrorPage());
        assertNull(loginPage.getUsuarioLogado());
        assertTrue(loginPage.containsText("Usuário e senha inválidos."));
    }

    @Test
    public void naoDeveriaAcessarAreaRestritaSemEstarLogado() {
        loginPage.navegaParaPaginaDeLances();

        assertTrue(loginPage.isLoginPage());
        assertFalse(loginPage.containsText("Dados do Leilão"));
    }


}
