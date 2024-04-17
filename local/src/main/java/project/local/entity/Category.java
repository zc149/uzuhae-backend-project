package project.local.entity;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum Category {
    MT1("마트"),
    CS2("편의점"),
    PS3("어린이집, 유치원"),
    SC4("학교"),
    AC5("학원"),
    PK6("주차장"),
    OL7("주유"),
    SW8("지하철역"),
    BK9("은행"),
    CT1("문화"),
    AG2("중개업소"),
    PO3("공공기관"),
    AT4("관광명소"),
    AD5("숙박"),
    FD6("푸드"),
    CE7("카페"),
    HP8("병원"),
    PM9("약국");

    private final String category;
    Category(String category) {
        this.category = category;
    }


}
