/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turism.dtos.minimum;

import co.edu.uniandes.csw.turism.entities.DestinationEntity;

import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author jd.cepeda
 */
@XmlRootElement
public class DestinationDTO implements Serializable {

    private Long id;
    private String name;
    private Date initialDate;
    private Date finalDate;
    private int duration;
    private String activities;

    public DestinationDTO() {
        // Método auto-generado
    }

    public DestinationDTO(DestinationEntity entity) {
        if (entity != null) {
            this.id = entity.getId();
            this.name = entity.getName();
            this.initialDate = entity.getInitialDate();
            this.finalDate = entity.getFinalDate();
            this.duration = entity.getDuration();
            this.activities = entity.getActivities();
        }
    }

    public DestinationEntity toEntity() {
        DestinationEntity entity = new DestinationEntity();
        entity.setId(this.getId());
        entity.setName(this.getName());
        entity.setInitialDate(this.getInitialDate());
        entity.setFinalDate(this.getFinalDate());
        entity.setDuration(this.getDuration());
        entity.setActivities(this.getActivities());
        return entity;
    }

    /**
     * getId
     *
     * @return
     */
    public Long getId() {
        return id;
    }

    /**
     * setId
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * getName
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * setName
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * getInitialDate
     *
     * @return
     */
    public Date getInitialDate() {
        return initialDate;
    }

    /**
     * setInitialDate
     *
     * @param initialDate
     */
    public void setInitialDate(Date initialDate) {
        this.initialDate = initialDate;
    }

    /**
     * getFinalDate
     *
     * @return
     */
    public Date getFinalDate() {
        return finalDate;
    }

    /**
     * setFinalDate
     *
     * @param finalDate
     */
    public void setFinalDate(Date finalDate) {
        this.finalDate = finalDate;
    }

    /**
     * getDuration
     *
     * @return
     */
    public int getDuration() {
        return duration;
    }

    /**
     * setDuration
     *
     * @param duration
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * getActivities
     *
     * @return
     */
    public String getActivities() {
        return activities;
    }

    /**
     * setActivities
     *
     * @param activities
     */
    public void setActivities(String activities) {
        this.activities = activities;
    }

}
