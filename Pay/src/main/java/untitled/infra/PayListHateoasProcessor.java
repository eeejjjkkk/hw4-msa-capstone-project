package untitled.infra;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import untitled.domain.*;

@Component
public class PayListHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<PayList>> {

    @Override
    public EntityModel<PayList> process(EntityModel<PayList> model) {
        return model;
    }
}
