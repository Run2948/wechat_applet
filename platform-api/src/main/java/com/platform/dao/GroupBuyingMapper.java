package com.platform.dao;

import com.platform.entity.GroupBuyingVo;

import java.util.List;
import java.util.Map;

/**
 * Dao
 *
 * @author xuyang
 * @email 295640759@qq.com
 * @date 2019-06-13 22:00:12
 */
public interface GroupBuyingMapper extends BaseDao<GroupBuyingVo> {

    List<GroupBuyingVo> queryLoseList(Map map);
}
