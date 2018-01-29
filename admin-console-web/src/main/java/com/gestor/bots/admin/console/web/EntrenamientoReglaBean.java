/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestor.bots.admin.console.web;

import com.gestor.bots.admin.console.model.EntrenamientoRegla;
import com.gestor.bots.admin.console.model.EntrenamientoReglaPK;
import com.gestor.bots.admin.console.model.Regla;
import com.gestor.bots.admin.console.servicio.EntrenamientoReglaService;
import com.gestor.bots.admin.console.servicio.ReglaService;
import com.gestor.bots.admin.console.web.common.BaseBean;
import com.gestor.bots.admin.console.web.common.FacesUtil;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.commons.beanutils.BeanUtils;

/**
 *
 * @author David
 */
@Named
@ViewScoped
public class EntrenamientoReglaBean extends BaseBean implements Serializable {

    private static final String ENTIDAD = "Entrenamiento Reglas";

    @Inject
    private EntrenamientoReglaService entrenamientoReglaService;
    private List<EntrenamientoRegla> entrenamientoReglaList;

    private EntrenamientoRegla entrenamientoRegla;
    private EntrenamientoRegla entrenamientoReglaSel;

    private EntrenamientoReglaPK entrenamientoReglaPK;
    private EntrenamientoReglaPK entrenamientoReglaSelPK;

    private List<Regla> reglaList;
    private Regla regla;

    @PostConstruct
    private void init() {
        super.setTipoFiltro("COD");
        
    }

    public void buscar() {
        this.entrenamientoReglaList = this.entrenamientoReglaService.buscar(super.getFiltro());
        this.reglaList = this.entrenamientoReglaService.buscarRegla();
    }

    @Override
    public void nuevo() {
        super.nuevo();
        this.entrenamientoRegla = new EntrenamientoRegla();
        this.entrenamientoReglaPK = new EntrenamientoReglaPK();
    }

    @Override
    public void modificar() {
        super.modificar();
        this.entrenamientoRegla = new EntrenamientoRegla();
        try {
            BeanUtils.copyProperties(this.entrenamientoRegla, this.entrenamientoReglaSel);
        } catch (Exception ex) {

            FacesUtil.addMessageError(null, "No se puede modificar la " + ENTIDAD);
        }
    }

    public void eliminar() {
        try {
            this.entrenamientoReglaService.eliminar(entrenamientoReglaSel);
            super.cancelar();
        } catch (Exception ex) {
            FacesUtil.addMessageError(null, "No se puede eliminar la " + ENTIDAD + this.entrenamientoReglaSel.getPk().getCodigoRegla());
            super.cancelar();
        }
        this.entrenamientoReglaList = this.entrenamientoReglaService.obtenerTodos();
    }
    
    @Override
    public void consultar() {
        super.consultar();
        this.entrenamientoRegla = this.entrenamientoReglaSel;
    }

    public void guardar() {

        if (super.isEnModificar()) {
            try {
                this.entrenamientoReglaService.modificar(entrenamientoRegla);
                FacesUtil.addMessageInfo("Se modifico El Entrenamiento de la Regla: " + this.entrenamientoRegla.getPk().getSecuenciaEntrenamiento());
                super.cancelar();
            } catch (Exception e) {
                FacesUtil.addMessageError(null, "Ocurrio un error al modificar la regla: " + this.entrenamientoRegla);
            }
        } else {
            try {
                this.entrenamientoReglaService.crear(entrenamientoRegla);
                FacesUtil.addMessageInfo("Se creo el regla: " + this.entrenamientoRegla.getPk().getSecuenciaEntrenamiento());
                super.cancelar();
            } catch (Exception e) {
                FacesUtil.addMessageError(null, "No se pudo crear el regla: " + this.entrenamientoRegla);
            }
        }
        this.entrenamientoReglaList = this.entrenamientoReglaService.obtenerTodos();
     
    }

    public EntrenamientoRegla getEntrenamientoReglaSel() {
        return entrenamientoReglaSel;
    }

    public void setEntrenamientoReglaSel(EntrenamientoRegla entrenamientoReglaSel) {
        this.entrenamientoReglaSel = entrenamientoReglaSel;
    }

    public EntrenamientoReglaService getEntrenamientoReglaService() {
        return entrenamientoReglaService;
    }

    public List<EntrenamientoRegla> getEntrenamientoReglaList() {
        return entrenamientoReglaList;
    }

    public EntrenamientoRegla getEntrenamientoRegla() {
        return entrenamientoRegla;
    }

    public EntrenamientoReglaPK getEntrenamientoReglaPK() {
        return entrenamientoReglaPK;
    }

    public EntrenamientoReglaPK getEntrenamientoReglaSelPK() {
        return entrenamientoReglaSelPK;
    }

    public String getTituloPanel() {
        return super.tituloPanel + EntrenamientoReglaBean.ENTIDAD;
    }

    public List<Regla> getReglaList() {
          reglaList = this.entrenamientoReglaService.buscarRegla();
        return reglaList;
    }

    public Regla getRegla() {
        return regla;
    }

}
