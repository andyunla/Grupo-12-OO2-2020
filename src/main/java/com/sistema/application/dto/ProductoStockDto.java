package com.sistema.application.dto;

public class ProductoStockDto {
     private long idProducto;
     private String nombre;
     private int talle;
     private double precio;
     private int stock;

     public ProductoStockDto(long idProducto, String nombre, int talle, double precio, int stock) {
          this.idProducto = idProducto;
          this.nombre = nombre;
          this.talle = talle;
          this.setPrecio(precio);
          this.stock = stock;
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
          this.precio = Math.floor(precio * 100) / 100;
     }

     public int getStock() {
          return this.stock;
     }

     public void setStock(int stock) {
          this.stock = stock;
     }

     @Override
     public String toString() {
          return "{" +
               " idProducto='" + getIdProducto() + "'" +
               ", nombre='" + getNombre() + "'" +
               ", talle='" + getTalle() + "'" +
               ", precio='" + getPrecio() + "'" +
               ", stock='" + getStock() + "'" +
               "}";
     }

}