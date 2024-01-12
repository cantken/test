package goods.service;

import goods.dto.Goods;
import java.util.Optional;

public interface GoodsService {

    Goods save(Goods goods);

    Optional<Goods> findById(Integer goodsId);

    Iterable<Goods>findAll();

    void deleteById(Integer goodsId);

}
