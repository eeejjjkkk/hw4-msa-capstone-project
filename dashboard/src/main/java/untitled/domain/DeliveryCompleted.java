package untitled.domain;

import java.util.Date;
import lombok.Data;
import untitled.infra.AbstractEvent;

@Data
public class DeliveryCompleted extends AbstractEvent {

    private Long id;
    private String orderId;
    private String payment;
    private String status;
    private String riderId;
    private Integer price;
}
