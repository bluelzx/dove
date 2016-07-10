package com.gustz.dove.web.base.view.tpl;

import java.io.IOException;
import java.util.Map;

public interface Tpl {

    static final String LINE_SEP = System.getProperty("line.separator");

    void write(Map<String, ?> context, Appendable writer) throws IOException;

    /**
     * Empty tpl.
     */
    Tpl EMPTY = new Tpl() {
        @Override
        public void write(Map<String, ?> context, Appendable writer) throws IOException {
        }
    };

    /**
     * Indicate that a NULL value for Tpl. <BR />
     * <B>NOTE: Null != Empty </B> <BR />
     * Is useful in caching(some map does not allow null value) : 
     * <pre> Map cache = new HashMap();
     * Tpl getTpl(String name){
     *   Tpl ret = cache.get(name);
     *   
     *   if (ret == null){
     *     ret = loadTpl(name);
     *     
     *     if (ret == null)
     *       ret = Tpl.NULL;
     *     
     *     cache.put(name, ret);
     *   }
     *    
     *   return ret;
     *   // or 
     *   // return ret == Tpl.NULL ? null :ret;
     * }
     *  
     *  </pre>
     */
    Tpl NULL = new Tpl() {
        @Override
        public void write(Map<String, ?> context, Appendable writer) throws IOException {
        }
    };

}
