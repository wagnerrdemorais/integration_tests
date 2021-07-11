package br.com.alura.leilao.dao;

import br.com.alura.leilao.model.Usuario;
import br.com.alura.leilao.util.JPAUtil;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

public class UsuarioDaoTest {

    private UsuarioDao dao;

    @Test
    public void deveriaEncontrarUsuarioCadastrado() {
        EntityManager em = JPAUtil.getEntityManager();
        this.dao = new UsuarioDao(em);

        Usuario usuario = new Usuario("fulano", "fulano@email.com","1234");
        em.getTransaction().begin();
        em.persist(usuario);
        em.getTransaction().commit();

        Usuario usuarioEncontrado = this.dao.buscarPorUsername(usuario.getNome());
        Assert.assertNotNull(usuarioEncontrado);
    }

    @Test
    public void naoDeveriaEncontrarUsuarioCadastrado() {
        EntityManager em = JPAUtil.getEntityManager();
        this.dao = new UsuarioDao(em);

        Usuario usuario = new Usuario("fulano", "fulano@email.com","1234");
        em.getTransaction().begin();
        em.persist(usuario);
        em.getTransaction().commit();

        Assert.assertThrows(NoResultException.class,
                () -> this.dao.buscarPorUsername("outro nome"));
    }

}