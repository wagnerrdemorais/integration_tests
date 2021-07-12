package br.com.alura.leilao.leiloes;

import org.openqa.selenium.WebDriver;

public class CadastroLeilaosPage {

    private final WebDriver browser;

    public CadastroLeilaosPage(WebDriver browser) {
        this.browser = browser;
    }

    public void quit() {
        this.browser.quit();
    }

}
