package com.gustz.dove.mpcli.api.service.base;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.commons.lang3.time.FastDateFormat;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sinovatech.fw.dao.HqlDao;
import com.sinovatech.fw.query.util.QueryInfo;
import com.sinovatech.fw.query.util.QueryInfoBuilder;

/**
 * 
 * TODO: Test base
 *
 * @author ZHENFENG ZHANG
 * @since  [Feb 4, 2015]
 * 
 * @param <VO>
 * @param <PO>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:test-service-context.xml" })
public abstract class MpCliTestBase<VO extends Serializable> {

    protected final FastDateFormat dateDfmt = FastDateFormat.getInstance("yyyy-MM-dd");

    protected final FastDateFormat timeDfmt = FastDateFormat.getInstance("HH:mm:ss");

    protected final FastDateFormat datetimeDfmt = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");

    protected static String isDelField = "isDelete";

    protected static long sn;

    protected static String cliAppCode;

    protected static String devAcCode;

    protected static String appId;

    protected static String appSecret;

    @Autowired
    private HqlDao hqlDao;

    /**
     * 在测试之前做的准备工作。一般请使用这样的方式来Override：
     * <pre> @Override
     * public void setUp() {
     *   // 先调用父类的准备工作 
     *   super.setUp();
     * 
     *   // 再调用自身的准备工作
     * }
     * </pre>
     * @throws Exception 
     */
    @Before
    public void setUp() throws Exception {
        sn = System.currentTimeMillis();
        cliAppCode = "test-client";
        devAcCode = "gh_b931f645aae1";
        appId = "wx31de3a6960ef0648";
        appSecret = "069fdb727759488f11219c8a40fa5c50";
    }

    /**
     * 在测试完成后的清理工作。一般请使用这样的方式来Override:
     * <pre> @Override   
     * public void tearDown() {
     *   // 先做自身的清理工作
     *  
     *   // 再做父类的清理工作
     *   super.tearDown();
     * }
     * </pre>
     */
    @After
    public void tearDown() throws Exception {
        //null
    }

    /**
     * 比较俩个VO对象是否相等。默认使用了反射方式将全部的属性转换为字符串后比较。
     * 
     * @param loaded
     * @param memoried
     */
    protected void compareVo(VO loaded, VO memoried) {
        String sloaded = ToStringBuilder.reflectionToString(loaded, ToStringStyle.SHORT_PREFIX_STYLE);
        String smemoried = ToStringBuilder.reflectionToString(memoried, ToStringStyle.SHORT_PREFIX_STYLE);
        System.out.println(sloaded);
        System.out.println("");
        System.out.println(smemoried);
        Assert.assertEquals("数据检测结果不一样", smemoried, sloaded);
    }

    protected Date fmtDate(Date date) throws ParseException {
        return dateDfmt.parse(dateDfmt.format(date));
    }

    protected Date fmtTime(Date date) throws ParseException {
        return timeDfmt.parse(timeDfmt.format(date));
    }

    protected Date fmtDateTime(Date date) throws ParseException {
        return datetimeDfmt.parse(datetimeDfmt.format(date));
    }

    protected BigDecimal setScale(BigDecimal bg) {
        return bg.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 结果集转换为字符串
     * 
     * <pre>
     * Map集合类不支持
     * </pre>
     * @param obj
     * @return
     */
    protected final String toString(Object obj) {
        return ReflectionToStringBuilder.toString(obj);
    }

    protected void copyProperties(Object dest, Object src) {
        try {
            PropertyUtils.copyProperties(dest, src);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Get unique test VO
     * 
     * @param voClass
     * @param poClass
     * @return
     * @throws Exception
     */
    protected Object uniqueTestVo(Class<?> voClass, Class<?> poClass) throws Exception {
        return this.listTestVos(1, voClass, poClass, null, null).get(0);
    }

    /**
     * Get unique test VO
     * 
     * @param voClass
     * @param poClass
     * @param idValue
     * @return 
     * @throws Exception
     */
    protected Object uniqueTestVo(Class<?> voClass, Class<?> poClass, Object idValue) throws Exception {
        return this.listTestVos(1, voClass, poClass, new String[] { "id" }, new Object[] { idValue }).get(0);
    }

    /**
     * Get unique test VO
     * 
     * @param voClass
     * @param poClass
     * @param fields
     * @param values
     * @return
     * @throws Exception
     */
    protected Object uniqueTestVo(Class<?> voClass, Class<?> poClass, String[] fields, Object[] values) throws Exception {
        return this.listTestVos(1, voClass, poClass, fields, values).get(0);
    }

    /**
     * Get test VO list
     * 
     * @param limit
     * @param voClass
     * @param poClass
     * @return
     * @throws Exception
     */
    protected List<?> listTestVos(int limit, Class<?> voClass, Class<?> poClass) throws Exception {
        return this.listTestVos(limit, voClass, poClass, null, null);
    }

    /**
     * Get test VO list
     * 
     * @param limit
     * @param voClass
     * @param poClass
     * @param fields
     * @param values
     * @return
     * @throws Exception
     */
    protected List<?> listTestVos(int limit, Class<?> voClass, Class<?> poClass, String[] fields, Object[] values)
            throws Exception {
        List<Object> _voList = new ArrayList<Object>();
        QueryInfo info = this.getQueryInfo(poClass, fields, values);
        // do query
        List<?> _list = this.hqlDao.list(info.getSql(), 1, limit, info.getParArr());
        if (_list.size() > 0) {
            Object _vo = null;
            for (Object _po : _list) {
                _vo = Class.forName(voClass.getName()).newInstance();
                copyProperties(_vo, _po);
                _voList.add(_vo);
            }
        }
        return _voList;
    }

    /**
     * Get query info
     * 
     * @param poClass 
     * @param fields   
     * @param values
     * @return
     */
    private QueryInfo getQueryInfo(Class<?> poClass, String[] fields, Object[] values) {
        // build HQL
        QueryInfoBuilder builder = QueryInfoBuilder.ins("from " + poClass.getSimpleName() + " where 1=1 "); //
        if (fields != null && fields.length > 0 && values != null && values.length > 0) {
            for (int i = 0; i < fields.length; i++) {
                if (isDelField.equals(fields[i])) {
                    continue;
                }
                builder.andEq(fields[i], values[i]);
            }
        }
        if (this.getIsDelField(poClass) != null) { // is delete where
            builder.andEq(isDelField, "N");
        }
        return builder.build();
    }

    /**
     * Is exist field
     * 
     * @param clazz
     * @param fieldName
     * @return
     */
    private boolean isExistField(Class<?> clazz, String fieldName) {
        for (Field _field : clazz.getDeclaredFields()) {
            if (_field != null && fieldName.equals(_field.getName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Get is delete field
     * 
     * @param clazz
     * @return
     */
    private String getIsDelField(Class<?> clazz) {
        if (isExistField(clazz, isDelField)) {
            return isDelField;
        }
        return null;
    }
}