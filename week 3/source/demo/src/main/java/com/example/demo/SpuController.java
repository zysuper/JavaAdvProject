package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping({"/spu"})
public class SpuController {
    @Autowired
    private SpuService spuService;

    @GetMapping({"/goods/{spuId}"})
    public Result findGoodsBySpuId(@PathVariable String spuId) {
        Goods goods = this.spuService.findBySpuId(spuId);
        return new Result(true, Integer.valueOf(20000), "", goods);
    }

    @GetMapping({"/goods/slow/{spuId}"})
    public Result findGoodsBySpuIdTwo(@PathVariable String spuId) {
        Goods goods = this.spuService.findBySpuId(spuId);
        try {
            TimeUnit.MILLISECONDS.sleep(500L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new Result(true, Integer.valueOf(20000), "", goods);
    }
}
