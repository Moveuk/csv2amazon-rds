package com.yournews.newsdatasetting.repository;

import com.yournews.newsdatasetting.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
