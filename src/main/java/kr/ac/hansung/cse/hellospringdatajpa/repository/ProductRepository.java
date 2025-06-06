package kr.ac.hansung.cse.hellospringdatajpa.repository;

import kr.ac.hansung.cse.hellospringdatajpa.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

// 이젠 Dao대신에 Repository로 부른다.
// 어떤 repository? => Product 엔티티에 대한 repository.
public interface ProductRepository extends JpaRepository<Product, Long> {

}
