package project.local.service.inter;

import project.local.dto.mypage.*;

import java.util.List;

public interface MyDataService {

    List<CardsDTO> requestCards(Long id) throws Exception;

    List<BillsDTO> requestBills(Long id) throws Exception;

    List<BillsDetailsDTO> requestBillsDetails(Long id, int month) throws Exception;

    public List<MySubscriptionDTO> requestSubscription() throws Exception;
}
