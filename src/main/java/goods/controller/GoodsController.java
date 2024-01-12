package goods.controller;


import com.mysql.cj.x.protobuf.Mysqlx;
import goods.dto.Goods;
import goods.service.GoodsService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.keyvalue.core.IterableConverter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @PostMapping("/goods")
    public ResponseEntity<Goods> add(@RequestBody Goods goods) {
        Goods addGoods = goodsService.save(goods);
        if (addGoods != null) {
            return ResponseEntity.status(HttpStatus.OK).body(addGoods);
        } else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @GetMapping("/goods")
    public ResponseEntity<List<Goods>> findAll() {
        List<Goods> findAllGoods = IterableConverter.toList(goodsService.findAll());
        if (!findAllGoods.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(findAllGoods);
        } else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @GetMapping("/goods/{goodsId}")
    public ResponseEntity<Goods> findById(@PathVariable Integer goodsId) {
        Optional<Goods> findByGoodsId = goodsService.findById(goodsId);
        return findByGoodsId.map(
                goods -> ResponseEntity.status(HttpStatus.OK).body(goods))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    @DeleteMapping("/goods/{goodsId}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer goodsId) {
        try {
            goodsService.deleteById(goodsId);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Error err) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @PutMapping("/goods/{goodsId}")
    public ResponseEntity<Goods> update(@PathVariable Integer goodsId,
                                        @RequestBody Goods goods) {
        //先搜尋有無此筆資料
        Goods updateGoods = goodsService.findById(goodsId).orElse(null);
        //如果不等於null則存入新資料
        if (updateGoods != null) {
            updateGoods.setName(goods.getName());
            goodsService.save(updateGoods);
            return ResponseEntity.status(HttpStatus.OK).body(updateGoods);
        }else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(updateGoods);
        }
    }



}
