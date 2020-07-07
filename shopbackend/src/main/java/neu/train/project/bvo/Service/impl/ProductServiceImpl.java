package neu.train.project.bvo.Service.impl;

import neu.train.common.utils.SecurityUtils;
import neu.train.framework.security.LoginUser;
import neu.train.project.bvo.Service.ProductService;
import neu.train.project.bvo.domain.SearchProduct;
import neu.train.project.bvo.domain.SimpleProduct;
import neu.train.project.bvo.domain.WishList;
import neu.train.project.bvo.domain.WishListExample;
import neu.train.project.bvo.domain.productDomain.Product;
import neu.train.project.bvo.mapper.WishListMapper;
import neu.train.project.bvo.mapper.productMapper.ProductMapper;
import neu.train.project.system.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductMapper productMapper;
    @Autowired
    WishListMapper wishListMapper;
    @Autowired
    CommonService commonService;

    @Override
    public Product getProductDetail(Integer proId) {
        return productMapper.selectByPrimaryKey(proId);
    }

    @Override
    public List<SimpleProduct> getWishListByDsrId() {
        Integer id=commonService.getDsrId();
        WishListExample wishListExample = new WishListExample();
        wishListExample.createCriteria().andDsrIdEqualTo(id);
        List<WishList> wishLists = wishListMapper.selectByExample(wishListExample);
        int[] proIds = wishLists.stream().mapToInt(WishList::getProId).toArray();
        List<SimpleProduct> results = new ArrayList<>();

        for (int proId:proIds){
            Product product=productMapper.selectByPrimaryKey(proId);
            SimpleProduct result = new SimpleProduct();
            result.setProId(proId);
            result.setTitle(product.getTitle());

            result.setImageUrl(product.getImages().get(0).getUri());
            result.setBrandName(product.getProductBrand().getNameEn());
            result.setCategoryName(product.getCategories().get(0).getCategoryName());
            result.setPrice(product.getRetailPrice());
            result.setManufacturerName(product.getManufacturer().getNameEn());
            results.add(result);
        }
        return results;
    }

    @Override
    public int deleteProInWishlistByProId(Integer proId) {
        SysUser user= SecurityUtils.getLoginUser().getUser();

        Integer id=commonService.getDsrId();

        WishListExample wishListExample = new WishListExample();
        wishListExample.createCriteria().andDsrIdEqualTo(id).andProIdEqualTo(proId);
        int i = wishListMapper.deleteByExample(wishListExample);
        return i;
    }

    @Override
    public int addProInWishListByProId(WishList wishList){
        SysUser sysUser = SecurityUtils.getLoginUser().getUser();
        int dsrId = commonService.getDsrId();
        wishList.setDsrId(dsrId);

        WishListExample wishListExample = new WishListExample();
        wishListExample.createCriteria().andProIdEqualTo(wishList.getProId()).andDsrIdEqualTo(dsrId);

        List<WishList> wishLists = wishListMapper.selectByExample(wishListExample);
        if (wishLists.size() !=0) {
            return -1;
        }
        wishList.setCreatedBy(sysUser.getUserName());
        wishList.setLastUpdateBy(sysUser.getUserName());
        return wishListMapper.insertSelective(wishList);
    }

    @Override
    public List<SearchProduct> getBrowseList(SimpleProduct product) {
        return productMapper.selectProductListBySimpleProduct(product);
    }

}
