package untitled.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import untitled.PayApplication;
import untitled.domain.PayAccept;
import untitled.domain.PayCancelled;

@Entity
@Table(name = "PayHistory_table")
@Data
public class PayHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String orderId;

    private Integer price;

    @PostPersist
    public void onPostPersist() {
        PayAccept payAccept = new PayAccept(this);
        payAccept.publishAfterCommit();

        PayCancelled payCancelled = new PayCancelled(this);
        payCancelled.publishAfterCommit();
    }

    public static PayHistoryRepository repository() {
        PayHistoryRepository payHistoryRepository = PayApplication.applicationContext.getBean(
            PayHistoryRepository.class
        );
        return payHistoryRepository;
    }

    public static void payCancel(OrderCancelled orderCancelled) {
        /** Example 1:  new item 
        PayHistory payHistory = new PayHistory();
        repository().save(payHistory);

        */

        /** Example 2:  finding and process
        
        repository().findById(orderCancelled.get???()).ifPresent(payHistory->{
            
            payHistory // do something
            repository().save(payHistory);


         });
        */

    }
}
