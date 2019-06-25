package com.bizleap.commons.domain.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@MappedSuperclass
public abstract class AbstractEntity {
	@Id
	@GeneratedValue
	private long id;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	private String boId;
	
	public AbstractEntity() {
	
	}

	public AbstractEntity(String boId) {
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
	
	public boolean sameBoId(AbstractEntity entity) {
		if(entity!=null)
			return this.getBoId().equals(entity.getBoId());
		return false;
	}

//	@Override
//	public String toString() {
//		return "AbstractEntity [boId=" + boId + "]";
//	}

	@Override
	public String toString() {
		return ""+new ToStringBuilder(this,ToStringStyle.NO_CLASS_NAME_STYLE)
				.append("boId :", getBoId());
	}
}
