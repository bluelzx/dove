package com.gustz.dove.web.base.view.tpl;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sinovatech.fw.util.New;

public class Tpls {

    private static final String TAG_DATA = "data";

    private static void write0(Appendable writer, Object o) throws IOException {
        if (o == null)
            return;

        if (o instanceof CharSequence) {
            writer.append((CharSequence) o);
            return;
        } else
            writer.append(String.valueOf(o));
    }

    private static class JsValueWriter implements Tpl {

        private String tag;

        public JsValueWriter(String tag) {
            super();
            this.tag = tag;
        }

        @Override
        public void write(Map<String, ?> context, Appendable writer) throws IOException {
            Object o = context.get(tag);
            if (o == null)
                return;

            writer.append(" var ").append(tag).append(" = ");
            write0(writer, o);
            writer.append(";").append(LINE_SEP);
        }
    }

    private static final Pattern dataScriptPattern = Pattern.compile("<script[^>]+id=\"data-script\"[^>]?>([\\s\\S]*?)</script>",
            Pattern.CASE_INSENSITIVE);

    public static Tpl parseHtml(String html) {
        Matcher matcher = dataScriptPattern.matcher(html);
        if (!matcher.find()) {
            return new PlainTemplate(html);
        } else {
            List<Tpl> tpls = New.list();

            // add data template
            tpls.add(new PlainTemplate(html.substring(0, matcher.start(1)) + ""));
            tpls.add(new JsValueWriter(TAG_DATA));
            //tpls.add(new JsValueWriter("list"));
            tpls.add(new PlainTemplate(html.substring(matcher.end(1)) + ""));

            return new CompositeTpl(tpls);
        }
    }

}
