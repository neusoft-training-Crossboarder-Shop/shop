package neu.train.project.mvo.service;

import neu.train.project.mvo.domain.mvoManufacturer;

public interface IManufacturerService {

    mvoManufacturer getManufacturer();

    void insertManufacturer(mvoManufacturer manufacturer);

    void updateManufacturer(mvoManufacturer manufacturer);


}
