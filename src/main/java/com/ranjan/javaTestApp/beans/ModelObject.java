package com.ranjan.javaTestApp.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
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
@Table(name = "data_model_object")
public class ModelObject {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "model_object_id")
	private Long objectId;
	@ManyToOne
	@JoinColumn(name = "model_id")
	private ModelClassBean model;
	@Column(name = "created_date")
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date createdDate;
	@Column(name = "created_by")
	private Long createdBy;

}
