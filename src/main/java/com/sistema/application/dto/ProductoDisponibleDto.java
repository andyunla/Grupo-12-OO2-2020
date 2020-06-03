package com.sistema.application.dto;

/* Producto disponible para agregar a un chango */
public class ProductoDisponibleDto {
     private long idProducto;
     private String nombre;
     private int talle;
     private double precio;
     private int stockTotal;  // Indica la cantidad de stockTotal actual, incluyendo la cantidad que está cargada en un item del chango
     private int stockDisponible;  // Indica la cantidad de stock disponible, excluyendo la cantidad cargada en el item, si hubiese
     private boolean enChango;     // Indica si este producto ya se encuentra en el chango

     public ProductoDisponibleDto(long idProducto, String nombre, int talle, double precio, int stockTotal, 
               int stockDisponible, boolean enChango) {
          this.idProducto = idProducto;
          this.nombre = nombre;
          this.talle = talle;
          this.precio = precio;
          this.stockTotal = stockTotal;
          this.stockDisponible = stockDisponible;
          this.enChango = enChango;
     }

     public long getIdProducto() {
          return this.idProducto;
     }

     public void setIdProducto(long idProducto) {
          this.idProducto = idProducto;
     }

     public String getNombre() {
          return this.nombre;
     }

     public void setNombre(String nombre) {
          this.nombre = nombre;
     }

     public int getTalle() {
          return this.talle;
     }

     public void setTalle(int talle) {
          this.talle = talle;
     }

     public double getPrecio() {
          return this.precio;
     }

     public void setPrecio(double precio) {
          this.precio = precio;
     }

     public int getStockTotal() {
          return this.stockTotal;
     }

     public void setStockTotal(int stockTotal) {
          this.stockTotal = stockTotal;
     }

     public boolean getEnChango() {
          return this.enChango;
     }

     public void setEnChango(boolean enChango) {
          this.enChango = enChango;
     }

     public int getStockDisponible() {
          return this.stockDisponible;
     }

     public void setStockDisponible(int stockDisponible) {
          this.stockDisponible = stockDisponible;
     }

     @Override
     public String toString() {
          return "{" +
               " idProducto='" + getIdProducto() + "'" +
               ", nombre='" + getNombre() + "'" +
               ", talle='" + getTalle() + "'" +
               ", precio='" + getPrecio() + "'" +
               ", stockTotal='" + getStockTotal() + "'" +
               ", stockDisponible='" + getStockDisponible()+ "'" +
               ", enChango='" + getEnChango() + "'" +
               "}";
     }

}