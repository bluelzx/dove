package com.gustz.dove.web.base.view.tpl;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

import com.sinovatech.fw.util.New;

public class CompositeTpl implements Tpl {

    private Collection<Tpl> members;

    public CompositeTpl(Collection<Tpl> members) {
        super();
        this.members = New.list(members);
    }

    @Override
    public void write(Map<String, ?> context, Appendable writer) throws IOException {
        if (members == null || members.isEmpty())
            return;

        for (Tpl member : members) {
            member.write(context, writer);
        }
    }

}
