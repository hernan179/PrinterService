    /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aero.aplication.dto;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Hernan Sanchez
 */
public class Cierre implements Serializable {

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

    public Integer getCantidadAnuladas() {
        return cantidadAnuladas;
    }

    public void setCantidadAnuladas(Integer cantidadAnuladas) {
        this.cantidadAnuladas = cantidadAnuladas;
    }

    public Integer getValorAnualdos() {
        return valorAnualdos;
    }

    public void setValorAnualdos(Integer valorAnualdos) {
        this.valorAnualdos = valorAnualdos;
    }

    public Integer getValorE() {
        return valorE;
    }

    public void setValorE(Integer valorE) {
        this.valorE = valorE;
    }

    public Integer getValorD() {
        return valorD;
    }

    public void setValorD(Integer valorD) {
        this.valorD = valorD;
    }

    public Integer getValorC() {
        return valorC;
    }

    public void setValorC(Integer valorC) {
        this.valorC = valorC;
    }
    Integer valorC;

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public Integer getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Integer valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Integer getInicio() {
        return inicio;
    }

    public void setInicio(Integer inicio) {
        this.inicio = inicio;
    }

    public Integer getFin() {
        return fin;
    }

    public void setFin(Integer fin) {
        this.fin = fin;
    }

    public String getUsuarioNombre() {
        return usuarioNombre;
    }

    public void setUsuarioNombre(String usuarioNombre) {
        this.usuarioNombre = usuarioNombre;
    }

    public String getSitioNombre() {
        return sitioNombre;
    }

    public void setSitioNombre(String sitioNombre) {
        this.sitioNombre = sitioNombre;
    }
}
