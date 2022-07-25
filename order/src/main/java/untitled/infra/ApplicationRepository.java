package untitled.infra;

import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import untitled.domain.*;

@RepositoryRestResource(
    collectionResourceRel = "applications",
    path = "applications"
)
public interface ApplicationRepository
    extends PagingAndSortingRepository<Application, Long> {
    // keep

}
