/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turism.ejbs;

import co.edu.uniandes.csw.turism.api.IBuyLogic;
import co.edu.uniandes.csw.turism.entities.BuyEntity;
import co.edu.uniandes.csw.turism.persistence.BuyPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author martinalbarracin
 */
@Stateless
public class BuyLogic implements IBuyLogic {
        
    @Inject
    private BuyPersistence persistence;

    @Override
    public int countBuys() {
        return persistence.count();
    }

    @Override
    public List<BuyEntity> getBuys() {
        return persistence.findAll();
    }

    @Override
    public List<BuyEntity> getBuys(Integer page, Integer maxRecords) {
        return persistence.findAll(page, maxRecords);
    }

    @Override
    public BuyEntity getBuy(Long id) {
        return persistence.find(id);
    }

    @Override
    public BuyEntity createBuy(BuyEntity entity) {
        persistence.create(entity);
        return entity;
    }

    @Override
    public BuyEntity updateBuy(BuyEntity entity) {
        return persistence.update(entity);
    }

    @Override
    public void deleteBuy(Long id) {
        persistence.delete(id);
    }
    
}
