package com.gustz.dove.cli.api.message.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gustz.dove.cli.api.service.dict.MsgTypeDict;
import com.gustz.dove.cli.api.service.dict.YnDict;
import com.gustz.dove.cli.api.service.vo.AbstCliBaseVo;

/**
 * 
 * TODO: 公用复合消息类
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class CommCpMsg extends AbstCliBaseVo {

    private static final long serialVersionUID = 1L;

    /**
     * 特殊情况：指定为@all，则向关注该企业应用的全部成员发送
     */
    public static final String TO_ALL_USER = "@all";

    /**
     * 成员ID列表（消息接收者，多个接收者用‘|’分隔，最多支持1000个）。特殊情况：指定为@all，则向关注该企业应用的全部成员发送
     */
    @JsonProperty("touser")
    private String toUser;
    private String[] toUserExt;

    /**
     * 部门ID列表，多个接收者用‘|’分隔，最多支持100个。当touser为@all时忽略本参数
     */
    @JsonProperty("toparty")
    private String toDept;
    private String[] toDeptExt;

    /**
     * 标签ID列表，多个接收者用‘|’分隔。当touser为@all时忽略本参数
     */
    @JsonProperty("totag")
    private String toTag;
    private String[] toTagExt;

    /**
     * 消息类型
     */
    @JsonProperty("msgtype")
    private MsgTypeDict msgType;

    /**
     * 企业应用的id，整型。可在应用的设置页面查看
     */
    @JsonProperty("agentid")
    private String agentId;

    /**
     * 表示是否是保密消息，0表示否，1表示是，默认0 
     */
    @JsonProperty("safe")
    private YnDict safe = YnDict.N;

    public CommCpMsg() {
        super();
    }

    public CommCpMsg(String toUser, MsgTypeDict msgType) {
        this();
        this.toUser = toUser;
        this.msgType = msgType;
    }

    public CommCpMsg(String toUser, String toDept, String toTag, MsgTypeDict msgType, String agentId, YnDict safe) {
        this();
        this.toUser = toUser;
        this.toDept = toDept;
        this.toTag = toTag;
        this.msgType = msgType;
        this.agentId = agentId;
        this.safe = safe;
    }

    public void setToUserExt(String[] toUserExt) {
        //this.toUserExt = toUserExt;
        String text = this.fmtText(toUserExt);
        if (text != null && !text.isEmpty()) {
            this.setToUser(text);
        }
    }

    public void setToDeptExt(String[] toDeptExt) {
        // this.toDeptExt = toDeptExt;
        String text = this.fmtText2(toDeptExt);
        if (text != null && !text.isEmpty()) {
            this.setToDept(text);
        }
    }

    public void setToTagExt(String[] toTagExt) {
        //this.toTagExt = toTagExt;
        String text = this.fmtText2(toTagExt);
        if (text != null && !text.isEmpty()) {
            this.setToTag(text);
        }
    }

    private String fmtText(String[] ss) {
        String text = "";
        if (ss != null && ss.length > 0) {
            for (int i = 0; i < ss.length; i++) {
                if (i > 0) {
                    text += "|";
                }
                text += ss[i];
            }
        }
        return text;
    }

    private String fmtText2(String[] ss) {
        String text = " ";
        if (ss != null && ss.length > 0) {
            for (int i = 0; i < ss.length; i++) {
                if (i > 0) {
                    text += " | ";
                }
                text += ss[i];
            }
            text += " ";
        }
        return text;
    }

    ///////////////////////////////////////
    public String[] getToUserExt() {
        return toUserExt;
    }

    public String[] getToDeptExt() {
        return toDeptExt;
    }

    public String[] getToTagExt() {
        return toTagExt;
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public String getToDept() {
        return toDept;
    }

    public void setToDept(String toDept) {
        this.toDept = toDept;
    }

    public String getToTag() {
        return toTag;
    }

    public void setToTag(String toTag) {
        this.toTag = toTag;
    }

    public MsgTypeDict getMsgType() {
        return msgType;
    }

    public void setMsgType(MsgTypeDict msgType) {
        this.msgType = msgType;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public YnDict getSafe() {
        return safe;
    }

    public void setSafe(YnDict safe) {
        this.safe = safe;
    }

}
