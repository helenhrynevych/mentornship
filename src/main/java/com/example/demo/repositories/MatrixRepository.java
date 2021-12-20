package com.example.demo.repositories;


import com.example.demo.models.Matrix;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatrixRepository extends JpaRepository<Matrix, Long> {
}