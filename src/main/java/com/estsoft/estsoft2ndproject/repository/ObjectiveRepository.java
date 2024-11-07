package com.estsoft.estsoft2ndproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.estsoft.estsoft2ndproject.domain.Objective;
import java.util.List;

@Repository
public interface ObjectiveRepository extends JpaRepository<Objective, Long> {
	List<Objective> findByUser_UserId(Long userId);
}
