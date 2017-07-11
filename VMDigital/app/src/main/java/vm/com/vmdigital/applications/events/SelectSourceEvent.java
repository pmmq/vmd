package vm.com.vmdigital.applications.events;

import vm.com.vmdigital.models.Source;

public class SelectSourceEvent {
    Source mSource;


    public SelectSourceEvent(Source source) {
        mSource = source;
    }

    public Source getSource() {
        return mSource;
    }

    public void setSource(Source source) {
        mSource = source;
    }

}
