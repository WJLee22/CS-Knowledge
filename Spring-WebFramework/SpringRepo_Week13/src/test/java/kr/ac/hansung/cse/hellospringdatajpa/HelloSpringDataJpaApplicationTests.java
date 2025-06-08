package kr.ac.hansung.cse.hellospringdatajpa;

import kr.ac.hansung.cse.hellospringdatajpa.entity.Product;
import kr.ac.hansung.cse.hellospringdatajpa.repository.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest // 스프링 컨테이너 기반 통합 테스트 수행
@Transactional // 각각의 테스트들이 트랜잭션 단위로 실행되어, 각 테스트가 끝나면 다시 롤백된다. 즉 레코드 삽입테스트의 경우엔 테스트 후 데이터가 DB에 남지 않는다.
class HelloSpringDataJpaApplicationTests {

    @Autowired
    private ProductRepository productRepository;

    private static final Logger logger = LoggerFactory.getLogger(HelloSpringDataJpaApplicationTests.class);

    @Test
    void contextLoads() {
    }

    // Test1: findProductById
    @Test
    @DisplayName("Test1: findProductById")
    public void findProductById() {

        // Optional 객체:  null 값을 처리할 때 발생할 수 있는 NullPointerException을 방지하기위해 Java 8에서 도입된 기능이다.
        // 값이 있으면 해당 값을 포함하는 옵셔널 객체를 리턴하고, 값이 없으면 즉 null이라면 비어 있는 옵셔널 객체를 리턴한다.
        // id가 1인 Product를 찾는다.
        Optional<Product> product = productRepository.findById(1L);
        // Optional 객체가 비어 있지 않은지 확인.
        // isPresent() 메서드는 Optional 객체가 값을 가지고 있는지 여부를 확인한다.
        // 값이 있으면 true, 없으면 false를 반환한다.
        // assertTrue는 JUnit에서 제공하는 단언 메서드로, 첫 번째 인자가 true일 때 테스트가 성공한다.
        assertTrue(product.isPresent(), "Product should be present");

        //lambda expression, parameters -> { statements; }, 익명 함수를 간결하게 작성할 수 있는 기능
        // 즉, id가 1인 Product가 존재한다면, 해당 Product를 로깅한다.
        product.ifPresent(p -> {
            logger.info("Product found: {}", p);
        });

    }

    @Test
    @DisplayName("Test2: findAllProducts")
    public void findAllProducts() {

        List<Product> products = productRepository.findAll();
        assertNotNull(products);
        products.forEach(p -> logger.info("Product found: {}", p));

    }

    // Test3: createProduct
    @Test
    @DisplayName("Test3: createProduct")
    public void createProduct() {

        Product product = new Product("OLED TV", "LG전자", "korea", 300.0);
        Product savedProduct = productRepository.save(product);

        Optional<Product> newProduct = productRepository.findById(savedProduct.getId());
        assertTrue(newProduct.isPresent(), "Product should be present");

        newProduct.ifPresent(p -> {
            logger.info("Product created: {}", p);
            assertEquals("OLED TV", p.getName());
        });
    }

    // Test4: findByName
    @Test
    @DisplayName("Test4: findByName")
    public void findByName() {

        Product product = productRepository.findByName("Galaxy S21");
        assertEquals("Galaxy S21", product.getName());
    }

    // Test5: findByNameContainingWithPaging
    @Test
    @DisplayName("Test5: findByNameContainingWithPaging")
    public void findByNameContainingWithPaging() {

        Pageable paging = PageRequest.of(0, 3);
        List<Product> productList = productRepository.findByNameContaining("MacBook", paging);

        logger.info("====findByNameContainingWithPaging: MacBook=====");
        productList.forEach(product -> logger.info("--> {}", product));

        assertEquals(3, productList.size(), "Expected 3 products containing 'MacBook'");

    }

    // Test6: findByNameContainingWithPagingAndSort
    @Test
    @DisplayName("Test6: findByNameContainingWithPagingAndSort")
    public void findByNameContainingWithPagingAndSort( ) {

        Pageable paging = PageRequest.of(0, 3, Sort.Direction.DESC, "id");

        List<Product> productList =
                productRepository.findByNameContaining("Galaxy", paging);

        logger.info("===findByNameContainingWithPagingAndSort: Galaxy====");
        productList.forEach(product -> logger.info("--> {}", product));

        assertEquals(3, productList.size(), "Expected 3 products containing 'Galaxy'");

    }

    // @Query Annotation
    // Test7: searchByNameUsingQuery
    @Test
    @DisplayName("Test7: searchByNameUsingQuery")
    public void searchByNameUsingQuery() {
        List<Product> productList= productRepository.searchByName("Air");

        logger.info("====searchByNameUsingQuery: Air====");
        productList.forEach(product -> logger.info("--> {}", product));

        assertEquals(6, productList.size(), "Expected 6 product containing 'Air'");
    }

}
