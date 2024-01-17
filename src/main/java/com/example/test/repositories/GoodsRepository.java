package com.example.test.repositories;

import com.example.test.entities.Goods;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface GoodsRepository extends CrudRepository<Goods, UUID> {
}
