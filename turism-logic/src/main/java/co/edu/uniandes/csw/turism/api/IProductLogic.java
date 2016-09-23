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
productS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
package co.edu.uniandes.csw.turism.api;

import co.edu.uniandes.csw.turism.entities.BuyEntity;
import co.edu.uniandes.csw.turism.entities.ProductEntity;
import java.util.List;

public interface IProductLogic {
    public int countProducts();
    public List<ProductEntity> getProducts();
    public List<ProductEntity> getProducts(Integer page, Integer maxRecords);
    public ProductEntity getProduct(Long id);
    public ProductEntity createProduct(ProductEntity entity); 
    public ProductEntity updateProduct(ProductEntity entity);
    public void deleteProduct(Long id);
    
    
    public List<BuyEntity> listBuys(Long productId);
    public BuyEntity getBuys(Long productId, Long buysId);
    public BuyEntity addBuys(Long productId, Long buysId);
    public List<BuyEntity> replaceBuys(Long productId, List<BuyEntity> list);
    public void removeBuys(Long productId, Long buysId);
}
