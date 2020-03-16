package com.example.momentouno.Models;

import java.io.Serializable;

public class TarjetaModel implements Serializable {
    private int _id;
    private String _numeroTarjeta;
    private String _mesVencimiento;
    private String _anioVencimiento;
    private double _cupoMax;
    private double _saldoDisponible;
    private double _saldoDeuda;
    private String _franquicia;

    public TarjetaModel(String _numeroTarjeta, String _mesVencimiento, String _anioVencimiento, double _cupoMax, double _saldoDisponible, double _saldoDeuda, String _franquicia) {
        this._numeroTarjeta = _numeroTarjeta;
        this._mesVencimiento = _mesVencimiento;
        this._anioVencimiento = _anioVencimiento;
        this._cupoMax = _cupoMax;
        this._saldoDisponible = _saldoDisponible;
        this._saldoDeuda = _saldoDeuda;
        this._franquicia = _franquicia;
    }

    public TarjetaModel(int _id, String _numeroTarjeta, String _mesVencimiento, String _anioVencimiento, double _cupoMax, double _saldoDisponible, double _saldoDeuda, String _franquicia) {
        this._id = _id;
        this._numeroTarjeta = _numeroTarjeta;
        this._mesVencimiento = _mesVencimiento;
        this._anioVencimiento = _anioVencimiento;
        this._cupoMax = _cupoMax;
        this._saldoDisponible = _saldoDisponible;
        this._saldoDeuda = _saldoDeuda;
        this._franquicia = _franquicia;
    }

    @Override
    public String toString() {
        return "TarjetaModel{" +
                "_id=" + _id +
                ", _numeroTarjeta='" + _numeroTarjeta + '\'' +
                ", _mesVencimiento='" + _mesVencimiento + '\'' +
                ", _anioVencimiento='" + _anioVencimiento + '\'' +
                ", _cupoMax=" + _cupoMax +
                ", _saldoDisponible=" + _saldoDisponible +
                ", _saldoDeuda=" + _saldoDeuda +
                ", _franquicia='" + _franquicia + '\'' +
                '}';
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_numeroTarjeta() {
        return _numeroTarjeta;
    }

    public void set_numeroTarjeta(String _numeroTarjeta) {
        this._numeroTarjeta = _numeroTarjeta;
    }

    public String get_mesVencimiento() {
        return _mesVencimiento;
    }

    public void set_mesVencimiento(String _mesVencimiento) {
        this._mesVencimiento = _mesVencimiento;
    }

    public String get_anioVencimiento() {
        return _anioVencimiento;
    }

    public void set_anioVencimiento(String _anioVencimiento) {
        this._anioVencimiento = _anioVencimiento;
    }

    public double get_cupoMax() {
        return _cupoMax;
    }

    public void set_cupoMax(double _cupoMax) {
        this._cupoMax = _cupoMax;
    }

    public double get_saldoDisponible() {
        return _saldoDisponible;
    }

    public void set_saldoDisponible(double _saldoDisponible) {
        this._saldoDisponible = _saldoDisponible;
    }

    public double get_saldoDeuda() {
        return _saldoDeuda;
    }

    public void set_saldoDeuda(double _saldoDeuda) {
        this._saldoDeuda = _saldoDeuda;
    }

    public String get_franquicia() {
        return _franquicia;
    }

    public void set_franquicia(String _franquicia) {
        this._franquicia = _franquicia;
    }
}
