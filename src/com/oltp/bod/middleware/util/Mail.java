/***********************************************************************************************************
 * <p>
 * Title: NegocioIVR
 * </p>
 * <p>
 * Description: Clase de prueba para el envio automatico de estados de cuentas desde el IVR a la cuenta de correo del cliente
 * </p>
 * Copyright: Copyright (c) 2013<br>
 * Company: OLTP Voice System<br>
 * 
 * @author Miguel Uzcategui
 * @version 1.0
 * @since JDK1.6
 ***********************************************************************************************************/
package com.oltp.bod.middleware.util;


import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import com.oltp.bod.middleware.util.EmailModel;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;



public class Mail {

	public void sendMail(String titulo, Address[] destinatarios, EmailModel valores)
			throws MessagingException {

		Transport transportador = null;

		
		//String servidor = "";
		String servidor = "";
		int puerto = 25;
		//String user = "";
		String user = "";
		//String password = "";
		String password = "";
		//String password = "";
		//String emisor = "";
		String emisor = "";
		//String emisor = "";
		// String destinatario4 = "";

		// Get System properties

		Properties props = System.getProperties();
		props.put("mail.smtp.host", servidor);
		props.put("mail.smtp.port", puerto);

		Session sesion = Session.getInstance(props);
		MimeMessage mensaje = new MimeMessage(sesion);
		mensaje.setFrom(new InternetAddress(emisor));

		mensaje.setRecipients(Message.RecipientType.TO, destinatarios);
		mensaje.setSubject(titulo, "ISO-8859-1");
        
		
		
		
		
		
		StringBuilder fragmento;
	
		
		fragmento = new StringBuilder();
		fragmento.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">");
		fragmento.append("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
		fragmento.append("<head>");
		fragmento.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
		fragmento.append("<title>Untitled Document</title>");
		fragmento.append("<style type=\"text/css\">");
		fragmento.append("<!--");
		fragmento.append(".style1 {");
		fragmento.append("font-size: 36px;");
		fragmento.append("color: #000000;");
		fragmento.append("}");
		fragmento.append(".style4 {color: #0000FF; font-weight: bold; font-style: italic; }");
		fragmento.append("-->");
		fragmento.append("</style>");
		fragmento.append("</head>");
		fragmento.append("<body>");
		fragmento.append("<div align=\"center\">");
		fragmento.append("<p>&nbsp;</p>");
		fragmento.append("<p align=\"left\"><img src=\"file:///C|/java/VenecreditoFase2/Venecredito/WebContent/images/logoBVC.gif\" width=\"174\" </p>");
		fragmento.append("<p align=\"left\"><em><strong> VP BANCA ELECTRONICA &quot;Gerencia de </strong></em><em><strong>Venecr&eacute;dito&quot;</strong></em></p>");
		fragmento.append("<table width=\"537\" border=\"1\">");
		fragmento.append("<tr bgcolor=\"#006600\">");
		fragmento.append("<td colspan=\"2\" bgcolor=\"#346969\"><div align=\"center\" class=\"style1\">Suspensi&oacute;n de Cheques</div></td>");
		fragmento.append("</tr>");
		fragmento.append("<tr>");
		fragmento.append("<td width=\"283\"><span class=\"style6\">Operador</span></td>");
		fragmento.append("<td width=\"238\">"+valores.getOperador()+"</td>");
		fragmento.append("</tr>");
		fragmento.append("<tr>");
		fragmento.append("<td><span class=\"style6\">Apellidos y Nombres (PN) Empresa (PJ)</span></td>");
		fragmento.append("<td>"+valores.getNombre()+"</td>");
		fragmento.append("</tr>");
		fragmento.append("<tr>");
		fragmento.append("<td><span class=\"style6\">Cedula/RIF/Pasaporte</span></td>");
		fragmento.append("<td>"+valores.getCedula()+"</td>");
		fragmento.append("</tr>");
		fragmento.append("<tr>");
		fragmento.append("<td><span class=\"style6\">Numero de Cuenta</span></td>");
		fragmento.append("<td>"+valores.getNumeroCtaMascara()+"</td>");
		fragmento.append("</tr>");
		fragmento.append("<tr>");
		fragmento.append("<td><span class=\"style6\">Serial Inicial</span></td>");
		fragmento.append("<td>"+valores.getSerialInicial()+"</td>");
		fragmento.append("</tr>");
		fragmento.append("<tr>");
		fragmento.append("<td><span class=\"style6\">Cantidad de Cheques</span></td>");
		fragmento.append("<td>"+valores.getCantASuspender()+"</td>");
		fragmento.append("</tr>");
		fragmento.append("<tr>");
		fragmento.append("<td><span class=\"style6\">Motivo de Suspension</span></td>");
		fragmento.append("<td>"+valores.getMotivo()+"</td>");
		fragmento.append("</tr>");
		fragmento.append("<tr>");
		fragmento.append("<td><span class=\"style6\">Fecha del Suceso</span></td>");
		fragmento.append("<td>"+valores.getFecha()+"</td>");
		fragmento.append("</tr>");
		fragmento.append("<tr>");
		fragmento.append("<td height=\"23\"><span class=\"style6\">Lugar del Suceso</span></td>");
		fragmento.append("<td>"+valores.getLugarSuceso()+"</td>");
		fragmento.append("</tr>");
		fragmento.append("<tr>");
		fragmento.append("<td><span class=\"style6\">Telefono de Contacto</span></td>");
		fragmento.append("<td>"+valores.getTelefonoContacto()+"</td>");
		fragmento.append("</tr>");
		fragmento.append("<tr>");
		fragmento.append("<td height=\"77\"><span class=\"style6\">Comentarios del Operador</span></td>");
		fragmento.append("<td>"+valores.getComentarios()+"</td>");
		fragmento.append("</tr>");
		fragmento.append("</table>");
		fragmento.append("<p>&nbsp;</p>");
		fragmento.append("<p class=\"style3\"><strong><em>Mensaje Generado Autom&aacute;ticamente por el Aplicativo IVR</em></strong></p>");
		fragmento.append("<p class=\"style3\"><strong><em>(Por Favor No Responder Este Mensaje)</em></strong></p>");
		fragmento.append("</div>");
		fragmento.append("</body>");
		fragmento.append("</html>");
		
		mensaje.setText(fragmento.toString(), "utf-8", "html");

		
		
		try {
			transportador = sesion.getTransport("smtp");
			transportador.connect(servidor, puerto, user, password);
			transportador.sendMessage(mensaje, mensaje.getAllRecipients());
			System.out.println("Mensaje Enviado Exitosamente");

		} catch (SendFailedException sfe) {
			System.out.println(sfe);
			throw sfe;
			// TODO: handle exception
		} finally {

			if (transportador != null) {
				transportador.close();
				transportador = null;
			}
		}
	}

	

	
	public static void main(String[] argv) {

		Mail mail = new Mail();

		try {
			
			
			
			
			
			String destinatario1 = "";
			//String destinatario1 = "";
			//String desitnatario1 = "";
			EmailModel valores = new EmailModel(); 
			
			
			
			MimeBodyPart adjunto = new MimeBodyPart();
			adjunto.setDataHandler(new DataHandler(new FileDataSource("E:\\referenciaBancariaBOD211288449.pdf")));
			adjunto.setFileName("referenciaBancariaBOD211288449.pdf");
			
			MimeMultipart multiParte = new MimeMultipart();
			multiParte.addBodyPart(adjunto);
			
			
			
			
			
			
			valores.setOperador("Pedro perez (30 caracteres max)");
			valores.setNombre("Consultores C.A (30 caracteres max)");
			valores.setCedula("23444000(8 caracteres max)");
			valores.setNumeroCtaMascara("0104-****-**-***-1234567");
			valores.setSerialInicial("000001");
			valores.setCantASuspender("25");
			valores.setMotivo("extravio (se obtiene de una lista)");
			valores.setFecha("08/08/2011 (introducida por el operador)");
			valores.setLugarSuceso("No lo se (introducido por el operador)");
			valores.setTelefonoContacto("00000000000(11 caracteres)");
			valores.setComentarios("El cliente no recuerda donde extravio la chequera (introducido por el operador)");
			
			
			
			Address[] destinatarios = { new InternetAddress(destinatario1)};
			//,new InternetAddress(destinatario2) };
			mail.sendMail("Referencia Bancaria", destinatarios, valores);

			Integer oficina = new Integer("010"); 
			if (oficina.toString().length()==1){
				
				System.out.println("0"+oficina.toString());
			}
			else {
				
				System.out.println(oficina.toString());
			} 
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

}
