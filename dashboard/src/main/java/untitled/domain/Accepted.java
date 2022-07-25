package untitled.domain;

import java.util.Date;
import lombok.Data;
import untitled.infra.AbstractEvent;

@Data
public class Accepted extends AbstractEvent {

    private Long id;
    private String orderId;
    private String iteminfo;
    private String payment;
    private String status;
    private String riderid;
}
