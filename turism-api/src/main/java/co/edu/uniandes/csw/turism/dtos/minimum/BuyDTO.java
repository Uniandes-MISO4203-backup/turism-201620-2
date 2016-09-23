/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turism.dtos.minimum;

import co.edu.uniandes.csw.turism.entities.BuyEntity;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author martinalbarracin
 */
@XmlRootElement
public class BuyDTO implements Serializable{
    
    private Long id;
    private Long quantity;
    
        public BuyDTO() {
    }

    public BuyDTO(BuyEntity entity) {
    if (entity!=null){
        this.id=entity.getId();
        this.quantity= entity.getQuantity();
        this.name=entity.getName();
        this.description=entity.getDescription();
        
       }
    }

    public BuyEntity toEntity() {
        BuyEntity entity = new BuyEntity();
        entity.setId(this.getId());
        entity.setName(this.getName());
        entity.setDescription(this.getDescription());
        entity.setQuantity(this.getQuantity());
        return entity;
    }
    
    
    
    public Long getQuantity() {
        return quantity;
    }
    
    public void setQuantity(Long quantity) {
        this.quantity = quantity;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    private String name;
    private String description;
    

    
}
