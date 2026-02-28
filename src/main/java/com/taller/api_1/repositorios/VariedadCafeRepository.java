package com.taller.api_1.repositorios;

import com.taller.api_1.entidades.VariedadCafe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VariedadCafeRepository extends JpaRepository<VariedadCafe, Long> {
    // No requiere código manual, Spring Data lo genera por ti.
}
