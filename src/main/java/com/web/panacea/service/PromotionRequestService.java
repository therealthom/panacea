package com.web.panacea.service;

import com.web.panacea.domain.PromotionRequest;
import java.util.List;

public interface PromotionRequestService {

    public abstract long countAllPromotionRequests();

    public abstract void deletePromotionRequest(PromotionRequest promotionRequest);

    public abstract PromotionRequest findPromotionRequest(Long id);

    public abstract List<PromotionRequest> findAllPromotionRequests();

    public abstract List<PromotionRequest> findPromotionRequestEntries(int firstResult, int maxResults);

    public abstract void savePromotionRequest(PromotionRequest promotionRequest);

    public abstract PromotionRequest updatePromotionRequest(PromotionRequest promotionRequest);

}
