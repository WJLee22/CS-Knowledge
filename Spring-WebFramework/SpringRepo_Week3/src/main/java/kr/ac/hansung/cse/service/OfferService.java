package kr.ac.hansung.cse.service;

import kr.ac.hansung.cse.dao.OfferDao;
import kr.ac.hansung.cse.model.Offer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfferService {
    //service는 비지니스 로직을 수행하는 계층이다.
    //service -> DAO
    @Autowired // 한 디스패쳐서블릿이 포함하고있는 스프링 컨테이너안의 OfferDao라는 이름의 Bean을 찾아서 의존성 주입받는다.
    private OfferDao offerDao;

    public List<Offer> getAllOffers() {
        // DAO를 호출하여 DB에서 모든 오퍼를 가져온다. 데이터베이스에서 가져온 Offer객체 리스트를 Controller에게 리턴해준다.
        // DAO는 데이터베이스와의 상호작용을 담당하는 계층이다.
        // DAO는 데이터베이스에 직접 접근하여 데이터를 가져오고, 그 결과를 Service에게 리턴한다.
        // Service는 DAO를 호출하여 비지니스 로직을 수행하고, 그 결과를 Controller에게 리턴한다.
        // Controller는 Service를 호출하여 비지니스 로직을 수행하고, 그 결과를 View에게 전달한다.
        // 즉, DAO가 DB에서 데이터를 가져오고, Service가 그 데이터를 가공하는 등 비지니스 로직 수행 후 로직 결과를 Controller에게 전달하고, Controller가 그 결과데이터를 모델에 담은후, 그 모델객체를 통째로 View에게 전달하는 구조이다.
        return offerDao.getOffers();
    }

    public void insertOffer(Offer offer) {
        offerDao.insert(offer);
    }
}
