package com.example.cms.model.repository;

import com.example.cms.model.entity.CourseMark;
import com.example.cms.model.entity.CourseMarkKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface CourseMarkRepository extends JpaRepository<CourseMark, CourseMarkKey> {
    @Modifying
    @Transactional
    @Query(value = "UPDATE marks m SET mark=LEAST(mark+5, 100) WHERE courseCode = :code", nativeQuery = true)
    void increaseFive(@Param("code") String code);
    // void is the return type, type first
    // List<of whatever type.


//    @Query(value = select ... where code = code)
//    List<CourseMark> getMarksByCode(code);
}

