/*
The MIT License (MIT)

Copyright (c) 2015 Los Andes University

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
package co.edu.uniandes.csw.turism.dtos.minimum;

import co.edu.uniandes.csw.turism.entities.AgencyEntity;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @generated
 */
@XmlRootElement
public class AgencyDTO implements Serializable{

    private Long id;
    private String name;
    private Integer number_phone;
    
    /**
     * @generated
     */
    public AgencyDTO() {
    }

    /**
     * Crea un objeto AgencyDTO a partir de un objeto AgencyEntity.
     *
     * @param entity Entidad AgencyEntity desde la cual se va a crear el nuevo objeto.
     * @generated
     */
    public AgencyDTO(AgencyEntity entity) {
	   if (entity!=null){
        this.id=entity.getId();
        this.name=entity.getName();
        this.number_phone=entity.getNumber_Phone();
       }
    }

    /**
     * Convierte un objeto AgencyDTO a AgencyEntity.
     *
     * @return Nueva objeto AgencyEntity.
     * @generated
     */
    public AgencyEntity toEntity() {
        AgencyEntity entity = new AgencyEntity();
        entity.setId(this.getId());
        entity.setName(this.getName());
        entity.setNumber_Phone(this.getNumber_Phone());
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
     * Obtiene el atributo number_phone.
     *
     * @return atributo number_phone.
     * @generated
     */
    public Integer getNumber_Phone() {
        return number_phone;
    }

    /**
     * Establece el valor del atributo number_phone.
     *
     * @param number_phone nuevo valor del atributo
     * @generated
     */      
    public void setNumber_Phone(Integer number_phone) {
        this.number_phone = number_phone;
    }
    
}
