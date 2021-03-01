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

import org.hibernate.annotations.Type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "model_name")
public class ModelClassBean {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "model_id")
	private Long modelId;
	@Column(name = "entity_name")
	private String entityName;
	@Column(name = "display_name")
	private String displayName;
	@Type(type="text")
	private String description;
	@Column(nullable = false, columnDefinition = "boolean default true")
	private boolean status;
	@Column(name = "created_by")
	private Long createdBy;
	@Column(name = "created_date")
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date createdDate;
	@ManyToOne
	@JoinColumn(name = "parent_id")
	private ModelClassBean parent;
	
}
