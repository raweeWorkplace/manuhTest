package com.ranjan.javaTestApp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ModelAttributeDTO {
	@JsonProperty(value = "ID")
	private String modelAttributeId;
	@JsonProperty(value = "ATTRIBUTE_NAME")
	private String attrName;
	@JsonProperty(value = "DISPLAY_NAME")
	private String dispName;
	@JsonProperty(value = "DATE_TYPE")
	private String dataType;
	@JsonProperty(value = "MANDITORY")
	private boolean isManditory=false;
	@JsonProperty(value = "MODEL_CLASS_ID")
	private String modelClass;
	@JsonProperty(value = "IS_VISIBLE")
	private boolean isVisiblity=true;
	@JsonProperty(value = "DESCRIPTION")
	private String description;
	@JsonProperty(value = "CREATED_BY")
	private String createdBy;
	@JsonProperty(value = "UNIQUE")
	private boolean isUnique=false;
	@JsonProperty(value = "HAS_CHILD")
	private boolean hasChild=false;

}
