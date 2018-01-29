package com.gestor.bots.admin.console.web;

import com.gestor.bots.admin.console.model.Cliente;
import com.gestor.bots.admin.console.servicio.ClienteService;
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
public class ClienteBean extends BaseBean implements Serializable {

    private static final String ENTIDAD = "Cliente";

    @Inject
    private ClienteService clienteService;
    private List<Cliente> clienteList;

    private Cliente cliente;
    private Cliente clienteSel;

    @PostConstruct
    public void init() {
        super.setTipoFiltro("RUC");
    }

    public void buscar() {
        this.clienteList = this.clienteService.buscar(super.getTipoFiltro(), super.getFiltro());
    }

    @Override
    public void nuevo() {
        super.nuevo();
        this.cliente = new Cliente();
    }

    @Override
    public void modificar() {
        super.modificar();
        this.cliente = new Cliente();
        try {
            BeanUtils.copyProperties(this.cliente, this.clienteSel);
        } catch (Exception ex) {
            FacesUtil.addMessageError(null, "No se puede modificar el " + ENTIDAD);
        }
    }

    public void eliminar() {
        try {
            this.clienteService.eliminar(clienteSel);
        } catch (Exception ex) {
            FacesUtil.addMessageError(null, "No se puede eliminar la " + ENTIDAD + this.clienteSel.getNombreComercial());
            super.cancelar();
        }
        this.clienteList = this.clienteService.obtenerTodos();
    }

    @Override
    public void consultar() {
        super.consultar();
        this.cliente = this.clienteSel;
    }

    public void guardar() {
        if (super.isEnModificar()) {
            try {
                this.clienteService.modificar(cliente);
                FacesUtil.addMessageInfo("Se modifico el cliente: " + this.cliente.getRazonSocial());
                super.cancelar();
                this.clienteList = this.clienteService.obtenerTodos();
            } catch (Exception e) {
                FacesUtil.addMessageError(null, "Ocurrio un error al modificar el cliente: " + this.cliente);
            }
        } else {
            try {
                this.clienteService.crear(cliente);
                this.clienteList = this.clienteService.obtenerTodos();
                FacesUtil.addMessageInfo("Se creo el cliente: " + this.cliente.getRazonSocial());
                super.cancelar();
            } catch (Exception e) {
                FacesUtil.addMessageError(null, "No se pudo crear el cliente: " + this.cliente);
            }
        }
        this.clienteList = this.clienteService.obtenerTodos();
    }

    public ClienteService getClienteService() {
        return clienteService;
    }

    public List<Cliente> getClienteList() {
        return clienteList;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Cliente getClienteSel() {
        return clienteSel;
    }

    public void setClienteSel(Cliente clienteSel) {
        this.clienteSel = clienteSel;
    }

    public String getTituloPanel() {
        return super.tituloPanel + ClienteBean.ENTIDAD;
    }

}
