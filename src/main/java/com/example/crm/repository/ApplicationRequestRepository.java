package com.example.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.crm.model.ApplicationRequest;

public interface ApplicationRequestRepository extends JpaRepository<ApplicationRequest, Long> { }
