import kr.ac.hansung.cse.dao.OfferDao;
import kr.ac.hansung.cse.model.Offer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// @Transactional어노테이션: 이 클래스의 각 메서드가 트랜잭션 단위로 실행이됨.
// testGetOfferByName()메서드의 경우 테스트 어노테이션이 붙은 테스트 메서이기이에 메서드가 끝나면 - 즉 테스트가 끝나면 자동으로 Rollback하여 원상복구. DB에 영향을 주지 않도록 함.
@Transactional
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "/dao-context.xml")
public class OfferDaoTest {

    @Autowired
    private OfferDao offerDao;

    // 테스트를 진행하기 앞서서 DB에 초기 데이터를 삽입하는 메서드
    @BeforeEach
    public void setUp() {
        Offer offer = new Offer();
        offer.setName("Test Offer");
        offer.setEmail("test@example.com");
        offer.setText("This is a test offer");
        // sql insert문을 실행하는 메서드
        offerDao.insert(offer);
    }

    // 이 메서드는 @Test 어노테이션이 붙어있기 때문에 JUnit이 이 메서드를 테스트 메서드로 인식함.
    // 이 테스트 코드의 존재이유는 insert() 와 getOffer(),getOffers(),update(),delete() 메서드가 잘 작동하는지 확인하기 위함.
    // 모든 테스트가 정상적으로 작동하면, "아, 이 OfferDAO는 잘 작동하는구나"라고 판단할 수 있다.
    // 즉, DAO 계층의 CRUD 메서드들이 잘 작동하는지 확인하기 위한 테스트 코드이다.
    // 이 Unit Test(단위 테스트)는 JUnit5의 Jupiter에다가 SpringExtension을 붙여서 스프링과 JUnit5를 통합하여 테스트하는 것이다.
    @Test
    @DisplayName("Test1: testGetOfferByName")
    public void testGetOfferByName() {
        // sql select문을 실행하는 메서드
        Offer offer = offerDao.getOffer("Test Offer");
        // 가져온 Offer 객체가 null이 아니고, name이 "Test Offer"인지 확인.테스트해보는 거임.
        assertNotNull(offer);
        assertEquals("Test Offer", offer.getName());
    }

    @Test
    @DisplayName("Test2: testGetOfferById")
    public void testGetOfferById() {

        Offer savedOffer = offerDao.getOffer("Test Offer");

        Offer offer = offerDao.getOffer(savedOffer.getId());
        assertNotNull(offer);
        assertEquals(savedOffer.getId(), offer.getId());
    }

    @Test
    @DisplayName("Test3: testGetOffers")
    public void testGetOffers() {

        List<Offer> offers = offerDao.getOffers();
        assertNotNull(offers);
        assertFalse(offers.isEmpty());
    }

    @Test
    @DisplayName("Test4: testInsert")
    public void testInsert() {

        Offer newOffer = new Offer();
        newOffer.setName("New Offer");
        newOffer.setEmail("new@example.com");
        newOffer.setText("This is a new offer");
        offerDao.insert(newOffer);
        assertNotNull(newOffer.getId());

        Offer savedOffer = offerDao.getOffer(newOffer.getId());
        assertNotNull(savedOffer);
        assertEquals("New Offer", savedOffer.getName());
    }

    @Test
    @DisplayName("Test5: testUpdate")
    public void testUpdate() {

        Offer offer = offerDao.getOffer("Test Offer");
        assertNotNull(offer);
        offer.setText("Updated text");
        offerDao.update(offer);

        Offer updatedOffer = offerDao.getOffer(offer.getId());
        assertNotNull(updatedOffer);
        assertEquals("Updated text", updatedOffer.getText());
    }

    @Test
    @DisplayName("Test6: testDelete")
    public void testDelete() {

        Offer offer = offerDao.getOffer("Test Offer");
        assertNotNull(offer);
        offerDao.delete(offer.getId());

        Offer deletedOffer = offerDao.getOffer(offer.getId());
        assertNull(deletedOffer);
    }

}
