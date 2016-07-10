package com.gustz.dove.cli.api.service.util;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Depict: App log style
 * 
 * @author zhangzhenfeng
 * @date [2014-03-13]
 * 
 */
public class AppLogStyle {

    private final Logger logger;

    private ThreadLocal<Long> bt = new ThreadLocal<Long>();

    private ThreadLocal<String> sn = new ThreadLocal<String>();

    private final Lock lock = new ReentrantLock();

    private StackTraceElement[] stes = (new Exception()).getStackTrace();

    /**
     * Get app logs instance
     */
    public AppLogStyle() {
        lock.lock();
        try {
            this.bt.remove();
            this.sn.remove();
            // set
            this.bt.set(System.currentTimeMillis());
            this.sn.set(this.bt.get() + "");
            logger = LoggerFactory.getLogger(getCurrPtClsName());
        } finally {
            lock.unlock();
        }
    }

    /**
     * Get app logs instance
     * 
     * @param snVal
     * @param ciVal
     */
    public AppLogStyle(String snVal, String ciVal) {
        lock.lock();
        try {
            this.bt.remove();
            this.sn.remove();
            // set
            bt.set(System.currentTimeMillis());
            this.sn.set("SN: " + snVal + ",CI: " + ciVal);
            logger = LoggerFactory.getLogger(getCurrPtClsName());
        } finally {
            lock.unlock();
        }
    }

    private String getCurrPtLineNum() {
        if (stes != null && stes.length >= 3) {
            return String.valueOf(stes[2].getLineNumber());
        }
        return "0";
    }

    private String getCurrPtClsName() {
        if (stes != null && stes.length >= 3) {
            return (stes[2].getClassName());
        }
        return null;
    }

    private String getCurrPtMethodName() {
        if (stes != null && stes.length >= 3) {
            return stes[2].getMethodName();
        }
        return null;
    }

    /**
     * 开始日志格式
     * 
     * <p>
     * 开始：序号[XX] 类名[XX] 方法名[XX] 参数[XX]
     * </p>
     * 
     * @param param
     *            参数
     */
    public void begin(String param) {
        final String format = "开始：序号[{}] 方法名[{}] 参数[{}] ";
        logger.info(format, this.sn.get(), getCurrPtMethodName(), param);
    }

    /**
     * 结束日志格式
     * 
     * <p>
     * 结束：序号[XX] 方法名[XX] 耗时[XX]ms
     * </p>
     */
    public void end() {
        final String format = "结束：序号[{}] 方法名[{}] 耗时[{}]ms ";
        logger.info(format, this.sn.get(), getCurrPtMethodName(), (System.currentTimeMillis() - this.bt.get()) + "");
    }

    /**
     * 结束日志格式
     * 
     * <p>
     * 结束：序号[XX] 方法名[XX] 信息[XX] 耗时[XX]ms
     * </p>
     */
    public void end(String msg) {
        final String format = "结束：序号[{}] 方法名[{}] 信息[{}] 耗时[{}]ms ";
        logger.info(format, this.sn.get(), getCurrPtMethodName(), msg, (System.currentTimeMillis() - this.bt.get()) + "");
    }

    /**
     * 异常日志格式
     * 
     * <p>
     * 异常：序号[XX] 类名[XX] 方法名[XX] 行号[XX] 信息[XX]
     * </p>
     * 
     * @param msg
     *            信息
     * @param t
     *            Throwable
     */
    public void error(String msg, Throwable t) {
        // 日志内容
        StringBuilder sbd = new StringBuilder();
        sbd.append("异常：序号[").append(this.sn.get()).append("] ");
        //sbd.append("类名[").append(getCurrPtClsName()).append("] ");
        sbd.append("方法名[").append(getCurrPtMethodName()).append("] ");
        sbd.append("行号[").append(getCurrPtLineNum()).append("] ");
        sbd.append("信息[").append(msg).append("]");
        logger.error(sbd.toString(), t); // no format
    }

    /**
     * 异常日志格式
     * 
     * <p>
     * 异常：序号[XX] 类名[XX] 方法名[XX] 行号[XX] 信息[XX]
     * </p>
     * 
     * @param msg
     *            信息
     */
    public void error(String msg) {
        final String format = "异常：序号[{}] 方法名[{}] 行号[{}] 信息[{}] ";
        logger.error(format, this.sn.get(), getCurrPtMethodName(), getCurrPtLineNum(), msg);
    }

    /**
     * 返回日志格式
     * 
     * <p>
     * 返回：序号[XX] 方法名[XX] 信息[XX]
     * </p>
     * 
     * @param msg
     *            信息
     */
    public void return_(String msg) {
        final String format = "返回：序号[{}] 方法名[{}] 信息[{}] ";
        logger.info(format, this.sn.get(), getCurrPtMethodName(), msg);
    }

    /**
     * 任务开始日志格式
     * 
     * <p>
     * 任务开始：序号[XX] 类名[XX] 方法名[XX] 信息[XX]
     * </p>
     */
    public void taskBegin(String msg) {
        final String format = "任务开始：序号[{}] 方法名[{}] 信息[{}] ";
        logger.info(format, this.sn.get(), getCurrPtMethodName(), msg);
    }

    /**
     * 任务开始日志格式
     * 
     * <p>
     * 任务开始：序号[XX] 类名[XX] 方法名[XX]
     * </p>
     */
    public void taskBegin() {
        final String format = "任务开始：序号[{}] 方法名[{}] ";
        logger.info(format, this.sn.get(), getCurrPtMethodName());
    }

    /**
     * 任务结束日志格式
     * 
     * <p>
     * 任务结束：序号[XX] 方法名[XX] 耗时[XX]ms
     * </p>
     * 
     * @param msg
     *            信息
     */
    public void taskEnd() {
        final String format = "任务结束：序号[{}] 方法名[{}] 耗时[{}]ms ";
        logger.info(format, this.sn.get(), getCurrPtMethodName(), (System.currentTimeMillis() - this.bt.get()) + "");
    }

    /**
     * 任务结束日志格式
     * 
     * <p>
     * 任务结束：序号[XX] 方法名[XX] 信息[XX] 耗时[XX]ms
     * </p>
     * 
     * @param msg
     *            信息
     */
    public void taskEnd(String msg) {
        final String format = "任务结束：序号[{}] 方法名[{}] 信息[{}] 耗时[{}]ms ";
        logger.info(format, this.sn.get(), getCurrPtMethodName(), msg, (System.currentTimeMillis() - this.bt.get()) + "");
    }

    /**
     * 运行时日志格式
     * 
     * <p>
     * 运行时：序号[XX] 类名[XX] 方法名[XX] 行号[XX] 信息[XX] 耗时[XX]ms
     * </p>
     * 
     * @param msg
     *            信息
     */
    public void runtimeEnd(String msg) {
        final String format = "运行时：序号[{}] 方法名[{}] 行号[{}] 信息[{}] 耗时[{}]ms ";
        logger.info(format, this.sn.get(), getCurrPtMethodName(), getCurrPtLineNum(), msg,
                (System.currentTimeMillis() - this.bt.get()) + "");
    }

    /**
     * 运行时日志格式
     * 
     * <p>
     * 运行时：序号[XX] 类名[XX] 方法名[XX] 行号[XX] 信息[XX]
     * </p>
     * 
     * @param msg
     *            信息
     */
    public void runtime(String msg) {
        final String format = "运行时：序号[{}] 方法名[{}] 行号[{}] 信息[{}] ";
        logger.info(format, this.sn.get(), getCurrPtMethodName(), getCurrPtLineNum(), msg);
    }

    /**
     * 安全过滤的日志格式
     * 
     * <p>
     * 安全过滤：序号[XX] 类名[XX] 方法名[XX] 行号[XX] 信息[XX]
     * </p>
     * 
     * @param msg
     *            信息
     */
    public void security(String msg) {
        final String format = "安全过滤：序号[{}] 方法名[{}] 行号[{}] 信息[{}] ";
        logger.warn(format, this.sn.get(), getCurrPtMethodName(), getCurrPtLineNum(), msg);
    }

    /**
     * 运行时预警日志格式
     * 
     * <p>
     * 运行时预警：序号[XX] 类名[XX] 方法名[XX] 行号[XX] 信息[XX]
     * </p>
     * 
     * @param msg
     *            信息
     */
    public void runtimeWarn(String msg) {
        final String format = "运行时预警：序号[{}] 方法名[{}] 行号[{}] 信息[{}] ";
        logger.warn(format, this.sn.get(), getCurrPtMethodName(), getCurrPtLineNum(), msg);
    }

}
