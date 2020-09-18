package broker.actions.not_requests;

import broker.models.Module;

public interface OnConnectionEstablishedListener {
    void onConnectionEstablished(Module module);
}
