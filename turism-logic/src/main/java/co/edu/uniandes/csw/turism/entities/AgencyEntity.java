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
public class AgencyEntity extends BaseEntity implements Serializable {

    @PodamExclude
    @OneToMany(mappedBy = "agency", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TripEntity> trips = new ArrayList<>();
    private Integer number_phone;
    /**
     * Obtiene la colección de trips.
     *
     * @return colección trips.
     * @generated
     */
    public List<TripEntity> getTrips() {
        return trips;
    }

    /**
     * Establece el valor de la colección de trips.
     *
     * @param trips nuevo valor de la colección.
     * @generated
     */
    public void setTrips(List<TripEntity> trips) {
        this.trips = trips;
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
