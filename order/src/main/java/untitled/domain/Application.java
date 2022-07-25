package untitled.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Application_table")
@Data
public class Application {

    @Id
    //@GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
}
