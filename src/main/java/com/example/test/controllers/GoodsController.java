package com.example.test.controllers;


import com.example.test.entities.Goods;
import com.example.test.services.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.keyvalue.core.IterableConverter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    @PostMapping("/goods")
    public ResponseEntity<Goods> add(@RequestBody Goods goods) {
        try {
            Goods addGoods = goodsService.save(goods);
            return ResponseEntity.status(HttpStatus.OK).body(addGoods);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/goods")
    public ResponseEntity<List<Goods>> findAll() {
        List<Goods> findAllGoods = IterableConverter.toList(goodsService.findAll());
        if (!findAllGoods.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(findAllGoods);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/goods/{goodsId}")
    public ResponseEntity<Goods> findById(@PathVariable UUID goodsId) {
        Optional<Goods> findByGoodsId = goodsService.findById(goodsId);
        ResponseEntity<Goods> response;
        if (findByGoodsId.isPresent()) {
            Goods goods = findByGoodsId.get();
            response = ResponseEntity.status(HttpStatus.OK).body(goods);
        } else {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return response;
    }

    @DeleteMapping("/goods/{goodsId}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID goodsId) {
        try {
            goodsService.deleteById(goodsId);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @PutMapping("/goods/{goodsId}")
    public ResponseEntity<Goods> update(@PathVariable UUID goodsId,
                                        @RequestBody Goods goods) {
        try {
            Goods updateGoods = goodsService.update(goodsId, goods);
            return ResponseEntity.status(HttpStatus.OK).body(updateGoods);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}
