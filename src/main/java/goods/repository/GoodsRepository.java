package goods.repository;

import goods.dto.Goods;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.repository.CrudRepository;

public interface GoodsRepository extends CrudRepository<Goods,Integer> {
}
