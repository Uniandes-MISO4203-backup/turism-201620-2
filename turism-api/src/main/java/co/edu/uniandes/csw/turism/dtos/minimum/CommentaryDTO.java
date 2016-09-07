/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turism.dtos.minimum;

import co.edu.uniandes.csw.turism.entities.CommentaryEntity;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 *
 * @author fe.ruiz
 */
/**
 * @generated
 */
@XmlRootElement
public class CommentaryDTO  implements Serializable{
  private Long id ; 
  private String description; 

/**
* @generated
*/ 
  public CommentaryDTO () 
  {
  }  
  
  public  CommentaryDTO (CommentaryEntity entity) {
      if (entity!=null){
        this.id=entity.getId();
        this.description = entity.getDescription();
       }
  }
  
  public CommentaryEntity toEntity () {
      CommentaryEntity entity = new CommentaryEntity();
      entity.setId(this.getId());
      entity.setDescription(this.description);
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
     * Obtiene el atributo description
     *
     * @return atributo description.
     * @generated
     */
    public String getDescription() {
        return description;
    }

     /**
     * Establece el valor del atributo description.
     *
     * @param description nuevo valor del atributo
     * @generated 
     */
    public void setDescription(String description) {
        this.description = description;
    }
}

