package com.sistema.application.dto;
 
public class ChangoDetalleDto { 
     private long idChango;
     private int cantidad;    // cantidad total de items del chango
     private double total;    // valor total del chango
     private boolean facturado;    // es true si el chango ya fue facturado

     public ChangoDetalleDto(long idChango, int cantidad, double total, boolean facturado) {
          this.idChango = idChango;
          this.cantidad = cantidad;
          this.total = total;
          this.facturado = facturado;
     }
     
     public long getIdChango() {
          return this.idChango;
     }

     public void setIdChango(long idChango) {
          this.idChango = idChango;
     }

     public int getCantidad() {
          return this.cantidad;
     }

     public void setCantidad(int cantidad) {
          this.cantidad = cantidad;
     }

     public double getTotal() {
          return this.total;
     }

     public void setTotal(double total) {
          this.total = total;
     }

     public boolean isFacturado() {
          return this.facturado;
     }

     public boolean getFacturado() {
          return this.facturado;
     }

     public void setFacturado(boolean facturado) {
          this.facturado = facturado;
     }

     @Override
     public String toString() {
          return "{" +
               " idChango='" + getIdChango() + "'" +
               ", cantidad='" + getCantidad() + "'" +
               ", total='" + getTotal() + "'" +
               ", facturado='" + isFacturado() + "'" +
               "}";
     }
}