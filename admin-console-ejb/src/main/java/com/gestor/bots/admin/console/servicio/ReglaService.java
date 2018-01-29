/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestor.bots.admin.console.servicio;

import com.gestor.bots.admin.console.dao.BotDAO;
import com.gestor.bots.admin.console.dao.ReglaDAO;
import com.gestor.bots.admin.console.model.Bot;
import com.gestor.bots.admin.console.model.Regla;
import com.gestor.bots.exception.CreacionException;
import com.gestor.bots.exception.EliminacionException;
import com.gestor.bots.exception.ModificacionException;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author David
 */
@LocalBean
@Stateless
public class ReglaService {

    @EJB
    private ReglaDAO reglaDAO;

    @EJB
    private BotDAO botDAO;

    public List<Regla> obtenerTodos() {
        return this.reglaDAO.findAll();
    }

    public List<Regla> buscar(String tipoFiltro, String valor) {
        System.err.println("El valor es: " + valor + ", el tipo es:" + tipoFiltro);
        Regla regla = new Regla();
        if ("NOM".equals(tipoFiltro)) {
            regla.setNombre(valor);
        } else if ("COD".equals(tipoFiltro)) {
            regla.setCodigo(valor);
        }
        return this.reglaDAO.find(regla);
    }

    public void crear(Regla regla) {
        try {
            this.reglaDAO.insert(regla);
        } catch (Exception e) {
            throw new CreacionException("ERR100", e.getMessage(), e);
        }
    }

    public void modificar(Regla regla) {
        try {
            this.reglaDAO.update(regla);
        } catch (Exception e) {
            throw new ModificacionException("ERR200", e.getMessage(), e);
        }
    }

    public void eliminar(Regla regla) {
        try {
            this.reglaDAO.remove(regla);
        } catch (Exception e) {
            throw new EliminacionException("ERR300", e.getMessage(), e);
        }
    }

    public List<Bot> buscarBot(String tipoFiltro, String valor) {
        System.err.println("El valor es: " + valor + ", el tipo es:" + tipoFiltro);
        Bot bot = new Bot();
        if ("COD".equals(tipoFiltro)) {
            bot.setCodigo(valor);
        } else if ("NOM".equals(tipoFiltro)) {
            bot.setNombre(valor);
            System.err.println("El nombre es:" + bot.getNombre());
        }
        return this.botDAO.find(bot);
    }


           
}
