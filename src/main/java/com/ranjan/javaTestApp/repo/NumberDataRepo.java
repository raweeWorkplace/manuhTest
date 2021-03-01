package com.ranjan.javaTestApp.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ranjan.javaTestApp.beans.NumberDataBean;

public interface NumberDataRepo extends JpaRepository<NumberDataBean, String> {
	@Query(value = "from NumberDataBean s where s.objvalue.valueId=:model_id")
	NumberDataBean findbyModelObject(Long model_id);

	@Query(value = "from NumberDataBean s where s.objvalue.valueId=:model_id")
	List<NumberDataBean> findAllbyModelObject(Long model_id);
}
