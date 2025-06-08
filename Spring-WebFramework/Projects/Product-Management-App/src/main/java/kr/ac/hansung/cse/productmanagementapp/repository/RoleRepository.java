package kr.ac.hansung.cse.productmanagementapp.repository;

import kr.ac.hansung.cse.productmanagementapp.entity.MyRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<MyRole, Integer> {
    Optional<MyRole> findByRolename(String rolename);
}