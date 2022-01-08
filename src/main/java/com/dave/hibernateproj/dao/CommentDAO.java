package com.dave.hibernateproj.dao;

import java.util.List;

import com.dave.hibernateproj.model.Comment;

public interface CommentDAO {
	
	public void openConnection();
    public void closeConnection();
	public boolean addComment(Comment comment);
	public Comment updateComment(Comment comment);
	public boolean getCommentByMyUser(String myUser);
	public List<Comment> getCommentList(int page, int itemsPerPage);
	public boolean deleteTransaction(Comment comment);
	public Comment findById(Integer id);

}
