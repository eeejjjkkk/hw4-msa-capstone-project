package untitled.domain;

import java.util.*;
import lombok.*;
import untitled.domain.*;
import untitled.infra.AbstractEvent;

@Data
@ToString
public class PayAccept extends AbstractEvent {

    private Long id;
    private Integer price;
    private String orderId;

    public PayAccept(PayList aggregate) {
        super(aggregate);
    }

    public PayAccept() {
        super();
    }
    // keep

}
