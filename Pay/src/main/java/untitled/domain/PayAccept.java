package untitled.domain;

import java.util.*;
import lombok.*;
import untitled.domain.*;
import untitled.infra.AbstractEvent;

@Data
@ToString
public class PayAccept extends AbstractEvent {

    private Long id;
    private String orderId;
    private Integer price;

    public PayAccept(PayHistory aggregate) {
        super(aggregate);
    }

    public PayAccept() {
        super();
    }
    // keep

}
