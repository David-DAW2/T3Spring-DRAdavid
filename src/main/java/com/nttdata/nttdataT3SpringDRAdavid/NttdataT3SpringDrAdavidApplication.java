package com.nttdata.nttdataT3SpringDRAdavid;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.nttdata.nttdataT3SpringDRAdavid.persistence.Customer;
import com.nttdata.nttdataT3SpringDRAdavid.services.ManagementCustomerI;

@SpringBootApplication
public class NttdataT3SpringDrAdavidApplication implements CommandLineRunner{
	private static final Logger LOG =LoggerFactory.getLogger(NttdataT3SpringDrAdavidApplication.class);


	/**
	 * Inyección de servicios clientes
	 */
	@Autowired
	ManagementCustomerI gestionClientes;

	public static void main(String[] args) {
		LOG.info("------------------------------INICIO DE APLICACIÓN--------------------------");

		SpringApplication.run(NttdataT3SpringDrAdavidApplication.class, args);
	}
	


	@Override
	public void run(String... args) throws Exception {

		/**
		 * Instanciación de clientes a insertar
		 */
		Customer davi=new Customer();
		davi.setCustomerName("David");
		davi.setFirstSurname("Rodriguez");
		davi.setSecondSurname("Aguilar");
		davi.setFechaNac("29/07/1995");
		davi.setDNI(38473);
		
		Customer julian = new Customer();
		julian.setCustomerName("julian");
		julian.setFirstSurname("marquez");
		julian.setSecondSurname("ossorio");
		julian.setDNI(90752);
		
		LOG.info("---------------------INSERCIÓN DE CLIENTES--------------------------\n");

		

		/**
		 * Inserción de clientes
		 */
		gestionClientes.insertarCliente(davi);
		gestionClientes.insertarCliente(julian);

		LOG.info("---------------------BUSCANDO CLIENTE--------------------------\n");
		Customer clienteEncontrado=gestionClientes.obtenerPorNombreYApellido("David", "Rodriguez", "Aguilar");
		//System.out.println(clienteEncontrado.getDNI()+" "+clienteEncontrado.getCustomerName());
		
		
		/**
		 * Llamada al metodo buscar clientes con un tiempo de permanencia pasado por parámetro en un contrato 
		 */
		List<Customer> listaCusByPermanency=gestionClientes.obtenerTodosLosClientes();
		
		LOG.info("---------------------MOSTRANDO TODOS LOS CLIENTES--------------------------\n");

		/**
		 * Iteración por clientes encontrados
		 */
		for (Customer customer : listaCusByPermanency) {
			System.out.println(customer.getcustomerId()+" "+customer.getCustomerName());
		}
	
		gestionClientes.BorrarCliente(julian);
	}

}
