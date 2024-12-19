package com.projet.demo.repository;

import com.projet.demo.Entity.Jewelry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JewelryRepository extends JpaRepository<Jewelry, Long> {

    List<Jewelry> findByName(String name);
}
