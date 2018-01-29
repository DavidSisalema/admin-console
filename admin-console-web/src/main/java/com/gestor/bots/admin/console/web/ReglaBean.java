/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestor.bots.admin.console.web;

import com.gestor.bots.admin.console.enums.EstadoReglaEnum;
import com.gestor.bots.admin.console.model.Bot;
import com.gestor.bots.admin.console.model.Regla;
import com.gestor.bots.admin.console.servicio.BotService;
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
public class ReglaBean extends BaseBean implements Serializable {

    private static final String ENTIDAD = "Regla";

    @Inject
    private ReglaService reglaService;
    private List<Regla> reglaList;

    private Regla regla;
    private Regla reglaSel;

    private List<Bot> botList;
    private Bot bot;

    private BotService botService;

    @PostConstruct
    private void init() {
        super.setTipoFiltro("COD");
    }

    public void buscar() {
        this.reglaList = this.reglaService.buscar(super.getTipoFiltro(), super.getFiltro());
    }

    @Override
    public void nuevo() {
        super.nuevo();
        this.regla = new Regla();
    }

    @Override
    public void modificar() {
        super.modificar();
        this.regla = new Regla();
        try {
            BeanUtils.copyProperties(this.regla, this.reglaSel);
        } catch (Exception ex) {

            FacesUtil.addMessageError(null, "No se puede modificar la " + ENTIDAD);
        }
    }

    public void eliminar() {
        try {
            this.reglaService.eliminar(reglaSel);
        } catch (Exception ex) {
            FacesUtil.addMessageError(null, "No se puede eliminar la " + ENTIDAD + this.reglaSel.getCodigo());
            super.cancelar();
        }
        this.reglaList = this.reglaService.obtenerTodos();
    }

    @Override
    public void consultar() {
        super.consultar();
        this.regla = this.reglaSel;
    }

    public void guardar() {

        if (super.isEnModificar()) {
            try {
                this.reglaService.modificar(regla);
                FacesUtil.addMessageInfo("Se modifico el regla: " + this.regla.getNombre());
                super.cancelar();
            } catch (Exception e) {
                FacesUtil.addMessageError(null, "Ocurrio un error al modificar la regla: " + this.regla);
            }
        } else {
            try {
                this.reglaService.crear(regla);
                FacesUtil.addMessageInfo("Se creo el regla: " + this.regla.getNombre());
                super.cancelar();
            } catch (Exception e) {
                FacesUtil.addMessageError(null, "No se pudo crear el regla: " + this.regla);
            }
        }
        this.reglaList = this.reglaService.obtenerTodos();
    }

    public Regla getReglaSel() {
        return reglaSel;
    }

    public void setReglaSel(Regla reglaSel) {
        this.reglaSel = reglaSel;
    }

    public ReglaService getReglaService() {
        return reglaService;
    }

    public List<Regla> getReglaList() {
        return reglaList;
    }

    public Regla getRegla() {
        return regla;
    }

    public String getTituloPanel() {
        return super.tituloPanel + ReglaBean.ENTIDAD;
    }

    public EstadoReglaEnum[] getValoresEstadoRegla() {
        return EstadoReglaEnum.values();
    }

    public List<Bot> getBotList() {
        botList = this.reglaService.buscarBot("rr", "dd");
        return botList;
    }

    public Bot getBot() {
        return bot;
    }

    public BotService getBotService() {
        return botService;
    }

}
