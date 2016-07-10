/*
 * @(#)SysDictGroupPos.java
 *
 * @Copyright(c) 2015 Beijing Sinova Technologies team. All rights reserved.
 *
 */
package com.gustz.dove.dev.data.sys.pos;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.util.StringUtils;

import com.sinovatech.fw.setup.pack.data.BasePos;
import com.sinovatech.rd.wcsb.repo.dict.po.DictGroupPo;

/**
 * TODO: SysDictGroupPo array
 *
 * @author ZHENFENG ZHANG
 * @since  [Jan 3, 2015]
 */
@XmlRootElement
public class DictGroupPos extends BasePos<DictGroupPo, String> {

    private static final long serialVersionUID = 1L;

    private List<DictGroupPo> poList = new ArrayList<DictGroupPo>();

    @Override
    protected Map<FkPoInfo, Class<?>> getFkPoInfoMap() {
        return null;
    }

    @XmlElements({ @XmlElement(name = "sysDictGroupPo", type = DictGroupPo.class) })
    @Override
    public List<DictGroupPo> getPoList() throws Exception {
        return this.poList;
    }

    @Override
    public void setPoList(List<DictGroupPo> poList) {
        if (poList != null && poList.size() > 0) { // delete sub data
            List<DictGroupPo> _list = new CopyOnWriteArrayList<DictGroupPo>(poList);
            for (DictGroupPo _po : _list) {
                if (_po != null && (StringUtils.isEmpty(_po.getGroupCode()) || StringUtils.isEmpty(_po.getGroupName()))) {
                    _list.remove(_po);
                }
            }
            poList = _list;
        }
        this.poList = poList;
    }

}
