package com.ranjan.javaTestApp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ranjan.javaTestApp.beans.DateDataBean;

public interface DateDataRepo extends JpaRepository<DateDataBean, String>{
	
	@Query(value = "from DateDataBean s where s.objvalue.valueId=:model_id")
	DateDataBean findbyModelObject(Long model_id);
}
