package com.hamidur.springBootRESTfulWebservices.repos;

import com.hamidur.springBootRESTfulWebservices.models.Evaluation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface EvaluationRepository extends CrudRepository<Evaluation, Integer> {
    @Query(value = "select * from evaluations where customer_id = :customer_id", nativeQuery = true)
    Set<Evaluation> findAllEvaluationsByCustomerId(@Param("customer_id") Integer customerId);
}
