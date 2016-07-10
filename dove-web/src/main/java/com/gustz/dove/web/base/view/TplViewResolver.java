package com.gustz.dove.web.base.view;

import java.util.Locale;

import javax.servlet.ServletContext;

import com.gustz.dove.web.base.view.tpl.Tpl;
import com.gustz.dove.web.base.view.tpl.TplFactory;
import com.gustz.dove.web.base.view.tpl.ViewHeadMeta;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

public class TplViewResolver extends UrlBasedViewResolver {

    private String resLibPath = "";

    private String cache = "false";

    private String viewPrefix = "";

    @Autowired
    private ServletContext servletContext;

    private static ViewHeadMeta viewHeadMeta;

    private void setProperty() {
        if (this.getViewClass() == null) {
            this.setViewClass(TplView.class);
        }
        if (StringUtils.isBlank(getContentType())) {
            this.setContentType("text/html;charset=utf-8");
        }
        if (StringUtils.isBlank(getPrefix())) {
            this.setPrefix("themes");
        }
        if (StringUtils.isBlank(getSuffix())) {
            this.setSuffix(".html");
        }
        if (StringUtils.isBlank(cache)) {
            cache = Boolean.FALSE.toString();
        }
        super.setCache(Boolean.valueOf(cache));
        if (StringUtils.isBlank(viewPrefix)) {
            this.setViewPrefix("views");
        }
        if (StringUtils.isBlank(resLibPath)) {
            this.setResLibPath("/WEB-INF/views/resource-lib.html");
        }
    }

    private static TplFactory createFactory(ServletContext servletContext, String prefix) {
        String realPath = servletContext.getRealPath("/");
        String cxtPath = servletContext.getContextPath();

        return new TplFactory(realPath, prefix, cxtPath, viewHeadMeta);
    }

    private TplFactory tf;

    private TplFactory getTemplateFactory() {
        if (tf == null)
            tf = createFactory(servletContext, getPrefix());

        return tf;
    }

    @Override
    protected TplView createView(String viewName, Locale locale) throws Exception {
        this.setProperty(); // 1.
        String path = viewName + getSuffix();
        Tpl tpl = getTemplateFactory().getTpl(path, resLibPath, viewPrefix, Boolean.valueOf(cache)); // 2.
        if (tpl == null)
            return null;

        TplView tplView = (TplView) super.createView(viewName, locale);
        tplView.setTpl(tpl);
        return tplView;
    }

    public void setResLibPath(String resLibPath) {
        this.resLibPath = resLibPath;
    }

    public void setCache(String cache) {
        this.cache = cache;
    }

    public void setViewPrefix(String viewPrefix) {
        this.viewPrefix = viewPrefix;
    }

    public void setViewHeadMeta(ViewHeadMeta viewHeadMeta) {
        TplViewResolver.viewHeadMeta = viewHeadMeta;
    }

}
