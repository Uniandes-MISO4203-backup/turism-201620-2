/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turism.api;

import co.edu.uniandes.csw.turism.entities.BuyEntity;
import java.util.List;

/**
 *
 * @author martinalbarracin
 */
public interface IBuyLogic {
    public int countBuys();
    public List<BuyEntity> getBuys();
    public List<BuyEntity> getBuys(Integer page, Integer maxRecords);
    public BuyEntity getBuy(Long id);
    public BuyEntity createBuy(BuyEntity entity);
    public BuyEntity updateBuy(BuyEntity entity);
    public void deleteBuy(Long id);
    
}
