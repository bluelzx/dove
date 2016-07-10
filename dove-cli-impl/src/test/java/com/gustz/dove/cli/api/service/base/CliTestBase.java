package com.gustz.dove.cli.api.service.base;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.commons.lang3.time.FastDateFormat;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
public abstract class CliTestBase<VO extends Serializable> {

    protected final FastDateFormat dateDfmt = FastDateFormat.getInstance("yyyy-MM-dd");

    protected final FastDateFormat timeDfmt = FastDateFormat.getInstance("HH:mm:ss");

    protected final FastDateFormat datetimeDfmt = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");

    protected static String isDelField = "isDelete";

    protected static long sn;

    protected static String cliAppCode;

    protected static String devAcCode;

    protected static String appId;

    protected static String appSecret;

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
        cliAppCode = "oa-dayrpt";
        devAcCode = "tlx-9528";
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

}