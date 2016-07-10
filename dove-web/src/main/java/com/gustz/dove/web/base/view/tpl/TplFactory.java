package com.gustz.dove.web.base.view.tpl;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TplFactory {

    private String cxtPath;

    private String realPath;

    private String resLibPath;

    private String prefix;

    private String viewPrefix;

    private ViewHeadMeta viewHeadMeta;

    private ConcurrentMap<String, Future<Tpl>> cacheMap = new ConcurrentHashMap<String, Future<Tpl>>();

    private ExecutorService executor = Executors.newCachedThreadPool();

    private ConcurrentMap<String, Object> locks = new ConcurrentHashMap<String, Object>();

    private final Object lock = new Object();

    public TplFactory(String realPath, String prefix, String cxtPath, ViewHeadMeta viewHeadMeta) {
        super();
        this.realPath = realPath;
        this.prefix = prefix;
        this.cxtPath = cxtPath;
        this.viewHeadMeta = viewHeadMeta;
    }

    private static void close(Closeable io) {
        if (io != null)
            try {
                io.close();
            } catch (Throwable t) {
                // print then ignore it.
                t.printStackTrace();
            }
    }

    public Tpl getTpl(String path, String resLibPath, String viewPrefix, boolean cache) throws FileNotFoundException {
        this.resLibPath = resLibPath;
        this.viewPrefix = viewPrefix;
        if (cache) { // cache
            return getTplWithCache(path);
        } else { // no cache
            return getTplNoCache(path);
        }
    }

    private Tpl getTplWithCache(final String path) {
        while (!cacheMap.containsKey(path)) {
            if (locks.putIfAbsent(path, lock) == null) { // yes, i get the lock
                Callable<Tpl> callable = new Callable<Tpl>() {

                    @Override
                    public Tpl call() throws Exception {
                        return loadTpl(path);
                    }
                };
                cacheMap.put(path, executor.submit(callable));
            }
        }
        try {
            return cacheMap.get(path).get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    private Tpl getTplNoCache(String path) throws FileNotFoundException {
        return loadTpl(path);
    }

    private Tpl loadTpl(String path) throws FileNotFoundException {
        String html = this.parseResLibHtml(path);

        return html == null ? null : Tpls.parseHtml(html);
    }

    private String getViewHeadMeta() {
        if (this.viewHeadMeta == null) {
            return "";
        }
        StringBuilder sbd = new StringBuilder("");
        // meta title
        String title = this.viewHeadMeta.getTitle();
        if (title != null && !title.isEmpty()) {
            sbd.append("<title>").append(title).append("</title>");
        }
        // meta content
        String kwd = this.viewHeadMeta.getKeywords();
        if (kwd != null && !kwd.isEmpty()) {
            sbd.append("<meta name=\"Keywords\" content=\"").append(kwd).append("\" />");
        }
        String desc = this.viewHeadMeta.getDescription();
        if (desc != null && !desc.isEmpty()) {
            sbd.append("<meta name=\"description\" content=\"").append(desc).append("\" />");
        }
        return sbd.toString();
    }

    private String parseResLibHtml(String path) throws FileNotFoundException {
        // get view head meta
        String libHtml = this.getViewHeadMeta();
        // common lib
        libHtml += readString(this.resLibPath).replace("\n", "");
        libHtml = libHtml.replace("<!DOCTYPE html>", "").replace("<html>", "").replace("<head>", "");
        libHtml = libHtml.replace("{{cxtPath}}", this.cxtPath);
        libHtml = libHtml.replace("</head>", "").replace("</html>", "");

        // add module lib
        libHtml += "<script src=\"" + this.cxtPath + "/" + this.viewPrefix + "/" + path.replace(".html", ".js");
        libHtml += "\" type=\"text/javascript\"></script>";

        // replace resource lib to html
        String html = this.readString(this.prefix + "/" + path);
        if (html == null || html.isEmpty()) {
            throw new FileNotFoundException("/" + this.prefix + "/" + path);
        }
        return html.replace("<head-lib id=\"resource-lib\"/>", libHtml);
    }

    /**
     * Read string value from a file.
     * 
     * @param path
     * @return the content of the file, or null if the file does not exists.
     * @throws RuntimeException IOException, permission Exception, or some others.
     */
    private String readString(String path) {
        File file = new File(this.realPath, path);

        InputStream is;
        try {
            is = new FileInputStream(file);
        } catch (FileNotFoundException e1) {
            return null;
        }

        InputStreamReader reader;
        try {
            reader = new InputStreamReader(is, "UTF-8"); // only utf8 is supported.
        } catch (UnsupportedEncodingException e) {
            close(is);
            throw new RuntimeException(e);
        }

        StringBuilder ret = new StringBuilder(102400);
        char[] cs = new char[102400];
        int c;
        try {
            while ((c = reader.read(cs)) > 0) {
                ret.append(cs, 0, c);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            close(reader);
        }

        return ret.toString();
    }

}
