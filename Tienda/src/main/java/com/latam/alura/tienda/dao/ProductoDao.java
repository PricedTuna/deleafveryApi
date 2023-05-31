package com.latam.alura.tienda.dao;

import com.latam.alura.tienda.modelo.Producto;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class ProductoDao {
    private EntityManager em;

    public ProductoDao(EntityManager em) {
        this.em = em;
    }

    public void guardar(Producto producto) {
        this.em.persist(producto);
    }

    public Producto consultaPorId(Long id){
        return em.find(Producto.class, id);
    }

    public List<Producto> consultarTodos() {
        String jqpl = "SELECT P FROM Producto AS P";
        return em.createQuery(jqpl, Producto.class).getResultList();
    }

    public List<Producto> consultaPorNombre(String nombre) {
        String jqpl = "SELECT P FROM Producto AS P WHERE P.nombre=:nombre";
        return em.createQuery(jqpl).setParameter("nombre", nombre).getResultList();
    }

    public List<Producto> consultaPorCategoria(String categoria) {
        String jqpl = "SELECT P FROM Producto AS P WHERE P.categoria.nombre=:categoria";
        return em.createQuery(jqpl).setParameter("categoria", categoria).getResultList();
    }

    public BigDecimal consultaPrecioPorNombre(String nombre) {
        String jpql = "SELECT P.precio FROM Producto AS P WHERE P.nombre=:nombre";
        return em.createQuery(jpql, BigDecimal.class).setParameter("nombre", nombre).getSingleResult();
    }
}
