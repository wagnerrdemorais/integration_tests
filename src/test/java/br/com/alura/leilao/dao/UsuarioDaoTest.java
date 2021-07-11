package br.com.alura.leilao.dao;

import br.com.alura.leilao.model.Usuario;
import br.com.alura.leilao.util.JPAUtil;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

public class UsuarioDaoTest {

    private UsuarioDao dao;
    private EntityManager em;

    @BeforeEach
    public void beforeEach() {
        this.em = JPAUtil.getEntityManager();
        this.dao = new UsuarioDao(em);
        em.getTransaction().begin();
    }

    @AfterEach
    public void afterEach() {
        em.getTransaction().rollback();
    }

    @Test
    public void deveriaEncontrarUsuarioCadastrado() {
        Usuario usuario = criarUsuario();
        Usuario usuarioEncontrado = this.dao.buscarPorUsername(usuario.getNome());
        Assert.assertNotNull(usuarioEncontrado);
    }

    @Test
    public void naoDeveriaEncontrarUsuarioCadastrado() {
        criarUsuario();
        Assert.assertThrows(NoResultException.class,
                () -> this.dao.buscarPorUsername("outro nome"));
    }

    @Test
    public void deveriaRemoverUmUsuario() {
        Usuario usuario = criarUsuario();
        dao.deletar(usuario);
        Assert.assertThrows(NoResultException.class,
                () -> this.dao.buscarPorUsername(usuario.getNome()));
    }

    public Usuario criarUsuario() {
        Usuario usuario = new Usuario("fulano", "fulano@email.com", "1234");
        em.persist(usuario);
        return usuario;
    }

}