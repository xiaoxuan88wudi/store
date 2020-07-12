package com.imooc.service;

import com.imooc.pojo.Stu;

/**
 * @author GodXuan
 * @date 2019/11/26 0026 - 下午 8:10
 */
public interface StuService {
    public Stu getStuInfo(int id);
    public void saveStu();
    public void updateStu(int id);
    public void deleteStu(int id);
}
