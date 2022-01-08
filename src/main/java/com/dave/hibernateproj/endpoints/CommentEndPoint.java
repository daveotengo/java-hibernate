package com.dave.hibernateproj.endpoints;

import java.util.List;
import java.util.Objects;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONObject;
import org.modelmapper.ModelMapper;

import com.dave.hibernateproj.DTO.CommentDTO;
import com.dave.hibernateproj.DTO.ResponseDTO;
import com.dave.hibernateproj.DTO.WSResponse;
import com.dave.hibernateproj.dao.CommentDAO;
import com.dave.hibernateproj.dao.impl.CommentDAOImpl;
import com.dave.hibernateproj.model.Comment;
import com.dave.hibernateproj.utils.ResponseMessages;
import com.dave.hibernateproj.utils.Validator;

@Path("comment")
public class CommentEndPoint {
	
	CommentDAO commentDAOService = new CommentDAOImpl();


	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getComment(@DefaultValue("0") @QueryParam("page") int page,
			@DefaultValue("20") @QueryParam("pageSize") int pageSize) {
		
		
		List<Comment> comments = commentDAOService.getCommentList(page,  pageSize );
		
		ResponseDTO rspDTO = new ResponseDTO();
		
		if(comments != null) {
			if(comments.size()>0) {
				rspDTO.setMessage("Success");
				rspDTO.setStatus("00");
				rspDTO.setData(comments);
			}else {
				rspDTO.setMessage("Failed");
				rspDTO.setStatus("06");
				rspDTO.setData(comments);
			}
		}else {
			rspDTO.setMessage("Failed");
			rspDTO.setStatus("07");
			rspDTO.setData(null);
		}
		
		return Response.status(Response.Status.OK).entity(rspDTO.toString()).build();

		
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response addComment(String payload) {

		JSONObject request = new JSONObject(payload);

	    
		// Integer id = request.optString("id").trim();
		 String name = request.optString("name").trim();
		 String myUser = request.optString("myUser").trim();
		 String webpage = request.optString("webpage").trim();
		 String datum = request.optString("datum").trim();
		 String summary = request.optString("summary").trim();
		 String comments = request.optString("comments").trim();
	    
		ResponseDTO rspDTO = new ResponseDTO();
		System.out.println("==comment==");

		System.out.println(comments);


		Comment comment = new Comment();
		comment.setName(name);
		comment.setMyUser(myUser);
		comment.setWebpage(webpage);
		comment.setDatum(datum);
		comment.setSummary(summary);
		comment.setComments(comments);
//
//		ModelMapper mm = new ModelMapper();
//		mm.map(commentDTO, comment);
		
	
		
		String[] paramsList  = new String[]{myUser, name, webpage, datum, 
				summary,comments};
        
        Validator.validateRequiredParameters(paramsList);
        
        boolean cmt = commentDAOService.getCommentByMyUser(myUser);
        
        System.out.println("printing out cmt");
        
        System.out.println(cmt);
        
        if(cmt!=true) {

			if (commentDAOService.addComment(comment)) {
				
				System.out.println("comment:");
				System.out.println(comment);
	
				rspDTO.setData(comment);
				rspDTO.setMessage("Success");
				rspDTO.setStatus("00");
				Response.status(Response.Status.CREATED).entity(rspDTO.toString()).build();
	  
			}else {
				
				rspDTO.setData(null);
				rspDTO.setMessage("Failed");
				rspDTO.setStatus("01");
				
			}
			
        }else {
        	
        	rspDTO.setData(null);
			rspDTO.setMessage("Comment already exist");
			rspDTO.setStatus("02");
			return Response.status(Response.Status.CONFLICT).entity(rspDTO.toString()).build();

			
        }

		
		return Response.status(Response.Status.OK).entity(rspDTO.toString()).build();

	}
	
	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteTransaction(@PathParam("id") Integer id) {
		
		
		Comment cmt = commentDAOService.findById(id);
		
		
		ResponseDTO rspDTO = new ResponseDTO();

		if(cmt!=null && cmt.toString().length()> 0) {
			
			boolean isDeleted =commentDAOService.deleteTransaction(cmt);

			if(isDeleted) {
				
				rspDTO.setMessage("Delete Successful");
				rspDTO.setStatus("00");
				//return Response.status(Response.Status.NO_CONTENT).entity(rspDTO.toString()).build();
	
			}else {
				
				rspDTO.setMessage("Delete Failed");
				rspDTO.setStatus("01");
				
			}
			
		}else {
			rspDTO.setMessage("Item with id: "+id+" You are trying to delete does not exist");
			rspDTO.setStatus("02");
		}
		
		return Response.status(Response.Status.OK).entity(rspDTO.toString()).build();

		
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response getCommentById(@PathParam("id") Integer id) {
		
		 ResponseDTO rspDTO = new ResponseDTO();

		 Comment cmt = commentDAOService.findById(id);
		 
		 if(cmt!=null && cmt.toString().length()>0) {

			 rspDTO.setMessage("Successful");
			 rspDTO.setStatus("00");
			 rspDTO.setData(cmt);

		 }else {
			 
			 rspDTO.setMessage("Comment with id: "+id +"does not exist");
			 rspDTO.setStatus("01");
			 
			 Response.status(Response.Status.NOT_FOUND).build();
		 }	
		 
		return Response.status(Response.Status.OK).entity(rspDTO.toString()).build();
	}
	
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateComment(Comment cmt) {
		
		 ResponseDTO rspDTO = new ResponseDTO();

		 if(cmt!=null&&cmt.toString().length()>0) {
				
			 cmt = commentDAOService.updateComment(cmt);

			 rspDTO.setMessage("Update Successful");
			 
			 rspDTO.setStatus("00");
			 
			 rspDTO.setData(cmt);

		 }else {
			 
			 rspDTO.setMessage("Update Failed");
			 
			 rspDTO.setStatus("01");
			 
		 }
		 
		return Response.status(Response.Status.OK).entity(rspDTO.toString()).build();

	}
	


}

