package com.ranjan.javaTestApp.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "number_data_value")
public class NumberDataBean {

	@Id
	@Column(length = 75)
	private String id;
	private Long dataValue;
	@ManyToOne
	@JoinColumn(name = "model_object_value_id")
	private ModelObjectValue objvalue;
}
