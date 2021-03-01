package com.ranjan.javaTestApp.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ranjan.javaTestApp.beans.ModelAttributeBean;

public interface ModelAttributeRepo extends JpaRepository<ModelAttributeBean, Long> {

	@Query(value = "from ModelAttributeBean p where p.modelClass.modelId =:model_id")
	List<ModelAttributeBean> getAllByModel(@Param("model_id") Long model_id);

	// @Query(value = "from ModelAttributeBean p where p.modelClass.modelId
	// =:model_id")
	ModelAttributeBean findByDispName(String dispName);

}
