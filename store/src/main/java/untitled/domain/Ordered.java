package untitled.domain;

import java.util.*;
import lombok.*;
import untitled.domain.*;
import untitled.infra.AbstractEvent;

@Data
@ToString
public class Ordered extends AbstractEvent {

    private Long id;
    private String address;
    private String destination;
    private String payment;
    private String iteminfo;
    // keep

}
