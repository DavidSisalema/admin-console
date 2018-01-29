/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestor.bots.admin.console.web;

import com.gestor.bots.admin.console.enums.EstadoUsuarioEnum;
import com.gestor.bots.admin.console.model.Usuario;
import com.gestor.bots.admin.console.servicio.UsuarioService;
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
public class UsuarioBean extends BaseBean implements Serializable  {

    private static final String ENTIDAD = "Usuario";
    
    @Inject
    private UsuarioService usuarioService;
    private List<Usuario> usuarioList;

    private Usuario usuario;
    private Usuario usuarioSel;
    
    
        
    @PostConstruct
    public void init() {
        super.setTipoFiltro("NOM");
    }
    
    public void buscar() {
        this.usuarioList = this.usuarioService.buscar(super.getTipoFiltro(), super.getFiltro());
    }
    
     @Override
    public void nuevo() {
        super.nuevo();
        this.usuario = new Usuario();
    }

    @Override
    public void modificar() {
        super.modificar();
        this.usuario = new Usuario();
        try {
            BeanUtils.copyProperties(this.usuario, this.usuarioSel);
        } catch (Exception ex) {            
            
            FacesUtil.addMessageError(null, "No se puede modificar la "+ENTIDAD);
        }
    }

    public void eliminar() {
        try {
            this.usuarioService.eliminar(usuarioSel);
        } catch (Exception ex) {
            FacesUtil.addMessageError(null, "No se puede eliminar la " + ENTIDAD + this.usuarioSel.getNombre());
            super.cancelar();
        }
        this.usuarioList = this.usuarioService.obtenerTodos(); 
    }
    
    @Override
    public void consultar() {
        super.consultar();
        this.usuario = this.usuarioSel;
    }

    public void guardar() {
        
        if (super.isEnModificar()) {
            try {
                this.usuarioService.modificar(usuario);
                FacesUtil.addMessageInfo("Se modifico el usuario: " + this.usuario.getNombre());
                super.cancelar();
            } catch (Exception e) {
                FacesUtil.addMessageError(null, "Ocurrio un error al modificar el usuario: "+this.usuario);
            }
        } else {
            try {
                this.usuarioService.crear(usuario);
                FacesUtil.addMessageInfo("Se creo el usuario: " + this.usuario.getNombre());
                super.cancelar();
            } catch (Exception e) {
                FacesUtil.addMessageError(null, "No se pudo crear el usuario: "+this.usuario);
            }
        }
       this.usuarioList = this.usuarioService.obtenerTodos(); 
    }
    
    public UsuarioService getUsuarioService() {
        return usuarioService;
    }

    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuarioSel(Usuario usuarioSel) {
        this.usuarioSel = usuarioSel;
    }
    

    public Usuario getUsuarioSel() {
        return usuarioSel;
    }
    
    public String getTituloPanel() {
        return super.tituloPanel+UsuarioBean.ENTIDAD;
    }

    public EstadoUsuarioEnum[] getValoresEstadoUsuario() {
        return EstadoUsuarioEnum.values();
    }
    
    

}
