package broker.actions.requests;

import broker.models.Module;
import broker.models.payload.Code;
import broker.models.payload.ModuleListPayload;
import broker.models.payload.SubscribePayloadIDs;

public class SubscribeExecutor extends ProtocolTaskExecutor {

    public SubscribeExecutor(SubscribePayloadIDs payload) {
        super(payload);
    }

    @Override
    public void execute(Module module) {
        // todo: лапками клац клац и метод создастся.

        ModuleListPayload moduleListPayload = new ModuleListPayload();

        //проверка на селф-подписку
        for (int i : module.getSubscribersList()){ //whole array
            if () //ids are eqyal
                moduleListPayload.setCode(Code.SELF_SUBSCRIBE);
        }

        //проверка на существующих подписчиков
        for () {
            if ()
                    moduleListPayload.setCode(Code.MODULE_DOES_NOT_EXIST);
        }

        //получение кода 20+получение списка ID

        moduleListPayload.setCode(Code.OK);

        SubscribePayloadIDs subscribePayloadIDs = new SubscribePayloadIDs();
        subscribePayloadIDs.setModulesToSubscribeOnId(module.getSubscribersList());

    }
}
