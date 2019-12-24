package com.vsaliy.urlshortener.dao;

import com.vsaliy.urlshortener.model.Statistica;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatisticaRepository extends MongoRepository<Statistica, String> {

}
