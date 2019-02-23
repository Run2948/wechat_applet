package com.platform.service.impl;

import com.platform.dao.AddressDao;
import com.platform.dao.CustomerDao;
import com.platform.entity.AddressEntity;
import com.platform.entity.CustomerEntity;
import com.platform.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 客户管理Service实现类
 */
@Service("customerService")
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private AddressDao addressDao;
    @Autowired
    private AddressDao addressVo;
    @Override
    public CustomerEntity queryObject(Integer id) {
        return customerDao.queryObject(id);
    }
    public CustomerEntity queryObject(CustomerEntity customerEntity) {
        CustomerEntity cVo=customerDao.queryObject(customerEntity);
        AddressEntity addressEntity=addressDao.queryObject(cVo.getAddressUserId());
        cVo.setAddressEntity(addressEntity);
        return cVo;
    }
    @Override
    public List<CustomerEntity> queryList(Map<String, Object> map) {
        return customerDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return customerDao.queryTotal(map);
    }

    @Override
    public int save(CustomerEntity customer) {
        return customerDao.save(customer);
    }

    @Override
    public int update(CustomerEntity customer) {
        AddressEntity addressEntity=new AddressEntity();
        addressEntity.setId((customer.getAddressUserId()));
        addressEntity.setUserName(customer.getUname());
        addressEntity.setCityName(customer.getUname());
        addressEntity.setTelNumber(customer.getMobile());
        addressEntity.setProvinceName(customer.getAddressEntity().getProvinceName());
        addressEntity.setCityName(customer.getAddressEntity().getCityName());
        addressEntity.setCountyName(customer.getAddressEntity().getCountyName());
        addressEntity.setDetailInfo(customer.getAddressEntity().getDetailInfo());
        addressVo.update(addressEntity);
        customerDao.update(customer);
        return customerDao.update(customer);
    }

    @Override
    public int delete(Integer id) {
        return customerDao.delete(id);
    }

    @Override
    public int deleteBatch(Integer[] ids) {
        return 0;
    }
}
