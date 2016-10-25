/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turism.dtos.minimum;

import co.edu.uniandes.csw.turism.entities.QuestionEntity;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 *
 * @author af.osorio10
 */
@XmlRootElement
public class QuestionDTO implements Serializable{
    
    private Long id;
    private String name;
    private String respuesta;
    
    /**
     * @generated
     */
    public QuestionDTO() {
        // Método auto-generado
    }

    /**
     * Crea un objeto QuestionDTO a partir de un objeto QuestionEntity.
     *
     * @param entity Entidad QuestionEntity desde la cual se va a crear el nuevo objeto.
     * @generated
     */
    public QuestionDTO(QuestionEntity entity) {
	if (entity!=null){
        this.id=entity.getId();
        this.name=entity.getName();
        this.respuesta=entity.getRespuesta();
       }
    }

    /**
     * Convierte un objeto QuestionDTO a QuestionEntity.
     *
     * @return Nueva objeto QuestionEntity.
     * @generated
     */
    public QuestionEntity toEntity() {
        QuestionEntity entity = new QuestionEntity();
        entity.setId(this.getId());
        entity.setName(this.getName());
        entity.setRespuesta(this.getRespuesta());
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
     * Obtiene el atributo name.
     *
     * @return atributo name.
     * @generated
     */
    public String getName() {
        return name;
    }

    /**
     * Establece el valor del atributo name.
     *
     * @param name nuevo valor del atributo
     * @generated
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtiene el atributo respuesta.
     *
     * @return atributo respuesta.
     * @generated
     */
    public String getRespuesta() {
        return respuesta;
    }

    /**
     * Establece el valor del atributo respuesta.
     *
     * @param respuesta nuevo valor del atributo
     * @generated
     */
    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }         
    
}
