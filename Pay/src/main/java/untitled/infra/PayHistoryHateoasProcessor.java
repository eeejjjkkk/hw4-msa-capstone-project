package untitled.infra;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import untitled.domain.*;

@Component
public class PayHistoryHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<PayHistory>> {

    @Override
    public EntityModel<PayHistory> process(EntityModel<PayHistory> model) {
        return model;
    }
}
