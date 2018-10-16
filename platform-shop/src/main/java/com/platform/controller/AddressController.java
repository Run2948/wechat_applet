package com.platform.controller;

import com.platform.entity.AddressEntity;
import com.platform.service.AddressService;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Controller
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-08-16 17:22:46
 */
@RestController
@RequestMapping("address")
public class AddressController {
    @Autowired
    private AddressService addressService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("address:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<AddressEntity> addressList = addressService.queryList(query);
        int total = addressService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(addressList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }


    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("address:info")
    public R info(@PathVariable("id") Integer id) {
        AddressEntity address = addressService.queryObject(id);

        return R.ok().put("address", address);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("address:save")
    public R save(@RequestBody AddressEntity address) {
        addressService.save(address);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("address:update")
    public R update(@RequestBody AddressEntity address) {
        addressService.update(address);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("address:delete")
    public R delete(@RequestBody Integer[] ids) {
        addressService.deleteBatch(ids);

        return R.ok();
    }
}
