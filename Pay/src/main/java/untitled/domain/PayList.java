package untitled.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import untitled.PayApplication;
import untitled.domain.PayAccept;
import untitled.domain.PayCancelled;

@Entity
@Table(name = "PayList_table")
@Data
public class PayList {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer price;

    private String orderId;

    @PostPersist
    public void onPostPersist() {
        PayAccept payAccept = new PayAccept(this);
        payAccept.publishAfterCommit();

        PayCancelled payCancelled = new PayCancelled(this);
        payCancelled.publishAfterCommit();
    }

    public static PayListRepository repository() {
        PayListRepository payListRepository = PayApplication.applicationContext.getBean(
            PayListRepository.class
        );
        return payListRepository;
    }

    public static void payCancel(OrderCancelled orderCancelled) {
        /** Example 1:  new item 
        PayList payList = new PayList();
        repository().save(payList);

        */

        /** Example 2:  finding and process
        
        repository().findById(orderCancelled.get???()).ifPresent(payList->{
            
            payList // do something
            repository().save(payList);


         });
        */

    }
}
