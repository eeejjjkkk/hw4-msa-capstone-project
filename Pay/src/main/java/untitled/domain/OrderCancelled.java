package untitled.domain;

import java.util.*;
import lombok.*;
import untitled.domain.*;
import untitled.infra.AbstractEvent;

@Data
@ToString
public class OrderCancelled extends AbstractEvent {

    private Long id;
    private String address;
    private String destination;
    private String iteminfo;
    private String payment;
    private String orderId;
    // keep

}
