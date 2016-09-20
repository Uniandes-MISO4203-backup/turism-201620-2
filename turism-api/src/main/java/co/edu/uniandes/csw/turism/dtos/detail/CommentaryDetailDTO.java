/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turism.dtos.detail;

import co.edu.uniandes.csw.turism.dtos.minimum.*;
import co.edu.uniandes.csw.turism.entities.CommentaryEntity;
import javax.xml.bind.annotation.XmlRootElement;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author fe.ruiz
 */
@XmlRootElement
public class CommentaryDetailDTO extends CommentaryDTO{

    @PodamExclude
    private ClientDTO client;
     
    /**
     * @generated
     */
    public CommentaryDetailDTO() {
     super();
    }

     /**
     * Crea un objeto CommentaryDetailDTO a partir de un objeto ProductEntity incluyendo los atributos de CommentaryDetailDTO.
     *
     * @param entity Entidad ProductEntity desde la cual se va a crear el nuevo objeto.
     * @generated
     */
    public CommentaryDetailDTO(CommentaryEntity entity) {
        super(entity);
        if (entity.getClient()!=null){
            this.client= new ClientDTO(entity.getClient());
        }
    }

      /**
     * Convierte un objeto  CommentaryDetailDTO a ProductEntity incluyendo los atributos de ProductDTO.
     *
     * @return Nueva objeto ProductEntity.
     * @generated
     */
    @Override
    public CommentaryEntity toEntity() {
        CommentaryEntity entity = super.toEntity();//new CommentaryEntity();
        if (this.getClient()!=null){
           entity.setClient(this.getClient().toEntity());
        }
        return entity; //To change body of generated methods, choose Tools | Templates.
    }
    
    
    public ClientDTO getClient() {
        return client;
    }

    public void setClient(ClientDTO client) {
        this.client = client;
    }

}

