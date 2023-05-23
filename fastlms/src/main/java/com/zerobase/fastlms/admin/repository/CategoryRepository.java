package com.zerobase.fastlms.admin.repository;

import com.zerobase.fastlms.admin.entity.Category;
import com.zerobase.fastlms.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
