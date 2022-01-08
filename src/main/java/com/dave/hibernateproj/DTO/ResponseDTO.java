package com.dave.hibernateproj.DTO;

public class ResponseDTO {
	private String status;
	private String message;
	private Object data;
	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	
//	@Override
//	public String toString() {
//		return "ResponseDTO [status=" + status + ", message=" + message + ", data=" + data + "]";
//	}
	
	
	@Override
    public String toString() {
        String response = "{" +

				"\"status\": \""+ status +"\"," +

				"\"message\": \""+ message +"\"," +
			
                "\"data\" : "+ data +"" +

                "}";


        return response;
    }

	
}
