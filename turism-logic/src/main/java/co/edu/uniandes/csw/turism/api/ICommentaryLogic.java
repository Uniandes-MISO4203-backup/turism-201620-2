/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turism.api; 

import co.edu.uniandes.csw.turism.entities.CommentaryEntity;
import java.util.List;
/**
 *
 * @author fe.ruiz
 */
public interface ICommentaryLogic {
     public int countComments();
    public List<CommentaryEntity> getComments(Long tripid);
    public List<CommentaryEntity> getComments(Integer page, Integer maxRecords, Long tripid);
    public CommentaryEntity getComment(Long id);
    public CommentaryEntity createComment(Long tripid,CommentaryEntity entity); 
    public CommentaryEntity updateComment(Long tripid,CommentaryEntity entity);
    public void deleteComment(Long id);
}

