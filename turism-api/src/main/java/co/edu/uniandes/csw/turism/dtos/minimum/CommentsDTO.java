/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turism.dtos.minimum;

import co.edu.uniandes.csw.turism.entities.CommentsEntity;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 *
 * @author fe.ruiz
 */
@XmlRootElement
public class CommentsDTO  implements Serializable{
  private Long id ; 
  private String commentary; 

/**
* @generated
*/ 
  public CommentsDTO () 
  {
  }  
  
  public  CommentsDTO (CommentsEntity entity) {
      if (entity!=null){
        this.id=entity.getId();
        this.commentary = entity.getComentary();
       }
  }
  
  public CommentsEntity toEntity () {
      CommentsEntity entity = new CommentsEntity();
      entity.setId(this.getId());
      entity.setComentary(this.getCommentary());
      return entity;
  }
   
   public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCommentary() {
        return commentary;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }
}

