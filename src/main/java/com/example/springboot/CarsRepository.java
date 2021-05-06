package com.example.springboot;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface CarsRepository extends JpaRepository<payroll.Cars,Long> {
    @Override
    <S extends payroll.Cars> S save(S entity);

    @Override
    Optional<payroll.Cars> findById(Long aLong);

    @Override
    <S extends payroll.Cars> Optional<S> findOne(Example<S> example);

    @Override
    <S extends payroll.Cars> List<S> findAll(Example<S> example);
}