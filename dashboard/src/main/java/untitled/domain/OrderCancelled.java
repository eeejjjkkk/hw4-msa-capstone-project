package untitled.domain;

import java.util.Date;
import lombok.Data;
import untitled.infra.AbstractEvent;

@Data
public class OrderCancelled extends AbstractEvent {

    private Long id;
    private String address;
    private String destination;
    private String iteminfo;
    private String payment;
    private String orderId;
}
