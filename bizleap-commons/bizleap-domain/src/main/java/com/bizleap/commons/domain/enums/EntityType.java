package com.bizleap.commons.domain.enums;

	 public enum EntityType {
		LOCATION("Location","LOC"),
		DEPARTMENT("Department","Dept"),
		JOB("Job","Job"),
		POSITION("Position","Pos"),
		EMPLOYEE("Employee","emp"),
		ADDRESS("Address","add");
			
		 	private String boIdPrefix;
			private String value;
			
			EntityType(String boIdPrefix, String value) {
				this.boIdPrefix = boIdPrefix;
				this.value = value;
			}

			public String getBoIdPrefix() {
				return boIdPrefix;
			}

			public void setBoIdPrefix(String boIdPrefix) {
				this.boIdPrefix = boIdPrefix;
			}

			public String getValue() {
				return value;
			}

			public void setValue(String value) {
				this.value = value;
			}		 
		} 
