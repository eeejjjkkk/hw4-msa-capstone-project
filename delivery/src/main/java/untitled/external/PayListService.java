package untitled.external;

import java.util.Date;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "Pay", url = "${api.url.Pay}")
public interface PayListService {
    @RequestMapping(method = RequestMethod.POST, path = "/payLists")
    public void pay(@RequestBody PayList payList);
    // keep

}
