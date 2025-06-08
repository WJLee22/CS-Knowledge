package kr.ac.hansung.cse.productmanagementapp.repository;

import kr.ac.hansung.cse.productmanagementapp.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

// 이젠 Dao대신에 Repository로 부른다.
// 어떤 repository? => Product 엔티티에 대한 repository.
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findByName(String name);
    // findByNameContaining 메서드는 이름에 searchKeyword 특정 문자열이 포함된 모든 Product를 찾는다.
    // + 조회결과 레코드들이 많을 수도 있으니 Pageable 매개변수를 사용하여 페이징 처리를 지원한다.
    List<Product> findByNameContaining(String searchKeyword, Pageable paging);

    // used to retrieve records from the Product entity
    // where the name attribute contains a specific substring
    //JPQL(Java Persistence Query Language)을 사용한 사용자 정의 쿼리, %는 0개 이상 문자와 일치
    @Query("select p from Product p where p.name like %?1%")
    List<Product> searchByName(String name);
}
