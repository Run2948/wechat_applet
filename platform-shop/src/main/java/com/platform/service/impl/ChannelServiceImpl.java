package com.platform.service.impl;

import com.platform.dao.ChannelDao;
import com.platform.entity.ChannelEntity;
import com.platform.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Service实现类
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-08-22 19:19:56
 */
@Service("channelService")
public class ChannelServiceImpl implements ChannelService {
    @Autowired
    private ChannelDao channelDao;

    @Override
    public ChannelEntity queryObject(Integer id) {
        return channelDao.queryObject(id);
    }

    @Override
    public List<ChannelEntity> queryList(Map<String, Object> map) {
        return channelDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return channelDao.queryTotal(map);
    }

    @Override
    public int save(ChannelEntity channel) {
        return channelDao.save(channel);
    }

    @Override
    public int update(ChannelEntity channel) {
        return channelDao.update(channel);
    }

    @Override
    public int delete(Integer id) {
        return channelDao.delete(id);
    }

    @Override
    public int deleteBatch(Integer[] ids) {
        return channelDao.deleteBatch(ids);
    }
}
