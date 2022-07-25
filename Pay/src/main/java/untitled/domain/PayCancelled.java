package untitled.domain;

import java.util.*;
import lombok.*;
import untitled.domain.*;
import untitled.infra.AbstractEvent;

@Data
@ToString
public class PayCancelled extends AbstractEvent {

    private Long id;
    private String orderId;
    private Integer price;

    public PayCancelled(PayHistory aggregate) {
        super(aggregate);
    }

    public PayCancelled() {
        super();
    }
    // keep

}
