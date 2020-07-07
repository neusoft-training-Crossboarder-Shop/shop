package neu.train.project.mvo.brand.service;

import neu.train.project.mvo.brand.pojo.BrdBrand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;


public interface BrandService {
    BrdBrand createBrand(HashMap<String,Object> data);
}
