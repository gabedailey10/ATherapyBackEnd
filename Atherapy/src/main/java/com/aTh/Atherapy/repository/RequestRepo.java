package com.aTh.Atherapy.repository;

import com.aTh.Atherapy.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepo extends JpaRepository<Request, Long> {
}
