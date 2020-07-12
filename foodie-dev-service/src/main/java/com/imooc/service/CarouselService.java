package com.imooc.service;

import com.imooc.pojo.Carousel;

import java.util.List;

/**
 * @author GodXuan
 * @date 2019/11/30 0030 - 下午 4:04
 */
public interface CarouselService {
    List<Carousel> queryAll(Integer isShow);
}
