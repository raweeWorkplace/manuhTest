package com.ranjan.javaTestApp.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@JsonInclude(value = Include.NON_NULL)
public class ModelClassDTO {
	@JsonProperty(value = "ID")
	private String modelId;
	@JsonProperty(value = "ENTITY_NAME")
	private String entityName;
	@JsonProperty(value = "DISPLAY_NAME")
	private String displayName;
	@JsonProperty(value = "DESCRIPTION")
	private String description;
	@JsonProperty(value = "CREATED_BY")
	private String createdBy;
	@JsonProperty(value = "PARENT_ID")
	private String parent;
	@JsonProperty(value = "STATUS")
	private boolean status = true;
	@JsonProperty(value = "ATTRIBUTE_LIST")
	private List<ModelAttributeDTO> attribute = null;

	public ModelClassDTO(List<ModelAttributeDTO> attribute) {
		this.attribute = attribute;
	}
}
