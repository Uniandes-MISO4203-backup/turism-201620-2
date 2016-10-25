/*
The MIT License (MIT)

Copyright (c) 2015 Los Andes University

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
package co.edu.uniandes.csw.turism.dtos.minimum;

import co.edu.uniandes.csw.turism.entities.TripEntity;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.sql.Date;

/**
 * @generated
 */
@XmlRootElement
public class TripDTO implements Serializable{

    private Long id;
    private String name;
    private String image;
    private Long price;
    private Date departureDate;
    private String destination;
    private int quota;
    private int duration;
    private String transport;
    private boolean promotion ;

    /**
     * @generated
     */
    public TripDTO() {
        // Método auto-generado
    }

    /**
     * Crea un objeto TripDTO a partir de un objeto TripEntity.
     *
     * @param entity Entidad TripEntity desde la cual se va a crear el nuevo objeto.
     * @generated
     */
    public TripDTO(TripEntity entity) {
        if (entity != null) {
            this.id = entity.getId();
            this.name = entity.getName();
            this.image = entity.getImage();
            this.price = entity.getPrice();
            this.departureDate = entity.getDepartureDate();
            this.destination = entity.getDestination();
            this.quota = entity.getQuota();
            this.duration = entity.getDuration();
            this.transport = entity.getTransport();
            this.promotion = entity.getPromotion();
        }
    }

    /**
     * Convierte un objeto TripDTO a TripEntity.
     *
     * @return Nueva objeto TripEntity.
     * @generated
     */
    public TripEntity toEntity() {
        TripEntity entity = new TripEntity();
        entity.setId(this.getId());
        entity.setName(this.getName());
        entity.setImage(this.getImage());
        entity.setPrice(this.getPrice());
        entity.setDepartureDate(this.getDepartureDate());
        entity.setDestination(this.getDestination());
        entity.setQuota(this.getQuota());
        entity.setDuration(this.getDuration());
        entity.setTransport(this.getTransport());
        entity.setPromotion(this.getPromotion());
        return entity;
    }

    /**
     * Obtiene el atributo id.
     *
     * @return atributo id.
     * @generated
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el valor del atributo id.
     *
     * @param id nuevo valor del atributo
     * @generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el atributo name.
     *
     * @return atributo name.
     * @generated
     */
    public String getName() {
        return name;
    }

    /**
     * Establece el valor del atributo name.
     *
     * @param name nuevo valor del atributo
     * @generated
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtiene el atributo image.
     *
     * @return atributo image.
     * @generated
     */
    public String getImage() {
        return image;
    }

    /**
     * Establece el valor del atributo image.
     *
     * @param image nuevo valor del atributo
     * @generated
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * Obtiene el atributo price.
     *
     * @return atributo price.
     * @generated
     */
    public Long getPrice() {
        return price;
    }

    /**
     * Establece el valor del atributo price.
     *
     * @param price nuevo valor del atributo
     * @generated
     */
    public void setPrice(Long price) {
        this.price = price;
    }

    /**
     * Obtiene el atributo departureDate.
     *
     * @return atributo departureDate.
     * @generated
     */
    public Date getDepartureDate() {
        return departureDate;
    }

    /**
     * Establece el valor del atributo departureDate.
     *
     * @param departureDate nuevo valor del atributo
     * @generated
     */
    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    /**
     * Obtiene el atributo destination.
     *
     * @return atributo destination.
     * @generated
     */
    public String getDestination() {
        return destination;
    }

    /**
     * Establece el valor del atributo destination.
     *
     * @param destination nuevo valor del atributo
     * @generated
     */
    public void setDestination(String destination) {
        this.destination = destination;
    }

    /**
     * Obtiene el atributo quota.
     *
     * @return atributo quota.
     * @generated
     */
    public int getQuota() {
        return quota;
    }

    /**
     * Establece el valor del atributo quota.
     *
     * @param quota nuevo valor del atributo
     * @generated
     */
    public void setQuota(int quota) {
        this.quota = quota;
    }

    /**
     * Obtiene el atributo duration.
     *
     * @return atributo duration.
     * @generated
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Establece el valor del atributo duration.
     *
     * @param duration nuevo valor del atributo
     * @generated
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * Obtiene el atributo transport.
     *
     * @return atributo transport.
     * @generated
     */
    public String getTransport() {
        return transport;
    }

    /**
     * Establece el valor del atributo transport.
     *
     * @param transport nuevo valor del atributo
     * @generated
     */
    public void setTransport(String transport) {
        this.transport = transport;
    }
    
    /** 
    *Obtener  el boolean de la promocion 
    */
    public boolean getPromotion() {
        return promotion;
    }

    /**
     * Dar un valor boolean promocion 
     * @param promotion 
     */
    public void setPromotion(boolean promotion) {
        this.promotion = promotion;
    }

}
