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
package co.edu.uniandes.csw.turism.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import co.edu.uniandes.csw.crud.spi.entity.BaseEntity;
import uk.co.jemos.podam.common.PodamExclude;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.ArrayList;
import javax.persistence.CascadeType;

/**
 * @generated
 */
@Entity
public class ClientEntity extends BaseEntity implements Serializable {

    @PodamExclude
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemEntity> wishList = new ArrayList<>();
    
    @PodamExclude
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PaymentMethodEntity> paymentMethods = new ArrayList<>();
    
    private String middleName;
    
    private String lastName;
    
    private String email;
    
    private String phoneNumber;
    
    private String address;
    
    private String photo;

    /**
     * Obtiene la colección de wishList.
     *
     * @return colección wishList.
     * @generated
     */
    public List<ItemEntity> getWishList() {
        return wishList;
    }

    /**
     * Establece el valor de la colección de wishList.
     *
     * @param wishList nuevo valor de la colección.
     * @generated
     */
    public void setWishList(List<ItemEntity> wishList) {
        this.wishList = wishList;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
    
    /**
     * Obtiene el atributo address.
     *
     * @return artributo address.
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
    
    public List<PaymentMethodEntity> getPaymentMethods() {
        return paymentMethods;
    }

    public void setPaymentMethods(List<PaymentMethodEntity> contacts) {
        this.paymentMethods = contacts;
    }
}
