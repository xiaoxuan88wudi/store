package com.imooc.service.impl;

import com.imooc.mapper.StuMapper;
import com.imooc.pojo.Stu;
import com.imooc.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author GodXuan
 * @date 2019/11/26 0026 - 下午 8:13
 */
@Service
public class StuServiceImpl implements StuService {

    @Autowired
    private StuMapper stuMapper;


    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Stu getStuInfo(int id) {
        return stuMapper.selectByPrimaryKey(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void saveStu() {
        Stu stu = new Stu();
        stu.setName("xuan");
        stu.setAge(24);
        stu.setId(1212);
        stuMapper.insert(stu);
    }


    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void updateStu(int id) {
        Stu stu = new Stu();
        stu.setId(id);
        stu.setName("xuanxuan");
        stu.setAge(25);
        stuMapper.updateByPrimaryKey(stu);
    }


    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void deleteStu(int id) {

        stuMapper.deleteByPrimaryKey(id);

    }
}
