/**
 * @(#)FileTypeUtil.java
 *
 * @Created date [2011-12-9]
 */
package com.gustz.dove.api.service.util;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import javax.activation.MimeType;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import org.apache.commons.lang3.StringUtils;

import eu.medsea.mimeutil.MimeUtil;

/**
 * Depict: File type
 * 
 * @author zhfgzhang
 * @date [2011-12-9]
 */
public abstract class FileTypeUtil {

    private static final String UNKNOWN_MIME_TYPE = "application/octet-stream";

    static {
        MimeUtil.registerMimeDetector("eu.medsea.mimeutil.detector.MagicMimeMimeDetector");
        MimeUtil.registerMimeDetector("eu.medsea.mimeutil.detector.ExtensionMimeDetector");
        MimeUtil.registerMimeDetector("eu.medsea.mimeutil.detector.WindowsRegistryMimeDetector");
        // MimeUtil.registerMimeDetector("eu.medsea.mimeutil.detector.OpendesktopMimeDetector");
    }

    /**
     * Get image file type
     * 
     * @param file
     * @throws IOException
     */
    public static String getImgFileType(File file) throws IOException {
        if (isImage(file)) {
            ImageInputStream iins = null;
            try {
                iins = ImageIO.createImageInputStream(file);
                Iterator<ImageReader> iter = ImageIO.getImageReaders(iins);
                if (!iter.hasNext()) {
                    return null;
                }
                return iter.next().getFormatName(); // file type
            } catch (IOException e) {
                throw e;
            } finally {
                if (iins != null) {
                    try {
                        iins.close();
                    } catch (IOException e2) {
                        throw e2;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Is image
     * 
     * @param file
     */
    public static boolean isImage(File file) {
        try {
            BufferedImage bri = ImageIO.read(file);
            int width = bri.getWidth();
            int height = bri.getHeight();
            return (width > 0 && height > 0);
        } catch (Exception e) {
            // null
        }
        return false;
    }

    /**
     * Get file hex string
     * 
     * @param bytes
     */
    public static String getFileHexStr(byte[] bytes) {
        StringBuilder sbd = new StringBuilder();
        if (bytes == null || bytes.length <= 0) {
            return null;
        }
        for (int i = 0, len = bytes.length; i < len; i++) {
            int hexv = bytes[i] & 0xFF;
            String _hexv = Integer.toHexString(hexv);
            if (_hexv.length() < 2) {
                sbd.append(0);
            }
            sbd.append(_hexv);
        }
        return sbd.toString().toUpperCase();
    }

    /**
     * Is auth file type
     * 
     * @param authKeys
     * @param mimeType
     * @return
     * @throws Exception 
     */
    public static boolean isAuthFileType(Set<String> authKeys, String mimeType) throws Exception {
        if (StringUtils.isBlank(mimeType))
            return false;

        if (authKeys == null || authKeys.isEmpty())
            throw new IllegalArgumentException("Auth file type set is empty!");

        return authKeys.contains(mimeType);
    }

    /**
     * Get file MIME type
     * 
     * @param file
     * @return
     * @throws IOException 
     */
    @SuppressWarnings("unchecked")
    public static String getFileMimeType(File file) throws IOException {
        if (!file.exists()) {
            throw new FileNotFoundException("File '" + file + "' does not exist!");
        }
        return fetchMimeType(MimeUtil.getMimeTypes(file));
    }

    /**
     * Get file MIME type
     * 
     * @param is
     * @return
     * @throws IOException 
     */
    @SuppressWarnings("unchecked")
    public static String getFileMimeType(InputStream is) throws IOException {
        return fetchMimeType(MimeUtil.getMimeTypes(new BufferedInputStream(is)));
    }

    private static String fetchMimeType(Collection<MimeType> coll) {
        if (coll != null && coll.size() > 0) {
            for (MimeType mimeType : coll) {
                if (mimeType != null && !UNKNOWN_MIME_TYPE.equals(mimeType.toString())) {
                    return mimeType.toString();
                }
            }
        }
        return UNKNOWN_MIME_TYPE;
    }

}