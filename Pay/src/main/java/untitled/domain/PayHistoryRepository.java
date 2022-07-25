package untitled.domain;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import untitled.domain.*;

@RepositoryRestResource(
    collectionResourceRel = "payHistories",
    path = "payHistories"
)
public interface PayHistoryRepository
    extends PagingAndSortingRepository<PayHistory, Long> {}
