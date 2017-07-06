package com.oltp.bod.middleware.util;

import java.io.*;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.log4j.Logger;


/***********************************************************************************************************
 * OLTP Voice System
 * Middleware de Comunicacion
 * Desarrollado por:
 * 					Jose Ferreira
 * Fecha de Creación: 14/12/2010
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
 * Title: Util
 * </p>
 * <p>
 * Description: Clase Utilitaria
 * </p>
 * Copyright: Copyright (c) 2013<br>
 * Company: OLTP Voice System<br>
 * 
 * @author Miguel Uzcategui
 * @version 1.0
 * @since JDK1.6
 ***********************************************************************************************************/

public class Util {

	private static final Logger LOG = Logger.getLogger(Util.class);

	public static final String PRODUCTOS = "com.oltp.bod.middleware.bussiness.productos";
	public static final String REPORTE = "com.oltp.bod.middleware.reporte.reporte";

	/**
	 * Devuelve el valor de la clave en el properties especificado
	 * 
	 * @param String
	 *            properties - el archivo properties que contiene la clave
	 * @param String
	 *            clave - clave dentro del properties
	 * @return String - el valor especificado en la clave
	 * @throws IOException
	 * 
	 */
	public static String valorVariableProperties(String properties, String clave) {

		String valor = "";

		try {
			if (clave != null && !clave.equals("")) {
				ResourceBundle recurso = ResourceBundle.getBundle(properties);
				valor = recurso.getString(clave);

				if (valor == null)
					valor = "";
			}
		} catch (Exception e) {
			LOG.error("Se ha producido un error al leer del properties errores"
					+ " la clave: " + validaNulo(clave) + ", detalle: "
					+ e.getMessage());
		}
		return valor.trim();
	}

	/**
	 * Valida que la cadena sea distinta de null. Devuelve un String vacio en el
	 * caso que sea null, y el mismo valor en caso contrario
	 * 
	 * @param String
	 *            nombre - la cadena a validar
	 * @return String - la cadena vacia en caso de que sea nula y la original en
	 *         caso contrario
	 * 
	 */
	public static String validaNulo(String nombre) {
		if ((null == nombre) || ("null").equalsIgnoreCase(nombre.trim()))
			return "";
		else
			return nombre.trim();

	}

	/***************************************************************************
	 * Obtiene la fecha en el formato anno, mes y dia
	 * 
	 * @return String - fecha formateada
	 **************************************************************************/
	public static String obtenerFechaFormatoYYYYMMDD() {

		String resultado = "";
		Calendar calendario = Calendar.getInstance();

		int dia = calendario.get(Calendar.DATE);
		int mes = calendario.get(Calendar.MONTH) + 1;

		resultado = Integer.toString(calendario.get(Calendar.YEAR));
		resultado += (mes < 10 ? "0" + Integer.toString(mes) : Integer
				.toString(mes));
		resultado += (dia < 10 ? "0" + Integer.toString(dia) : Integer
				.toString(dia));
		return resultado;
	}

	public static String formatearMonto(String monto) {
		return monto.substring(0, monto.length() - 2) + "."
				+ monto.substring(monto.length() - 2);
	}

	/***************************************************************************
	 * Rellena con el caracter dado la cadena en la posicion que se indique
	 * hasta alcanzar la longitud dada
	 * 
	 * @param String
	 *            cadena - cadena original
	 * @param char caracterRelleno - caracter de relleno
	 * @param int longitud - la longitud total de la cadena
	 * @param int lado - 0 izquierda y 1 derecha
	 * @return String - la cadena formateada
	 **************************************************************************/
	public static String fill(String cadena, char caracterRelleno,
			int longitud, int lado) {

		String salida = cadena;

		// Si tiene la misma longitud la devuelve
		if (salida.length() == longitud)
			return salida;

		// Si es más larga la trunca
		if (salida.length() > longitud)
			return salida.substring(0, longitud);

		// Si es menor, entonces modificamos
		if (salida.length() < longitud) {
			if (lado == 1) {
				// Rellenar por la derecha
				for (int k = cadena.length(); k < longitud; k++) {
					salida = salida + caracterRelleno;
				}
			} else if (lado == 0) {
				// Rellenar por la izquierda
				for (int k = cadena.length(); k < longitud; k++) {
					salida = caracterRelleno + salida;
				}
			} else {
				return "Lado pasado a la función fill es incorrecto";
			}
			return salida;
		}
		return cadena;
	}

	public static boolean esNumerico(String campo) {
		try {
			new BigDecimal(campo);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static byte[] obtenerCampoArreglo(byte[] arreglo, int inicio,
			int largo) {
		byte[] respuesta = new byte[largo];

		for (int i = inicio; i < (inicio + largo); i++) {
			respuesta[i - inicio] = arreglo[i];
		}

		return respuesta;
	}

	public static void printBytes(byte[] array, String name) {
		for (int k = 0; k < array.length; k++) {
			System.out.println(name + "[" + k + "] = " + "0x"
					+ UnicodeFormatter.byteToHex(array[k]));
		}
	}

	static class UnicodeFormatter {

		static public String byteToHex(byte b) {
			// Returns hex String representation of byte b
			char hexDigit[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8',
					'9', 'a', 'b', 'c', 'd', 'e', 'f' };
			char[] array = { hexDigit[(b >> 4) & 0x0f], hexDigit[b & 0x0f] };
			return new String(array);
		}

		static public String charToHex(char c) {
			// Returns hex String representation of char c
			byte hi = (byte) (c >>> 8);
			byte lo = (byte) (c & 0xff);
			return byteToHex(hi) + byteToHex(lo);
		}
	}

	
	
	/***************************************************************************
	 * Obtiene el objet date dada la fecha en el formato yyyyMMddHH:mm:ss
	 * 
	 * @param String
	 *            strFecha - fecha
	 * @throws ParseException
	 * 
	 * @return Date - objeto date con la fecha
	 **************************************************************************/

	public static Date obtenerDate(String strFecha) throws ParseException {
		SimpleDateFormat formatodelTexto = new SimpleDateFormat("dd/MM/yyyy");

		Date fecha = null;
		try {
			fecha = formatodelTexto.parse(strFecha);
		} catch (java.text.ParseException ex) {
			throw ex;
		}
		return fecha;

	}

	/***************************************************************************
	 * Obtiene el objet date dada la fecha en el formato yyyyMMddHH:mm:ss
	 * 
	 * @param String
	 *            strFecha - fecha
	 * @throws ParseException
	 * 
	 * @return Date - objeto date con la fecha
	 **************************************************************************/

	public static Date obtenerDate2(String strFecha) throws ParseException {
		SimpleDateFormat formatodelTexto = new SimpleDateFormat("ddMMyy");

		Date fecha = null;
		try {
			fecha = formatodelTexto.parse(strFecha);
		} catch (java.text.ParseException ex) {
			throw ex;
		}
		return fecha;

	}

	/***************************************************************************
	 * Obtiene el objet date dada la fecha en el formato yyyyMMddHH:mm:ss
	 * 
	 * @param String
	 *            strFecha - fecha
	 * @throws ParseException
	 * 
	 * @return Date - objeto date con la fecha
	 **************************************************************************/

	public static Date obtenerDate3(String strFecha) throws ParseException {
		SimpleDateFormat formatodelTexto = new SimpleDateFormat("MM/dd/yyyy");

		Date fecha = null;
		try {
			fecha = formatodelTexto.parse(strFecha);
		} catch (java.text.ParseException ex) {
			throw ex;
		}
		return fecha;

	}

	/***************************************************************************
	 * Obtiene la fecha en el formato dia, mes, anno, hora, minutos y segundos
	 * separados por los caracteres / y : respectivamente
	 * 
	 * @param Date
	 *            fecha - fecha a formatear
	 * 
	 * @return String - fecha formateada
	 **************************************************************************/
	public static String obtenerFechaFormatoDDMMYYYY(Date fecha) {
		String separador = "/";
		String resultado = "";
		Calendar calendario = Calendar.getInstance();
		calendario.setTime(fecha);

		int dia = calendario.get(Calendar.DATE);
		int mes = calendario.get(Calendar.MONTH) + 1;
		resultado = (dia < 10 ? "0" + Integer.toString(dia) : Integer
				.toString(dia));
		resultado += separador;
		resultado += (mes < 10 ? "0" + Integer.toString(mes) : Integer
				.toString(mes));
		resultado += separador;
		resultado += Integer.toString(calendario.get(Calendar.YEAR));

		return resultado;
	}

	/***************************************************************************
	 * Obtiene la fecha en el formato dia, mes, anno, hora, minutos y segundos
	 * separados por los caracteres / y : respectivamente
	 * 
	 * @param Date
	 *            fecha - fecha a formatear
	 * 
	 * @return String - fecha formateada
	 **************************************************************************/
	public static String obtenerFechaFormatoYYYYMMDD(Date fecha) {
		String resultado = "";
		Calendar calendario = Calendar.getInstance();
		calendario.setTime(fecha);

		int dia = calendario.get(Calendar.DATE);
		int mes = calendario.get(Calendar.MONTH) + 1;

		resultado = Integer.toString(calendario.get(Calendar.YEAR));

		resultado += (mes < 10 ? "0" + Integer.toString(mes) : Integer
				.toString(mes));

		resultado += (dia < 10 ? "0" + Integer.toString(dia) : Integer
				.toString(dia));

		return resultado;
	}

	

	/***************************************************************************
	 * Obtiene la fecha en el formato dia, mes, anno, hora, minutos y segundos
	 * separados por los caracteres / y : respectivamente
	 * 
	 * @param Date
	 *            fecha - fecha a formatear
	 * 
	 * @return String - fecha formateada
	 **************************************************************************/
	public static String obtenerFechaFormatoDDMM(Date fecha) {
		String separador = "/";
		String resultado = "";
		Calendar calendario = Calendar.getInstance();
		calendario.setTime(fecha);

		int dia = calendario.get(Calendar.DATE);
		int mes = calendario.get(Calendar.MONTH) + 1;
		resultado = (dia < 10 ? "0" + Integer.toString(dia) : Integer
				.toString(dia));
		resultado += separador;
		resultado += (mes < 10 ? "0" + Integer.toString(mes) : Integer
				.toString(mes));

		return resultado;
	}

	private static Date getPrimerDiaDelMes(Calendar cal) {
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
				cal.getActualMinimum(Calendar.DAY_OF_MONTH),
				cal.getMinimum(Calendar.HOUR_OF_DAY),
				cal.getMinimum(Calendar.MINUTE),
				cal.getMinimum(Calendar.SECOND));
		return cal.getTime();
	}

	private static Date getUltimoDiaDelMes(Calendar cal) {

		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
				cal.getActualMaximum(Calendar.DAY_OF_MONTH),
				cal.getMaximum(Calendar.HOUR_OF_DAY),
				cal.getMaximum(Calendar.MINUTE),
				cal.getMaximum(Calendar.SECOND));
		return cal.getTime();
	}

	public static String[] obtenerDiasLimitesMes(String mes) {

		String[] resultado = { "", "" };

		Calendar hoy = Calendar.getInstance();
		Calendar cal = Calendar.getInstance();
		cal.set(hoy.get(Calendar.YEAR), Integer.valueOf(mes) - 1, 15,
				cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE),
				cal.get(Calendar.SECOND));

		// cal.set(Calendar.MONTH, Integer.valueOf(mes)-1);

		if (cal.compareTo(hoy) > 0
				|| cal.get(Calendar.MONTH) == hoy.get(Calendar.MONTH))
			cal.set(Calendar.YEAR, cal.get(Calendar.YEAR) - 1);

		resultado[0] = obtenerFechaFormatoDDMMYYYY(getPrimerDiaDelMes(cal));
		resultado[1] = obtenerFechaFormatoDDMMYYYY(getUltimoDiaDelMes(cal));

		return resultado;
	}

	public static ArrayList<String> convertirArreglo(String[] arreglo) {

		ArrayList<String> resultado = new ArrayList<String>();

		for (int i = 0; i < arreglo.length; i++) {
			resultado.add(arreglo[i]);
		}
		return resultado;
	}

	public static String enmascararCadena(String cadena) {
		String resultado = "";
		if (cadena != null && cadena.length() > 0) {
			if (cadena.length() > 4) {
				resultado = cadena.substring(cadena.length() - 5);
			}
			resultado = fill(resultado, '*', cadena.length(), 0);
		}
		return resultado;
	}

	public static void main(String args[]) {

		try {
			/*
			 * String respuesta = "100|"; String[] b =
			 * "100:160:17,07,08,09".split(":")[2].split(","); for (int i = 0; i
			 * < b.length; i++) { respuesta = respuesta + b[i] + "|"; }
			 * System.out.println(respuesta);
			 * System.out.println(esNumerico("-+4.64"));
			 * 
			 * System.out.println(respuesta.substring(respuesta.length() - 3));
			 * 
			 * String a = "0|/export/BFC/FaxDatos/M000045106.TXT";
			 * System.out.println(a.substring(2));
			 * 
			 * ScripletReporte sr = new ScripletReporte();
			 * 
			 * System.out.println(sr.setMontoFormateado("-20000000.00", 2));
			 * System.out.println(sr.setFechaFormateada("2/1/11")); String
			 * cuenta = "01510091553000045106"; cuenta = cuenta.substring(0, 4)
			 * + "************" + cuenta.substring(16);
			 * System.out.println(cuenta); String clave = "2323"; for (int i =
			 * 0; i < 12; i++) { String[] ab =
			 * obtenerDiasLimitesMes(String.valueOf(i+1));
			 * System.out.println(ab[0] + "-" + ab[1]); }
			 * 
			 * 
			 * System.out.println("El valor de la clave:" + clave +
			 * " encriptada es:" +encriptarClave(clave));
			 * 
			 * String aa= "003:10:BD:BFCFXP:0:55|I040081042.TXT";
			 * 
			 * String bb = "011:30:BD:BFCFXP:0:0|M240081042.TXT";
			 * 
			 * System.out.println(aa.split(":")[5].substring(2));
			 * System.out.println(bb.split(":")[5].substring(2));
			 * 
			 * System.out.println(aa.split(":")[5].split("\\|")[1]);
			 * System.out.println(bb.split(":")[5].split("\\|")[1]);
			 */

			// WsEncriptarSoapProxy servicioDLL = new WsEncriptarSoapProxy();
			// System.out.println(obtenerFechaFormatoDDMM(obtenerDate("1/10/20")));

			/*
			 * BigInteger contador = new BigInteger( "99999999999999999123");
			 * String origen = Util.fill(contador.toString(), '0', 10, 0);
			 * origen = origen.substring(origen.length()-3);
			 * System.out.println(origen);
			 */
			// String endpoint =
			// "http://10.60.102.178/WSFCTripleDes/WsEncriptar.asmx";
			// WsEncriptarSoapProxy servicioDLL = new
			// WsEncriptarSoapProxy(endpoint);
			// System.out.println(servicioDLL.encriptar("2323"));

		

			// B2D0EC89FBA2BEBA

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}