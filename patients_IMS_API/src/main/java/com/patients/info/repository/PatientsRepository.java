package com.patients.info.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.patients.info.model.PatientsModel;

@Repository
public interface PatientsRepository extends CrudRepository<PatientsModel, Long> {

}
