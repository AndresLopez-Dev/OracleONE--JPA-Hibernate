package com.latam.alura.tienda.prueba;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import com.latam.alura.dao.CategoriaDAO;
import com.latam.alura.dao.ClienteDAO;
import com.latam.alura.dao.PedidoDAO;
import com.latam.alura.dao.ProductoDAO;
import com.latam.alura.tienda.modelo.Categoria;
import com.latam.alura.tienda.modelo.Cliente;
import com.latam.alura.tienda.modelo.ItemsPedido;
import com.latam.alura.tienda.modelo.Pedido;
import com.latam.alura.tienda.modelo.Producto;
import com.latam.alura.utils.JPAUtils;
import com.latam.alura.vo.RelatorioDeVenta;



public class RegistroDePedido {

	public static void main(String[] args) {
		registrarProducto();
		
		EntityManager em = JPAUtils.getEntityManager();
		
		ProductoDAO productoDao= new ProductoDAO(em);
		Producto producto = productoDao.consultaPorId(1l);
		
		ClienteDAO clienteDao = new ClienteDAO(em);
		PedidoDAO pedidoDao = new PedidoDAO(em);
		
		Cliente cliente = new Cliente("Andres","5ad56s65a");
		Pedido pedido = new Pedido(cliente);
		pedido.agregarItems(new ItemsPedido(5,producto,pedido));
		
		em.getTransaction().begin();
		
		clienteDao.guardar(cliente);
		pedidoDao.guardar(pedido);
		em.getTransaction().commit();
		
		BigDecimal valorTotal = pedidoDao.valorTotalVendido();
		System.out.println("Valor total: "+valorTotal);
		
//		List<Object[]> relatorio = pedidoDao.relatorioDeVentas();
//		
//		for(Object[] objec:relatorio) {
//			System.out.print(objec[0]);
//			System.out.print(objec[1]);
//			System.out.print(objec[2]);
//			System.out.println("");
//		}
		
		List<RelatorioDeVenta> relatorio = pedidoDao.relatorioDeVentasVO();
		
		relatorio.forEach(System.out::println);
		
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
