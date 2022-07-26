package untitled.domain;

import java.util.Date;
import lombok.Data;
import untitled.infra.AbstractEvent;

@Data
public class OrderPlaced extends AbstractEvent {

    private Long id;
    private String address;
    private String destination;
    private String payment;
    private String iteminfo;
    private String orderId;
}
