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

import co.edu.uniandes.csw.turism.entities.ClientEntity;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @generated
 */
@XmlRootElement
public class ClientDTO implements Serializable{

    private Long id;
    private String name;
    private String middleName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;
    private String photo;

    /**
     * @generated
     */
    public ClientDTO() {
        // Método auto-generado
    }

    /**
     * Crea un objeto ClientDTO a partir de un objeto ClientEntity.
     *
     * @param entity Entidad ClientEntity desde la cual se va a crear el nuevo objeto.
     * @generated
     */
    public ClientDTO(ClientEntity entity) {
        if (entity != null) {
            this.id = entity.getId();
            this.name = entity.getName();
            this.middleName = entity.getMiddleName();
            this.lastName = entity.getLastName();
            this.email = entity.getEmail();
            this.phoneNumber = entity.getPhoneNumber();
            this.address = entity.getAddress();
            this.photo = entity.getPhoto();
        }
    }

    /**
     * Convierte un objeto ClientDTO a ClientEntity.
     *
     * @return Nueva objeto ClientEntity.
     * @generated
     */
    public ClientEntity toEntity() {
        ClientEntity entity = new ClientEntity();
        entity.setId(this.getId());
        entity.setName(this.getName());
        entity.setMiddleName(this.getMiddleName());
        entity.setLastName(this.getLastName());
        entity.setEmail(this.getEmail());
        entity.setPhoneNumber(this.getPhoneNumber());
        entity.setAddress(this.getAddress());
        entity.setPhoto(this.getPhoto());
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
     * getMiddleName
     *
     * @return
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * setMiddleName
     *
     * @param middleName
     */
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    /**
     * getLastName
     *
     * @return
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * setLastName
     *
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * getEmail
     *
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     * setEmail
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * getPhoneNumber
     *
     * @return
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * setPhoneNumber
     *
     * @param phoneNumber
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    /**
     * Obtiene el atributo address.
     * 
     * @return atributo address
     * @generated
     */
    public String getAddress() {
        return address;
    }

    /**
     * Establece el valor del atributo address.
     *
     * @param address nuevo valor del atributo
     * @generated
     */
    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

}
