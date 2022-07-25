package untitled.domain;

import java.util.*;
import lombok.*;
import untitled.domain.*;
import untitled.infra.AbstractEvent;

@Data
@ToString
public class Accepted extends AbstractEvent {

    private Long id;
    private String orderId;
    private String iteminfo;
    private String payment;
    private String status;
    private String riderid;

    public Accepted(StoreOrder aggregate) {
        super(aggregate);
    }

    public Accepted() {
        super();
    }
    // keep

}
