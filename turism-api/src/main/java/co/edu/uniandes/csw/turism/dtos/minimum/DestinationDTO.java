/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turism.dtos.minimum;

import co.edu.uniandes.csw.turism.entities.DestinationEntity;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jd.cepeda
 */
@XmlRootElement
public class DestinationDTO implements Serializable {

    private Long id;
    private String name;

    public DestinationDTO() {
    }

    public DestinationDTO(DestinationEntity entity) {
        if (entity != null) {
            this.id = entity.getId();
            this.name = entity.getName();
        }
    }

    public DestinationEntity toEntity() {
        DestinationEntity entity = new DestinationEntity();
        entity.setId(this.getId());
        entity.setName(this.getName());
        return entity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

