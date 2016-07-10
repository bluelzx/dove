package com.gustz.dove.api.service.simple;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Assert;
import org.junit.Test;

import com.sinovatech.fw.api.service.DataService;
import com.sinovatech.fw.api.vo.AbstractBaseVo;
import com.gustz.dove.api.service.base.TestBase;

/**
 * TODO: 数据服务的基础测试
 * 
 * @since [Nov 24, 2014]
 */
@SuppressWarnings("rawtypes")
public abstract class DataServiceTestBase<VO extends AbstractBaseVo<ID>, ID extends Serializable> extends TestBase {

    private DataService<VO, ID> service;

    @Override
    public void setUp() throws Exception {
        super.setUp();

        if (service == null)
            throw new IllegalStateException("Please call setService(service) first.");
    }

    /**
     * Delete test
     */
    @Test
    public void testDelete() throws Exception {

        final ID id = service.save(prepareVo());

        final VO loaded = service.get(id);
        Assert.assertNotNull(loaded);

        service.delete(id);

        Assert.assertNull(service.get(id));
    }

    /**
     * Save test
     */
    @Test
    public void testSave() throws Exception {

        final VO vo = prepareVo();
        final ID id = service.save(vo);
        setId(vo, id);

        try {
            final VO loaded = service.get(id);
            Assert.assertNotNull(loaded);

            compareVo(loaded, vo);

        } finally {
            tryDeleting(id);
        }
    }

    /**
     * Update test
     */
    @Test
    public void testUpdate() throws Exception {

        final ID id = service.save(prepareVo());

        try {
            final VO loaded = service.get(id);
            Assert.assertNotNull(loaded);

            changeVo(loaded);

            service.update(loaded);

            final VO loaded2 = service.get(id);
            Assert.assertNotNull(loaded2);

            compareVo(loaded2, loaded);

        } finally {
            tryDeleting(id);
        }
    }

    /**
     * 尝试删除一个测试对象。
     * 
     * @param id
     */
    private void tryDeleting(ID id) {
        if (id == null)
            return;

        try {
            service.delete(id);
        } catch (Throwable tr) {
            System.out.println("=========================================================== \n"
                    + "An error occurs while trying deleting data after testing. \n "
                    + "It does not matter to the test result and you can ignore it. \n"
                    + "But it is strongly recommended that you should fix it. \n"
                    + "===========================================================");

            tr.printStackTrace();
        }
    }

    protected abstract void changeVo(VO vo) throws Exception;

    /**
     * 比较俩个VO对象是否相等。默认使用了反射方式将全部的属性转换为字符串后比较。
     * 
     * @param loaded
     * @param memoried
     * @throws Exception
     */
    protected void compareVo(VO loaded, VO memoried) throws Exception {
        String sloaded = desc(loaded), smemoried = desc(memoried);
        System.out.println("sloaded=:" + sloaded);
        System.out.println("");
        System.out.println("smemoried=:" + smemoried);
        Assert.assertEquals("数据检测结果不一样", smemoried, sloaded);
    }

    private static String desc(Object o) {
        return ToStringBuilder.reflectionToString(o, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    protected abstract VO prepareVo() throws Exception;

    protected final void setService(DataService<VO, ID> service) {
        this.service = service;
    }

    protected abstract void setId(VO vo, ID id);

}