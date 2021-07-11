package br.com.alura.leilao.dao;

import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.model.Usuario;
import br.com.alura.leilao.util.JPAUtil;
import br.com.alura.leilao.util.UsuarioBuilder;
import br.com.alura.leilao.util.builder.LeilaoBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDate;

public class LeilaoDaoTest {

    private LeilaoDao dao;
    private EntityManager em;

    @BeforeEach
    void beforeEach() {
        this.em = JPAUtil.getEntityManager();
        this.dao = new LeilaoDao(em);
        em.getTransaction().begin();
    }

    @AfterEach
    void afterEach() {
        em.getTransaction().rollback();
    }

    @Test
    void deveriaCadastrarUmLeilao() {
        Usuario usuario = new UsuarioBuilder()
                .comNome("Fulano")
                .comEmail("fulano@email.com")
                .comSenha("1234").criar();

        em.persist(usuario);

        Leilao leilao = new LeilaoBuilder()
                .comNome("Mochila")
                .comValorInicial("500")
                .comData(LocalDate.now())
                .comUsuario(usuario)
                .criar();

        leilao = dao.salvar(leilao);

        Leilao salvo = dao.buscarPorId(leilao.getId());
        Assertions.assertNotNull(salvo);
    }

    @Test
    void deveriaAtualizarUmLeilao() {
        Usuario usuario = criarUsuario();
        Leilao leilao = new Leilao("Mochila", new BigDecimal("70"), LocalDate.now(), usuario);
        leilao = dao.salvar(leilao);

        leilao.setNome("OutroNome");
        leilao.setValorInicial(new BigDecimal("400"));

        Leilao salvo = dao.buscarPorId(leilao.getId());
        Assertions.assertEquals("OutroNome", salvo.getNome());
    }

    Usuario criarUsuario() {
        Usuario usuario = new Usuario("fulano", "fulano@email.com", "1234");
        em.persist(usuario);
        return usuario;
    }

}