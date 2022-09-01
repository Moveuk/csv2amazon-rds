package com.yournews.newsdatasetting.repository;

import com.yournews.newsdatasetting.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {
}
