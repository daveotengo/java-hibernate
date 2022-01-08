package com.dave.hibernateproj.DTO;

import javax.validation.constraints.NotNull;

public class CommentDTO {
	

	private Integer id;

	private String name;

	private String myUser;
	private String webpage;
	private String datum;
	private String summary;
	private String comments;
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMyUser() {
		return myUser;
	}
	public void setMyUser(String myUser) {
		this.myUser = myUser;
	}
	public String getWebpage() {
		return webpage;
	}
	public void setWebpage(String webpage) {
		this.webpage = webpage;
	}
	public String getDatum() {
		return datum;
	}
	public void setDatum(String datum) {
		this.datum = datum;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	
	
	@Override
    public String toString() {
        String response = "{" +

				"\"id\": \""+ id +"\"," +

				"\"name\": \""+ name +"\"," +
				"\"myUser\": \""+ myUser +"\"," +
				"\"webpage\": \""+ webpage +"\"," +
				"\"datum\": \""+ datum +"\"," +
				"\"summary\": \""+ summary +"\"," +

                "\"comments\" : \""+ comments +"\"" +

                "}";


        return response;
    }

}
