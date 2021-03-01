package com.ranjan.javaTestApp.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ranjan.javaTestApp.beans.ModelObjectValue;

public interface ModelObjectValueRepo extends JpaRepository<ModelObjectValue, Long>{
	
	@Query("from ModelObjectValue m where m.modelObj.objectId=:modelObj")
	List<ModelObjectValue> findByModelObject(Long modelObj);

}
