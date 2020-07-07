package neu.train.project.mvo.service;

import com.github.pagehelper.PageHelper;
import neu.train.project.mvo.mapper.ManManufacturerMapper;
import neu.train.project.mvo.domain.ManManufacturer;
import neu.train.project.mvo.domain.ManManufacturerExample;
import neu.train.project.mvo.domain.ManManufacturer.Column;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ManManufacturerService {
    @Resource
    private ManManufacturerMapper brandMapper;
    //    private Column[] columns = new Column[]{Column.brdId,Column.manId, Column.name, Column.desc, Column.picUrl, Column.floorPrice};
    private Column[] columns = new Column[]{Column.manId,Column.sysUserId,Column.nameEn,Column.nameCn,Column.gmcReportType,Column.gmcReportUrl,Column.description,Column.createdBy,Column.createTime,Column.lastUpdateBy,Column.lastUpdateTime,Column.callCnt,Column.stsCd};
    public List<ManManufacturer> query(Integer page, Integer limit, String sort, String order) {
        ManManufacturerExample example = new ManManufacturerExample();
        example.or().andDeletedEqualTo(false);
        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }
        PageHelper.startPage(page, limit);
        return brandMapper.selectByExampleSelective(example, columns);
    }

    public List<ManManufacturer> query(Integer page, Integer limit) {
        return query(page, limit, null, null);
    }

    public ManManufacturer findById(Integer id) {
        return brandMapper.selectByPrimaryKey(id);
    }

    public List<ManManufacturer> querySelective(String id, String name, Integer page, Integer size, String sort, String order) {
        ManManufacturerExample example = new ManManufacturerExample();
        ManManufacturerExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(id)) {
            criteria.andManIdEqualTo(Integer.valueOf(id));
        }
        if (!StringUtils.isEmpty(name)) {
            criteria.andNameCnLike("%" + name + "%");
        }
        if (!StringUtils.isEmpty(name)) {
            criteria.andNameEnLike("%" + name + "%");
        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, size);
        return brandMapper.selectByExample(example);
    }

    public int updateById(ManManufacturer manufacturer) {
        manufacturer.setLastUpdateTime(LocalDateTime.now());
        return brandMapper.updateByPrimaryKeySelective(manufacturer);
    }

    public void deleteById(Integer id) {
        brandMapper.logicalDeleteByPrimaryKey(id);
    }

    public void add(ManManufacturer manufacturer) {
        manufacturer.setCreateTime(LocalDateTime.now());
        manufacturer.setLastUpdateTime(LocalDateTime.now());
        brandMapper.insertSelective(manufacturer);
    }

    public List<ManManufacturer> all() {
        ManManufacturerExample example = new ManManufacturerExample();
        example.or().andDeletedEqualTo(false);
        return brandMapper.selectByExample(example);
    }
}
