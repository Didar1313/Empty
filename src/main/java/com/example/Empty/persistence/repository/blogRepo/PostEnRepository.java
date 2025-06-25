package com.example.Empty.persistence.repository.blogRepo;

import com.example.Empty.persistence.blogEntity.PostEn;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostEnRepository extends JpaRepository<PostEn, Long> {
}
