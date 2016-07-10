/**
 * @(#)FileUtil.java
 *
 * @Created date [2011-3-30]
 */
package com.gustz.dove.api.service.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

import javax.xml.bind.DatatypeConverter;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Depict: File util
 * 
 * @author zhfgzhang
 * @date [2011-3-30]
 */
public abstract class FileUtil extends FileUtils {

    // .tmp 
    public static final String TEMP_SUFFIX = ".tmp";

    // .properties
    public static final String PROP_SUFFIX = ".properties";

    /**
     * 重命名文件夹
     * 
     * @param pattern
     * @param dirPath
     * @throws Exception
     */
    public static void renameDir(String pattern, String dirPath) throws Exception {
        File file = null;
        StringBuilder sbd = null;
        if (dirPath != null) {
            file = new File(dirPath);
            sbd = new StringBuilder(file.getParent()).append(File.separator).append(pattern);
            file.renameTo(new File(sbd.toString()));
        }
    }

    /**
     * 获取文件扩展名(不包括点号)
     * 
     * @param fileName
     */
    public static String getFileExtName(String fileName) {
        return getFileExtName(new File(fileName));
    }

    /**
     * 获取文件扩展名(不包括点号)
     * 
     * @param fileName
     */
    public static String getFileExtName(File fileName) {
        if (fileName == null) {
            return null;
        }
        String extName = null;
        String _fileName = fileName.getName();
        if (StringUtils.isNotEmpty(_fileName) && _fileName.contains(".")) {
            extName = _fileName.substring(_fileName.lastIndexOf(".") + 1, _fileName.length()).toLowerCase();
        }
        return extName;
    }

    /**
     * 移除文件扩展名(包括点号)
     * 
     * @param fileName
     * @throws IOException
     */
    public static String removeFileExtName(String fileName) throws IOException {
        return removeFileExtName(new File(fileName));
    }

    /**
     * 移除文件扩展名(包括点号)
     * 
     * @param file
     * @throws IOException
     */
    public static String removeFileExtName(File file) throws IOException {
        if (file == null || (!file.exists()) || (!file.isFile())) {
            return "";
        }
        String _fileName = file.getCanonicalPath();
        if (_fileName.contains(".")) {
            _fileName = _fileName.substring(0, _fileName.lastIndexOf("."));
        }
        return _fileName;
    }

    /**
     * 创建指定的文件夹
     * 
     * @param dir
     * @throws Exception
     */
    public static boolean createfiles(File dir) {
        if (dir != null && !dir.exists()) {
            dir.setWritable(true, false);
            return dir.mkdirs();
        }
        return false;
    }

    /**
     * 创建指定的文件夹
     * 
     * @param dirName
     * @throws Exception
     */
    public static boolean createfiles(String dirName) {
        return createfiles(new File(dirName));
    }

    /**
     * 获取新建指定的文件夹
     * 
     * @param dir
     * @throws Exception
     */
    public static File getCreatefiles(final File dir) {
        if (dir != null && !dir.exists()) {
            dir.setWritable(true, false);
            dir.mkdirs();
        }
        return dir;
    }

    /**
     * 获取新建指定的文件夹
     * 
     * @param dirName
     * @throws Exception
     */
    public static String getCreatefiles(String dirName) {
        String filePath = null;
        File file = getCreatefiles(new File(dirName));
        if (file != null) {
            filePath = file.getPath().concat(File.separator);
        }
        return filePath;
    }

    /**
     * 创建指定的文件
     * 
     * @param file
     * @throws IOException
     */
    public static void createNewFile(File file) throws IOException {
        if (file != null && !file.exists()) {
            file.setWritable(true, false);
            file.createNewFile();
        }
    }

    /**
     * 创建指定的文件
     * 
     * @param fileName
     * @throws IOException
     */
    public static void createNewFile(String fileName) throws IOException {
        createNewFile(new File(fileName));
    }

    /**
     * 获取新建指定的文件
     * 
     * @param file
     * @throws IOException
     */
    public static File getCreateNewFile(File file) throws IOException {
        if (file != null && !file.exists()) {
            file.setWritable(true, false);
            file.createNewFile();
        }
        return file;
    }

    /**
     * 获取新建指定的文件
     * 
     * @param fileName
     * @throws IOException
     */
    public static String getCreateNewFile(String fileName) throws IOException {
        String filePath = null;
        File file = getCreateNewFile(new File(fileName));
        if (file != null && file.isFile()) {
            filePath = file.getPath().concat(File.separator);
        }
        return filePath;
    }

    /**
     * 根据路径删除指定的目录或文件(删除当前目录)
     * 
     * @param filePath
     */
    public static void deleteFolder(String filePath) {
        File file = new File(filePath);
        // 判断目录或文件是否存在
        if (file.exists()) {
            if (file.isFile()) {
                // 调用删除文件方法
                deleteFile(filePath);
            } else {
                // 调用删除目录方法
                deleteDir(filePath);
            }
        }
    }

    /**
     * 删除单个文件
     * 
     * @param file
     */
    public static void deleteFile(File file) {
        if (file != null && file.exists() && file.isFile()) {
            file.delete();
        }
    }

    /**
     * 删除单个文件
     * 
     * @param filePath
     */
    public static void deleteFile(String filePath) {
        if (filePath != null) {
            deleteFile(new File(filePath));
        }
    }

    /**
     * 删除目录/文件夹以及目录下的文件
     * 
     * @param filePath
     */
    public static void deleteDir(String filePath) {
        File file = null;
        // 如果sPath不以文件分隔符结尾,自动添加文件分隔符.
        if (!filePath.endsWith(File.separator)) {
            filePath = filePath.concat(File.separator);
        }
        File dirFile = new File(filePath);
        // 如果dir对应的文件不存在,或者不是一个目录,则退出.
        if (dirFile.exists() && dirFile.isDirectory()) {
            // 删除文件夹下的所有文件(包括子目录)
            File[] files = dirFile.listFiles();
            for (int i = 0, len = files.length; i < len; i++) {
                file = files[i];
                if (file.isFile()) {
                    // 删除子文件
                    deleteFile(file.getAbsolutePath());
                } else {
                    // 删除子目录
                    deleteDir(file.getAbsolutePath());
                }
            }
            // 删除当前目录
            dirFile.delete();
        }
    }

    /**
     * 删除目录下的全部文件夹/文件
     * 
     * @param currentDir
     *            is delete current dir?
     * @param filePath
     */
    public static void deleteFiles(final boolean currentDir, String filePath) {
        File file = null;
        // 如果sPath不以文件分隔符结尾,自动添加文件分隔符.
        if (!filePath.endsWith(File.separator)) {
            filePath = filePath.concat(File.separator);
        }
        File dirFile = new File(filePath);
        // 如果dir对应的文件不存在,或者不是一个目录,则退出.
        if (dirFile.exists() && dirFile.isDirectory()) {
            // 删除文件夹下的所有文件(包括子目录)
            File[] files = dirFile.listFiles();
            for (int i = 0, len = files.length; i < len; i++) {
                file = files[i];
                if (file.isFile()) {
                    // 删除子文件
                    deleteFile(file.getAbsolutePath());
                } else {
                    // 删除子目录
                    deleteDir(file.getAbsolutePath());
                }
            }
            // 是否删除当前目录
            if (currentDir) {
                dirFile.delete();
            }
        }
    }

    /**
     * 执行复制Jar包中的文件内容
     * 
     * @param srcJarfileName
     * @param destFile
     * @throws IOException
     */
    private static void doCopyJarFile(String srcJarfileName, File destFile) throws IOException {
        if (destFile.exists() && destFile.isDirectory()) {
            throw new IOException("Destination '" + destFile + "' exists but is a directory or class is null.");
        }
        InputStream ins = null;
        OutputStream ops = null;
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try {
            ins = classLoader.getResourceAsStream(srcJarfileName);
            ops = new FileOutputStream(destFile);
            IOUtils.copy(ins, ops);
        } catch (IOException e) {
            throw e;
        } finally {
            IOUtils.closeQuietly(ins);
            IOUtils.closeQuietly(ops);
        }
    }

    /**
     * 复制Jar包中的文件内容
     * 
     * @param srcJarfileName
     * @param destFile
     * @throws IOException
     */
    public static void copyJarFile(String srcJarfileName, File destFile) throws IOException {
        doCopyJarFile(srcJarfileName, destFile);
    }

    /**
     * 复制Jar包中的文件内容
     * 
     * @param srcJarfileName
     * @param destFileName
     * @throws IOException
     */
    public static void copyJarFile(String srcJarfileName, String destFileName) throws IOException {
        copyJarFile(srcJarfileName, new File(destFileName));
    }

    /**
     * 复制Jar包中的文件内容
     * 
     * @param srcClass
     * @param destFile
     * @param suffixName
     * @throws IOException
     */
    public static void copyJarFile(Class<?> srcClass, File destFile, String suffixName) throws IOException {
        String srcJarfileName = null;
        if (srcClass != null) {
            srcJarfileName = srcClass.getPackage().getName().replace(".", File.separator);
            if (suffixName != null) {
                srcJarfileName = new StringBuilder(srcJarfileName).append(suffixName).toString();
            }
            doCopyJarFile(srcJarfileName, destFile);
        }
    }

    /**
     * 复制Jar包中的文件内容
     * 
     * @param srcClass
     * @param destFileName
     * @param suffixName
     * @throws IOException
     */
    public static void copyJarFile(Class<?> srcClass, String destFileName, String suffixName) throws IOException {
        copyJarFile(srcClass, new File(destFileName), suffixName);
    }

    /**
     * 复制Jar包中的文件内容
     * 
     * @param srcClass
     * @param destFile
     * @throws IOException
     */
    public static void copyJarFile(Class<?> srcClass, File destFile) throws IOException {
        String srcJarfileName = null;
        if (srcClass != null) {
            srcJarfileName = srcClass.getName().replace(".", File.separator);
            doCopyJarFile(srcJarfileName, destFile);
        }
    }

    /**
     * 复制Jar包中的文件内容
     * 
     * @param srcClass
     * @param destFileName
     * @throws IOException
     */
    public static void copyJarFile(Class<?> srcClass, String destFileName) throws IOException {
        copyJarFile(srcClass, new File(destFileName));
    }

    /**
     * 获取用户当前临时目录(C:\DOCUME~1\ADMINI~1\LOCALS~1\Temp\或TOMCAT temp)
     */
    public static String getUserTempDir() {
        return System.getProperty("java.io.tmpdir").concat(File.separator);
    }

    /**
     * 创建用户当前临时文件 (C:\DOCUME~1\ADMINI~1\LOCALS~1\Temp\xxx.txt或TOMCAT
     * temp\xxx.txt)
     * 
     * @param fileName
     */
    public static String getUserTempFile(String fileName) {
        StringBuilder sbd = new StringBuilder(getUserTempDir());
        sbd.append(fileName == null ? "" : fileName);
        return sbd.toString();
    }

    /**
     * 创建用户当前临时文件 (C:\DOCUME~1\ADMINI~1\LOCALS~1\Temp\xxx.txt或TOMCAT
     * temp\xxx.txt)
     * 
     * @param fileSuffix
     */
    public static String getUserTempFileSx(String fileSuffix) {
        fileSuffix = fileSuffix == null ? ".txt" : fileSuffix;
        return getUserTempFile(NumberUtil.getRandomSN6().concat(fileSuffix));
    }

    /**
     * 将对象写入文件
     * 
     * @param obj
     * @param file
     * @throws IOException
     */
    public static void writeObjToFile(Object obj, File file) throws IOException {
        if (file == null || (!file.exists())) {
            return;
        }
        ObjectOutputStream oops = null;
        try {
            // 不追加内容直接覆盖
            oops = new ObjectOutputStream(new FileOutputStream(file));
            oops.writeObject(obj);
            oops.flush();
        } catch (IOException e) {
            throw e;
        } finally {
            IOUtils.closeQuietly(oops);
        }
    }

    /**
     * 将对象写入文件
     * 
     * @param obj
     * @param fileName
     * @throws IOException
     */
    public static void writeObjToFile(Object obj, String fileName) throws IOException {
        writeObjToFile(obj, new File(fileName));
    }

    /**
     * 将对象写入文件
     * 
     * @param obj
     * @param fileName
     * @param isNewFile
     * @throws IOException
     */
    public static void writeObjToFile(Object obj, String fileName, boolean isNewFile) throws IOException {
        writeObjToFile(obj, new File(fileName), isNewFile);
    }

    /**
     * 将对象写入文件
     * 
     * @param obj
     * @param file
     * @param isNewFile
     * @throws IOException
     */
    public static void writeObjToFile(Object obj, File file, boolean isNewFile) throws IOException {
        if (file == null) {
            return;
        }
        if (isNewFile) {
            if (!file.exists()) {
                createNewFile(file);
            }
        }
        writeObjToFile(obj, file);
    }

    /**
     * 从文件中读取对象
     * 
     * @param file
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static Object readObjFromFile(File file) throws IOException, ClassNotFoundException {
        if (file == null || (!file.exists())) {
            return null;
        }
        ObjectInputStream oins = null;
        try {
            oins = new ObjectInputStream(new FileInputStream(file));
            return oins.readObject();
        } catch (IOException e) {
            throw e;
        } finally {
            IOUtils.closeQuietly(oins);
        }
    }

    /**
     * 从文件中读取对象
     * 
     * @param fileName
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static Object readObjFromFile(String fileName) throws IOException, ClassNotFoundException {
        return readObjFromFile(new File(fileName));
    }

    /**
     * 过滤文件(文件/文件夹)
     * 
     * @param fileName
     */
    public static FilenameFilter filtrateFile(String fileName) {
        return filtrateFile(new File(fileName));
    }

    /**
     * 过滤文件(文件/文件夹)
     * 
     * @param file
     */
    public static FilenameFilter filtrateFile(File file) {
        FilenameFilter filenameFilter = null;
        if (file != null && file.exists()) {
            filenameFilter = new LocalFileFilter(file.getPath(), file.getName());
        }
        return filenameFilter;
    }

    /**
     * Depict: 文件过滤器(根据文件名/文件夹名)
     * 
     * @author zhfgzhang
     * @date [2012-4-7]
     */
    private static class LocalFileFilter implements FilenameFilter {

        private String fileName = null;

        public LocalFileFilter(String dirName, String fileName) {
            this.fileName = fileName;
        }

        public boolean accept(File dir, String name) {
            return !(this.fileName.equals(name));
        }
    }

    /**
     * 过滤文件(文件/文件夹)
     * 
     * @param dirName
     * @param prefix
     */
    public static FilenameFilter filtratePreFile(String dirName, String prefix) {
        return filtratePreFile(new File(dirName), prefix);
    }

    /**
     * 过滤文件(文件/文件夹)
     * 
     * @param dirFile
     * @param prefix
     */
    public static FilenameFilter filtratePreFile(File dirFile, String prefix) {
        FilenameFilter filenameFilter = null;
        if (dirFile != null && dirFile.exists()) {
            filenameFilter = new LocalPrefixFilter(dirFile, prefix);
        }
        return filenameFilter;
    }

    /**
     * Depict: 文件过滤器(根据文件前缀名/文件夹前缀名)
     * 
     * @author zhfgzhang
     * @date [2012-4-7]
     */
    private static class LocalPrefixFilter implements FilenameFilter {

        private String prefix = null;

        public LocalPrefixFilter(File dirFile, String prefix) {
            this.prefix = prefix;
        }

        public boolean accept(File dir, String name) {
            return !(name.startsWith(this.prefix));
        }
    }

    /**
     * 过滤文件(文件/文件夹)
     * 
     * @param dirName
     * @param suffix
     */
    public static FilenameFilter filtrateSufFile(String dirName, String suffix) {
        return filtrateSufFile(new File(dirName), suffix);
    }

    /**
     * 过滤文件(文件/文件夹)
     * 
     * @param dirFile
     * @param suffix
     */
    public static FilenameFilter filtrateSufFile(File dirFile, String suffix) {
        FilenameFilter filenameFilter = null;
        if (dirFile != null && dirFile.exists()) {
            filenameFilter = new LocalSuffixFilter(dirFile, suffix);
        }
        return filenameFilter;
    }

    /**
     * Depict: 文件过滤器(根据文件后缀名/文件夹后缀名)
     * 
     * @author zhfgzhang
     * @date [2012-4-7]
     */
    private static class LocalSuffixFilter implements FilenameFilter {

        String suffix = null;

        public LocalSuffixFilter(File dirFile, String suffix) {
            this.suffix = suffix;
        }

        public boolean accept(File dir, String name) {
            return !(name.endsWith(this.suffix));
        }
    }

    /**
     * 不过滤文件(文件/文件夹)
     * 
     * @param dirName
     * @param suffix
     */
    public static FilenameFilter unFiltrateSufFile(String dirName, String suffix) {
        return unFiltrateSufFile(new File(dirName), suffix);
    }

    /**
     * 不过滤文件(文件/文件夹)
     * 
     * @param dirFile
     * @param suffix
     */
    public static FilenameFilter unFiltrateSufFile(File dirFile, String suffix) {
        FilenameFilter filenameFilter = null;
        if (dirFile != null && dirFile.exists()) {
            filenameFilter = new unLocalSuffixFilter(dirFile, suffix);
        }
        return filenameFilter;
    }

    /**
     * Depict: 文件过滤器(根据文件后缀名/文件夹后缀名)
     * 
     * @author zhfgzhang
     * @date [2012-4-7]
     */
    private static class unLocalSuffixFilter implements FilenameFilter {

        String suffix = null;

        public unLocalSuffixFilter(File dirFile, String suffix) {
            this.suffix = suffix;
        }

        public boolean accept(File dir, String name) {
            return name.endsWith(this.suffix);
        }
    }

    /**
     * 是否是可用文件
     * 
     * @param file
     */
    public static boolean isUsableFile(File file) {
        return (file != null && file.length() > 0 && file.isFile());
    }

    /**
     * 重命名文件
     * 
     * @param srcFile
     * @param destFile
     */
    public static void renameTo(File srcFile, File destFile) {
        if (srcFile != null) {
            srcFile.renameTo(destFile);
        }
    }

    /**
     * 重命名文件
     * 
     * @param srcFile
     * @param destFile
     */
    public static void renameTo(String srcFile, String destFile) {
        if (srcFile != null && !"".equals(srcFile) && destFile != null && !"".equals(destFile)) {
            File _file = new File(srcFile);
            _file.renameTo(new File(destFile));
        }
    }

    /**
     * 获取随机的文件名
     * 
     * @param path
     * @param suffix
     */
    public static String getRandFileName(String path, String suffix) {
        if (path == null || "".equals(path)) {
            return NumberUtil.getOsDateTimeSN() + suffix;
        }
        return path + NumberUtil.getOsDateTimeSN() + suffix;
    }

    /**
     * 获取XML文件名
     * 
     * @param path
     */
    public static String getXmlFileName(String path) {
        return getRandFileName(path, ".xml");
    }

    /**
     * 获取txt文件名
     * 
     * @param path
     */
    public static String getTxtFileName(String path) {
        return getRandFileName(path, ".txt");
    }

    /**
     * 获取txt文件名
     * 
     * @param fileName
     * @param path
     */
    public static String getTxtFileName(String fileName, String path) {
        if (path == null || "".equals(path)) {
            return fileName + ".txt";
        }
        return path + fileName + ".txt";
    }

    /**
     * Append data to file
     * 
     * @param fileName
     * @param data
     * @throws IOException
     */
    public static void append2File(String fileName, String data) throws IOException {
        FileWriter writer = null;
        try {
            // 追加数据到文件中
            writer = new FileWriter(fileName, true);
            writer.write(data);
        } finally {
            IOUtils.closeQuietly(writer);
        }
    }

    /**
     * Write data to file
     * 
     * @param fileName
     * @param data
     * @throws IOException
     */
    public static void write2File(String fileName, String data) throws IOException {
        FileWriter writer = null;
        try {
            // 追加数据到文件中
            writer = new FileWriter(fileName, false);
            writer.write(data);
        } finally {
            IOUtils.closeQuietly(writer);
        }
    }

    /**
     * 新建目录
     * 
     * @param folderPath
     *            String 如 c:/fqf
     * @return boolean
     */
    public void newFolder(String folderPath) {
        String filePath = folderPath;
        File myFilePath = new File(filePath);
        if (!myFilePath.exists()) {
            myFilePath.mkdirs();
        }
    }

    /**
     * 按行写入文件
     * 
     * @param fileName
     * @param lines
     * @throws IOException 
     */
    public static void writeLines(String fileName, Collection<?> lines) throws IOException {
        // 1、创建文件
        File file = new File(fileName);
        createNewFile(file);
        // 2、写文件
        FileUtils.writeLines(file, lines);
    }

    /**
     * Move file
     * 
     * @param src
     * @param src
     * @return
     */
    public static boolean mv(String src, String dest) {
        return mv(new File(src), new File(dest));
    }

    /**
     * Move file
     * 
     * @param srcFile
     * @param destFile
     * @return
     */
    public static boolean mv(File srcFile, File destFile) {
        boolean flag = true;
        try {
            copyFile(srcFile, destFile);
        } catch (Exception ex) {
            flag = false;
        }
        if (flag) {
            try {
                deleteFile(destFile);
            } catch (Exception ex) {
                flag = false;
            }
        }
        return flag;
    }

    /**
     * Determine whether the multipart content is still available.
     * If a temporary file has been moved, the content is no longer available.
     * @param fileItem
     */
    public static boolean isAvailable(final FileItem fileItem) {
        // If in memory, it's available.
        if (fileItem.isInMemory()) {
            return true;
        }
        // Check actual existence of temporary file.
        if (fileItem instanceof DiskFileItem) {
            return ((DiskFileItem) fileItem).getStoreLocation().exists();
        }
        return false;
    }

    /**
     * Upload file
     * 
     * @param fileItem
     * @param mimeType
     * @param maxSize
     * @param authFileType
     * @param outFileName path+name
     * @return
     */
    public static FileRsCode uploadFile(final FileItem fileItem, String mimeType, int maxSize, Set<String> authFileType,
            String outFileName) {
        if (!isAvailable(fileItem)) {
            throw new IllegalStateException("File has already been moved - cannot be transferred again");
        }
        try {
            // 1.验证文件类型
            if (!FileTypeUtil.isAuthFileType(authFileType, mimeType)) {
                FileRsCode.C0002.setMsg("上传文件的类型：" + mimeType + "，不在允许的文件类型[ " + authFileType + " ]范围内。");
                return FileRsCode.C0002;
            }
            File dest = new File(outFileName);
            if (dest.exists() && !dest.delete()) {
                throw new IOException("Destination file [" + dest.getAbsolutePath() + "] already exists and could not be deleted");
            }
            // 2.验证文件大小 maxSize=-1取消限制大小
            long max = fileItem.getSize();
            if (max <= (maxSize * 1024) || maxSize == -1) {
                fileItem.write(dest);
            } else {
                FileRsCode.C0001.setMsg("上传文件的大小：" + (max / 1024) + "kb，已超过最大限制：" + maxSize + "kb。");
                return FileRsCode.C0001;
            }
        } catch (Exception e) {
            e.printStackTrace();
            FileRsCode.C9999.setMsg("上传文件异常！ " + e.getMessage());
            return FileRsCode.C9999;
        }
        return FileRsCode.C0000;
    }

    public enum FileRsCode {

        /**
         * 上传文件成功
         */
        C0000("上传文件成功！"),
        /**
         * 上传的文件大小未授权
         */
        C0001("上传的文件大小未授权！"),
        /**
         * 上传的文件类型未授权
         */
        C0002("上传的文件类型未授权！"),
        /**
         * 上传文件异常
         */
        C9999("上传文件异常！");

        private final String code;

        private String msg;

        FileRsCode(String msg) {
            this.code = this.name();
            this.msg = msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getMsg() {
            return this.msg;
        }

        public String getCode() {
            return this.code;
        }
    }

    /**
     * Download file
     * 
     * @param ops
     * @param fileName path+name
     * @throws IOException 
     */
    public static void downloadFile(OutputStream ops, String fileName) throws IOException {
        BufferedInputStream input = null;
        BufferedOutputStream output = null;
        try {
            input = new BufferedInputStream(new FileInputStream(new File(fileName)));
            output = new BufferedOutputStream(ops);
            IOUtils.copy(input, output);
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    throw e;
                }
            }
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    throw e;
                }
            }
        }
    }

    /**
     * Get jar file input stream
     * 
     * @param file
     * @param prefix
     * @param suffix
     * @return
     * @throws IOException
     */
    public static List<InputStream> getJarFileIns(File file, String prefix, String suffix) throws IOException {
        JarFile jarFile = new JarFile(file);
        Enumeration<?> jarEntries = jarFile.entries();
        List<InputStream> _list = new ArrayList<InputStream>();
        ZipEntry ze = null;
        while (jarEntries.hasMoreElements()) {
            ze = (ZipEntry) jarEntries.nextElement();
            if (ze != null && ze.getName().startsWith(prefix) && ze.getName().endsWith(suffix)) {
                _list.add(new BufferedInputStream(jarFile.getInputStream(ze)));
            }
        }
        return _list;
    }

    /**
     * Get jar file input stream
     * 
     * @param file
     * @param prefix
     * @return
     * @throws IOException
     */
    public static List<InputStream> getJarFileInsByPre(File file, String prefix) throws IOException {
        JarFile jarFile = new JarFile(file);
        Enumeration<?> jarEntries = jarFile.entries();
        List<InputStream> _list = new ArrayList<InputStream>();
        ZipEntry ze = null;
        while (jarEntries.hasMoreElements()) {
            ze = (ZipEntry) jarEntries.nextElement();
            if (ze != null && ze.getName().startsWith(prefix)) {
                _list.add(new BufferedInputStream(jarFile.getInputStream(ze)));
            }
        }
        return _list;
    }

    /**
     * Get jar file input stream
     * 
     * @param file
     * @param suffix
     * @return
     * @throws IOException
     */
    public static List<InputStream> getJarFileInsBySuf(File file, String suffix) throws IOException {
        JarFile jarFile = new JarFile(file);
        Enumeration<?> jarEntries = jarFile.entries();
        List<InputStream> _list = new ArrayList<InputStream>();
        ZipEntry ze = null;
        while (jarEntries.hasMoreElements()) {
            ze = (ZipEntry) jarEntries.nextElement();
            if (ze != null && ze.getName().endsWith(suffix)) {
                _list.add(new BufferedInputStream(jarFile.getInputStream(ze)));
            }
        }
        return _list;
    }

    /**
     * Get jar file input stream
     * 
     * @param file
     * @param suffix
     * @return
     * @throws IOException
     */
    public static InputStream getJarFileInsBySuf_(File file, String suffix) throws IOException {
        List<InputStream> _list = getJarFileInsBySuf(file, suffix);
        if (_list != null && _list.size() > 0) {
            return _list.get(0);
        }
        return null;
    }

    /**
     * Get file input stream
     * 
     * @param fileName
     * @return
     * @throws IOException
     */
    public static InputStream getInputStream(String fileName) throws IOException {
        if (StringUtils.isEmpty(fileName)) {
            return null;
        }
        return new BufferedInputStream(new FileInputStream(new File(fileName)));
    }

    /**
     * Load property file in jar file
     * 
     * @param jarFile
     * @param suffix
     * @return
     */
    public static Properties loadInJar(File jarFile, String suffix) {
        Properties prop = new Properties();
        InputStream ins = null;
        try {
            JarFile _jarfile = new JarFile(jarFile);
            Enumeration<?> jarEntries = _jarfile.entries();
            ZipEntry ze = null;
            while (jarEntries.hasMoreElements()) {
                ze = (ZipEntry) jarEntries.nextElement();
                if (ze != null && ze.getName().endsWith(suffix)) {
                    ins = new BufferedInputStream(_jarfile.getInputStream(ze));
                    prop.load(ins);
                    break;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            IOUtils.closeQuietly(ins);
        }
        return prop;
    }

    /**
     * Load property file
     * 
     * @param ins
     * @return
     * @throws IOException
     */
    public static Properties loadProp(InputStream ins) throws IOException {
        Properties prop = new Properties();
        prop.load(ins);
        return prop;
    }

    /**
     * Load property file in jar file
     * 
     * @param jarFile
     * @param prefix
     * @param suffix
     * @return
     */
    public static List<Properties> loadPropsInJar(File jarFile, String prefix, String suffix) {
        List<Properties> _list = null;
        Properties prop = null;
        InputStream ins = null;
        try {
            JarFile _jarfile = new JarFile(jarFile);
            Enumeration<?> jarEntries = _jarfile.entries();
            _list = new ArrayList<Properties>();
            ZipEntry ze = null;
            while (jarEntries.hasMoreElements()) {
                ze = (ZipEntry) jarEntries.nextElement();
                if (ze != null && ze.getName().startsWith(prefix) && ze.getName().endsWith(suffix)) {
                    ins = new BufferedInputStream(_jarfile.getInputStream(ze));
                    prop = new Properties();
                    prop.load(ins);
                    _list.add(prop);
                    IOUtils.closeQuietly(ins);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            IOUtils.closeQuietly(ins);
        }
        return _list;
    }

    /**
     * Load property file in jar files
     * 
     * @param jarFile
     * @param prefix
     * @return
     */
    public static Properties loadInJarFiles(File jarFile, String prefix) {
        Properties prop = new Properties();
        InputStream ins = null;
        try {
            JarFile _jarfile = new JarFile(jarFile);
            Enumeration<?> jarEntries = _jarfile.entries();
            ZipEntry ze = null;
            while (jarEntries.hasMoreElements()) {
                ze = (ZipEntry) jarEntries.nextElement();
                if (ze != null && ze.getName().startsWith(prefix) && ze.getName().endsWith(PROP_SUFFIX)) {
                    ins = new BufferedInputStream(_jarfile.getInputStream(ze));
                    prop.load(ins);
                    break;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            IOUtils.closeQuietly(ins);
        }
        return prop;
    }

    /**
     * Find jar path
     * 
     * @param prefix
     *            /xxx-core-*.jar:conf/
     * @return
     */
    public static String findJarPath(String prefix) {
        if (StringUtils.isEmpty(prefix) || !(prefix.contains("*.jar"))) {
            return null;
        }
        int index = 0;
        if (prefix.startsWith("/")) {
            index = 1;
        }
        prefix = prefix.substring(index, prefix.indexOf("*.jar")); // 去除*.jar后缀
        // 类文件所在路径
        String _jarPtPath = new File(getCurrClsPath()).getParent();
        File[] _files = new File(_jarPtPath).listFiles(); // 类文件所在父级路径
        if (_files != null && _files.length > 0) {
            for (File _sfile : _files) {
                if (_sfile != null && _sfile.getName().endsWith(".jar") && _sfile.getName().startsWith(prefix)) {
                    return _sfile.getPath();
                }
            }
        }
        return null;
    }

    /**
     * Get jar in property file
     * 
     * @param jarFile
     *            /WEB-INF/lib/xxx.jar
     * @param prefix
     *            xxx-*.jar:conf/xxx.properties
     * @return
     */
    public static Properties getJarPropFile(String jarFile, String prefix) {
        if (StringUtils.isEmpty(jarFile) || StringUtils.isEmpty(prefix)
                || !(prefix.contains(":") || !prefix.endsWith(PROP_SUFFIX))) {
            throw new RuntimeException("Config pattern is error![ key=xxx-*.jar:conf/xxx.properties ]");
        }
        return FileUtil.loadInJarFiles(new File(jarFile), prefix.split(":")[1]);
    }

    /**
     * Set jar properties to map
     * 
     * @param jarFile
     *            /WEB-INF/lib/xxx.jar
     * @param prefix
     *            xxx.conf=xxx-*.jar:conf/
     * @param propFileMap
     * @param propMap
     * @throws RuntimeException
     */
    public static void setJarPropsFile(String jarFile, String prefix, Map<String, String> propFileMap,
            Map<String, Properties> propMap) throws RuntimeException {
        if (StringUtils.isEmpty(jarFile) || StringUtils.isEmpty(prefix)
                || !(prefix.contains(":") || !prefix.endsWith(PROP_SUFFIX)) || propFileMap == null || propMap == null) {
            throw new RuntimeException("Config pattern is error![ key=xxx-*.jar:conf/ ]");
        }
        String _tmpFile = null;
        String _tmpKey = null;
        Properties _tmpProp = null;
        InputStream ins = null;
        try {
            String[] _tmps = prefix.split(":");
            prefix = _tmps[1]; // 前缀匹配符 conf/
            String _name = _tmps[0].substring(0, _tmps[0].indexOf("=")); // xxx.conf
            JarFile _jfile = new JarFile(jarFile);
            Enumeration<?> jarEntries = _jfile.entries();
            ZipEntry ze = null;
            while (jarEntries.hasMoreElements()) {
                ze = (ZipEntry) jarEntries.nextElement();
                if (ze != null) {
                    _tmpFile = ze.getName();
                    if (_tmpFile.startsWith(prefix) && _tmpFile.endsWith(PROP_SUFFIX)) {
                        ins = new BufferedInputStream(_jfile.getInputStream(ze));
                        _tmpProp = new Properties();
                        _tmpProp.load(ins); // 加载属性文件
                        // 存放Map中
                        _tmpKey = _name + "." + _tmpFile.substring(prefix.length(), _tmpFile.lastIndexOf(PROP_SUFFIX)); // 属性文件名称
                        propFileMap.put(_tmpKey, jarFile + ":" + _tmpFile); // key:XXX-XXX.conf.XXX=XXX.properties
                        propMap.put(_tmpKey, _tmpProp);
                        IOUtils.closeQuietly(ins); // 关闭流
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            IOUtils.closeQuietly(ins);
        }
    }

    /**
     * Get stantand file path
     * 
     * @param fileName
     * @throws IOException
     */
    public static String getStantandPath(String fileName) throws IOException {
        return new File(fileName).getCanonicalPath();
    }

    /**
     * Get abs file path
     * 
     * @param fileName
     * @throws IOException
     */
    public static String getAbsPath(String fileName) throws IOException {
        return new File(fileName).getAbsolutePath();
    }

    /**
     * Get current user dir
     */
    public static String getCurrUserDir() {
        return System.getProperty("user.dir");
    }

    /**
     * Get current class path
     * 
     * @return
     */
    public static String getCurrClsPath() {
        return FileUtil.class.getProtectionDomain().getCodeSource().getLocation().getPath();
    }

    /**
     * Decode base64 fileName
     * 
     * @param fileName
     * @return
     */
    public static String decodeB64FileName(String fileName) {
        fileName = fileName.replace(" ", "+");
        return new String(DatatypeConverter.parseBase64Binary(fileName));
    }

    /**
     * Encode base64 fileName
     * 
     * @param fileName
     * @return
     */
    public static String encodeB64FileName(String fileName) {
        return DatatypeConverter.printBase64Binary(fileName.getBytes());
    }

    /**
     * Get base64 mimeType
     * 
     * @param fileName path+name
     * @return
     * @throws Exception 
     */
    public static String getB64FileMimeType(String fileName) throws Exception {
        fileName = fileName.replace(" ", "+");
        fileName = new String(DatatypeConverter.parseBase64Binary(fileName));
        return FileTypeUtil.getFileMimeType(new File(fileName));
    }

    /**
     * Get file mimeType
     * 
     * @param fileName path+name
     * @return
     * @throws Exception
     */
    public static String getFileMimeType(String fileName) throws Exception {
        return FileTypeUtil.getFileMimeType(new File(fileName));
    }

}