package com.oltp.bod.middleware.bussiness;

import java.io.PrintStream;
import java.util.Random;

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
 * Title: NegocioSwitch
 * </p>
 * <p>
 * Description: Clase de Bussiness Service que contiene la logica asociada al
 * procesamiento de la rafaga enviada por el Switch
 * </p>
 * Copyright: Copyright (c) 2013<br>
 * Company: OLTP Voice System<br>
 * 
 * @author Miguel Uzcategui
 * @version 1.0
 * @since JDK1.6
 ***********************************************************************************************************/

public class NegocioSwitch {

	private static final Logger LOG = Logger.getLogger(NegocioSwitch.class);

	public static void procesarRespuesta(String[] arreglo_lectura,
			PrintStream escritor) {

		LOG.debug("Se procesara la respuesta a enviar al cliente IVR");
		LOG.debug("Operacion:" + arreglo_lectura[0]);

		Random r = new Random();
		int valor = 0;

		String rafagaSalida = "";

        if (arreglo_lectura[0].equals("RB")) {
			// ENVIO DE REFRENCIAS BANCARIAS POR EMAIL
			// T1|Nombre del cliente|Identificacion del Cliente|Tipo de cuenta|Numero de la Cuenta|Saldos promedio de la cuenta
			

			if (arreglo_lectura[1].equals("11111111")) {
				rafagaSalida = "0|GONZALEZ CAMPOS RAFAEL ANGEL|11288449|25/11/2014|CUENTA CORRIENTE CLASICA||1160101440033955190|3 CIFRAS ALTAS|";
			} else {

				valor = r.nextInt(9 + 1);

				switch (valor) {
				case 0:
					rafagaSalida = "1|";
					break;
				case 1:
					rafagaSalida = "2|";
					break;
				case 2:
					rafagaSalida = "22|";
					break;
				case 3:
					rafagaSalida = "46|";
					break;
				case 4:
					rafagaSalida = "51|";
					break;
				case 5:
					rafagaSalida = "90|";
					break;
				case 6:
					rafagaSalida = "91|";
					break;
				case 7:
					rafagaSalida = "92|";
					break;
				case 8:
					rafagaSalida = "111|";
					break;
				default:
					rafagaSalida = "125|";
					break;
				}
			}

		} else if (arreglo_lectura[0].equals("MV")) {
			// CONSULTA MOVIMIENTOS DE CUENTA
			// T12|Cedula|Clave Telefonica Encriptada|Numero de la Cuenta|Codigo
			// del Producto Cuenta|Mes a Consultar
			// CodRespuesta|Nombre del TarjetaHabiente|Linea 1 direccion|Linea 2
			// direccion|Linea 3 direccion|Ciudad direccion|Saldo
			// Inicial|Cantidad de Depósitos|Monto de Dépositos|Cantidad de
			// Intereses|Monto de Intereses|Cantidad Otros Créditos|Monto Otros
			// Créditos|Cantidad de Cheques Pagados|Monto de Cheques
			// Pagados|Cantidad IDB|Monto de IDB|Cantidad Otros Débitos|Monto
			// Otros Débitos|Saldo Final|Saldo Girable|Límite de Sobregiro|Saldo
			// Final BS.F|Información de Mercadeo|Ruta del Archivo del Detalle

			if (arreglo_lectura[1].equals("11111111")) {
				rafagaSalida = "0|OJEDA GONZALEZ, AURA MAGALI|ARQUE RESD CAMARACA|LIBERTADOR La Candelaria|AV ESTE 3, ENTRE AVILANES Y AV EL P|*|11105.11|0|0|10|11581.89|0|0|53|21414.44|0|0|53|1272.56|21414.44|7|7820.0|0|Creemos en los que arrancan el año con optimismo... Ahora somos más banco... Banco BOD. ¿Qué quieres alcanzar?|//BOD/Fax/EstadosCuenta/I120045106.TXT|";
			} else {

				valor = r.nextInt(9 + 1);

				switch (valor) {
				case 0:
					rafagaSalida = "1|";
					break;
				case 1:
					rafagaSalida = "2|";
					break;
				case 2:
					rafagaSalida = "22|";
					break;
				case 3:
					rafagaSalida = "46|";
					break;
				case 4:
					rafagaSalida = "51|";
					break;
				case 5:
					rafagaSalida = "90|";
					break;
				case 6:
					rafagaSalida = "91|";
					break;
				case 7:
					rafagaSalida = "92|";
					break;
				case 8:
					rafagaSalida = "111|";
					break;
				default:
					rafagaSalida = "125|";
					break;
				}
			}

		} else if (arreglo_lectura[0].equals("T3")) {
			// CONSULTA SALDO DE TARJETA DE CREDITO
			// T13|Cedula|Clave Telefonica Encriptada|Numero de la
			// Tarjeta|Codigo del Producto Tarjeta
			// CodRespuesta|Saldo al Último Corte|Pago Mínimo|Fecha Tope de
			// Pago|Saldo Actual

			if (arreglo_lectura[1].equals("11111111")) {
				rafagaSalida = "0|5586.25|512.25|14/02/2014/5586.25|";
			} else {

				valor = r.nextInt(10 + 1);

				switch (valor) {
				case 0:
					rafagaSalida = "1|";
					break;
				case 1:
					rafagaSalida = "2|";
					break;
				case 2:
					rafagaSalida = "22|";
					break;
				case 3:
					rafagaSalida = "46|";
					break;
				case 4:
					rafagaSalida = "51|";
					break;
				case 5:
					rafagaSalida = "90|";
					break;
				case 6:
					rafagaSalida = "91|";
					break;
				case 7:
					rafagaSalida = "92|";
					break;
				case 8:
					rafagaSalida = "111|";
					break;
				case 9:
					rafagaSalida = "52|";
					break;
				default:
					rafagaSalida = "125|";
					break;
				}
			}

		}  else {
			rafagaSalida = "TD|";
		}

		escritor.println(rafagaSalida);

		LOG.debug("Respuesta Enviada:" + rafagaSalida);
		LOG.debug("Respuesta enviada al cliente IVR");
	}
}
