package com.yournews.newsdatasetting.repository;

import com.yournews.newsdatasetting.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Long> {
}
