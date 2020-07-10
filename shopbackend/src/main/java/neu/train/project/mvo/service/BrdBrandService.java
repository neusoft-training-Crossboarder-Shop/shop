package neu.train.project.mvo.service;

import com.github.pagehelper.PageHelper;
import neu.train.project.mvo.mapper.BrdBrandMapper;
import neu.train.project.mvo.domain.BrdBrand;
import neu.train.project.mvo.domain.BrdBrandExample;
import neu.train.project.mvo.domain.BrdBrand.Column;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: 高歌
 * @Id: 20175045
 * @Github : EvilicLufas
 */


@Service
public class BrdBrandService {
    @Resource
    private BrdBrandMapper brandMapper;
//    private Column[] columns = new Column[]{Column.brdId,Column.manId, Column.name, Column.desc, Column.picUrl, Column.floorPrice};
    private Column[] columns = new Column[]{Column.brdId,Column.manId,Column.nameEn,Column.nameCn,Column.imgId,Column.createdBy,Column.createTime,Column.lastUpdateBy,Column.lastUpdateTime,Column.callCnt,Column.stsCd,Column.deleted,Column.brdDesc,Column.picUrl};
    public List<BrdBrand> query(Integer page, Integer limit, String sort, String order) {
        BrdBrandExample example = new BrdBrandExample();
        example.or().andDeletedEqualTo(false);
        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }
        PageHelper.startPage(page, limit);
        return brandMapper.selectByExampleSelective(example, columns);
    }

    public List<BrdBrand> query(Integer page, Integer limit) {
        return query(page, limit, null, null);
    }

    public BrdBrand findById(Integer id) {
        return brandMapper.selectByPrimaryKey(id);
    }

    public List<BrdBrand> querySelective(String id, String name, Integer page, Integer size, String sort, String order) {
        BrdBrandExample example = new BrdBrandExample();
        BrdBrandExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(id)) {
            criteria.andBrdIdEqualTo(Integer.valueOf(id));
        }
        /**
         * 判读是否包含名字
         */
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

    public int updateById(BrdBrand brand) {
        brand.setLastUpdateTime(LocalDateTime.now());
        return brandMapper.updateByPrimaryKeySelective(brand);
    }

    public void deleteById(Integer id) {
        brandMapper.logicalDeleteByPrimaryKey(id);
    }

    public void add(BrdBrand brand) {
        brand.setCreateTime(LocalDateTime.now());
        brand.setLastUpdateTime(LocalDateTime.now());
        brandMapper.insertSelective(brand);
    }

    public List<BrdBrand> all() {
        BrdBrandExample example = new BrdBrandExample();
        example.or().andDeletedEqualTo(false);
        return brandMapper.selectByExample(example);
    }
}
