package com.oltp.bod.middleware.reporte;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import com.oltp.bod.middleware.util.Util;

import net.sf.jasperreports.engine.JRScriptletException;

public class ScripletReporte extends
		net.sf.jasperreports.engine.JRDefaultScriptlet {

	/*************************************************************************************************************
	 * Obtiene el formateador decimal
	 * 
	 * @param char separadorDecimal - caracter de separacion decimal
	 * @param char separadorMiles - caracter de separacion de miles
	 * @param Integer
	 *            digDecimales - numero de digitos decimales
	 * @param boolean agruparMiles - se agrupa miles o no
	 * @return DecimalFormat - formateador
	 *************************************************************************************************************/
	public static DecimalFormat crearFormateadorDecimal(char separadorDecimal,
			char separadorMiles, Integer digDecimales, boolean agruparMiles) {

		DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
		simbolos.setDecimalSeparator(separadorDecimal);
		simbolos.setGroupingSeparator(separadorMiles);
		DecimalFormat formateador = null;

		if (digDecimales.equals(1)) {
			formateador = new DecimalFormat("#################0.0", simbolos);
			formateador.setMaximumFractionDigits(1);
		} else if (digDecimales.equals(2)) {
			formateador = new DecimalFormat("#################0.00", simbolos);
			formateador.setMaximumFractionDigits(2);
		} else if (digDecimales.equals(3)) {
			formateador = new DecimalFormat("#################0.000", simbolos);
			formateador.setMaximumFractionDigits(3);
		} else if (digDecimales.equals(4)) {
			formateador = new DecimalFormat("#################0.0000", simbolos);
			formateador.setMaximumFractionDigits(4);
		}
		else{
			formateador = new DecimalFormat("#################0.00", simbolos);
			formateador.setMaximumFractionDigits(2);
		}
		formateador.setGroupingUsed(agruparMiles);
		formateador.setGroupingSize(3);
		formateador.setParseIntegerOnly(false);
		return formateador;
	}

	/*************************************************************************************************************
	 * Ajusta la presentacion del monto al formato
	 * 
	 * @param BigDecimal
	 *            monto - monto a formatear
	 * @param Integer
	 *            digDecimales - numero de digitos decimales
	 * @return String - cadena formateada
	 *************************************************************************************************************/
	public String setMontoFormateado(String monto, Integer digDecimales) {
		String resultado = "";

		if (monto != null && monto != "" && !monto.equals("") && Util.esNumerico(monto)) {

			DecimalFormat formateador = crearFormateadorDecimal('.', ',',
					digDecimales, true);

			BigDecimal montoNumerico = new BigDecimal(monto);
			resultado = formateador.format(montoNumerico);
		}

		return resultado;
	}

	/*************************************************************************************************************
	 * Ajusta la presentacion del monto al formato
	 * 
	 * @param BigDecimal
	 *            monto - monto a formatear
	 * @param Integer
	 *            digDecimales - numero de digitos decimales
	 * @return String - cadena formateada
	 *************************************************************************************************************/
	public String setFechaFormateada(String fecha) {
		String resultado = "";
		if (fecha != null && fecha != "" && !fecha.equals("")) {
			try {
				resultado = Util.obtenerFechaFormatoDDMM(Util
						.obtenerDate(fecha));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return resultado;
	}

	/**
	 * Begin EVENT_AFTER_COLUMN_INIT This line is generated by iReport. Don't
	 * modify or move please!
	 */
	public void afterColumnInit() throws JRScriptletException {
		super.beforeColumnInit();
	}

	/**
	 * End EVENT_AFTER_COLUMN_INIT This line is generated by iReport. Don't
	 * modify or move please!
	 */
	/**
	 * Begin EVENT_AFTER_DETAIL_EVAL This line is generated by iReport. Don't
	 * modify or move please!
	 */
	public void afterDetailEval() throws JRScriptletException {
		super.afterDetailEval();
	}

	/**
	 * End EVENT_AFTER_DETAIL_EVAL This line is generated by iReport. Don't
	 * modify or move please!
	 */
	/**
	 * Begin EVENT_AFTER_GROUP_INIT This line is generated by iReport. Don't
	 * modify or move please!
	 */
	public void afterGroupInit(String groupName) throws JRScriptletException {
		super.afterGroupInit(groupName);
	}

	/**
	 * End EVENT_AFTER_GROUP_INIT This line is generated by iReport. Don't
	 * modify or move please!
	 */
	/**
	 * Begin EVENT_AFTER_PAGE_INIT This line is generated by iReport. Don't
	 * modify or move please!
	 */
	public void afterPageInit() throws JRScriptletException {
		super.afterPageInit();
	}

	/**
	 * End EVENT_AFTER_PAGE_INIT This line is generated by iReport. Don't modify
	 * or move please!
	 */
	/**
	 * Begin EVENT_AFTER_REPORT_INIT This line is generated by iReport. Don't
	 * modify or move please!
	 */
	public void afterReportInit() throws JRScriptletException {

	}

	/**
	 * End EVENT_AFTER_REPORT_INIT This line is generated by iReport. Don't
	 * modify or move please!
	 */
	/**
	 * Begin EVENT_BEFORE_COLUMN_INIT This line is generated by iReport. Don't
	 * modify or move please!
	 */
	public void beforeColumnInit() throws JRScriptletException {

	}

	/**
	 * End EVENT_BEFORE_COLUMN_INIT This line is generated by iReport. Don't
	 * modify or move please!
	 */
	/**
	 * Begin EVENT_BEFORE_DETAIL_EVAL This line is generated by iReport. Don't
	 * modify or move please!
	 */
	public void beforeDetailEval() throws JRScriptletException {

	}

	/** end EVENT_BEFORE_DETAIL_EVAL Please don't touch or move this comment */

	/**
	 * End EVENT_BEFORE_DETAIL_EVAL This line is generated by iReport. Don't
	 * modify or move please!
	 */
	/**
	 * Begin EVENT_BEFORE_GROUP_INIT This line is generated by iReport. Don't
	 * modify or move please!
	 */
	public void beforeGroupInit(String groupName) throws JRScriptletException {

	}

	/**
	 * End EVENT_BEFORE_GROUP_INIT This line is generated by iReport. Don't
	 * modify or move please!
	 */
	/**
	 * Begin EVENT_BEFORE_PAGE_INIT This line is generated by iReport. Don't
	 * modify or move please!
	 */
	public void beforePageInit() throws JRScriptletException {

	}

	/**
	 * End EVENT_BEFORE_PAGE_INIT This line is generated by iReport. Don't
	 * modify or move please!
	 */
	/**
	 * Begin EVENT_BEFORE_REPORT_INIT This line is generated by iReport. Don't
	 * modify or move please!
	 */
	public void beforeReportInit() throws JRScriptletException {

	}

	/**
	 * End EVENT_BEFORE_REPORT_INIT This line is generated by iReport. Don't
	 * modify or move please!
	 */

}
