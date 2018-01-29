/*
 * GESTOR
 * Sistema: CONSOLA BOT
 * Creado: 22-dic-2017 - 09:43:30
 * 
 * Los contenidos de este archivo son propiedad intelectual de Gestorinc S.A.
 * y se encuentran protegidos por la licencia: "GESTOR FIDUCIA/FONDOS G5".
 * 
 * Usted puede encontrar una copia de esta licencia en: 
 *   http://www.gestorinc.com
 * 
 * Copyright 2015-2018 Gestorinc S.A. Todos los derechos reservados.
 */
package com.gestor.bots.admin.console.model;
 
import com.gestor.bots.admin.console.enums.EstadoReglaEnum;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Enumerated;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Entidad que almacena las reglas que puede atender el Bot
 *
 * @author GESTOR
 * @version 1.0
 */
@Entity
@Table(name = "REGLA")
public class Regla implements Serializable {

    /**
     * Constante autogenerada serialVersionUID.
     */
    private static final long serialVersionUID = 6674187054537456449L;

    /**
     * Propiedad que representa a la clave primaria de la entidad Regla, 
     * se relaciona con la columna COD_REGLA.
     */
    @Id
    @Column(name = "COD_REGLA", nullable = false, length = 16)
    private String codigo;


    @Column(name = "COD_BOT" , nullable = true , length = 10)
    private String codigoBot;
    
    @Column(name = "NOMBRE" , nullable = false , length = 100)
    private String nombre;
    
    @Column(name = "CATEGORIA" , nullable = false , length = 32)
    private String categoria;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "ESTADO" , nullable = false , length = 3)
    private EstadoReglaEnum estado;
    
    /** Propiedad  bot representa una relación de muchos a uno con la Entidad Bot.*/;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "COD_BOT", referencedColumnName = "COD_BOT", insertable = false, updatable = false) 
    private Bot bot;

    /**
     * Crea una nueva instancia de la entidad Regla.
     */
    public Regla() {
    }
	
    /** 
     * Crea una nueva instancia de la entidad Regla de acuerdo a su clave primaria. 
     *  
     * @param codigo valor para la clave primaria de la entidad Regla. 
     */ 
    public Regla(String codigo) { 
        this.codigo = codigo; 
    }

   
    /** 
     * Obtiene el valor de la propiedad codigo relacionado con la columna COD_REGLA. 
     *  
     * @return el valor asignado a la propiedad codigo. 
     */ 
    public String getCodigo() { 
        return codigo; 
    } 
     
    /** 
     * Asigna un valor a la propiedad codigo, relacionado con la columna COD_REGLA. 
     *  
     * @param codigo el valor a ser asignado a la propiedad codigo. 
     */ 
    public void setCodigo(String codigo) { 
        this.codigo = codigo; 
    }

    /** 
     * Obtiene el valor de la propiedad codigoBot relacionado con la columna COD_BOT. 
     *  
     * @return el valor asignado a la propiedad codigoBot. 
     */ 
    public String getCodigoBot() { 
        return codigoBot; 
    } 
     
    /** 
     * Asigna un valor a la propiedad codigoBot, relacionado con la columna COD_BOT. 
     *  
     * @param codigoBot el valor a ser asignado a la propiedad codigoBot. 
     */ 
    public void setCodigoBot(String codigoBot) { 
        this.codigoBot = codigoBot; 
    }

    /** 
     * Obtiene el valor de la propiedad nombre relacionado con la columna NOMBRE. 
     *  
     * @return el valor asignado a la propiedad nombre. 
     */ 
    public String getNombre() { 
        return nombre; 
    } 
     
    /** 
     * Asigna un valor a la propiedad nombre, relacionado con la columna NOMBRE. 
     *  
     * @param nombre el valor a ser asignado a la propiedad nombre. 
     */ 
    public void setNombre(String nombre) { 
        this.nombre = nombre; 
    }

    /** 
     * Obtiene el valor de la propiedad categoria relacionado con la columna CATEGORIA. 
     *  
     * @return el valor asignado a la propiedad categoria. 
     */ 
    public String getCategoria() { 
        return categoria; 
    } 
     
    /** 
     * Asigna un valor a la propiedad categoria, relacionado con la columna CATEGORIA. 
     *  
     * @param categoria el valor a ser asignado a la propiedad categoria. 
     */ 
    public void setCategoria(String categoria) { 
        this.categoria = categoria; 
    }

    /** 
     * Obtiene el valor de la propiedad estado relacionado con la columna ESTADO. 
     *  
     * @return el valor asignado a la propiedad estado. 
     */ 
    public EstadoReglaEnum getEstado() { 
        return estado; 
    } 
     
    /** 
     * Asigna un valor a la propiedad estado, relacionado con la columna ESTADO. 
     *  
     * @param estado el valor a ser asignado a la propiedad estado. 
     */ 
    public void setEstado(EstadoReglaEnum estado) { 
        this.estado = estado; 
    }

    /** 
     * Obtiene el valor de la propiedad bot relacionado con la entidad Bot. 
     *  
     * @return el valor asignado a la propiedad bot 
     */ 
    public Bot getBot() { 
        return bot; 
    } 
     
    /** 
     * Asigna un valor a la propiedad bot, relacionado con la entidad Bot. 
     *  
     * @param bot el valor a ser asignado a la propiedad bot 
     */ 
    public void setBot(Bot bot) { 
        this.bot = bot; 
    }

    /** 
     * Obtiene el valor de la propiedad entrenamientoReglas relacionado con la entidad EntrenamientoRegla. 
     *  
     * @return el valor asignado a la propiedad entrenamientoReglas 
     */ 


    /** 
     * {@inheritDoc} 
     */ 
    @Override 
    public int hashCode() { 
        int hash = (codigo != null) ? codigo.hashCode() : 0; 
        return hash; 
    }

    /** 
     * {@inheritDoc} 
     */ 
    @Override 
    public boolean equals(Object object) { 
        boolean igual = true; 
        if (!(object instanceof Regla)) { 
            igual = false; 
        } 
        Regla other = (Regla) object; 
        if ((this.codigo == null && other.codigo != null) 
                || (this.codigo != null && !this.codigo 
                        .equals(other.codigo))) { 
            igual = false; 
        } 
        return igual; 
    }

    /** 
     * {@inheritDoc} 
     */ 
    @Override 
    public String toString() { 
        return "Regla[codigo=" + codigo + "]"; 
    }     

} 
