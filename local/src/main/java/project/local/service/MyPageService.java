package project.local.service;

import project.local.dto.*;

import java.time.LocalDate;
import java.util.List;

public interface MyPageService {

    Long findUser(Long id);

    List<CardsDTO> requestCards(Long id) throws Exception;

    List<BillsDTO> requestBills(Long id) throws Exception;

    TimeAndTotalAmountDTO getTimeAndTotalAmount(List<BillsDTO> bills, LocalDate time);

    List<BillsDetailsDTO> requestBillsDetails(Long id, int month) throws Exception;

    MypageDTO responseMypage(List<CardsDTO> cards, int totalAmount, List<BillsDetailsDTO> billsDetails);
}
