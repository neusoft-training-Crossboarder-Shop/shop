package neu.train.project.bvo.Service;

import neu.train.project.bvo.domain.SimpleProduct;
import neu.train.project.bvo.domain.WishList;
import neu.train.project.bvo.domain.productDomain.Product;

import java.util.List;

public interface ProductService {


    public Product getProductDetail(Integer proId) ;

    public List<SimpleProduct> getWishListByDsrId();

    public int deleteProInWishlistByProId(Integer proId);

    int addProInWishListByProId(WishList wishList);
}
