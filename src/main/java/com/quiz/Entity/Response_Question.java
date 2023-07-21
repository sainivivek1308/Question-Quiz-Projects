package com.quiz.Entity;

public class Response_Question {
	private int id;
	private String response;
	
	public Response_Question(int id, String response) {
		super();
		this.id = id;
		this.response = response;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	@Override
	public String toString() {
		return "Response_Question [id=" + id + ", response=" + response + "]";
	}
	
	
}

