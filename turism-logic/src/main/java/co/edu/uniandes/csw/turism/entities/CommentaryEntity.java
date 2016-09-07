/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turism.entities;
import java.io.Serializable;
import javax.persistence.Entity;
import co.edu.uniandes.csw.crud.spi.entity.BaseEntity;

/**
 *
 * @author fe.ruiz
 */
/**
 * @generated
 */
@Entity
public class CommentaryEntity extends BaseEntity implements Serializable{
    //private  long id ;
    private String description  ;

      /**
     * Obtiene el atributo description.
     *
     * @return atributo pdescription.
     * @generated
     */
    public String getDescription() {
        return description;
    }
    
      /**
     * establece el atributo  description.

     * @generated
     */

    public void setDescription(String description) {
        this.description = description;
    }
}
