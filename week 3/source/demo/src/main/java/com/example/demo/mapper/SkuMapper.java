package com.example.demo.mapper;

import com.example.demo.Sku;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SkuMapper extends Mapper<Sku> {
    Sku selectById(String paramString);

    @Update({"update tb_sku set num = num - #{num}, sale_num = sale_num + #{num} where id=#{skuId} and num >= #{num}"})
    void decrCount(@Param("skuId") String paramString, @Param("num") Integer paramInteger);

    @Update({"update tb_sku set num = num + #{num}, sale_num = sale_num - #{num} where id=#{skuId}"})
    void incrCount(@Param("skuId") String paramString, @Param("num") Integer paramInteger);

    @Select({"select sk.id, sk.name,sk.image,sk.images,sk.price from tb_sku sk where sk.spu_id=#{spuId} ;"})
    List<Sku> querySkusByspuId(String paramString);
}
