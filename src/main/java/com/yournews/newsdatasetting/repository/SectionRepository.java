package com.yournews.newsdatasetting.repository;

import com.yournews.newsdatasetting.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SectionRepository extends JpaRepository<Section, Long> {
}
