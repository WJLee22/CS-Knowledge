package kr.ac.hansung.cse.controller;

import kr.ac.hansung.cse.model.Offer;
import kr.ac.hansung.cse.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.*;

import java.net.URI;
import java.util.List;

// @Controller + @ResponseBody = @RestController
@RestController
@RequestMapping("/api/offers")
public class OfferRestController {

    @Autowired
    private OfferService offerService;

    // Retrieve single offer
    @GetMapping("/{id}")
    // @PathVariable: URI 경로에서 {id} 변수 값을 추출하여 메서드 파라미터로 바인딩
    public ResponseEntity<Offer> getOffer(@PathVariable("id") int id) {

        Offer offer = offerService.getOfferById(id);
        if(offer== null) {
            throw new OfferNotFoundException(id);
        }
        // ResponseEntity: HTTP 응답을 나타내는 객체. body와 status를 함께 설정할 수 있다. header도 설정할 수 있으나 여기서는 사용하지 않음.
        return new ResponseEntity<Offer>(offer, HttpStatus.OK) ; // body, status
    }

    // Retrieve All Offers
    @GetMapping
    public ResponseEntity<List<Offer>> getOffers() {

        List<Offer> offers = offerService.getAllOffers();
        if(offers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Offer>>(offers, HttpStatus.OK) ; // body, status
    }

    // Create new offer
    @PostMapping
    // @RequestBody: HTTP 요청의 body 부분을 json -> Offer 객체로 변환하여 메서드 파라미터로 바인딩
    public ResponseEntity<Void> createOffer(@RequestBody Offer offer) {

        offerService.insertOffer(offer);

        // HttpHeaders: HTTP 응답 헤더를 설정하는 객체
        // 새롭게 생성된 offer 리소스의 id를 추가하여 http 응답 헤더 구성 후, 클라이언트에게 새로 생성된 리소스의 URI를 알려준다.
        HttpHeaders headers = new HttpHeaders();
        // url 생성
        URI locationUri = ServletUriComponentsBuilder
                .fromCurrentRequest()  // 현재 요청 URL 기준 (예: /api/offers)
                .path("/{id}")        // 여기에 /{id} 추가 → /api/offers/{id}
                .buildAndExpand(offer.getId())  // {id}를 실제 ID로 치환
                .toUri();  // URI 객체로 변환

        headers.setLocation(locationUri);

        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);

    }

    //Update an offer
    @PutMapping("/{id}")
    // @PathVariable: URI 경로에서 {id} 변수 값을 추출하여 메서드 파라미터로 바인딩.
    // @RequestBody: HTTP 요청의 body 부분을 json -> Offer 객체로 변환하여 메서드 파라미터로 바인딩
    public ResponseEntity<Offer> updateOffer(@PathVariable("id") int id, @RequestBody Offer offer){
        // 해당 id를 가진 offer가 존재하는지 확인
       Offer currentOffer = offerService.getOfferById(id);
        if(currentOffer == null)
            throw new OfferNotFoundException(id);

        currentOffer.setName(offer.getName());
        currentOffer.setEmail(offer.getEmail());
        currentOffer.setText(offer.getText());

        offerService.updateOffer(currentOffer);

        return new ResponseEntity<Offer>(currentOffer, HttpStatus.OK);
    }

    // delete an offer
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOffer(@PathVariable("id") int id) {

        Offer currentOffer = offerService.getOfferById(id);
        if(currentOffer == null)
            throw new OfferNotFoundException(id);

        offerService.deleteOfferById(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }



}
