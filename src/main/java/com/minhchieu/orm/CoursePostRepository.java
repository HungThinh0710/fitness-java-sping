package com.minhchieu.orm;

import com.minhchieu.model.CoursePost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoursePostRepository extends JpaRepository<CoursePost, Long> {
}
