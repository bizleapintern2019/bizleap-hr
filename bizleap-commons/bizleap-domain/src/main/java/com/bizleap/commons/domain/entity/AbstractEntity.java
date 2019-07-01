package com.bizleap.commons.domain.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@MappedSuperclass
public abstract class AbstractEntity {
	
	@Id	//Generate Primary Key
	@GeneratedValue //auto increment
	private long id;

	private String boId;
	
//	@Column(
//	        name = "status",
//	        length = 10
//	    )
//	    @Enumerated(EnumType.STRING)
//	    @JsonView({Summary.class})
//	    private EntityStatus status;
//	
//	    public EntityStatus getStatus() {
//			return status;
//		}
//
//		public void setStatus(EntityStatus status) {
//			this.status = status;
//		}

	public AbstractEntity() {
		
	}

	public AbstractEntity(String boId) {
		this.boId = boId;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getBoId() {
		return boId;
	}

	public void setBoId(String boId) {
		this.boId = boId;
	}
	
	public boolean isBoIdRequired() {
        return this.boId == null || "-1".equals(this.getBoId());
    }

 
	public static String makeBoId(String boIdStr, int lineNumber) {
        if (lineNumber < 10) {
            return boIdStr + "0000" + lineNumber;
        } else if (lineNumber < 100 && lineNumber > 9) {
            return boIdStr + "000" + lineNumber;
        } else {
            return lineNumber < 1000 && lineNumber > 99 ? boIdStr + "00" + lineNumber : boIdStr + "0" + lineNumber;
        }
    }
	
	public boolean isEqual(String boId) {
		return this.boId.equals(boId);
	}
	
	public boolean sameBoId(AbstractEntity entity) {
		if(entity!=null)
			return this.getBoId().equals(entity.getBoId());
		return false;
	}
	
	@Override
	public String toString() {
		return ""+new ToStringBuilder(this,ToStringStyle.NO_CLASS_NAME_STYLE)
				.append("boId :", getBoId());
				//.append("Status :",getStatus());
				
	}
}