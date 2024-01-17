package com.example.test.services;

import com.example.test.entities.Goods;
import com.example.test.entities.SystemUser;
import com.example.test.repositories.GoodsRepository;
import com.example.test.repositories.SystemUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private SystemUserRepository systemUserRepository;

    @Autowired
    private GoodsRepository goodsRepository;

    @Override
    public Goods save(Goods goods) throws RuntimeException {
        UUID uuid = goods.getCrUser().getUserId();

        SystemUser user = systemUserRepository.findById(uuid).orElseThrow(() -> new RuntimeException("user not found"));

        goods.setCrUser(user);

        return goodsRepository.save(goods);
    }

    @Override
    public Optional<Goods> findById(UUID goodsId) throws EmptyResultDataAccessException {
        return goodsRepository.findById(goodsId);
    }

    @Override
    public Iterable<Goods> findAll() {
        return goodsRepository.findAll();
    }

    @Override
    public Goods update(UUID goodsId, Goods goods) throws RuntimeException {
        Goods updated = goodsRepository.findById(goodsId).orElseThrow(() -> new RuntimeException("goods not found"));;

        UUID uuid = goods.getUpUser().getUserId();
        SystemUser user = systemUserRepository.findById(uuid).orElseThrow(() -> new RuntimeException("user not found"));

        updated.setUpUser(user);
        updated.setName(goods.getName());
        return goodsRepository.save(goods);
    }

    @Override
    public void deleteById(UUID goodsId) throws EmptyResultDataAccessException {
        goodsRepository.deleteById(goodsId);
    }
}
