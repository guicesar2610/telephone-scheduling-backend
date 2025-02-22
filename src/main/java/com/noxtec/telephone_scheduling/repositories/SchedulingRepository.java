package com.noxtec.telephone_scheduling.repositories;

import com.noxtec.telephone_scheduling.entities.Scheduling;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface SchedulingRepository extends JpaRepository<Scheduling, Long> {
    List<Scheduling> findByNameContaining(String name);

    Optional<Scheduling> findByCellphone(String cellphone);
}


