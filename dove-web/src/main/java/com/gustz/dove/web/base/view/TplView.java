package com.gustz.dove.web.base.view;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gustz.dove.web.base.view.tpl.Tpl;
import org.springframework.web.servlet.view.AbstractUrlBasedView;

public class TplView extends AbstractUrlBasedView {

    private Tpl tpl;

    public Tpl getTpl() {
        return tpl;
    }

    public void setTpl(Tpl tpl) {
        this.tpl = tpl;
    }

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        tpl.write(model, response.getWriter());
    }

}
