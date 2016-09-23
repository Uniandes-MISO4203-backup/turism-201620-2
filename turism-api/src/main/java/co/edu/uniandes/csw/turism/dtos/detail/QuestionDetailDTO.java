/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turism.dtos.detail;

import co.edu.uniandes.csw.turism.dtos.minimum.*;
import co.edu.uniandes.csw.turism.entities.QuestionEntity;
import javax.xml.bind.annotation.XmlRootElement;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author af.osorio10
 */
@XmlRootElement
public class QuestionDetailDTO extends QuestionDTO{
    
    @PodamExclude
    private TripDTO trip;
    
    public QuestionDetailDTO() {
        super();
    }
    
    /**
     * Crea un objeto QuestionDetailDTO a partir de un objeto QuestionEntity incluyendo los atributos de QuestionDTO.
     *
     * @param entity Entidad QuestionEntity desde la cual se va a crear el nuevo objeto.
     * @author af.osorio10
     */
    public QuestionDetailDTO(QuestionEntity entity) {
        super(entity);    
        if (entity.getTrip()!=null){
        this.trip = new TripDTO(entity.getTrip());
        }
    }
    
    /**
     * Convierte un objeto QuestionDetailDTO a QuestionEntity incluyendo los atributos de QuestionDTO.
     *
     * @return Nuevo objeto QuestionEntity.
     * @author af.osorio10
     */
    @Override
    public QuestionEntity toEntity() {
        QuestionEntity entity = super.toEntity();
        if (this.getTrip()!=null){
        entity.setTrip(this.getTrip().toEntity());
        }
        return entity;
    }
    
    /**
     * Obtiene el atributo trip.
     *
     * @return atributo trip.
     * @author af.osorio10
     */
    public TripDTO getTrip() {
        return trip;
    }

    /**
     * Establece el valor del atributo trip.
     *
     * @param trip nuevo valor del atributo
     * @author af.osorio10
     */
    public void setTrip(TripDTO trip) {
        this.trip = trip;
    }
    
}
