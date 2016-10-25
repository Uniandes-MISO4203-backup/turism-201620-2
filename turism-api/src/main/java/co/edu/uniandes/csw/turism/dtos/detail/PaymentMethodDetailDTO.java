/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turism.dtos.detail;

import co.edu.uniandes.csw.turism.dtos.minimum.ClientDTO;
import co.edu.uniandes.csw.turism.dtos.minimum.PaymentMethodDTO;
import co.edu.uniandes.csw.turism.entities.PaymentMethodEntity;
import javax.xml.bind.annotation.XmlRootElement;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author jd.cepeda
 */
@XmlRootElement
public class PaymentMethodDetailDTO extends PaymentMethodDTO {

    @PodamExclude
    private ClientDTO client;

    public PaymentMethodDetailDTO() {
        super();
    }

    public PaymentMethodDetailDTO(PaymentMethodEntity entity) {
        super(entity);
        if (entity.getClient() != null) {
            this.client = new ClientDTO(entity.getClient());
        }

    }

    @Override
    public PaymentMethodEntity toEntity() {
        PaymentMethodEntity entity = super.toEntity();
        if (this.getClient() != null) {
            entity.setClient(this.getClient().toEntity());
        }
        return entity;
    }

    public ClientDTO getClient() {
        return client;
    }

    public void setClient(ClientDTO client) {
        this.client = client;
    }

}
