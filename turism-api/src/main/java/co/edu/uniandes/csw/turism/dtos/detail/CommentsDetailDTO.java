/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turism.dtos.detail;

import co.edu.uniandes.csw.turism.dtos.minimum.*;
import co.edu.uniandes.csw.turism.entities.CommentsEntity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author fe.ruiz
 */
public class CommentsDetailDTO extends CommentsDTO{

    public CommentsDetailDTO() {
     super();
    }

    public CommentsDetailDTO(CommentsEntity entity) {
        super(entity);
    }

    @Override
    public CommentsEntity toEntity() {
        CommentsEntity entity = new CommentsEntity();
        return entity; //To change body of generated methods, choose Tools | Templates.
    }
}

