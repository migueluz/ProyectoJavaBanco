package com.oltp.bod.middleware.bussiness;

import java.io.PrintStream;
import java.sql.Date;
import java.text.DateFormat;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;

import org.apache.log4j.Logger;

import com.oltp.bod.middleware.reporte.ProcesadorReporte;
import com.oltp.bod.middleware.reporte.ReporteDTO;



/***********************************************************************************************************
 * OLTP Voice System
 * Middleware de Comunicacion
 * Desarrollado por:
 * 					Miguel Uzcategui
 * Fecha de Creación: 14/12/2013
 ***********************************************************************************************************/

/***********************************************************************************************************
 * BITÁCORA DE ACTUALIZACIONES
 * Desarrollador Fecha Inicio Fecha Fin             
 ***********************************************************************************************************/

/***********************************************************************************************************
 * <p>
 * Title: NegocioIVR
 * </p>
 * <p>
 * Description: Clase de Bussiness Service que contiene la logica asociada al
 * procesamiento de la rafaga enviada por el IVR
 * </p>
 * Copyright: Copyright (c) 2013<br>
 * Company: OLTP Voice System<br>
 * 
 * @author Miguel Uzcategui
 * @version 1.0
 * @since JDK1.6
 ***********************************************************************************************************/


public class NegocioIVR {

	private static final Logger LOG = Logger.getLogger(NegocioIVR.class);

	public static void procesarLectura(String[] arreglo_lectura,
			PrintStream escritor) {
		try {
			String codigoRespuesta = "";

			if (arreglo_lectura.length == 0) {
				codigoRespuesta = "000";
				LOG.debug("Se retornara al ivr " + codigoRespuesta);
				escritor.println(codigoRespuesta);
			}
			
			else if(arreglo_lectura[0].equals("RB")){
				
				ProcesadorReporte p = new ProcesadorReporte();
				Map<String, Object> parametros = null;
		        String nombreArchivoSalida;
				
				try {
					   ReporteDTO reporte = new ReporteDTO();
						//ProcesadorReporte p = new ProcesadorReporte();
						parametros = new HashMap<String, Object>();
						

						Date now = new Date(0);
						DateFormat df4 = DateFormat.getDateInstance(DateFormat.FULL);
						String fecha = df4.format(now);
						System.out.println("Hoy es:" +fecha);
						    
						parametros.put("fecha", 
								reporte.getFecha());
						parametros.put("nombreCliente", 
								reporte.getNombreCliente());
						parametros.put("cedulaCliente",
								reporte.getCedulaCliente());
						parametros.put("fechaCliente",
								reporte.getFechaCliente());
						parametros.put("tipoCuenta",
								reporte.getTipoCuenta());
						parametros.put("numeroCuenta",
								reporte.getNumeroCuenta());
						parametros.put("parametroCuenta",
								reporte.getParametroCuenta());
							
						String holaPDF = "D:\\referenciaBancariaBOD2"+reporte.getCedulaCliente().trim()+".pdf";
						String fileJasper = "reportes\\referenciaBancariaClienteNatural.jasper";
						JasperPrint print = JasperFillManager.fillReport(fileJasper,parametros, new JREmptyDataSource());
						
						 JRPdfExporter
			             exporter = new JRPdfExporter();
							exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,
									holaPDF);
							exporter.setParameter(JRExporterParameter.JASPER_PRINT,
									print);
							exporter.exportReport();
						
						
									p.generarReporte(reporte);
						
					
				} catch (JRException e) {
					LOG.error("Error en generarReporte()"  
						+ e.getMessage());
					e.printStackTrace();
					throw e;
				} 
				
			
			
			
			
			}
			
			
			else {
				LOG.debug("Se procesara la transaccion de: "
						+ arreglo_lectura[0]);

				if (arreglo_lectura[0].equals("RB")) {
					// ENIVA REFERENCIA POR EMAIL
					if (arreglo_lectura.length == 5) {
						LOG.debug("Se enviara una referencia banacaria :" + arreglo_lectura [3]+
								" del cliente: "
								+ arreglo_lectura[1]);
						NegocioSwitch.procesarRespuesta(arreglo_lectura, escritor);
					} else {
						codigoRespuesta = "BOD|";
						LOG.debug("Se retornara al ivr " + codigoRespuesta);
						escritor.println(codigoRespuesta);
					}
				} 
				
				
				else if (arreglo_lectura[0].equals("MC")) {
					// CONSULTA ESTADO DE CUENTA
					if (arreglo_lectura.length == 6) {
						LOG.debug("Se consultara el estado de cuenta del mes: " +arreglo_lectura[5]+" de la cuenta:" + arreglo_lectura [3]+
								" del cliente: "
								+ arreglo_lectura[1]);
						NegocioSwitch.procesarRespuesta(arreglo_lectura, escritor);
					} else {
						codigoRespuesta = "BOD|";
						LOG.debug("Se retornara al ivr " + codigoRespuesta);
						escritor.println(codigoRespuesta);
					}					
				} 
				
				
				else if (arreglo_lectura[0].equals("MVTDC")) {
					// CONSULTA SALDO DE TARJETA DE CREDITO
					if (arreglo_lectura.length == 5) {
						LOG.debug("Se consultara el saldo de la tarjeta: " +arreglo_lectura[3]+
								" del cliente: "
								+ arreglo_lectura[1]);
						NegocioSwitch.procesarRespuesta(arreglo_lectura, escritor);
					} else {
						codigoRespuesta = "BOD|";
						LOG.debug("Se retornara al ivr " + codigoRespuesta);
						escritor.println(codigoRespuesta);
					}					
				
				} else {
					codigoRespuesta = "BOD|";
					LOG.debug("Se retornara al ivr " + codigoRespuesta);
					escritor.println(codigoRespuesta);
				}
			}

		} catch (Exception e) {
			LOG.error("Error enviando rafaga al switch transaccional, detalle:"
					+ e.getMessage());
		}
	}

}
