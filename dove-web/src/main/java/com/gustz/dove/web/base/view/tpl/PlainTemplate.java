package com.gustz.dove.web.base.view.tpl;

import java.io.IOException;
import java.util.Map;

public class PlainTemplate implements Tpl {

    private String text;

    public PlainTemplate(String text) {
        super();
        this.text = text;
    }

    @Override
    public void write(Map<String, ?>context, Appendable writer) throws IOException {
        writer.append(text);
    }

}
