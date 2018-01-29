/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestor.bots.admin.console.servicio;

import com.gestor.bots.admin.console.dao.EntrenamientoReglaDAO;
import com.gestor.bots.admin.console.dao.ReglaDAO;
import com.gestor.bots.admin.console.model.EntrenamientoRegla;
import com.gestor.bots.admin.console.model.EntrenamientoReglaPK;
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
public class EntrenamientoReglaService {

    @EJB
    private EntrenamientoReglaDAO entrenamientoReglaDAO;

    @EJB
    private ReglaDAO reglaDAO;

    public List<EntrenamientoRegla> obtenerTodos() {
        return this.entrenamientoReglaDAO.findAll();

    }

    public List<EntrenamientoRegla> buscar(String valor) {
        Integer varInt;
        EntrenamientoRegla entrenamientoRegla = new EntrenamientoRegla();
        EntrenamientoReglaPK entrenamientoReglaPK = new EntrenamientoReglaPK();
        if (!valor.isEmpty()) {
            varInt = Integer.parseInt(valor);
            entrenamientoReglaPK.setSecuenciaEntrenamiento(varInt);
            entrenamientoRegla.setPk(entrenamientoReglaPK);
        }
        return this.entrenamientoReglaDAO.find(entrenamientoRegla);
    }

    public void crear(EntrenamientoRegla entrenamientoRegla) {
        try {
            this.entrenamientoReglaDAO.insert(entrenamientoRegla);
        } catch (Exception e) {
            throw new CreacionException("ERR100", e.getMessage(), e);
        }
    }

    public void modificar(EntrenamientoRegla entrenamientoRegla) {
        try {
            this.entrenamientoReglaDAO.update(entrenamientoRegla);
        } catch (Exception e) {
            throw new ModificacionException("ERR200", e.getMessage(), e);
        }
    }

    public void eliminar(EntrenamientoRegla entrenamientoRegla) {
        try {
            this.entrenamientoReglaDAO.remove(entrenamientoRegla);
        } catch (Exception e) {
            throw new EliminacionException("ERR300", e.getMessage(), e);
        }
    }

    public List<Regla> buscarRegla() {
        Regla regla = new Regla();
        return this.reglaDAO.find(regla);
    }
}
