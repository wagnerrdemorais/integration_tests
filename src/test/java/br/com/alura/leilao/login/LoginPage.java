package br.com.alura.leilao.login;

import br.com.alura.leilao.PageObject;
import br.com.alura.leilao.leiloes.LeiloesPage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

public class LoginPage extends PageObject {

    String URL_LOGIN = "http://localhost:8080/login";
    public LoginPage() {
        super(null);
        browser.navigate().to(URL_LOGIN);
    }

    public void quit() {
        this.browser.quit();
    }
    public void preencheFormularioLogin(String username, String password) {
        browser.findElement(By.id("username")).sendKeys(username);
        browser.findElement(By.id("password")).sendKeys(password);
    }

    public LeiloesPage efetuaLogin() {
        browser.findElement(By.id("login-form")).submit();
        return new LeiloesPage(browser);
    }

    public boolean isLoginPage() {
        return browser.getCurrentUrl().equals(URL_LOGIN);
    }

    public String getUsuarioLogado() {
        try {
            return browser.findElement(By.id("usuario-logado")).getText();
        }catch (NoSuchElementException e) {
            return null;
        }
    }

    public void navegaParaPaginaDeLances() {
        browser.navigate().to("http://localhost:8080/leiloes/2");
    }

    public boolean containsText(String text) {
        return browser.getPageSource().contains(text);
    }

    public boolean isLoginErrorPage() {
        return browser.getCurrentUrl().equals(URL_LOGIN+"?error");
    }
}
