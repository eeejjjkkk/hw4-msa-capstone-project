package untitled.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "OrderDetail_table")
@Data
public class OrderDetail {

    @Id
    //@GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
}
