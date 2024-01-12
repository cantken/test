package goods.service;

import goods.dto.Goods;
import goods.repository.GoodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public class goodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsRepository goodsRepository;

    @Override
    public Goods save(Goods goods) {
        return goodsRepository.save(goods);
    }

    @Override
    public Optional<Goods> findById(Integer goodsId) {
        return goodsRepository.findById(goodsId);
    }

    @Override
    public Iterable<Goods> findAll() {
        return goodsRepository.findAll();
    }

    @Override
    public void deleteById(Integer goodsId) throws EmptyResultDataAccessException {
        goodsRepository.deleteById(goodsId);
    }
}
