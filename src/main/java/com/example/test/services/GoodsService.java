package com.example.test.services;

import com.example.test.entities.Goods;

import java.util.Optional;
import java.util.UUID;

public interface GoodsService {

    Goods save(Goods goods);

    Optional<Goods> findById(UUID goodsId);

    Iterable<Goods>findAll();

    Goods update(UUID goodsId, Goods goods);

    void deleteById(UUID goodsId);

}
