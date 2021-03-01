package com.ranjan.javaTestApp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ranjan.javaTestApp.beans.StringDataBean;

public interface StringDataRepo extends JpaRepository<StringDataBean, String>{
	
	@Query(value = "from StringDataBean s where s.objvalue.valueId=:model_id")
	StringDataBean findbyModelObject(Long model_id);
}
