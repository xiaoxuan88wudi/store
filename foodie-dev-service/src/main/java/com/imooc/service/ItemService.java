package com.imooc.service;

import com.imooc.pojo.Items;
import com.imooc.pojo.ItemsImg;
import com.imooc.pojo.ItemsParam;
import com.imooc.pojo.ItemsSpec;
import com.imooc.pojo.vo.CommentLevelCountsVO;
import com.imooc.pojo.vo.ShopcartVO;
import com.imooc.utils.PagedGridResult;

import java.util.List;

public interface ItemService {
    Items queryItemById(String id);
    List<ItemsImg> queryItemImgList(String id);
    List<ItemsSpec> queryItemSpecList(String id);
    ItemsParam queryItemParam(String id);
    CommentLevelCountsVO queryCommentCounts(String id);
    PagedGridResult queryPagedComments(String itemId, Integer level,
                                       Integer page, Integer pageSize);

    PagedGridResult searchItems(String keywords, String sort,
                               Integer page, Integer pageSize);

    PagedGridResult searchItems(Integer catId, String sort,
                                      Integer page, Integer pageSize);
    List<ShopcartVO> queryItemsBySpecIds(String specIds);
    ItemsSpec queryItemSpecById(String specId);
    String queryItemMainImgById(String itemId);
    void decreaseItemSpecStock(String specId, int buyCounts);
}
