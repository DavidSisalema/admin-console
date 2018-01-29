/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestor.bots.admin.console.web;

import com.gestor.bots.admin.console.model.Bot;
import com.gestor.bots.admin.console.model.Cliente;
import com.gestor.bots.admin.console.servicio.BotService;
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
 * @author Hendrix
 */
@Named
@ViewScoped
public class BotBean extends BaseBean implements Serializable {

    private static final String ENTIDAD = "Bot";

    @Inject
    private BotService botService;
    private List<Bot> botList;
    private List<Cliente> clientesConBot;

    private List<Bot> bots;

    private Bot bot;
    private Bot botSel;

    @PostConstruct
    private void init() {
        super.setTipoFiltro("COD");
    }

    public void seleccionar() {
        this.bots = this.botService.obtenerPorRuc(super.getFiltro());
    }

    public List<Cliente> getClientesConBot() {
        return clientesConBot;
    }

    public void buscar() {
        this.botList = this.botService.buscar(super.getTipoFiltro(), super.getFiltro());
    }

    @Override
    public void nuevo() {
        super.nuevo();
        this.bot = new Bot();
    }

    @Override
    public void modificar() {
        super.modificar();
        this.bot = new Bot();
        try {
            BeanUtils.copyProperties(this.bot, this.botSel);
        } catch (Exception ex) {
            FacesUtil.addMessageError(null, "No se puede modificar el " + ENTIDAD);
        }
    }

    public void eliminar() {
        try {
            this.botService.eliminar(botSel);
        } catch (Exception ex) {
            FacesUtil.addMessageError(null, "No se puede eliminar la " + ENTIDAD + this.botSel.getCodigo());
            super.cancelar();
        }
        this.botList = this.botService.obtenerTodos();
    }

    @Override
    public void consultar() {
        super.consultar();
        this.bot = this.botSel;
    }

    public void guardar() {
        if (super.isEnModificar()) {
            try {
                this.botService.modificar(bot);
                FacesUtil.addMessageInfo("Se modifico el bot: " + this.bot.getNombre());
                super.cancelar();
                this.botList = this.botService.obtenerTodos();
            } catch (Exception e) {
                FacesUtil.addMessageError(null, "Ocurrio un error al modificar el cliente: " + this.bot);
            }
        } else {
            try {
                this.botService.crear(bot);
                this.botList = this.botService.obtenerTodos();
                FacesUtil.addMessageInfo("Se creo el cliente: " + this.bot.getNombre());
                super.cancelar();
            } catch (Exception e) {
                FacesUtil.addMessageError(null, "No se pudo crear el cliente: " + this.bot);
            }
        }
        this.botList = this.botService.obtenerTodos();
    }

    public BotService getBotService() {
        return botService;
    }

    public List<Bot> getBotList() {
        return botList;
    }

    public List<Bot> getBots() {
        return bots;
    }

    public Bot getBot() {
        return bot;
    }

    public void setBot(Bot bot) {
        this.bot = bot;
    }

    public Bot getBotSel() {
        return botSel;
    }

    public void setBotSel(Bot botSel) {
        this.botSel = botSel;
    }

    public String getTituloPanel() {
        return super.tituloPanel + BotBean.ENTIDAD;
    }

}
