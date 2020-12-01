package src;

import java.util.ArrayList;

public abstract class Source {
    String data;
    String source;

    Source() {
        data = "";
        source = System.getProperty("user.dir");
    }

    Source(String source) {
        data = "";
        this.source = source;
    }

    public abstract String readSource();

    public abstract boolean writeToSource(String data);

    public abstract boolean deleteSource();
}