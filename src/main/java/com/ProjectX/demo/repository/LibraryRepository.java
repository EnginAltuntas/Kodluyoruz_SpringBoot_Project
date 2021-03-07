package com.ProjectX.demo.repository;

import com.ProjectX.demo.domainObject.booksDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LibraryRepository extends JpaRepository<booksDO,Long> {
//    Optional<booksDO> findByBookname(String book_name);
}
