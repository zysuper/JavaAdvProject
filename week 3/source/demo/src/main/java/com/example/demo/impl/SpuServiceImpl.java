package com.example.demo.impl;

import com.example.demo.*;
import com.example.demo.mapper.SkuMapper;
import com.example.demo.mapper.SpuMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SpuServiceImpl implements SpuService {
    @Autowired
    private SpuMapper spuMapper;
    @Autowired
    private SkuMapper skuMapper;

    private static final Logger log = LoggerFactory.getLogger(SpuServiceImpl.class);
    public Goods findBySpuId(String spuId) {
        Spu spu = this.spuMapper.queryBySpuid(spuId);
        List<Sku> skuList = this.skuMapper.querySkusByspuId(spuId);
        Goods goods = new Goods();
        goods.setSpu(spu);
        goods.setSkuList(skuList);
        return goods;
    }

}

