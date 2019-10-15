package com.rta.opain.delegate.dto;

import java.io.Serializable;
import java.util.Date;

public class Cierre
        implements Serializable {

    Integer valor;
    Integer valorTotal;
    Integer inicio;
    Integer fin;
    String usuarioNombre;
    String sitioNombre;
    Integer cantidad;
    Date fecha;
    Integer valorE;
    Integer valorD;
    Integer cantidadAnuladas;
    Integer valorAnualdos;
    Integer valorC;
    String comprob;

    public String getComprob() {
        return comprob;
    }

    public void setComprob(String comprob) {
        this.comprob = comprob;
    }

    public Integer getCantidadAnuladas() {
        return this.cantidadAnuladas;
    }

    public void setCantidadAnuladas(Integer cantidadAnuladas) {
        this.cantidadAnuladas = cantidadAnuladas;
    }

    public Integer getValorAnualdos() {
        return this.valorAnualdos;
    }

    public void setValorAnualdos(Integer valorAnualdos) {
        this.valorAnualdos = valorAnualdos;
    }

    public Integer getValorE() {
        return this.valorE;
    }

    public Integer getCantidad() {
        return this.cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public void setValorE(Integer valorE) {
        this.valorE = valorE;
    }

    public Integer getValorD() {
        return this.valorD;
    }

    public void setValorD(Integer valorD) {
        this.valorD = valorD;
    }

    public Integer getValorC() {
        return this.valorC;
    }

    public void setValorC(Integer valorC) {
        this.valorC = valorC;
    }

    public Date getFecha() {
        return this.fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getValor() {
        return this.valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public Integer getValorTotal() {
        return this.valorTotal;
    }

    public void setValorTotal(Integer valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Integer getInicio() {
        return this.inicio;
    }

    public void setInicio(Integer inicio) {
        this.inicio = inicio;
    }

    public Integer getFin() {
        return this.fin;
    }

    public void setFin(Integer fin) {
        this.fin = fin;
    }

    public String getUsuarioNombre() {
        return this.usuarioNombre;
    }

    public void setUsuarioNombre(String usuarioNombre) {
        this.usuarioNombre = usuarioNombre;
    }

    public String getSitioNombre() {
        return this.sitioNombre;
    }

    public void setSitioNombre(String sitioNombre) {
        this.sitioNombre = sitioNombre;
    }
}
