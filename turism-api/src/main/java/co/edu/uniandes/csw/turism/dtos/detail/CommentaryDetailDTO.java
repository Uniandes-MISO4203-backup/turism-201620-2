/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turism.dtos.detail;

import co.edu.uniandes.csw.turism.dtos.minimum.*;
import co.edu.uniandes.csw.turism.entities.CommentaryEntity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author fe.ruiz
 */
public class CommentaryDetailDTO extends CommentaryDTO{

    public CommentaryDetailDTO() {
     super();
    }

    public CommentaryDetailDTO(CommentaryEntity entity) {
        super(entity);
    }

    @Override
    public CommentaryEntity toEntity() {
        CommentaryEntity entity = super.toEntity();//new CommentaryEntity();
        return entity; //To change body of generated methods, choose Tools | Templates.
    }
}

