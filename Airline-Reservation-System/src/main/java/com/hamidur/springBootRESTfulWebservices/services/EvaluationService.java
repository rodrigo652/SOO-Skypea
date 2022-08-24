package com.hamidur.springBootRESTfulWebservices.services;

import com.hamidur.springBootRESTfulWebservices.models.Evaluation;
import com.hamidur.springBootRESTfulWebservices.repos.EvaluationRepository;
import com.hamidur.springBootRESTfulWebservices.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class EvaluationService {
    private final EvaluationRepository evaluationRepository;

    @Autowired
    public EvaluationService(final EvaluationRepository evaluationRepository)
    {
        this.evaluationRepository = evaluationRepository;
    }

    public Set<Evaluation> getEvaluations(){
        Set<Evaluation> evaluations = new LinkedHashSet<>();
        Iterable<Evaluation> evaluationsIterable = evaluationRepository.findAll();
        evaluationsIterable.forEach(evaluations::add);
        return evaluations;
    }

    public Evaluation getEvaluationById(Integer evaluationId) throws IllegalArgumentException
    {
        if(Util.validateNumber(evaluationId))
        {
            Optional<Evaluation> evaluation = evaluationRepository.findById(evaluationId);
            return evaluation.orElse(null);
        }
        return null;
    }

    public Evaluation addEvaluation(Evaluation evaluation) throws IllegalArgumentException
    {
        if(Util.validateEvaluation(evaluation))
        {
            return evaluationRepository.save(evaluation);
        }
        return null;
    }

    public Set<Evaluation> getAllEvaluationsByCustomerId(Integer customerId) throws IllegalArgumentException
    {
        if(Util.validateNumber(customerId))
        {
            Iterable<Evaluation> iterable = evaluationRepository.findAllEvaluationsByCustomerId(customerId);
            return iterableToEvaluationSet(iterable);
        }
        return null;
    }

    private Set<Evaluation> iterableToEvaluationSet(Iterable<Evaluation> iterable)
    {
        if(iterable != null)
        {
            Set<Evaluation> evaluations = new LinkedHashSet<>();
            iterable.forEach(evaluation -> evaluations.add(evaluation));
            return evaluations;
        }
        return null;
    }
}
