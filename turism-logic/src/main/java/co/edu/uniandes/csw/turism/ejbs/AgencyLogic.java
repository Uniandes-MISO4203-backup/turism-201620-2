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
package co.edu.uniandes.csw.turism.ejbs;

import co.edu.uniandes.csw.turism.api.IAgencyLogic;
import co.edu.uniandes.csw.turism.entities.AgencyEntity;
import co.edu.uniandes.csw.turism.persistence.AgencyPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;

/**
 * @generated
 */
@Stateless
public class AgencyLogic implements IAgencyLogic {

    @Inject private AgencyPersistence persistence;


    /**
     * Obtiene el número de registros de Agency.
     *
     * @return Número de registros de Agency.
     * @generated
     */
    public int countAgencys() {
        return persistence.count();
    }

    /**
     * Obtiene la lista de los registros de Agency.
     *
     * @return Colección de objetos de AgencyEntity.
     * @generated
     */
    @Override
    public List<AgencyEntity> getAgencys() {
        return persistence.findAll();
    }

    /**
     * Obtiene la lista de los registros de Agency indicando los datos para la paginación.
     *
     * @param page Número de página.
     * @param maxRecords Número de registros que se mostraran en cada página.
     * @return Colección de objetos de AgencyEntity.
     * @generated
     */
    @Override
    public List<AgencyEntity> getAgencys(Integer page, Integer maxRecords) {
        return persistence.findAll(page, maxRecords);
    }

    /**
     * Obtiene los datos de una instancia de Agency a partir de su ID.
     *
     * @param id Identificador de la instancia a consultar
     * @return Instancia de AgencyEntity con los datos del Agency consultado.
     * @generated
     */
    public AgencyEntity getAgency(Long id) {
        return persistence.find(id);
    }

    /**
     * Se encarga de crear un Agency en la base de datos.
     *
     * @param entity Objeto de AgencyEntity con los datos nuevos
     * @return Objeto de AgencyEntity con los datos nuevos y su ID.
     * @generated
     */
    @Override
    public AgencyEntity createAgency(AgencyEntity entity) {
        persistence.create(entity);
        return entity;
    }

    /**
     * Actualiza la información de una instancia de Agency.
     *
     * @param entity Instancia de AgencyEntity con los nuevos datos.
     * @return Instancia de AgencyEntity con los datos actualizados.
     * @generated
     */
    @Override
    public AgencyEntity updateAgency(AgencyEntity entity) {
        return persistence.update(entity);
    }

    /**
     * Elimina una instancia de Agency de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     * @generated
     */
    @Override
    public void deleteAgency(Long id) {
        persistence.delete(id);
    }
  
}
