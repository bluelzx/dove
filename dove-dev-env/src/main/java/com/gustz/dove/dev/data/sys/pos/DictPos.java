/*
 * @(#)SysDictPos.java
 *
 * @Copyright(c) 2015 Beijing Sinova Technologies team. All rights reserved.
 *
 */
package com.gustz.dove.dev.data.sys.pos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.StringUtils;

import com.sinovatech.fw.setup.pack.data.BasePos;
import com.sinovatech.rd.wcsb.repo.dict.po.DictGroupPo;
import com.sinovatech.rd.wcsb.repo.dict.po.DictPo;

/**
 * TODO: SysDictPo array
 *
 * @author ZHENFENG ZHANG
 * @since  [Jan 3, 2015]
 */
@XmlRootElement
public class DictPos extends BasePos<DictPo, String> {

    private static final long serialVersionUID = 1L;

    private List<DictPo> poList = new ArrayList<DictPo>();

    //    @Override
    //    public Map<String, String> getFkSqlWhereMap() {
    //        Map<String, String> _map = new HashMap<String, String>();
    //        _map.put("groupCode", " IS_DELETE='N' ");
    //        return _map;
    //    }

    @Override
    protected Map<FkPoInfo, Class<?>> getFkPoInfoMap() {
        // FK PO info map
        Map<FkPoInfo, Class<?>> _map = new HashMap<FkPoInfo, Class<?>>();
        _map.put(new FkPoInfo("groupCode", "groupCode"), DictGroupPo.class);
        return _map;
    }

    @XmlElements({ @XmlElement(name = "sysDictPo", type = DictPo.class) })
    @Override
    public List<DictPo> getPoList() {
        return poList;
    }

    @Override
    public void setPoList(List<DictPo> poList) {
        if (poList != null && poList.size() > 0) { // delete sub data
            List<DictPo> _list = new CopyOnWriteArrayList<DictPo>(poList);
            for (DictPo _po : _list) {
                if (_po != null
                        && (StringUtils.isEmpty(_po.getGroupCode()) || StringUtils.isEmpty(_po.getDataKey()) || StringUtils
                                .isEmpty(_po.getDataValue()))) {
                    _list.remove(_po);
                }
            }
            poList = _list;
        }
        this.poList = poList;
    }

}
