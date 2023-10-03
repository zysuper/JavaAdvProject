package com.example.demo.mapper;

import com.example.demo.Spu;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

public interface SpuMapper extends Mapper<Spu> {
    @Select({"select sp.id,sp.name,sp.images,sp.image from tb_spu sp where id=#{spuId} "})
    Spu queryBySpuid(String paramString);
}
