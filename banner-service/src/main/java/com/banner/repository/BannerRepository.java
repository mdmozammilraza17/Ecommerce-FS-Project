package com.banner.repository;

import com.banner.DTO.BannerResponseDTO;
import com.banner.Entity.BannerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BannerRepository extends JpaRepository<BannerEntity, Long> {
    boolean existsByDisplayOrder(Integer displayOrder);
    List<BannerEntity> findAllByOrderByDisplayOrderAsc();
}
