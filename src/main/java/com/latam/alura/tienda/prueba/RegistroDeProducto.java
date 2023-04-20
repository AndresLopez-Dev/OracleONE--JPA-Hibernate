package com.latam.alura.tienda.prueba;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import com.latam.alura.dao.CategoriaDAO;
import com.latam.alura.dao.ProductoDAO;
import com.latam.alura.tienda.modelo.Categoria;
import com.latam.alura.tienda.modelo.Producto;
import com.latam.alura.utils.JPAUtils;



public class RegistroDeProducto {

	public static void main(String[] args) {
//		registrarProducto();
//		EntityManager em = JPAUtils.getEntityManager();
//		
//		ProductoDAO productoDao = new ProductoDAO(em);
//		Producto producto = productoDao.consultaPorId(1l);
//		
//		System.out.println(producto.getNombre());
//		
////		List<Producto> productos = productoDao.consultaPorNombreDeCategoria("CELULARES");
//		BigDecimal precio = productoDao.consultarPrecioPorNombreDeProducto("Samsung");
//		System.out.println(precio);
//		productos.forEach(prod->System.out.println(prod.getDescripcion()));
	}

	private static void registrarProducto() {
		Categoria celulares = new Categoria("CELULARES");
		Producto celular = new Producto("Samsung","Telefono usado",new BigDecimal(1000),celulares);
		
		EntityManager em = JPAUtils.getEntityManager();
		
		ProductoDAO productoDao = new ProductoDAO(em);
		CategoriaDAO categoriaDao = new CategoriaDAO(em);
		
		em.getTransaction().begin();
		
		categoriaDao.guardar(celulares);
		productoDao.guardar(celular);
		
//     --------------------------------------
		em.getTransaction().commit();
		em.close();
		
//		-------------------------------------
//		em.flush();
//		
//		em.clear();
//		
//		celulares = em.merge(celulares);
//		celulares.setNombre("SOFTWARE");
//		em.flush();
//		em.clear();
//		
//		celulares = em.merge(celulares);
//		em.remove(celulares);
//		em.flush();
	}

}
