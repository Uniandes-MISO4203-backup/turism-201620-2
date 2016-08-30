/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turism.api; 

import co.edu.uniandes.csw.turism.entities.CommentsEntity;
import java.util.List;
/**
 *
 * @author fe.ruiz
 */
public interface ICommentsLogic {
     public int countComments();
    public List<CommentsEntity> getComments();
    public List<CommentsEntity> getComments(Integer page, Integer maxRecords);
    public CommentsEntity getComment(Long id);
    public CommentsEntity createComment(CommentsEntity entity); 
    public CommentsEntity updateComment(CommentsEntity entity);
    public void deleteComment(Long id);
}
