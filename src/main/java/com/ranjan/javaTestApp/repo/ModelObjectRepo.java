package com.ranjan.javaTestApp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ranjan.javaTestApp.beans.ModelObject;

public interface ModelObjectRepo extends JpaRepository<ModelObject, Long>{

}
