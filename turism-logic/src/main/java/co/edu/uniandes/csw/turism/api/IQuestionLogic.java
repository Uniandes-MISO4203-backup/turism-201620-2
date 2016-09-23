/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turism.api;

import co.edu.uniandes.csw.turism.entities.QuestionEntity;
import java.util.List;

/**
 *
 * @author af.osorio10
 */
public interface IQuestionLogic {
    
    public int countQuestions();
    public List<QuestionEntity> getQuestions(Long tripid);
    public List<QuestionEntity> getQuestions(Integer page, Integer maxRecords, Long tripid);
    public QuestionEntity getQuestion(Long questionid);
    public QuestionEntity createQuestion(Long tripid, QuestionEntity entity);
    public QuestionEntity updateQuestion(Long tripid, QuestionEntity entity);
    public void deleteQuestion(Long id);
    
}
