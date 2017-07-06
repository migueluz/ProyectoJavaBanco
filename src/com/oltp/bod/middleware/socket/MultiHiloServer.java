package com.oltp.bod.middleware.socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import com.oltp.bod.middleware.bussiness.NegocioIVR;

/***********************************************************************************************************
 * OLTP Voice System
 * Middleware de Comunicacion entre el IVR y Switch Transaccional (Banco Bicentenario)
 * Desarrollado por: Jose Ferreira
 * Fecha de Creación: 16/08/2011
 ***********************************************************************************************************/

/***********************************************************************************************************
 * BITÁCORA DE ACTUALIZACIONES
 * Desarrollador Fecha Inicio Fecha Fin             
 ***********************************************************************************************************/

/***********************************************************************************************************
 * NOTA: Recuerda no eliminar código ya desarrollado, debes comentar la porción de código e incluir la
 * nueva, documentando la fecha de la sustitición.
 ***********************************************************************************************************/

/***********************************************************************************************************
 * <p>
 * Title: MultiHiloServer
 * </p>
 * <p>
 * Description: Clase principal del jar ejecutable la cual inicializa el
 * servidor socket local, el cliente socket de comunicacion con el switch y los
 * clientes que atenderan las peticiones del IVR
 * </p>
 * Copyright: Copyright (c) 2013<br>
 * Company: OLTP Voice System<br>
 * 
 * @author Miguel Uzcategui
 * @version 1.0
 * @since JDK1.6
 ***********************************************************************************************************/
public class MultiHiloServer {

	private static final Logger LOG = Logger.getLogger(MultiHiloServer.class);


	// SERVIDOR SOCKET (MIDDLEWARE LOCAL)
	private static int puertoMiddleware;
	private static ServerSocket servidorMiddleware;

	// CLIENTE SOCKET IVR
	private static Socket clienteIVR;
	private static BufferedReader lector = null;
	private static PrintStream escritor = null;


	private static void inicializar() throws Exception {

		try {
			LOG.debug("Se cargaran las variables de configuracion");
			
			ResourceBundle recurso = ResourceBundle.getBundle("com.oltp.bod.middleware.middleware");
			puertoMiddleware = Integer.parseInt(recurso.getString("middleware.port"));
			
			/*Properties propiedades = new Properties();
			propiedades.load(new FileInputStream(new File(
					"C:\\Simulador_BOD_Middleware_Server_IVR\\conf\\middleware.properties")));
			puertoMiddleware = Integer.parseInt(propiedades
					.getProperty("middleware.port"));
			*/
			LOG.info("Se cargaron exitosamente las variables, puertoMiddleware:" + puertoMiddleware);
		} catch (Exception e) {
			LOG.fatal("No se pudo cargar las variables de configuracion, detalle: "
					+ e.getMessage());
			throw e;
		}
	}
	
	private static void procesar() throws Exception {

			activarMiddleware();

			atenderPeticionesIVR();
			
			servidorMiddleware.close();
			
			LOG.debug("Se reiniciara el procesamiento en el BODIVRMiddlewareServer");
			String[] argumentos = null;
			main(argumentos);
			LOG.debug("BODIVRMiddlewareServer reiniciado");
		
	}


	private static void activarMiddleware() throws Exception {

		try {
			LOG.debug("Se intentara activar el servidor middleware local");
			servidorMiddleware = new ServerSocket(puertoMiddleware);
			LOG.info("Servidor middleware local activo IP: "
					+ servidorMiddleware.getInetAddress().getLocalHost()
							.getHostAddress() + " y puerto:"
					+ servidorMiddleware.getLocalPort());
		} catch (Exception e) {
			LOG.fatal("No se pudo activar el servidor middleware local, detalle: "
					+ e.getMessage());
			throw e;
		}
	}

	private static void atenderPeticionesIVR() {
		String lecturaIVR;
		while (true) {
			
			try {
				LOG.debug("Esperando peticion del IVR");
				clienteIVR = servidorMiddleware.accept();
				// clienteIVR.setSoTimeout(timeoutSwitch);
				LOG.debug("Conexion aceptada del equipo IVR con IP: "
						+ clienteIVR.getInetAddress().getLocalHost()
								.getHostAddress() + " y puerto:"
						+ clienteIVR.getPort());
				
				lector = new BufferedReader(new InputStreamReader(
						clienteIVR.getInputStream()));
				escritor = new PrintStream(clienteIVR.getOutputStream());
				LOG.debug("Esperando leer del canal IVR");
				lecturaIVR = lector.readLine();

				LOG.debug("Se procesara la lectura: "
						+ lecturaIVR);

				String[] arreglo_lectura = lecturaIVR.split("\\|");

				LOG.debug("Se procesara un total de "
						+ arreglo_lectura.length + " parametros");

				NegocioIVR.procesarLectura(arreglo_lectura, escritor);
				
				Thread.sleep(15000);
				
				if (clienteIVR != null
						&& clienteIVR.isConnected()) {
					clienteIVR.shutdownInput();
					clienteIVR.shutdownOutput();
					clienteIVR.close();
				}
				
			} catch (Exception e) {
				LOG.error("Procesando peticion del cliente IVR, detalle: "
						+ e.getMessage());
			}
		}
	}

	public static void main(String args[]) {

		try {

			Runtime.getRuntime().addShutdownHook(new Thread() {
				public void run() {
					LOG.info("Finalizacion del BODIVRMiddlewareServer");
					try {

						if (servidorMiddleware != null
								&& !servidorMiddleware.isClosed())
							servidorMiddleware.close();

					} catch (Exception e) {
						LOG.error("Finalizando la ejecucion del BODIVRMiddlewareServer, detalle:"
								+ e.getMessage());
					}
				}
			});

			LOG.info("Arranque del BODIVRMiddlewareServer");

			inicializar();

			procesar();
			

		} catch (Exception e) {
			LOG.fatal("Se detuvo el BODIVRMiddlewareServer, detalle:"+e.getMessage());
			System.gc();
			System.exit(0);
		}
	}
}