package untitled.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import untitled.StoreApplication;
import untitled.domain.Accepted;
import untitled.domain.AlreadyInAssigned;
import untitled.domain.Assigned;

@Entity
@Table(name = "StoreOrder_table")
@Data
public class StoreOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String orderId;

    private String iteminfo;

    private String payment;

    private String status;

    private String riderid;

    @PostPersist
    public void onPostPersist() {}

    @PrePersist
    public void onPrePersist() {
        Assigned assigned = new Assigned(this);
        assigned.publishAfterCommit();

        Accepted accepted = new Accepted(this);
        accepted.publishAfterCommit();

        AlreadyInAssigned alreadyInAssigned = new AlreadyInAssigned(this);
        alreadyInAssigned.publishAfterCommit();
    }

    public static StoreOrderRepository repository() {
        StoreOrderRepository storeOrderRepository = StoreApplication.applicationContext.getBean(
            StoreOrderRepository.class
        );
        return storeOrderRepository;
    }

    public void accept() {

        setStatus("ACCEPTED");

        Accepted accepted = new Accepted(this);
        accepted.publishAfterCommit();

    }

    public void reject() {

    }

    public void assign() {

        setStatus("ASSIGN");
        setRiderid("rider1");

        Assigned assigned = new Assigned(this);
        assigned.publishAfterCommit();

    }


    public static void cancelStoreOrder(OrderCancelled orderCancelled) {
        /** Example 1:  new item 
        StoreOrder storeOrder = new StoreOrder();
        repository().save(storeOrder);

        */

        /** Example 2:  finding and process
        
        repository().findById(orderCancelled.get???()).ifPresent(storeOrder->{
            
            storeOrder // do something
            repository().save(storeOrder);


         });
        */

    }

    public static void loadOrderList(OrderPlaced orderPlaced) {
        /** Example 1:  new item */ 
        StoreOrder storeOrder = new StoreOrder();

        storeOrder.setOrderId(orderPlaced.getOrderId()); 
        storeOrder.setIteminfo(orderPlaced.getIteminfo()); 
        storeOrder.setPayment(orderPlaced.getPayment()); 
        
        repository().save(storeOrder);

        

        /** Example 2:  finding and process
        
        repository().findById(orderPlaced.get???()).ifPresent(storeOrder->{
            
            storeOrder // do something
            repository().save(storeOrder);


         });
        */

    }
}
