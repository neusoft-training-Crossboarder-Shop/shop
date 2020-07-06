package neu.train.project.bvo.Service.impl;

import neu.train.project.bvo.Service.ProductService;
import neu.train.project.bvo.domain.productDomain.Product;
import neu.train.project.bvo.mapper.productMapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductMapper productMapper;
    @Override
    public Product getProductDetail(Integer proId) {
        return productMapper.selectByPrimaryKey(proId);
    }
}
