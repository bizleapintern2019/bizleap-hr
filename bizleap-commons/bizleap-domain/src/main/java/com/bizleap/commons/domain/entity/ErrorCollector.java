package com.bizleap.commons.domain.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class ErrorCollection {
	private int lineNumber;
	private Object source;
	private String message="";
	
	public ErrorCollection() {}
	
	public ErrorCollection(int lineNumber, Object source, String message) {
		this.lineNumber = lineNumber;
		this.source = source;
		this.message = message;
	}

	public int getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}

	public Object getSource() {
		return source;
	}

	public void setSource(Object source) {
		this.source = source;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public static class Builder{
		String message;
		Object source;
		int lineNumber;
		
		public Builder setLineNumber(int lineNumber) {
			this.lineNumber = lineNumber;
			return this;
		}
		
		public Builder setSource(Object source) {
			this.source = source;
			return this;
		}
		
		public Builder setMessage(String message) {
			this.message = message;
			return this;
		}
		
		public ErrorCollection build() {
			return new ErrorCollection(lineNumber,source,message);
		}
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append("---------------------------------------------------------------"+"\n")
				.append("\t\t\t\t\tERROR\n")
				.append("---------------------------------------------------------------"+"\n")
				.append(getMessage()+"\n")
				.append("Source Object : "+getSource()+" at line number "+getLineNumber()+"\n")
				.append("---------------------------------------------------------------")
				.build();
	}
	
	
}
