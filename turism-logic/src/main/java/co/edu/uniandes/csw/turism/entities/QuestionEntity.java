/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turism.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import co.edu.uniandes.csw.crud.spi.entity.BaseEntity;
import uk.co.jemos.podam.common.PodamExclude;
import javax.persistence.ManyToOne;

/**
 *
 * @author af.osorio10
 */
@Entity
public class QuestionEntity extends BaseEntity implements Serializable{
    
    private String respuesta;
    
    @PodamExclude
    @ManyToOne
    private TripEntity trip;
    
    /**
     * Obtiene el atributo respuesta.
     *
     * @return atributo respuesta.
     * @generated
     */
    public String getRespuesta(){
        return respuesta;
    }

    /**
     * Establece el valor del atributo respuesta.
     *
     * @param respuesta nuevo valor del atributo
     * @generated
     */
    public void setRespuesta(String respuesta){
        this.respuesta = respuesta;
    }
    
    /**
     * Obtiene el atributo trip.
     *
     * @return atributo trip.
     * @generated
     */
    public TripEntity getTrip() {
        return trip;
    }

    /**
     * Establece el valor del atributo trip.
     *
     * @param trip nuevo valor del atributo
     * @generated
     */
    public void setTrip(TripEntity trip) {
        this.trip = trip;
    }
    
}
