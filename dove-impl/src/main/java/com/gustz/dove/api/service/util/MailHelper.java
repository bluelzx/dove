package com.gustz.dove.api.service.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO: Mail helper
 *
 * @author ZHENFENG ZHANG
 * @since  [Mar 24, 2015]
 */
public class MailHelper {

    private static final Logger logger = LoggerFactory.getLogger(MailHelper.class);

    // SMTP邮件服务
    private static String smtpServer = null;

    // 发件人地址
    private static String userName = null;

    // 发件人密码
    private static String password = null;

    static class MailHolder {
        public static final MailHelper INSTANCE = new MailHelper();
    }

    private MailHelper() {
        // null
    }

    /**
     * Get mail instance
     * 
     * @return this
     */
    public static MailHelper getInstance() {
        return MailHolder.INSTANCE;
    }

    /**
     * Get mail instance
     * 
     * @param smtpServer
     * @param userName
     * @param password
     * @return this
     */
    public static MailHelper getInstance(final String smtpServer, final String userName, final String password) {
        MailHelper.smtpServer = smtpServer;
        MailHelper.userName = userName;
        MailHelper.password = password;
        return MailHolder.INSTANCE;
    }

    private static enum ContentTypeEnum {

        HTML {
            @Override
            public String toString() {
                return "text/html;charset=UTF-8"; // HTML格式
            }
        },
        PLAIN {
            @Override
            public String toString() {
                return "text/plain;charset=UTF-8"; // 默认：文本格式
            }
        };

    }

    /**
     * 发送HTML邮件
     * 
     * @author zzf
     * @param mail
     * @return
     * @throws Exception
     */
    public boolean sendHtml(MailBean mail) throws Exception {
        return doSend(ContentTypeEnum.HTML, mail);
    }

    /**
     * 发送文本邮件
     * 
     * @param mail
     * @return
     * @throws Exception
     */
    public boolean sendPlain(MailBean mail) throws Exception {
        return doSend(ContentTypeEnum.PLAIN, mail);
    }

    /**
     * 发送自定义格式邮件
     * 
     * @param contType
     *            内容类型
     * @param mail
     * @return
     * @throws Exception
     */
    public boolean doSend(ContentTypeEnum contType, MailBean mail) throws Exception {
        long _bt = System.currentTimeMillis();
        logger.info("contType=:" + contType + ",mail=:" + mail);

        if (mail.getSmtpServer() == null) {
            mail.setSmtpServer(smtpServer);
        }
        if (mail.getUserName() == null) {
            mail.setUserName(userName);
        }
        if (mail.getFromAddr() == null) {
            mail.setFromAddr(userName);
        }
        if (mail.getPassword() == null) {
            mail.setPassword(password);
        }
        if (mail.getContent() == null) {
            mail.setContent("");
        }
        Properties prop = new Properties();
        prop.put("mail.smtp.host", mail.getSmtpServer()); // 设置邮件smtp服务器地址
        prop.put("mail.smtp.auth", "true"); // 设置服务器smtp需要验证
        Transport transport = null;
        try {
            Session session = Session.getInstance(prop, null);
            transport = session.getTransport("smtp");
            transport.connect(mail.getSmtpServer(), mail.getUserName(), mail.getPassword());
            if (!transport.isConnected()) {
                logger.error("SMTP服务器连接失败!");
                return false;
            }
            Message newMessage = new MimeMessage(session);

            // 设置发件人的地址
            newMessage.setFrom(new InternetAddress(new String(mail.getFromAddr().getBytes("utf-8"), "iso-8859-1")));

            // 设置收件人的地址
            String toAddr = mail.getToAddr();
            if (toAddr != null && !"".equals(toAddr)) {
                InternetAddress[] ia = InternetAddress.parse(getToAddrs(toAddr));
                newMessage.setRecipients(Message.RecipientType.TO, ia);
            }

            // 判断是否存在邮件抄送
            if (mail.getCcAddr() != null && !"".equals(mail.getCcAddr())) {
                InternetAddress[] addresscc = InternetAddress.parse(getToAddrs(mail.getCcAddr()));
                newMessage.setRecipients(Message.RecipientType.CC, addresscc);
            }
            if (mail.getBccAddr() != null && !"".equals(mail.getBccAddr())) {
                InternetAddress[] addresscc = InternetAddress.parse(getToAddrs(mail.getBccAddr()));
                newMessage.setRecipients(Message.RecipientType.BCC, addresscc);
            }

            // 设定发送时间
            newMessage.setSentDate(new Date());
            // 设置邮件，以普通邮件方式发送
            newMessage.setDataHandler(new DataHandler(mail.getContent(), contType.toString()));

            // 设置邮件优先级
            if (mail.getCritical() != null) {
                newMessage.addHeader("X-Priority", "1");
            }

            // 设置mail主题，注意转码
            newMessage.setSubject(MimeUtility.encodeText(mail.getSubject(), "utf-8", "B"));

            // 设置邮件内容,对邮件发送内容分批进行处理(附件以及内容),没有附件的情况
            List<String> attaList = mail.getAttachments();
            if (attaList == null) {
                attaList = new ArrayList<String>();
            }
            if (!attaList.isEmpty()) {
                Multipart multipart = new MimeMultipart();
                // 设置内容，注意转码
                String content = new String(mail.getContent().getBytes("utf-8"), "utf-8");
                int fileCount = attaList.size();
                if (fileCount > 0) {
                    // 循环所有的附件
                    for (int i = 0; i < fileCount; i++) {
                        MimeBodyPart messageBodyPart = new MimeBodyPart();
                        if (attaList.get(i) == null) {
                            continue;
                        }
                        String attach = MimeUtility.decodeText(attaList.get(i).toString());
                        FileDataSource source = new FileDataSource(attach);
                        messageBodyPart.setDataHandler(new DataHandler(source));
                        messageBodyPart.setFileName(MimeUtility.encodeText(source.getName())); // 文件全路径
                        multipart.addBodyPart(messageBodyPart);
                    }
                    MimeBodyPart messageBodyPart = new MimeBodyPart();
                    messageBodyPart.setText(content);
                    messageBodyPart.setContent(content, contType.toString());
                    multipart.addBodyPart(messageBodyPart);
                    newMessage.setContent(multipart);
                } else {
                    newMessage.setText(content);
                }
            }
            transport.sendMessage(newMessage, newMessage.getAllRecipients());
            newMessage.saveChanges();

            logger.info("发送邮件成功！");
        } catch (Exception e) {
            logger.error("发送自定义格式邮件异常", e);
            throw new RuntimeException(e);
        } finally {
            if (transport != null) {
                transport.close();
            }
            logger.info("耗时：" + (System.currentTimeMillis() - _bt));
        }
        return true;
    }

    /**
     * Get to address
     * 
     * @param toAddr
     * @return
     */
    private static String getToAddrs(String toAddr) {
        if (StringUtils.isNotEmpty(toAddr)) {
            toAddr = toAddr.replace(";", ",");
            Set<String> set = new LinkedHashSet<String>(); // 去重
            for (String _addr : toAddr.split(",")) {
                if (!set.contains(_addr)) {
                    set.add(_addr);
                }
            }
            return set.toString().replace("[", "").replace("]", "");
        }
        return null;
    }

    /**
     * 构建邮件
     * 
     * @param subject
     *            主题
     * @param content
     *            内容
     * @param toAddr
     *            收件地址
     * @param ccAddr
     *            抄送地址
     * @param bccAddr
     *            暗送地址
     * @param attachments
     *            附件
     * @return
     * @throws Exception
     */
    public MailBean buildEmailInfo(String subject, String content, String toAddr, String ccAddr, String bccAddr,
            List<String> attachments) throws Exception {
        MailBean mail = new MailBean(subject, content, toAddr, ccAddr, bccAddr, attachments);
        // 得到统一的执行时间
        mail.setFromAddr(userName);
        mail.setSmtpServer(smtpServer);
        mail.setUserName(userName);
        mail.setPassword(password);
        return mail;
    }

    /**
     * 填充邮件对象DTO
     * 
     * @param subject
     *            主题
     * @param content
     *            内容
     * @param toAddr
     *            收件地址
     * @param ccAddr
     *            抄送地址
     * @param attachments
     *            附件
     * @return
     * @throws Exception
     */
    public MailBean buildEmailInfo(String subject, String content, String toAddr, String ccAddr, List<String> attachments)
            throws Exception {
        MailHelper.MailBean mail = new MailBean(subject, content, toAddr, ccAddr, null, attachments);
        // 得到统一的执行时间
        mail.setFromAddr(userName); // 发件地址
        mail.setSmtpServer(smtpServer); // SMTP地址
        mail.setUserName(userName); // 发件人
        mail.setPassword(password); // 发件邮箱密码
        return mail;
    }

    public class MailBean implements Serializable {

        private static final long serialVersionUID = 1L;

        // 邮件服务器
        private String smtpServer;

        // 用户名
        private String userName;

        // 密码
        private String password;

        // 主题
        private String subject;

        // 内容
        private String content;

        // 邮件紧急
        private String critical;

        // 邮件任务ID
        private String mailTaskId;

        // 收件地址
        private String toAddr;

        // 发件地址
        private String fromAddr;

        // 抄送地址
        private String ccAddr;

        // 暗送地址
        private String bccAddr;

        // 附件
        private List<String> attachments = new ArrayList<String>();

        public MailBean() {
            // null
        }

        public MailBean(String subject, String content) {
            this.subject = subject;
            this.content = content;
        }

        public MailBean(String subject, String content, String toAddr, String ccAddr, String bccAddr) {
            this(subject, content);
            this.toAddr = toAddr;
            this.ccAddr = ccAddr;
            this.bccAddr = bccAddr;
        }

        public MailBean(String subject, String content, String toAddr, String ccAddr, String bccAddr, List<String> attachments) {
            this(subject, content, toAddr, ccAddr, bccAddr);
            this.attachments = attachments;
        }

        public String getMailTaskId() {
            return mailTaskId;
        }

        public void setMailTaskId(String mailTaskId) {
            this.mailTaskId = mailTaskId;
        }

        public String getSmtpServer() {
            return smtpServer;
        }

        public void setSmtpServer(String smtpServer) {
            this.smtpServer = smtpServer;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCritical() {
            return critical;
        }

        public void setCritical(String critical) {
            this.critical = critical;
        }

        public List<String> getAttachments() {
            return attachments;
        }

        public void setAttachments(List<String> attachments) {
            this.attachments = attachments;
        }

        public String getToAddr() {
            return toAddr;
        }

        public void setToAddr(String toAddr) {
            this.toAddr = toAddr;
        }

        public String getFromAddr() {
            return fromAddr;
        }

        public void setFromAddr(String fromAddr) {
            this.fromAddr = fromAddr;
        }

        public String getCcAddr() {
            return ccAddr;
        }

        public void setCcAddr(String ccAddr) {
            this.ccAddr = ccAddr;
        }

        public String getBccAddr() {
            return bccAddr;
        }

        public void setBccAddr(String bccAddr) {
            this.bccAddr = bccAddr;
        }
    }

}