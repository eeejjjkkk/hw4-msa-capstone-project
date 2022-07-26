package untitled.domain;

import java.util.*;
import lombok.*;
import untitled.domain.*;
import untitled.infra.AbstractEvent;

@Data
@ToString
public class AlreadyInAssigned extends AbstractEvent {

    private Long id;
    private String orderId;
    private String iteminfo;
    private String payment;
    private String status;
    private String riderid;

    public AlreadyInAssigned(StoreOrder aggregate) {
        super(aggregate);
    }

    public AlreadyInAssigned() {
        super();
    }
    // keep

}
