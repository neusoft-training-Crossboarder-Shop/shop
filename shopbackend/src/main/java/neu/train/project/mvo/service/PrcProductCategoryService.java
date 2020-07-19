//package neu.train.project.mvo.service;
//
//import com.github.pagehelper.PageHelper;
//import neu.train.project.mvo.domain.PrcProductCategory;
//import neu.train.project.mvo.domain.PrcProductCategoryExample;
//import neu.train.project.mvo.mapper.PrcProductCategoryMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.util.StringUtils;
//
//import java.time.LocalDateTime;
//import java.util.HashSet;
//import java.util.List;
//
///**
// * @Author: 高歌
// * @Id: 20175045
// * @Github : EvilicLufas
// */
//
//
//@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
//@Service
//public class PrcProductCategoryService {
//    @Autowired
//    private PrcProductCategoryMapper proCtgMapper;
//    //    private Column[] columns = new Column[]{Column.brdId,Column.manId, Column.name, Column.desc, Column.picUrl, Column.floorPrice};
//    private PrcProductCategory.Column[] columns = new PrcProductCategory.Column[]{PrcProductCategory.Column.prcId, PrcProductCategory.Column.proId, PrcProductCategory.Column.categoryName, PrcProductCategory.Column.categoryPath, PrcProductCategory.Column.createdBy, PrcProductCategory.Column.createTime, PrcProductCategory.Column.lastUpdateBy, PrcProductCategory.Column.lastUpdateTime, PrcProductCategory.Column.callCnt, PrcProductCategory.Column.stsCd, PrcProductCategory.Column.deleted};
//    public List<PrcProductCategory> query(Integer page, Integer limit, String sort, String order) {
//        PrcProductCategoryExample example = new PrcProductCategoryExample();
//        example.or().andDeletedEqualTo(false);
//        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
//            example.setOrderByClause(sort + " " + order);
//        }
//        PageHelper.startPage(page, limit);
//        return proCtgMapper.selectByExampleSelective(example, columns);
//    }
//
//    public List<PrcProductCategory> query(Integer page, Integer limit) {
//        return query(page, limit, null, null);
//    }
//
//    public PrcProductCategory findById(Integer id) {
//        return proCtgMapper.selectByPrimaryKey(id);
//    }
//
//    public List<PrcProductCategory> querySelective(String id, String name, Integer page, Integer size, String sort, String order) {
//        PrcProductCategoryExample exampleName = new PrcProductCategoryExample();
//        PrcProductCategoryExample.Criteria criteriaCn = exampleName.createCriteria();
//        PrcProductCategoryExample exampleEn = new PrcProductCategoryExample();
//        PrcProductCategoryExample.Criteria criteriaEn = exampleEn.createCriteria();
//
//        if (!StringUtils.isEmpty(id)) {
//            criteriaCn.andBrdIdEqualTo(Integer.valueOf(id));
//            criteriaEn.andBrdIdEqualTo(Integer.valueOf(id));
//        }
//
//        /**
//         * 判读是否包含名字 CN / EN
//         */
//        if (!StringUtils.isEmpty(name)) {
//            criteriaCn.andNameCnLike("%" + name + "%");
//            criteriaEn.andNameEnLike("%" + name + "%");
//
//        }
//
//        criteriaCn.andDeletedEqualTo(false);
//        criteriaEn.andDeletedEqualTo(false);
//
//        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
//            exampleName.setOrderByClause(sort + " " + order);
//            exampleEn.setOrderByClause(sort + " " + order);
//        }
//
//        PageHelper.startPage(page, size);
//
//
//        //查询输入的名字查到的中文名品牌列表
//        List<PrcProductCategory>  CnBrandList = proCtgMapper.selectByExample(exampleName);
//        //查询输入的名字查到的英文名品牌列表
//        List<PrcProductCategory>  EnBrandList = proCtgMapper.selectByExample(exampleEn);
//        //合并列表
//        CnBrandList.addAll(EnBrandList);
//
//        return removeDuplicate(CnBrandList);
//    }
//    //使用 hash Set 去重
//    public static List<PrcProductCategory>  removeDuplicate(List<PrcProductCategory>  list) {
//        HashSet<PrcProductCategory> h = new HashSet(list);
//        list.clear();
//        list.addAll(h);
//        return list;
//    }
//
//    public int updateById(PrcProductCategory brand) {
//        brand.setLastUpdateTime(LocalDateTime.now());
//        return proCtgMapper.updateByPrimaryKeySelective(brand);
//    }
//
//    public void deleteById(Integer id) {
//        proCtgMapper.logicalDeleteByPrimaryKey(id);
//    }
//
//    public void add(PrcProductCategory brand) {
//        brand.setCreateTime(LocalDateTime.now());
//        brand.setLastUpdateTime(LocalDateTime.now());
//        proCtgMapper.insertSelective(brand);
//    }
//
//    public List<PrcProductCategory> all() {
//        PrcProductCategoryExample example = new PrcProductCategoryExample();
//        example.or().andDeletedEqualTo(false);
//        return proCtgMapper.selectByExample(example);
//    }
//}
//
