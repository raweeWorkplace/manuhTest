package com.ranjan.javaTestApp.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "model_attribute")
public class ModelAttributeBean {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "model_attr_id")
	private Long  modelAttributeId;
	@Column(name = "attribute_name")
	private String attrName;
	@Column(name = "display_name")
	private String dispName;
	@Column(length = 30,nullable = false)
	private String dataType;
	@Column(name = "mandatory")
	private boolean isManditory;
	@Column(name = "is_visibility")
	private boolean isVisiblity;
	@Column(length = 500)
	private String description;
	@Column(name = "created_date")
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date createdDate;
	@Column(name = "created_by")
	private Long createdBy;
	@Column(name = "is_unique",nullable = false, columnDefinition = "boolean default true")
	private boolean isUnique;
	@Column(name = "has_child")
	private boolean hasChild;
	@ManyToOne
	@JoinColumn(name = "model_id")
	private ModelClassBean modelClass;
}
