package com.bizleap.commons.domain.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public abstract class Entity {
	private String boId;
	
	public Entity() {
		
	}
	
	public Entity(String boId) {
		this.boId = boId;
	}
	
	public String getBoId() {
		return boId;
	}

	public void setBoId(String boId) {
		this.boId = boId;
	}

	public boolean isEqual(String boId) {
		return this.boId.equals(boId);
	}
	
	public boolean sameBoId(Entity entity) {
		if(entity != null)
			return this.getBoId().equals(entity.getBoId());
		return false;
	}
	
	@Override
	public String toString() {
		return "" + 
				new ToStringBuilder(this, ToStringStyle.NO_CLASS_NAME_STYLE)
				.append("boId:", getBoId());
	}
}