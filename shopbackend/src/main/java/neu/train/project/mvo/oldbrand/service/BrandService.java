package neu.train.project.mvo.oldbrand.service;

import neu.train.project.mvo.oldbrand.pojo.BrdBrand;

import java.util.HashMap;


public interface BrandService {
    BrdBrand createBrand(HashMap<String,Object> data);
}
