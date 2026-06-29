package com.banner.repository;

import com.banner.Entity.BannerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BannerRepository extends JpaRepository<BannerEntity, Long> {
    boolean existsByDisplayOrder(Integer displayOrder);
}
