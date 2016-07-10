package com.gustz.dove.api.dict.service.impl;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import com.sinovatech.fw.api.vo.Order;
import com.sinovatech.fw.api.vo.annotation.provider.DictTextProvider;
import com.sinovatech.fw.dao.SqlDao;
import com.sinovatech.fw.query.util.QueryInfo;
import com.sinovatech.fw.query.util.QueryInfoBuilder;
import com.sinovatech.fw.service.annotation.Logger4j;
import com.sinovatech.fw.service.impl.AbstractDataService;
import com.sinovatech.fw.util.New;
import com.sinovatech.rd.wcsb.api.dict.service.DictService;
import com.sinovatech.rd.wcsb.api.dict.vo.DictVo;
import com.gustz.dove.api.service.util.CommConstants;
import com.sinovatech.rd.wcsb.repo.dict.dao.DictDao;
import com.sinovatech.rd.wcsb.repo.dict.po.DictPo;

/**
 * 
 * TODO: 字典服务接口的实现
 *
 * @author ZHENFENG ZHANG
 * @since  [Jan 19, 2015]
 */
@Service
public class DictServiceImpl extends AbstractDataService<DictVo, DictPo, String> implements DictService, DictTextProvider,
        ApplicationListener<ContextRefreshedEvent> {

    private Map<String, List<InnerDict>> cacheMap = null;

    @Autowired
    private SqlDao sqlDao;

    @Autowired
    private DictDao dao;

    /**
     * Init method
     */
    @Logger4j("startup dict service.")
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        // 刷新数据字典
        refreshDict();
    }

    /**
     * Refresh dict
     */
    @Logger4j("refresh dict config.")
    @Override
    public void refreshDict() {
        Lock lock = new ReentrantLock();
        try {
            lock.lock();
            cacheMap = DataMocker.mockData(this);
        } finally {
            lock.unlock();
        }
    }

    @Autowired
    public void needDao(DictDao dao) {
        super.setDao(dao);
    }

    @Override
    public String getId(DictVo vo) {
        return vo.getId();
    }

    private static class DataMocker {

        static Map<String, List<InnerDict>> mockData(DictService dictService) {
            Map<String, List<InnerDict>> ret = new LinkedHashMap<String, List<InnerDict>>();

            List<DictVo> _list = dictService.listAll();
            if (_list != null && _list.size() > 0) {
                String _grCode = null;
                InnerDict innerDict = null;
                for (DictVo _vo : _list) {
                    _grCode = _vo.getGroupCode();
                    innerDict = new InnerDict(_vo.getDataKey().trim(), _vo.getDataValue().trim());
                    if (ret.get(_grCode) != null) {
                        ret.get(_grCode).add(innerDict);
                    } else {
                        List<InnerDict> _addList = New.list();
                        _addList.add(innerDict);
                        ret.put(_grCode, _addList);
                    }
                }
            }
            return ret;
        }
    }

    private static String findText(List<InnerDict> dicts, String key) {
        String _dictKey = null;
        for (InnerDict _dict : dicts) {
            _dictKey = _dict.getKey();
            if (_dictKey != null && _dictKey.equals(key)) {
                return _dict.getText();
            }
        }
        return null;
    }

    private static String findTexts(List<InnerDict> dicts, String[] keys) {
        StringBuilder sbd = new StringBuilder();
        for (String _tmpKey : keys) {
            if (_tmpKey != null) {
                for (InnerDict _dict : dicts) {
                    if (_tmpKey.equals(_dict.getKey())) {
                        sbd.append(_dict.getText()).append(",");
                    }
                }
            }
        }
        if (sbd.lastIndexOf(",") > -1) {
            sbd.deleteCharAt(sbd.lastIndexOf(","));
        }
        return sbd.toString();
    }

    /**
     * Get list by groupCode
     * 
     * @param groupCode
     * @return  List<'DictService.InnerDict'>
     */
    @Override
    public List<DictService.InnerDict> listGroup(String groupCode) {
        if (cacheMap == null || cacheMap.isEmpty()) {
            refreshDict();
        }
        List<DictService.InnerDict> ret = cacheMap.get(groupCode);
        if (ret == null) {
            throw new IllegalStateException("No dictionary found for groupCode: " + groupCode);
        }
        return ret;
    }

    /**
     * Get map by groupCode
     * 
     * @param groupCode
     * @return Map<'String, String'>
     */
    @Override
    public Map<String, String> mapGroup(String groupCode) {
        Map<String, String> ret = New.linkedMap();
        List<InnerDict> list = this.listGroup(groupCode);
        for (InnerDict _dict : list) {
            ret.put(_dict.getKey(), _dict.getText());
        }
        return ret;
    }

    /**
     * Get map by groupCode
     * 
     * @param groupCode
     * @return Map<'String, String'>
     */
    @Override
    public Map<String, String> mapGroup(Enum<?> groupCode) {
        return this.mapGroup(groupCode.name());
    }

    /**
     * Get text by groupCode and key
     * 
     * @param groupCode
     * @param key
     * @return text
     */
    @Override
    public String text(String groupCode, String key) {
        return findText(listGroup(groupCode), key);
    }

    /**
     * Get text by groupCode and key
     * 
     * @param groupCode
     * @param key
     * @return text
     */
    @Override
    public String text(Enum<?> groupCode, String key) {
        return this.text(groupCode.name(), key);
    }

    /**
     * Get texts by groupCode and keys
     * 
     * @param groupCode
     * @param keys
     * @return text1,text2,text3...
     */
    @Override
    public String texts(String groupCode, String[] keys) {
        return findTexts(listGroup(groupCode), keys);
    }

    /**
     * Get texts by groupCode and keys
     * 
     * @param groupCode
     * @param keys
     * @return text1,text2,text3...
     */
    @Override
    public String texts(Enum<?> groupCode, String[] keys) {
        return this.texts(groupCode.name(), keys);
    }

    /**
     * 是否已存在KEY
     * 
     * @param id
     * @param groupCode
     * @param dataKey
     * @return
     */
    @Override
    public boolean isExistKey(String id, String groupCode, String dataKey) {
        if (StringUtils.isEmpty(groupCode)) {
            throw new IllegalArgumentException("Argument groupCode is null/empty!");
        }
        if (StringUtils.isBlank(dataKey)) {
            return false;
        }
        return this.isExistByKw(new DictVo(id, groupCode, dataKey, null));
    }

    /**
     * 是否已存在VALUE
     * 
     * @param id
     * @param groupCode
     * @param dataValue
     * @return
     */
    @Override
    public boolean isExistValue(String id, String groupCode, String dataValue) {
        if (StringUtils.isEmpty(groupCode)) {
            throw new IllegalArgumentException("Argument groupCode is null/empty!");
        }
        if (StringUtils.isBlank(dataValue)) {
            return false;
        }
        return this.isExistByKw(new DictVo(id, groupCode, null, dataValue));
    }

    /**
     * 是否已存在该记录
     * 
     * @param vo
     * @return
     */
    private boolean isExistByKw(DictVo vo) {
        String _id = vo.getId();
        if (StringUtils.isNotBlank(_id)) { // update
            String _gid = vo.getGroupCode();
            DictPo _oldPo = this.dao.get(_id);
            if (_gid.equals(_oldPo.getGroupCode())) {
                String _dkey = vo.getDataKey();
                if (StringUtils.isNotBlank(_dkey) && _dkey.equals(_oldPo.getDataKey())) {
                    return false; // 1.没有改变数据键
                }
                String _dvalue = vo.getDataValue();
                if (StringUtils.isNotBlank(_dvalue) && _dvalue.equals(_oldPo.getDataValue())) {
                    return false; // 1.没有改变数据值
                }
            }
        }
        // 2. save/update
        vo.setId(null);
        return (this.ecCount(vo) > 0);
    }

    /**
     * 分页查询
     * 
     * @param search
     * @param start
     * @param limit
     * @param orders
     * @return
     */
    @Override
    public List<DictVo> ecList(DictVo search, int start, int limit, List<Order> orders) {
        QueryInfo queryInfo = prepareQuery(search).order(orders).build();

        List<DictVo> _list = sqlDao.list(queryInfo.getSql(), start, limit, DictVo.class, queryInfo.getParArr());
        for (DictVo _vo : _list) {
            this.setDict(_vo);
        }
        return _list;
    }

    private void setDict(DictVo vo) {
        String text = "否";
        if (CommConstants.YES.equals(vo.getIsEncrypt())) {
            text = "是";
        }
        vo.setIsEncryptText(text);
    }

    /**
     * 查询全部
     * 
     * @return
     */
    @Override
    public List<DictVo> listAll() {
        QueryInfo queryInfo = prepareQuery(new DictVo()).build();

        return sqlDao.listAll(queryInfo.getSql(), DictVo.class, queryInfo.getParArr());
    }

    /**
     * 查询总记录数
     * 
     * @param search
     * @return
     */
    @Override
    public int ecCount(DictVo search) {
        QueryInfo queryInfo = prepareQuery(search).build();
        return sqlDao.count(queryInfo.getCountSql(), queryInfo.getParArr());
    }

    private QueryInfoBuilder prepareQuery(DictVo search) {
        String sql = "select t.*,t1.GROUP_NAME from WCSB_DICT t,WCSB_DICT_GROUP t1 where 1=1 and t.GROUP_CODE=t1.GROUP_CODE ";
        QueryInfoBuilder builder = QueryInfoBuilder.ins(sql) //
                .andEq("t.DATA_KEY", search.getDataKey()) //
                .andEq("t.DATA_VALUE", search.getDataValue())
                //
                .andContains("t.DATA_KEY", search.getDataKeyLk()) //
                .andContains("t.DATA_VALUE", search.getDataValueLk());
        // 组别
        builder.andEq("t1.GROUP_CODE", search.getGroupCode())//
                .andContains("t1.GROUP_NAME", search.getGroupName()) //
                .andEq("t.ID", search.getId());
        //
        // order by
        builder.orderAsc("t.SN");

        return builder;
    }

    /**
     * 保存字典
     * 
     * @param vo
     * @return
     */
    @Override
    public String save(DictVo vo) {
        DictPo _po = this.vo2Po(vo);
        _po.setCreateTime(new Date());
        // 加密数据值
        _po = this.encrypt(_po);
        return this.dao.save(_po);
    }

    /**
     * 更新字典
     * 
     * @param vo
     */
    @Override
    public void update(DictVo vo) {
        DictPo _newPo = this.vo2Po(vo); // 页面新值
        DictPo _oldPo = this.dao.get(vo.getId()); // 数据库旧值
        //不能改变的值
        _newPo.setCreateTime(_oldPo.getCreateTime());
        // 加密数据值
        _newPo = this.encrypt(_newPo);
        this.dao.update(_newPo);
    }

    /**
     * Encrypt data value
     * 
     * @param po
     * @return
     */
    private DictPo encrypt(DictPo po) {
        // null
        return po;
    }

    /**
     * 按ID获取字典
     * 
     * @param id
     * @return 
     */
    @Override
    public DictVo get(String id) {
        return this.po2Vo(this.dao.get(id));
    }

    /**
     * 是否存在外键的字典
     *  
     * @param groupCode
     * @return
     */
    @Override
    public boolean isExistFkDict(String groupCode) {
        if (StringUtils.isEmpty(groupCode)) {
            throw new IllegalArgumentException("Parameter groupCode is " + groupCode + "!");
        }
        return (this.ecCount(new DictVo(null, groupCode)) > 0);
    }

}
