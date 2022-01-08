package com.dave.hibernateproj.dao.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.dave.hibernateproj.dao.CommentDAO;
import com.dave.hibernateproj.model.Comment;
import com.dave.hibernateproj.utils.DbAccessUtils;


public class CommentDAOImpl implements CommentDAO {

	static final Logger log = Logger.getLogger(CommentDAOImpl.class.getSimpleName());
	Session session;

	public void openConnection() {
		SessionFactory sessionFactory = DbAccessUtils.getSessionFactory();
		session = sessionFactory.openSession();
	}

	public void closeConnection() {
		if (session != null) {
			session.close();
		}
	}


	public Comment updateComment(Comment comment) {
		
		Transaction tx = null;

		try {
			openConnection();
			tx = session.beginTransaction();
			session.update(comment);
			tx.commit();

			return comment;
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
			log.error("Exception=>", e);
		} catch (NoResultException ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
			log.error("Exception=>", ex);
		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
			log.error("Exception=>", ex);
		} finally {
			closeConnection();
		}

		return null;

	}

	public boolean addComment(Comment comment) {
		Transaction tx = null;

		try {
			openConnection();
			tx = session.beginTransaction();
			session.save(comment);
			tx.commit();

			return true;
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
			log.error("Exception=>", e);
		} catch (NoResultException ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
			log.error("Exception=>", ex);
		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
			log.error("Exception=>", ex);
		} finally {
			closeConnection();
		}
		return false;
	}
	
	
	public boolean getCommentByMyUser(String myUser) {
		Comment comment = null;
		try {
			openConnection();

			CriteriaBuilder cb = session.getCriteriaBuilder();

			// Create Criteria against a particular persistent class
			CriteriaQuery<Comment> criteria = cb.createQuery(Comment.class);

			// Query roots always reference entitie
			Root<Comment> walletRoot = criteria.from(Comment.class);
			Predicate cond = null;
			// If both columns should match the test values
			cond = cb.and(cb.equal(walletRoot.get("myUser"), myUser));
			criteria.select(walletRoot);
			criteria.where(cond);

			// Fetch single result
			 comment = session.createQuery(criteria).getSingleResult();
			 
			 System.out.println("printing single resullt");
			 
			 System.out.println(comment);

			if (comment != null) {
				return true;
			}
		} catch (NoResultException ex) {
			log.error("Exception=>", ex);
		} catch (Exception ex) {
			log.error(ex);
		} finally {
			closeConnection();
		}
		
		return false;

	}

	@Override
	public List<Comment> getCommentList( int page, int itemsPerPage) {
		Comment commentModel = null;
		List<Comment> modelList = new ArrayList<Comment>();

		try {
			
			openConnection();


			int offset = 0;

			if (page == 0 || page == 1) {
				page = 0;
			} else {
				offset = (page - 1) * itemsPerPage;
			}
			
			

			String sql = "SELECT fb.id,fb.myUser,fb.webpage,fb.name,fb.datum,fb.summary,fb.comments FROM feedback.comment fb group by fb.id order by fb.id desc";
			List<Object[]> comments = session.createNativeQuery(sql)
					.setFirstResult(offset).setMaxResults(itemsPerPage).getResultList();

			if (comments != null && comments.size() > 0) {

				for (Object[] result : comments) {
					
					commentModel = new Comment();

					commentModel.setId(Integer.parseInt(String.valueOf(result[0])));
					commentModel.setMyUser(String.valueOf(result[1]));
					commentModel.setWebpage(String.valueOf(result[2]));
					commentModel.setName(String.valueOf(result[3]));
					commentModel.setDatum(String.valueOf(result[4]));
					commentModel.setSummary(String.valueOf(result[5]));
					commentModel.setComments(String.valueOf(result[6]));
					
					modelList.add(commentModel);
				}

//				response.setStatus("00");
//				response.setMessage("Operation Successful");
//				response.setTransactions(modelList);

			} else {
//				response.setStatus("06");
//				response.setMessage("No transactions found");
			}

		} catch (NoResultException e) {
//			response.setStatus("06");
//			response.setMessage("No transactions found");
			log.error("Exception=>", e);
		} catch (Exception ex) {
//			response.setStatus("99");
//			response.setMessage("Unable to process request");
			ex.printStackTrace();
			log.error("Exception=>", ex);
		} finally {
			closeConnection();
		}

		return modelList;
	}
	
	
	@Override
	public boolean deleteTransaction(Comment comment) {
		Transaction tx = null;

		try {
			openConnection();
			tx = session.beginTransaction();
			session.delete(comment);
			tx.commit();
			return true;
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
			log.error("Exception=>", e);
		} catch (NoResultException ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
			log.error("Exception=>", ex);
		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
			log.error("Exception=>", ex);
		} finally {
			closeConnection();
		}
		
		return false;
	}

	@Override
	public Comment findById(Integer id) {
		
		try {
			openConnection();

			CriteriaBuilder cb = session.getCriteriaBuilder();

			// Create Criteria against a particular persistent class
			CriteriaQuery<Comment> criteria = cb.createQuery(Comment.class);

			// Query roots always reference entitie
			Root<Comment> commentRoot = criteria.from(Comment.class);
			criteria.select(commentRoot);
			criteria.where(cb.equal(commentRoot.get("id"), id));

			// Fetch single result
			Comment comment = session.createQuery(criteria).getSingleResult();

			if (comment != null) {
				return comment;
			}

		} catch (NoResultException e) {
			log.error("Exception=>", e);
		} catch (Exception ex) {
			log.error("Exception=>", ex);
		} finally {
			closeConnection();
		}

		return null;
	}
	





}
