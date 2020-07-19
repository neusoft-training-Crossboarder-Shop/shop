package neu.train.project.mvo.service;

import com.github.pagehelper.PageHelper;
import neu.train.project.mvo.mapper.ManManufacturerMapper;
import neu.train.project.mvo.domain.ManManufacturer;
import neu.train.project.mvo.domain.ManManufacturerExample;
import neu.train.project.mvo.domain.ManManufacturer.Column;
import org.springframework.beans.factory.annotation.Autowired;
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

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Service
public class ManManufacturerService {
    @Autowired
    private ManManufacturerMapper manufacturerMapper;
    //    private Column[] columns = new Column[]{Column.brdId,Column.manId, Column.name, Column.desc, Column.picUrl, Column.floorPrice};
    private Column[] columns = new Column[]{Column.manId,Column.sysUserId,Column.nameEn,Column.nameCn,Column.gmcReportType,Column.gmcReportUrl,Column.description,Column.createdBy,Column.createTime,Column.lastUpdateBy,Column.lastUpdateTime,Column.callCnt,Column.stsCd,Column.picUrl};
    public List<ManManufacturer> query(Integer page, Integer limit, String sort, String order) {
        ManManufacturerExample example = new ManManufacturerExample();
        example.or().andDeletedEqualTo(false);
        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }
        PageHelper.startPage(page, limit);
        return manufacturerMapper.selectByExampleSelective(example, columns);
    }

    public List<ManManufacturer> query(Integer page, Integer limit) {
        return query(page, limit, null, null);
    }

    public ManManufacturer findById(Integer id) {
        return manufacturerMapper.selectByPrimaryKey(id);
    }

    public List<ManManufacturer> querySelective(String id, String name, Integer page, Integer size, String sort, String order) {
        ManManufacturerExample exampleCn = new ManManufacturerExample();
        ManManufacturerExample.Criteria criteriaCn = exampleCn.createCriteria();
        ManManufacturerExample exampleEn = new ManManufacturerExample();
        ManManufacturerExample.Criteria criteriaEn = exampleEn.createCriteria();


        if (!StringUtils.isEmpty(id)) {
            criteriaCn.andManIdEqualTo(Integer.valueOf(id));
            criteriaEn.andManIdEqualTo(Integer.valueOf(id));
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
//            System.out.println("EEEEEEEEEEEEEEEEEEEEEEccccccccccc"+exampleCn.toString());
            exampleEn.setOrderByClause(sort + " " + order);
//            System.out.println("EEEEEEEEEEEEEEEEEEEEEEeeeeeeeeeee"+exampleEn.toString());
        }

        PageHelper.startPage(page, size);


        //查询输入的名字查到的中文名品牌列表
        List<ManManufacturer>  CnManList = manufacturerMapper.selectByExample(exampleCn);
        System.out.println("CnManList: "+CnManList.toString());
        //查询输入的名字查到的英文名品牌列表
        List<ManManufacturer>  EnManList = manufacturerMapper.selectByExample(exampleEn);
        System.out.println("EnManList: "+EnManList.toString());
        //合并列表
        CnManList.addAll(EnManList);

        return removeDuplicate(CnManList);

    }
    //使用 hash Set 去重
    public static List<ManManufacturer>  removeDuplicate(List<ManManufacturer>  list) {
        HashSet<ManManufacturer> h = new HashSet(list);
        list.clear();
        list.addAll(h);
        return list;
    }


    public int updateById(ManManufacturer manufacturer) {
        manufacturer.setLastUpdateTime(LocalDateTime.now());
        return manufacturerMapper.updateByPrimaryKeySelective(manufacturer);
    }

    public void deleteById(Integer id) {
        manufacturerMapper.logicalDeleteByPrimaryKey(id);
    }

    public void add(ManManufacturer manufacturer) {
        manufacturer.setCreateTime(LocalDateTime.now());
        manufacturer.setLastUpdateTime(LocalDateTime.now());
        manufacturerMapper.insertSelective(manufacturer);
    }

    public List<ManManufacturer> all() {
        ManManufacturerExample example = new ManManufacturerExample();
        example.or().andDeletedEqualTo(false);
        return manufacturerMapper.selectByExample(example);
    }
}
