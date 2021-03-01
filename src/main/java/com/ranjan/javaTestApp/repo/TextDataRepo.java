package com.ranjan.javaTestApp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ranjan.javaTestApp.beans.TextDataBean;

public interface TextDataRepo extends JpaRepository<TextDataBean, String>{
	@Query(value = "from TextDataBean s where s.objvalue.valueId=:model_id")
	TextDataBean findbyModelObject(Long model_id);
}
