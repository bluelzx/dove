package com.gustz.dove.dev.data;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.sinovatech.fw.dao.SqlDao;
import com.sinovatech.fw.setup.pack.data.resolver.dao.BaseSqlDao;

/**
 * TODO: SQL DAO builder
 *
 * @author ZHENFENG ZHANG
 * @since  [May 27, 2015]
 */
public class SqlDaoBuilder implements BaseSqlDao {

    @Autowired
    private SqlDao sqlDao;

    @Override
    public List<?> listAll(String sql, Class<?> voCls) {
        return sqlDao.listAll(sql, voCls);
    }

    @Override
    public List<?> list(String sql, Class<?> voCls, Object... args) {
        return sqlDao.list(sql, 1, Integer.MAX_VALUE, voCls, args);
    }

    @Override
    public Object unique(String sql, Class<?> voCls, Object... args) {
        List<?> _list = sqlDao.list(sql, 1, 1, voCls, args);
        if (_list != null && _list.size() > 0) {
            return _list.get(0);
        }
        return null;
    }

    @Override
    public int execUpdate(String sql, Object... args) {
        return sqlDao.execUpdate(sql, args);
    }

}
