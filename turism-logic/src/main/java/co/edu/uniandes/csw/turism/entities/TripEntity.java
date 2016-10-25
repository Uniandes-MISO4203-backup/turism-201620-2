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
import uk.co.jemos.podam.common.PodamExclude;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.ArrayList;
import javax.persistence.CascadeType;

/**
 * @generated
 */
@Entity
public class TripEntity extends BaseEntity implements Serializable {

    private String image;

    private Long price;
    
    private Date departureDate;
    
    private String destination;
    
    private int quota;
    
    private int duration;
    
    private String transport;
    
    private boolean promotion ;

    

    @PodamExclude
    @ManyToOne
    private AgencyEntity agency;

    @PodamExclude
    @OneToMany
    private List<CategoryEntity> category = new ArrayList<>();

    @PodamExclude
    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<QuestionEntity> questions = new ArrayList<>();
    private List<CommentaryEntity> commentarys = new ArrayList<>();
    
    /**
     * Obtiene el atributo image.
     *
     * @return atributo image.
     * @generated
     */
    public String getImage(){
        return image;
    }

    /**
     * Establece el valor del atributo image.
     *
     * @param image nuevo valor del atributo
     * @generated
     */
    public void setImage(String image){
        this.image = image;
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
    
    /**
     * Obtiene el atributo departureDate.
     *
     * @return atributo departureDate.
     * @generated
     */
    public Date getDepartureDate() {
        return departureDate;
    }

    /**
     * Establece el valor del atributo departureDate.
     *
     * @param departureDate nuevo valor del atributo
     * @generated
     */
    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }
    
    /**
     * Obtiene el atributo destination.
     *
     * @return atributo destination.
     * @generated
     */
    public String getDestination() {
        return destination;
    }

    /**
     * Establece el valor del atributo destination.
     *
     * @param destination nuevo valor del atributo
     * @generated
     */
    public void setDestination(String destination) {
        this.destination = destination;
    }

    /**
     * Obtiene el atributo quota.
     *
     * @return atributo quota.
     * @generated
     */
    public int getQuota() {
        return quota;
    }

    /**
     * Establece el valor del atributo quota.
     *
     * @param quota nuevo valor del atributo
     * @generated
     */
    public void setQuota(int quota) {
        this.quota = quota;
    }

    /**
     * Obtiene el atributo duration.
     *
     * @return atributo duration.
     * @generated
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Establece el valor del atributo duration.
     *
     * @param duration nuevo valor del atributo
     * @generated
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * Obtiene el atributo transport.
     *
     * @return atributo transport.
     * @generated
     */
    public String getTransport() {
        return transport;
    }

    /**
     * Establece el valor del atributo transport.
     *
     * @param transport nuevo valor del atributo
     * @generated
     */
    public void setTransport(String transport) {
        this.transport = transport;
    }

    /**
     * Obtiene el atributo agency.
     *
     * @return atributo agency.
     * @generated
     */
    public AgencyEntity getAgency() {
        return agency;
    }

    /**
     * Establece el valor del atributo agency.
     *
     * @param agency nuevo valor del atributo
     * @generated
     */
    public void setAgency(AgencyEntity agency) {
        this.agency = agency;
    }

    /**
     * Obtiene la colección de category.
     *
     * @return colección category.
     * @generated
     */
    public List<CategoryEntity> getCategory() {
        return category;
    }

    /**
     * Establece el valor de la colección de category.
     *
     * @param category nuevo valor de la colección.
     * @generated
     */
    public void setCategory(List<CategoryEntity> category) {
        this.category = category;
    }
    
    /**
     * Obtiene la colección de questions.
     *
     * @return colección questions.
     * @generated
     */
    public List<QuestionEntity> getQuestions() {
        return questions;
    }

    /**
     * Establece el valor de la colección de questions.
     *
     * @param questions nuevo valor de la colección.
     * @generated
     */
    public void setQuestions(List<QuestionEntity> questions) {
        this.questions = questions;
    }
    
    /**
     * Obtener valor boolean  promocion 
     * @return 
     */
    public boolean getPromotion() {
        return promotion;
    }

    /**
     * Asignar valor si en promosion  
     * @param promotion 
     */
    public void setPromotion(boolean promotion) {
        this.promotion = promotion;
    }
    
    
    /**
     * Obtiene la colección de commentarys.
     *
     * @return colección commentarys.
     * @generated
     */
    public List<CommentaryEntity> getCommentarys() {
        return commentarys;
    }

    /**
     * Establece el valor de la colección de questions.
     *
     * @param commentarys 
     * @generated
     */
    public void setCommentars(List<CommentaryEntity> commentarys) {
        this.commentarys = commentarys;
    }
}
