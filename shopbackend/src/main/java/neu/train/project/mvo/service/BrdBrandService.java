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
import java.util.HashSet;
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
        BrdBrandExample exampleCn = new BrdBrandExample();
        BrdBrandExample.Criteria criteriaCn = exampleCn.createCriteria();
        BrdBrandExample exampleEn = new BrdBrandExample();
        BrdBrandExample.Criteria criteriaEn = exampleEn.createCriteria();

        if (!StringUtils.isEmpty(id)) {
            criteriaCn.andBrdIdEqualTo(Integer.valueOf(id));
            criteriaEn.andBrdIdEqualTo(Integer.valueOf(id));
        }

        /**
         * 判读是否包含名字 CN / EN
         */
        if (!StringUtils.isEmpty(name)) {
            criteriaCn.andNameCnLike("%" + name + "%");
            criteriaEn.andNameEnLike("%" + name + "%");

        }

        criteriaCn.andDeletedEqualTo(false);
        criteriaEn.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            exampleCn.setOrderByClause(sort + " " + order);
            exampleEn.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, size);


        //查询输入的名字查到的中文名品牌列表
        List<BrdBrand>  CnBrandList = brandMapper.selectByExample(exampleCn);
        //查询输入的名字查到的英文名品牌列表
        List<BrdBrand>  EnBrandList = brandMapper.selectByExample(exampleEn);
        //合并列表
        CnBrandList.addAll(EnBrandList);

        return removeDuplicate(CnBrandList);
    }
    //使用 hash Set 去重
    public static List<BrdBrand>  removeDuplicate(List<BrdBrand>  list) {
        HashSet<BrdBrand> h = new HashSet(list);
        list.clear();
        list.addAll(h);
        return list;
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
