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
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 * @generated
 */
@Entity
public class ProductEntity extends BaseEntity implements Serializable {

    private Long price;
    
    private Date expirationDate;
    
    
    @PodamExclude
    @OneToMany(mappedBy = "product")
    private List<BuyEntity> buys = new ArrayList<>();

    public List<BuyEntity> getBuys() {
        return buys;
    }

    public void setBuy(List<BuyEntity> buys) {
        this.buys = buys;
    }

    /**
     * Obtiene el atributo price.
     *
     * @return atributo price.
     * @generated
     */
    public Long getPrice(){
        return price;
    }

    /**
     * Establece el valor del atributo price.
     *
     * @param price nuevo valor del atributo
     * @generated
     */
    public void setPrice(Long price){
        this.price = price;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate=expirationDate;
    }

}
