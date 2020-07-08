package neu.train.project.mvo.brand.service;

import neu.train.project.mvo.brand.pojo.ManManufacturer;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CompanyService {
    List<ManManufacturer> listCompany(int sysUserId);

}
