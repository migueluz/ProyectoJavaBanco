package com.oltp.bod.middleware.reporte;


public class ReporteDTO {


	String nombreArchivoDetalleOriginal;
	
	String tipoReporte;
	
	String nombreArchivoDetalleActualizado;

	String nombreCliente;
	
	String cedulaCliente;
	
	String tipoCuenta; 
	
	String NumeroCuenta;
	
	String parametroCuenta;
	
	String fechaCliente;
	
	String fecha;

	public String getNombreArchivoDetalleOriginal() {
		return nombreArchivoDetalleOriginal;
	}

	public void setNombreArchivoDetalleOriginal(String nombreArchivoDetalleOriginal) {
		this.nombreArchivoDetalleOriginal = nombreArchivoDetalleOriginal;
	}

	public String getTipoReporte() {
		return tipoReporte;
	}

	public void setTipoReporte(String tipoReporte) {
		this.tipoReporte = tipoReporte;
	}

	public String getNombreArchivoDetalleActualizado() {
		return nombreArchivoDetalleActualizado;
	}

	public void setNombreArchivoDetalleActualizado(
			String nombreArchivoDetalleActualizado) {
		this.nombreArchivoDetalleActualizado = nombreArchivoDetalleActualizado;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public String getCedulaCliente() {
		return cedulaCliente;
	}

	public void setCedulaCliente(String cedulaCliente) {
		this.cedulaCliente = cedulaCliente;
	}

	public String getTipoCuenta() {
		return tipoCuenta;
	}

	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	public String getNumeroCuenta() {
		return NumeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		NumeroCuenta = numeroCuenta;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getParametroCuenta() {
		return parametroCuenta;
	}

	public void setParametroCuenta(String parametroCuenta) {
		this.parametroCuenta = parametroCuenta;
	}

	public String getFechaCliente() {
		return fechaCliente;
	}

	public void setFechaCliente(String fechaCliente) {
		this.fechaCliente = fechaCliente;
	}
	
	


}
