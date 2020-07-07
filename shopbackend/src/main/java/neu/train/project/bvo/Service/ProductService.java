package neu.train.project.bvo.Service;

import neu.train.project.bvo.domain.SearchProduct;
import neu.train.project.bvo.domain.SimpleProduct;
import neu.train.project.bvo.domain.WishList;
import neu.train.project.bvo.domain.productDomain.Product;

import java.util.List;

public interface ProductService {


    Product getProductDetail(Integer proId) ;

    List<SimpleProduct> getWishListByDsrId();

    int deleteProInWishlistByProId(Integer proId);

    int addProInWishListByProId(WishList wishList);

    List<SearchProduct> getBrowseList(SimpleProduct product);
}
