package com.gustz.dove.api.dict.service;

import java.util.List;
import java.util.Map;

import com.gustz.dove.api.dict.vo.DictVo;
import com.sinovatech.fw.api.service.DataService;
import com.sinovatech.fw.api.vo.Order;

/**
 * 
 * TODO: 字典服务接口
 *
 * @author ZHENFENG ZHANG
 * @since  [Jan 19, 2015]
 */
public interface DictService extends DataService<DictVo, String> {

    class InnerDict {

        private String key;

        private String text;

        @SuppressWarnings("unused")
        private InnerDict() {
        }

        public String getKey() {
            return key;
        }

        public InnerDict(String key, String text) {
            super();
            this.key = key;
            this.text = text;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

    }

    /**
     * Get list by groupCode
     * 
     * @param groupCode
     * @return  List<'DictService.InnerDict'>
     */
    List<DictService.InnerDict> listGroup(String groupCode);

    /**
     * Get map by groupCode
     * 
     * @param groupCode
     * @return Map<'String, String'>
     */
    Map<String, String> mapGroup(String groupCode);

    /**
     * Get map by groupCode
     * 
     * @param groupCode
     * @return Map<'String, String'>
     */
    Map<String, String> mapGroup(Enum<?> groupCode);

    /**
     * Get text by groupCode and key
     * 
     * @param groupCode
     * @param key
     * @return text
     */
    String text(String groupCode, String key);

    /**
     * Get text by groupCode and key
     * 
     * @param groupCode
     * @param key
     * @return text
     */
    String text(Enum<?> groupCode, String key);

    /**
     * Get texts by groupCode and keys
     * 
     * @param groupCode
     * @param keys
     * @return text1,text2,text3...
     */
    String texts(String groupCode, String[] keys);

    /**
     * Get texts by groupCode and keys
     * 
     * @param groupCode
     * @param keys
     * @return text1,text2,text3...
     */
    String texts(Enum<?> groupCode, String[] keys);

    /**
     * Refresh dict
     */
    void refreshDict();

    /**
     * 是否已存在KEY
     * 
     * @param id
     * @param groupCode
     * @param dataKey
     * @return
     */
    boolean isExistKey(String id, String groupCode, String dataKey);

    /**
     * 是否已存在VALUE
     * 
     * @param id
     * @param groupCode
     * @param dataValue
     * @return
     */
    boolean isExistValue(String id, String groupCode, String dataValue);

    /**
     * 分页查询
     * 
     * @param search
     * @param start
     * @param limit
     * @param orders
     * @return
     */
    List<DictVo> ecList(DictVo search, int start, int limit, List<Order> orders);

    /**
     * 查询全部
     * 
     * @return
     */
    List<DictVo> listAll();

    /**
     * 查询总记录数
     * 
     * @param search
     * @return
     */
    int ecCount(DictVo search);

    /**
     * 是否存在外键的字典
     * 
     * @param groupCode
     * @return
     */
    boolean isExistFkDict(String groupCode);

}
