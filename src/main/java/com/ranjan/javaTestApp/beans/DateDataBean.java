package com.ranjan.javaTestApp.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "date_data_value")
public class DateDataBean {

	@Id
	@Column(length = 75)
	private String id;
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date dataValue;
	@ManyToOne
	@JoinColumn(name = "model_object_value_id")
	private ModelObjectValue objvalue;
}
