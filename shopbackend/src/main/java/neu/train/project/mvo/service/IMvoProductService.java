package neu.train.project.mvo.service;

import neu.train.project.mvo.domain.mvoImage;
import neu.train.project.mvo.domain.mvoProduct;
import neu.train.project.mvo.domain.mvoProductDescription;
import neu.train.project.mvo.domain.vo.MvoSearchProduct;
import neu.train.project.mvo.domain.vo.MvoSimpleProduct;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IMvoProductService {
    List<mvoProduct> getProductList(MvoSearchProduct product);

    mvoProduct getProductByProId(Integer id);


    void insertProduct(mvoProduct product);

    @Transactional
    List<mvoImage> getImagesByProId(Integer proId);

    void updateProduct(mvoProduct product);


    @Transactional
    void deleteProductByIds(int[] ids);

    boolean uploadProductImage(Integer proId, Integer imgId, Integer typeCd, String imageUrl);

    @Transactional
    List<mvoProductDescription> getDescriptionByProId(Integer proId);

    @Transactional
    void updateDescription(List<mvoProductDescription> productDescriptions);

    boolean deleteProductImageById(Integer imgId);

    void updateProductProStatus(Integer proId, String status);
}
