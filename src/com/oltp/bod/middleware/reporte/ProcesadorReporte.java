package com.oltp.bod.middleware.reporte;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


import com.oltp.bod.middleware.util.Util;
import org.apache.log4j.Logger;

//import com.oltp.bfc.middleware.util.Util;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRCsvDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.view.JasperViewer;

public class ProcesadorReporte {

	private static final Logger LOG = Logger.getLogger(ProcesadorReporte.class);

	private static JRCsvDataSource getDataSourceEstadoCuenta(File archivo)
			throws JRException, FileNotFoundException {
		String[] columnNames = new String[] { "Fecha de Creacion",
				"Fecha Efectiva", "DB / CR", "Monto", "Numero Documento",
				"Consecutivo", "Descripcion", "Saldo" };
		JRCsvDataSource ds = new JRCsvDataSource(
				archivo);
		//JRCsvDataSource ds = new JRCsvDataSource(JRLoader.getLocationInputStream(archivoOriginal));
		ds.setFieldDelimiter("^".charAt(0));
		ds.setRecordDelimiter("\r\n");
		ds.setColumnNames(columnNames);
		return ds;
	}
	
	private static JRCsvDataSource getDataSourceUltimosMovimientos(File archivo)
			throws JRException, FileNotFoundException {
		String[] columnNames = new String[] { "Fecha de Creacion",
				"Fecha Efectiva", "DB / CR", "Monto", "Numero Documento",
				"Descripcion", "Saldo" };
		JRCsvDataSource ds = new JRCsvDataSource(
				archivo);
		//JRCsvDataSource ds = new JRCsvDataSource(JRLoader.getLocationInputStream(archivoOriginal));
		ds.setFieldDelimiter("^".charAt(0));
		ds.setRecordDelimiter("\r\n");
		ds.setColumnNames(columnNames);
		return ds;
	}

	/***************************************************************************
	 * Metodo para generar el reporte
	 * 
	 * @throws Exception
	 **************************************************************************/
	public void generarReporte(ReporteDTO reporte) throws Exception {

		//JasperPrint jasperPrint = null;
		//JRExporter exporter = null;
		Map<String, Object> parametros = null;
        //String nombreArchivoSalida;
		//String archivoJasper;

		//File detalles = null;
		
		//JRCsvDataSource datasource = null;
		
		try {
			//detalles = new File(reporte.getNombreArchivoDetalleOriginal());

			//if (detalles.exists()) {

			//	archivoJasper = new File(reporte.getTipoReporte())
				//		.getAbsolutePath();

			//	ScripletReporte sr = new ScripletReporte();

				////Util.REPORTE, "ruta.local.temporal");
				
				

			//	int inicio = reporte.getNombreArchivoDetalleOriginal()
				//		.toLowerCase().lastIndexOf("/");
			//	int fin = reporte.getNombreArchivoDetalleOriginal()
				//		.toLowerCase().lastIndexOf(".txt");

				//nombreArchivoSalida += reporte
					//	.getNombreArchivoDetalleOriginal().substring(
						//		inicio + 1)
						//+ ".doc";

				//reporte.setNombreArchivoDetalleActualizado(nombreArchivoSalida);

				//ProcesadorReporte p = new ProcesadorReporte();
				parametros = new HashMap<String, Object>();
				

				//Date now = new Date();
				//DateFormat df4 = DateFormat.getDateInstance(DateFormat.FULL);
				//String fecha = df4.format(now);
				//System.out.println("Hoy es2:" +fecha);	
				    
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
					
		
				System.out.println("la cedula es: "+reporte.getCedulaCliente());
				
				String holaPDF = "E:\\referenciaBancariaBOD2"+reporte.getCedulaCliente().trim()+".pdf";
				System.out.println("el nombre del reporte es: "+holaPDF);
				String fileJasper = "reportes\\referenciaBancariaClienteNatural.jasper";
				JasperPrint print = JasperFillManager.fillReport(fileJasper,parametros, new JREmptyDataSource());
				JasperViewer.viewReport(print, false);
				 JRPdfExporter
	             exporter = new JRPdfExporter();
					exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,
							holaPDF);
					exporter.setParameter(JRExporterParameter.JASPER_PRINT,
							print);
					exporter.exportReport();
				
				
							//p.generarReporte(reporte);
				
				
				/*	jasperPrint = JasperFillManager.fillReport(archivoJasper,
						parametros);
				exporter = new JRRtfExporter();
				exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,
						nombreArchivoSalida);
				exporter.setParameter(JRExporterParameter.JASPER_PRINT,
						jasperPrint);
				exporter.exportReport();
				LOG.info("Se genero satisfactoriamente el reporte "
						+ nombreArchivoSalida); */
			
		} catch (JRException e) {
			LOG.error("Error en generarReporte()" + this + " ==> "
				+ e.getMessage());
			e.printStackTrace();
			throw e;
		} 
		catch (Exception e) {
			//LOG.error("Error en generarReporte()" + this + " ==> "
				//+ e.getMessage());
			e.printStackTrace();
			throw e;
		} 

	}
	/***************************************************************************
	 * Metodo para obtener la imagen
	 * 
	 * @param byte[] imagenBytes - arreglo de la representacion en bytes de la
	 *        imagen
	 * @return Image - imagen creada
	 **************************************************************************/
	private Image getImagen(byte[] imagenBytes) {
		Image imagen = null;
		try {
			imagen = Toolkit.getDefaultToolkit().createImage(imagenBytes);
			System.setProperty("java2d.font.usePlatformFont", "true");
		} catch (Exception e) {
			LOG.error("Problema creando la imagen: " + e.toString() + ": "
					+ e.getMessage());
		}
		float waitTime = 0;
		while ((imagen.getHeight(null) == -1) || (waitTime < 200)) {
			waitTime += 0.025;
		}
		return imagen;
	}

	/***************************************************************************
	 * Metodo para obtener un arreglo de bytes en base a un archivo
	 * 
	 * @param File
	 *            archivo - archivo a obtenerle su representacion en bytes
	 * @return byte[] - arreglo de bytes
	 **************************************************************************/
	private static byte[] toByteArray(File archivo) throws IOException {

		FileInputStream fileInput = new FileInputStream(archivo);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buf = new byte[4000];
		InputStream is = fileInput;
		try {
			for (;;) {
				int dataSize = is.read(buf);
				if (dataSize == -1) {
					break;
				}
				baos.write(buf, 0, dataSize);
			}
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException ex) {
				}
			}
		}
		return baos.toByteArray();
	}

	private static String enmascararCuenta(String cuenta) {
		return  "0151************" + cuenta.substring(6);
	}

	/***************************************************************************
	 * Metodo principal de la clase
	 * 
	 * @param String
	 *            [] args - arreglo de argumentos de tipo string
	 **************************************************************************/
	public static void main(String[] args) {

		
		ProcesadorReporte p = new ProcesadorReporte();
		try {
			
			//Map<String, Object> parametros = null;
			ReporteDTO reporte = new ReporteDTO();
			
	
			
			//parametros = new HashMap<String, Object>();
			
			Date now = new Date();
			DateFormat df4 = DateFormat.getDateInstance(DateFormat.FULL);
			String fecha = df4.format(now);
			System.out.println("Hoy es:" +fecha);
			reporte.setFecha(fecha);
			reporte.setNombreCliente("GONZALEZ CAMPOS RAFAEL ANGEL");
			reporte.setCedulaCliente("11288449");
			reporte.setFechaCliente("01/01/2015");
			reporte.setTipoCuenta("CUENTA CORRIENTE CLASICA");
			reporte.setNumeroCuenta("0123456789012345678");
			reporte.setParametroCuenta("3 CIFRAS ALTAS");
			
			/*parametros.put("fecha", 
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
					reporte.getParametroCuenta());*/
			
			//String holaPDF = "E:\\referenciaBancariaBOD2"+reporte.getCedulaCliente().trim()+".pdf";
			//String fileJasper = "reportes\\referenciaBancariaClienteNatural.jasper";
			//JasperPrint print = JasperFillManager.fillReport(fileJasper,parametros, new JREmptyDataSource());
			
			
			
		/*	 JRPdfExporter
             exporter = new JRPdfExporter();
				exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,
						holaPDF);
				exporter.setParameter(JRExporterParameter.JASPER_PRINT,
						print);
				exporter.exportReport();*/
			
			
						p.generarReporte(reporte); 

			
		} catch (Exception e) {
			LOG.error("Se ha producido un error al generar el reporte ==> "
					+ e.getMessage());
		}

	}

}
