/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turism.ejbs;

import co.edu.uniandes.csw.turism.api.IQuestionLogic;
import co.edu.uniandes.csw.turism.entities.QuestionEntity;
import co.edu.uniandes.csw.turism.persistence.QuestionPersistence;
import co.edu.uniandes.csw.turism.api.ITripLogic;
import co.edu.uniandes.csw.turism.entities.TripEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;

/**
 *
 * @author af.osorio10
 */
@Stateless
public class QuestionLogic implements IQuestionLogic{
    
    @Inject private QuestionPersistence persistence;

    @Inject
    private ITripLogic tripLogic;
    
    /**
     * Obtiene el número de registros de Question.
     *
     * @return Número de registros de Question.
     * @generated
     */
    public int countQuestions() {
        return persistence.count();
    }
    
    /**
     * Obtiene la lista de los registros de Question que pertenecen a un Trip.
     *
     * @param tripid id del Trip el cual es padre de los Questions.
     * @return Colección de objetos de QuestionEntity.
     * @generated
     */
    @Override
    public List<QuestionEntity> getQuestions(Long tripid) {
        TripEntity trip = tripLogic.getTrip(tripid);
        return trip.getQuestions();
    }
    
    /**
     * Obtiene la lista de los registros de Question que pertenecen a un Trip indicando los datos para la paginación.
     *
     * @param page Número de página.
     * @param maxRecords Número de registros que se mostraran en cada página.
     * @param tripid id del Trip el cual es padre de los Questions.
     * @return Colección de objetos de QuestionEntity.
     * @generated
     */
    @Override
    public List<QuestionEntity> getQuestions(Integer page, Integer maxRecords, Long tripid) {
        if (tripid!=null){
        return persistence.findAll(page, maxRecords, tripid);    
        }else{
        return persistence.findAll(page, maxRecords);    
        }
    }
    
    /**
     * Obtiene los datos de una instancia de Question a partir de su ID.
     *
     * @pre La existencia del elemento padre Trip se debe garantizar.
     * @param questionid) Identificador del Question a consultar
     * @return Instancia de QuestionEntity con los datos del Question consultado.
     * @generated
     */
    @Override
    public QuestionEntity getQuestion(Long questionid) {
            return persistence.find(questionid);
    }
    
    /**
     * Se encarga de crear un Question en la base de datos.
     *
     * @param entity Objeto de QuestionEntity con los datos nuevos
     * @param tripid id del Trip el cual sera padre del nuevo Question.
     * @return Objeto de QuestionEntity con los datos nuevos y su ID.
     * @generated
     */
    @Override
    public QuestionEntity createQuestion(Long tripid, QuestionEntity entity) {
        TripEntity trip = tripLogic.getTrip(tripid);
        entity.setTrip(trip);
        entity = persistence.create(entity);
        return entity;
    }
    
    /**
     * Actualiza la información de una instancia de Question.
     *
     * @param entity Instancia de QuestionEntity con los nuevos datos.
     * @param tripid id del Trip el cual sera padre del Question actualizado.
     * @return Instancia de QuestionEntity con los datos actualizados.
     * @generated
     */
    @Override
    public QuestionEntity updateQuestion(Long tripid, QuestionEntity entity) {
        TripEntity trip = tripLogic.getTrip(tripid);
        entity.setTrip(trip);
        return persistence.update(entity);
    }
    
    /**
     * Elimina una instancia de Question de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     * @param tripid id del Trip el cual es padre del Question.
     * @generated
     */
    @Override
    public void deleteQuestion(Long id) {
        QuestionEntity old = getQuestion(id);
        persistence.delete(old.getId());
    }    
}
