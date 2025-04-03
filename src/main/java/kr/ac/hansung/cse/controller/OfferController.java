package kr.ac.hansung.cse.controller;

import kr.ac.hansung.cse.model.Offer;
import kr.ac.hansung.cse.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class OfferController {
    // 컨트롤러 -> Service -> DAO -> DB 이런 흐름.
    //so, 컨트롤러가 Service를 호출하기 위해서, Service를 의존성 주입받아야 함.
    // @Autowired로 의존성 주입을 받는다. 추후에 Spring이 자동으로 OfferService라는 빈을 offerService필드에다가 빈(객체)을 생성-주입해준다.

    /*    의존성 주입 흐름
        Spring은 애플리케이션 시작 시 컴포넌트 스캔을 수행.

        @Controller가 붙은 OfferController와 @Service가 붙은 OfferService를 발견하고, 각각을 싱글톤 빈으로 생성.
                (기본적으로 싱글톤 스코프로 관리.)

        OfferController의 @Autowired 필드(offerService)에 Spring이 컨테이너 내 존재하는 OfferService 빈을 자동으로 주입.
        */
    @Autowired
    private OfferService offerService;

    // offers 경로로 오는 HTTP GET 요청을 처리하는 showOffers 헨들러 메서드
    @GetMapping("/offers")
    public String showOffers(Model model) {
        //컨트롤러는 서비스를 호출한다. getAllOffers() 메서드를 호출해서 DB에서 모든 오퍼.제안들을 가져온다.
        // OfferService 서비스의 getAllOffers() 비지니스 로직을 실행하여 모든 오퍼를 가져온다.
        //즉, 이 offerService.getAllOffers() 구문은 Service Layer를 호출하고 그 비지니스 로직 수행 결과를 가져오는 것이다.
        List<Offer> offers = offerService.getAllOffers();
        // Model 객체에다가, 서비스 계층에서 수행된 결과값인 오퍼 리스트 데이터를 모델 속성(Model Attribute)으로써 저장한다.
        model.addAttribute("id_offers", offers);
        // 이 모델을 offers라는 뷰에 전달한다. 그럼 offers라는 뷰는 이 모델에 들어있는 결과물을 끄집어내서 사용하여 HTML을 렌더링한다.
        return "offers";
    }

    @GetMapping("/createoffer")
    // 이러면, 모델 객체를 스프링에서 자동으로 생성해주고, 그 모델안에 하나의 속성으로 offer라는 키값으로 Offer객체를 넣어준다.
    // 그리고 이 모델 객체는 createoffer라는 이름의 view로 전달되고, 이 Offer객체는 createoffer.html에서 사용된다.
    public String createOffer(Model model) {
        model.addAttribute("offer", new Offer());
        return "createoffer";
    }

    @PostMapping("/docreate")
    public String doCreate(Model model, Offer offer) {
        // (Spring Data Binding) 이 메서드는 createoffer.html에서 form으로 전달된 데이터(name, email...)들을 Offer 객체의 필드에 자동으로 바인딩해준다.
        // 그 후, 서비스 계층을 통해서 DB에 데이터를 저장한다.
        // Controller -> Service -> DAO -> DB
        offerService.insertOffer(offer);

        return "offercreated";
    }

}

