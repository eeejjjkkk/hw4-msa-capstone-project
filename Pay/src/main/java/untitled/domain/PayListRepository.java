package untitled.domain;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import untitled.domain.*;

@RepositoryRestResource(collectionResourceRel = "payLists", path = "payLists")
public interface PayListRepository
    extends PagingAndSortingRepository<PayList, Long> {}
