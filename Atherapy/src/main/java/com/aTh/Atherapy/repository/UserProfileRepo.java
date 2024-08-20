package com.aTh.Atherapy.repository;

import com.aTh.Atherapy.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepo extends JpaRepository<UserProfile, Long> {}