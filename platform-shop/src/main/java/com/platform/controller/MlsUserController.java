package com.platform.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.platform.entity.MlsUserEntity2;
import com.platform.service.MlsUserService;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;

/**
 * Controller
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-08-16 15:02:28
 */
@RestController
@RequestMapping("mlsuser")
public class MlsUserController {
    @Autowired
    private MlsUserService mlsUserService;
    /**
     * 查看列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
    	params.put("fidIsNull", "fidIsNull");
        Query query = new Query(params);
        List<MlsUserEntity2> userList = mlsUserService.queryList(query);
        int total = mlsUserService.queryTotal(query);
        PageUtils pageUtil = new PageUtils(userList, total, query.getLimit(), query.getPage());
        return R.ok().put("page", pageUtil);
    }


    /**
     * 推广关系变更
     * @param user
     * @return
     */
//    @RequestMapping("/updatePromoter")
//    public R updatePromoter(@RequestBody MlsUserVo user) {
//        Map map=new HashMap();
//        map.put("mobile",user.getMobile());
//        List<UserEntity> userList=userService.queryList(map);
//        if(userList.size()>0){
//            user.setPromoterId(userList.get(0).getId());
//            user.setPromoterName(userList.get(0).getUsername());
//        }
//        else{
//         return   R.error().put("error","该用户不存在");
//        }
//         userService.updatePromoter(user);
//        return R.ok().put("user", user);
//    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id) {
    	MlsUserEntity2 user = mlsUserService.queryObject(id);
        return R.ok().put("user", user);
    }

//    /**
//     * 保存
//     */
//    @RequestMapping("/save")
//    @RequiresPermissions("user:save")
//    public R save(@RequestBody UserEntity user) {
//        userService.save(user);
//
//        return R.ok();
//    }
//
    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody MlsUserEntity2 user) {
    	mlsUserService.updatefx(user);
        return R.ok();
    }
//
//    /**
//     * 删除
//     */
//    @RequestMapping("/delete")
//    @RequiresPermissions("user:delete")
//    public R delete(@RequestBody Integer[] ids) {
//        userService.deleteBatch(ids);
//
//        return R.ok();
//    }
//
//    /**
//     * 查看所有列表
//     */
//    @RequestMapping("/queryAll")
//    public R queryAll(@RequestParam Map<String, Object> params) {
//
//        List<UserEntity> userList = userService.queryList(params);
//        for(UserEntity user : userList) {
//        	user.setUsername(Base64.decode(user.getUsername()));
//        }
//        return R.ok().put("list", userList);
//    }
//
//    /**
//     * 总计
//     */
//    @RequestMapping("/queryTotal")
//    public R queryTotal(@RequestParam Map<String, Object> params) {
//        int sum = userService.queryTotal(params);
//
//        return R.ok().put("userSum", sum);
//    }
//    /**
//     * 导出会员
//     */
//    @RequestMapping("/export")
////    @RequiresPermissions("user:export")
//    public R export(@RequestParam Map<String, Object> params, HttpServletResponse response) {
//
//        List<UserEntity> userList = userService.queryList(params);
//
//        ExcelExport ee = new ExcelExport("会员列表");
//
//        String[] header = new String[]{"会员名称", "昵称", "性别", "是否实名", "手机号码", "真实姓名", "身份证号"};
//
//        List<Map<String, Object>> list = new ArrayList<>();
//
//        if (userList != null && userList.size() != 0) {
//            for (UserEntity userEntity : userList) {
//                LinkedHashMap<String, Object> map = new LinkedHashMap<>();
//                map.put("USERNAME", Base64.decode(userEntity.getUsername()));
//                map.put("Nickname", Base64.decode(userEntity.getNickname()));
//                map.put("GENDER", userEntity.getGender() == 1 ? "男" : (userEntity.getGender() == 2 ? "女" : "未知"));
//                map.put("isReal", (userEntity.getIsReal().equals("1")? "未实名" :"实名"));
//                map.put("MOBILE", userEntity.getMobile());
//                map.put("realName", userEntity.getRealName());
//                map.put("idCard", userEntity.getIdCard());
//                list.add(map);
//            }
//        }
//
//        ee.addSheetByMap("会员", list, header);
//        ee.export(response);
//        return R.ok();
//    }
}
