package com.example.demo.repositories;


import com.example.demo.models.Matrix;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatrixRepository extends MongoRepository<Matrix, String> {
}
