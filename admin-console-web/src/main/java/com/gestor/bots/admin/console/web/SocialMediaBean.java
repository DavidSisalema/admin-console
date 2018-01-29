package com.gestor.bots.admin.console.web;

import com.gestor.bots.admin.console.dao.SocialMediaDAO;
import com.gestor.bots.admin.console.model.SocialMedia;
import com.gestor.bots.admin.console.servicio.SocialMediaService;
import com.gestor.bots.admin.console.web.common.BaseBean;
import com.gestor.bots.admin.console.web.common.FacesUtil;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
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
public class SocialMediaBean extends BaseBean implements Serializable {

    private static final String ENTIDAD = "Red Social";

    @Inject
    private SocialMediaService socialMediaService;

    private List<SocialMedia> socialMediaList;

    private SocialMedia socialMedia;
    private SocialMedia socialMediaSel;

    @PostConstruct
    public void init() {
        this.socialMediaList = this.socialMediaService.obtenerTodos();
    }

    @Override
    public void nuevo() {
        super.nuevo();
        this.socialMedia = new SocialMedia();
    }

    @Override
    public void modificar() {
        super.modificar();
        this.socialMedia = new SocialMedia();
        try {
            BeanUtils.copyProperties(this.socialMedia, this.socialMediaSel);
        } catch (Exception ex) {
            FacesUtil.addMessageError(null, "No se puede modificar la " + ENTIDAD);
        }
    }

    public void eliminar() {
        try {
            this.socialMediaService.eliminar(socialMediaSel);
        } catch (Exception ex) {
            FacesUtil.addMessageError(null, "No se puede eliminar la " + ENTIDAD + this.socialMediaSel.getCodigo());
            super.cancelar();
        }
        this.socialMediaList = this.socialMediaService.obtenerTodos();
    }

    @Override
    public void consultar() {
        super.consultar();
        this.socialMedia = this.socialMediaSel;
    }

    public void guardar() {
        if (super.isEnModificar()) {
            try {
                this.socialMediaService.modificar(socialMedia);
                FacesUtil.addMessageInfo("Se modifico la red social: " + this.socialMedia.getNombre());
                super.cancelar();
            } catch (Exception e) {
                FacesUtil.addMessageError(null, "Ocurrio un error al modificar la red social: " + this.socialMedia);
            }
        } else {
            try {
                this.socialMediaService.crear(socialMedia);
                FacesUtil.addMessageInfo("Se creo la red social: " + this.socialMedia.getNombre());
                super.cancelar();
            } catch (Exception e) {
                FacesUtil.addMessageError(null, "No se pudo crear la red social: " + this.socialMedia);
            }
        }
        this.socialMediaList = this.socialMediaService.obtenerTodos();
    }

    public List<SocialMedia> getSocialMediaList() {
        return socialMediaList;
    }

    public SocialMedia getSocialMedia() {
        return socialMedia;
    }

    public void setSocialMedia(SocialMedia socialMedia) {
        this.socialMedia = socialMedia;
    }

    public SocialMedia getSocialMediaSel() {
        return socialMediaSel;
    }

    public void setSocialMediaSel(SocialMedia socialMediaSel) {
        this.socialMediaSel = socialMediaSel;
    }

    public String getTituloPanel() {
        return super.tituloPanel + SocialMediaBean.ENTIDAD;
    }

}
