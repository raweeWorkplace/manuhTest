package com.ranjan.javaTestApp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ranjan.javaTestApp.beans.ModelClassBean;

public interface ModelClassRepo extends JpaRepository<ModelClassBean, Long>{

}
