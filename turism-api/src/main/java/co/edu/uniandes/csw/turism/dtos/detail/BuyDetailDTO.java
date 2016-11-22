/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turism.dtos.detail;

import co.edu.uniandes.csw.turism.dtos.minimum.BuyDTO;
import co.edu.uniandes.csw.turism.dtos.minimum.ProductDTO;
import co.edu.uniandes.csw.turism.entities.BuyEntity;
import javax.xml.bind.annotation.XmlRootElement;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @product martinalbarracin
 */
@XmlRootElement
public class BuyDetailDTO extends BuyDTO {
        
    @PodamExclude
    private ProductDTO product;

    /**
     * @generated
     */
    public BuyDetailDTO() {
        super();
    }

    /**
     * Crea un objeto BuyDetailDTO a partir de un objeto BuyEntity incluyendo los atributos de BookDTO.
     *
     * @param entity Entidad BuyEntity desde la cual se va a crear el nuevo objeto.
     * @generated
     */
    public BuyDetailDTO(BuyEntity entity) {
        super(entity);
        if (entity != null && entity.getProduct()!=null){
        this.product = new ProductDTO(entity.getProduct());
        }

    }

    /**
     * Convierte un objeto BuyDetailDTO a BuyEntity incluyendo los atributos de BookDTO.
     *
     * @return Nueva objeto BuyEntity.
     * @generated
     */
    @Override
    public BuyEntity toEntity() {
        BuyEntity entity = super.toEntity();
        if (this.getProduct()!=null){
        entity.setProduct(this.getProduct().toEntity());
        }
        return entity;
    }

    /**
     * Obtiene el atributo product.
     *
     * @return atributo product.
     * @generated
     */
    public ProductDTO getProduct() {
        return product;
    }

    /**
     * Establece el valor del atributo product.
     *
     * @param product nuevo valor del atributo
     * @generated
     */
    public void setProduct(ProductDTO product) {
        this.product = product;
    }
    
}
