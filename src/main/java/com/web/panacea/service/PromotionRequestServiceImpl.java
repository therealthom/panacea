package com.web.panacea.service;

import com.web.panacea.domain.PromotionRequest;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PromotionRequestServiceImpl implements PromotionRequestService {

    public long countAllPromotionRequests() {
        return PromotionRequest.countPromotionRequests();
    }

    public void deletePromotionRequest(PromotionRequest promotionRequest) {
        promotionRequest.remove();
    }

    public PromotionRequest findPromotionRequest(Long id) {
        return PromotionRequest.findPromotionRequest(id);
    }

    public List<PromotionRequest> findAllPromotionRequests() {
        return PromotionRequest.findAllPromotionRequests();
    }

    public List<PromotionRequest> findPromotionRequestEntries(int firstResult, int maxResults) {
        return PromotionRequest.findPromotionRequestEntries(firstResult, maxResults);
    }

    public void savePromotionRequest(PromotionRequest promotionRequest) {
        promotionRequest.persist();
    }

    public PromotionRequest updatePromotionRequest(PromotionRequest promotionRequest) {
        return promotionRequest.merge();
    }
}
