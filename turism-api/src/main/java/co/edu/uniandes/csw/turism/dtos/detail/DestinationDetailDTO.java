/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turism.dtos.detail;

import co.edu.uniandes.csw.turism.dtos.minimum.DestinationDTO;
import co.edu.uniandes.csw.turism.dtos.minimum.TripDTO;
import co.edu.uniandes.csw.turism.entities.DestinationEntity;
import javax.xml.bind.annotation.XmlRootElement;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author jd.cepeda
 */
@XmlRootElement
public class DestinationDetailDTO extends DestinationDTO {

    @PodamExclude
    private TripDTO trip;

    public DestinationDetailDTO() {
        super();
    }

    public DestinationDetailDTO(DestinationEntity entity) {
        super(entity);
        if (entity.getTrip() != null) {
            this.trip = new TripDTO(entity.getTrip());
        }

    }

    @Override
    public DestinationEntity toEntity() {
        DestinationEntity entity = super.toEntity();
        if (this.getTrip() != null) {
            entity.setTrip(this.getTrip().toEntity());
        }
        return entity;
    }

    public TripDTO getTrip() {
        return trip;
    }

    public void setTrip(TripDTO trip) {
        this.trip = trip;
    }
}