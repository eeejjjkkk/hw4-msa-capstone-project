package untitled.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import untitled.OrderApplication;
import untitled.domain.OrderCancelled;
import untitled.domain.OrderPlaced;

@Entity
@Table(name = "Order_table")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String address;

    private String destination;

    private String iteminfo;

    private String payment;

    private String orderId;

    @PostPersist
    public void onPostPersist() {
        OrderPlaced orderPlaced = new OrderPlaced(this);
        orderPlaced.publishAfterCommit();
    }

    @PrePersist
    public void onPrePersist() {
        OrderCancelled orderCancelled = new OrderCancelled(this);
        orderCancelled.publishAfterCommit();
    }

    public static OrderRepository repository() {
        OrderRepository orderRepository = OrderApplication.applicationContext.getBean(
            OrderRepository.class
        );
        return orderRepository;
    }

    public static void notifyUserCancelIsNotAvailable(
        AlreadyInAssigned alreadyInAssigned
    ) {
        /** Example 1:  new item 
        Order order = new Order();
        repository().save(order);

        */

        /** Example 2:  finding and process
        
        repository().findById(alreadyInAssigned.get???()).ifPresent(order->{
            
            order // do something
            repository().save(order);


         });
        */

    }

    public static void orderChanged(DeliveryStarted deliveryStarted) {
        /** Example 1:  new item 
        Order order = new Order();
        repository().save(order);

        */

        /** Example 2:  finding and process
        
        repository().findById(deliveryStarted.get???()).ifPresent(order->{
            
            order // do something
            repository().save(order);


         });
        */

    }
}
