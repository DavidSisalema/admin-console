/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestor.bots.admin.console.servicio;

import com.gestor.bots.admin.console.dao.BotDAO;
import com.gestor.bots.admin.console.dao.ClienteDAO;
import com.gestor.bots.admin.console.model.Bot;
import com.gestor.bots.admin.console.model.Cliente;
import com.gestor.bots.exception.CreacionException;
import com.gestor.bots.exception.EliminacionException;
import com.gestor.bots.exception.ModificacionException;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author Hendrix
 */
@LocalBean
@Stateless
public class BotService {

    @EJB
    private ClienteDAO clienteDAO;
    @EJB
    private BotDAO botDAO;

    public List<Cliente> obtenerClientesConBots() {
        return this.clienteDAO.findWithBot();
    }

    public List<Bot> obtenerPorRuc(String ruc) {
        return this.botDAO.findByRuc(ruc);
    }

    public List<Bot> obtenerTodos() {
        return this.botDAO.findAll();
    }

    public List<Bot> buscar(String tipoFiltro, String valor) {
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

    public void crear(Bot bot) {
        try {
            this.botDAO.insert(bot);
        } catch (Exception e) {
            throw new CreacionException("ERR100", e.getMessage(), e);
        }
    }

    public void modificar(Bot bot) {
        try {
            this.botDAO.update(bot);
        } catch (Exception e) {
            throw new ModificacionException("ERR200", e.getMessage(), e);
        }
    }

    public void eliminar(Bot bot) {
        try {
            this.botDAO.remove(bot);
        } catch (Exception e) {
            throw new EliminacionException("ERR300", e.getMessage(), e);
        }
    }

    
}
