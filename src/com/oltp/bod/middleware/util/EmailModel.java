package com.oltp.bod.middleware.util;



public class EmailModel {
	
       private String operador; 
       private String nombre; 
       private String cedula;
       private String numeroCtaMascara;
       private String serialInicial;
       private String cantASuspender;
       private String Listado;
       private String fecha;
       private String lugarSuceso;
       private String telefonoContacto;
       private String comentarios;
       private String motivo;
       
       
        
        public EmailModel()
        {
        }

		public String getOperador() {
			return operador;
		}

		public void setOperador(String operador) {
			this.operador = operador;
		}

		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}

		public String getNumeroCtaMascara() {
			return numeroCtaMascara;
		}

		public void setNumeroCtaMascara(String numeroCtaMascara) {
			this.numeroCtaMascara = numeroCtaMascara;
		}

		public String getSerialInicial() {
			return serialInicial;
		}

		public void setSerialInicial(String serialInicial) {
			this.serialInicial = serialInicial;
		}

		public String getCantASuspender() {
			return cantASuspender;
		}

		public void setCantASuspender(String cantASuspender) {
			this.cantASuspender = cantASuspender;
		}

		public String getListado() {
			return Listado;
		}

		public void setListado(String listado) {
			Listado = listado;
		}

		public String getFecha() {
			return fecha;
		}

		public void setFecha(String fecha) {
			this.fecha = fecha;
		}

		public String getLugarSuceso() {
			return lugarSuceso;
		}

		public void setLugarSuceso(String lugarSuceso) {
			this.lugarSuceso = lugarSuceso;
		}

		public String getComentarios() {
			return comentarios;
		}

		public void setComentarios(String comentarios) {
			this.comentarios = comentarios;
		}

		public String getMotivo() {
			return motivo;
		}

		public void setMotivo(String motivo) {
			this.motivo = motivo;
		}

		public String getCedula() {
			return cedula;
		}

		public void setCedula(String cedula) {
			this.cedula = cedula;
		}

		public String getTelefonoContacto() {
			return telefonoContacto;
		}

		public void setTelefonoContacto(String telefonoContacto) {
			this.telefonoContacto = telefonoContacto;
		}

	
		

        

		

}
