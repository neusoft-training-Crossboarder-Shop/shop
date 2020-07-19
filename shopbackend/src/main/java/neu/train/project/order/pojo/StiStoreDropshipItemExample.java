package neu.train.project.order.pojo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StiStoreDropshipItemExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public StiStoreDropshipItemExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andDilIdIsNull() {
            addCriterion("dil_id is null");
            return (Criteria) this;
        }

        public Criteria andDilIdIsNotNull() {
            addCriterion("dil_id is not null");
            return (Criteria) this;
        }

        public Criteria andDilIdEqualTo(Integer value) {
            addCriterion("dil_id =", value, "dilId");
            return (Criteria) this;
        }

        public Criteria andDilIdNotEqualTo(Integer value) {
            addCriterion("dil_id <>", value, "dilId");
            return (Criteria) this;
        }

        public Criteria andDilIdGreaterThan(Integer value) {
            addCriterion("dil_id >", value, "dilId");
            return (Criteria) this;
        }

        public Criteria andDilIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("dil_id >=", value, "dilId");
            return (Criteria) this;
        }

        public Criteria andDilIdLessThan(Integer value) {
            addCriterion("dil_id <", value, "dilId");
            return (Criteria) this;
        }

        public Criteria andDilIdLessThanOrEqualTo(Integer value) {
            addCriterion("dil_id <=", value, "dilId");
            return (Criteria) this;
        }

        public Criteria andDilIdIn(List<Integer> values) {
            addCriterion("dil_id in", values, "dilId");
            return (Criteria) this;
        }

        public Criteria andDilIdNotIn(List<Integer> values) {
            addCriterion("dil_id not in", values, "dilId");
            return (Criteria) this;
        }

        public Criteria andDilIdBetween(Integer value1, Integer value2) {
            addCriterion("dil_id between", value1, value2, "dilId");
            return (Criteria) this;
        }

        public Criteria andDilIdNotBetween(Integer value1, Integer value2) {
            addCriterion("dil_id not between", value1, value2, "dilId");
            return (Criteria) this;
        }

        public Criteria andProIdIsNull() {
            addCriterion("pro_id is null");
            return (Criteria) this;
        }

        public Criteria andProIdIsNotNull() {
            addCriterion("pro_id is not null");
            return (Criteria) this;
        }

        public Criteria andProIdEqualTo(Integer value) {
            addCriterion("pro_id =", value, "proId");
            return (Criteria) this;
        }

        public Criteria andProIdNotEqualTo(Integer value) {
            addCriterion("pro_id <>", value, "proId");
            return (Criteria) this;
        }

        public Criteria andProIdGreaterThan(Integer value) {
            addCriterion("pro_id >", value, "proId");
            return (Criteria) this;
        }

        public Criteria andProIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("pro_id >=", value, "proId");
            return (Criteria) this;
        }

        public Criteria andProIdLessThan(Integer value) {
            addCriterion("pro_id <", value, "proId");
            return (Criteria) this;
        }

        public Criteria andProIdLessThanOrEqualTo(Integer value) {
            addCriterion("pro_id <=", value, "proId");
            return (Criteria) this;
        }

        public Criteria andProIdIn(List<Integer> values) {
            addCriterion("pro_id in", values, "proId");
            return (Criteria) this;
        }

        public Criteria andProIdNotIn(List<Integer> values) {
            addCriterion("pro_id not in", values, "proId");
            return (Criteria) this;
        }

        public Criteria andProIdBetween(Integer value1, Integer value2) {
            addCriterion("pro_id between", value1, value2, "proId");
            return (Criteria) this;
        }

        public Criteria andProIdNotBetween(Integer value1, Integer value2) {
            addCriterion("pro_id not between", value1, value2, "proId");
            return (Criteria) this;
        }

        public Criteria andStrIdIsNull() {
            addCriterion("str_id is null");
            return (Criteria) this;
        }

        public Criteria andStrIdIsNotNull() {
            addCriterion("str_id is not null");
            return (Criteria) this;
        }

        public Criteria andStrIdEqualTo(Integer value) {
            addCriterion("str_id =", value, "strId");
            return (Criteria) this;
        }

        public Criteria andStrIdNotEqualTo(Integer value) {
            addCriterion("str_id <>", value, "strId");
            return (Criteria) this;
        }

        public Criteria andStrIdGreaterThan(Integer value) {
            addCriterion("str_id >", value, "strId");
            return (Criteria) this;
        }

        public Criteria andStrIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("str_id >=", value, "strId");
            return (Criteria) this;
        }

        public Criteria andStrIdLessThan(Integer value) {
            addCriterion("str_id <", value, "strId");
            return (Criteria) this;
        }

        public Criteria andStrIdLessThanOrEqualTo(Integer value) {
            addCriterion("str_id <=", value, "strId");
            return (Criteria) this;
        }

        public Criteria andStrIdIn(List<Integer> values) {
            addCriterion("str_id in", values, "strId");
            return (Criteria) this;
        }

        public Criteria andStrIdNotIn(List<Integer> values) {
            addCriterion("str_id not in", values, "strId");
            return (Criteria) this;
        }

        public Criteria andStrIdBetween(Integer value1, Integer value2) {
            addCriterion("str_id between", value1, value2, "strId");
            return (Criteria) this;
        }

        public Criteria andStrIdNotBetween(Integer value1, Integer value2) {
            addCriterion("str_id not between", value1, value2, "strId");
            return (Criteria) this;
        }

        public Criteria andSalPriceIsNull() {
            addCriterion("sal_price is null");
            return (Criteria) this;
        }

        public Criteria andSalPriceIsNotNull() {
            addCriterion("sal_price is not null");
            return (Criteria) this;
        }

        public Criteria andSalPriceEqualTo(BigDecimal value) {
            addCriterion("sal_price =", value, "salPrice");
            return (Criteria) this;
        }

        public Criteria andSalPriceNotEqualTo(BigDecimal value) {
            addCriterion("sal_price <>", value, "salPrice");
            return (Criteria) this;
        }

        public Criteria andSalPriceGreaterThan(BigDecimal value) {
            addCriterion("sal_price >", value, "salPrice");
            return (Criteria) this;
        }

        public Criteria andSalPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("sal_price >=", value, "salPrice");
            return (Criteria) this;
        }

        public Criteria andSalPriceLessThan(BigDecimal value) {
            addCriterion("sal_price <", value, "salPrice");
            return (Criteria) this;
        }

        public Criteria andSalPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("sal_price <=", value, "salPrice");
            return (Criteria) this;
        }

        public Criteria andSalPriceIn(List<BigDecimal> values) {
            addCriterion("sal_price in", values, "salPrice");
            return (Criteria) this;
        }

        public Criteria andSalPriceNotIn(List<BigDecimal> values) {
            addCriterion("sal_price not in", values, "salPrice");
            return (Criteria) this;
        }

        public Criteria andSalPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sal_price between", value1, value2, "salPrice");
            return (Criteria) this;
        }

        public Criteria andSalPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sal_price not between", value1, value2, "salPrice");
            return (Criteria) this;
        }

        public Criteria andDropshipPriceIsNull() {
            addCriterion("dropship_price is null");
            return (Criteria) this;
        }

        public Criteria andDropshipPriceIsNotNull() {
            addCriterion("dropship_price is not null");
            return (Criteria) this;
        }

        public Criteria andDropshipPriceEqualTo(BigDecimal value) {
            addCriterion("dropship_price =", value, "dropshipPrice");
            return (Criteria) this;
        }

        public Criteria andDropshipPriceNotEqualTo(BigDecimal value) {
            addCriterion("dropship_price <>", value, "dropshipPrice");
            return (Criteria) this;
        }

        public Criteria andDropshipPriceGreaterThan(BigDecimal value) {
            addCriterion("dropship_price >", value, "dropshipPrice");
            return (Criteria) this;
        }

        public Criteria andDropshipPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("dropship_price >=", value, "dropshipPrice");
            return (Criteria) this;
        }

        public Criteria andDropshipPriceLessThan(BigDecimal value) {
            addCriterion("dropship_price <", value, "dropshipPrice");
            return (Criteria) this;
        }

        public Criteria andDropshipPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("dropship_price <=", value, "dropshipPrice");
            return (Criteria) this;
        }

        public Criteria andDropshipPriceIn(List<BigDecimal> values) {
            addCriterion("dropship_price in", values, "dropshipPrice");
            return (Criteria) this;
        }

        public Criteria andDropshipPriceNotIn(List<BigDecimal> values) {
            addCriterion("dropship_price not in", values, "dropshipPrice");
            return (Criteria) this;
        }

        public Criteria andDropshipPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("dropship_price between", value1, value2, "dropshipPrice");
            return (Criteria) this;
        }

        public Criteria andDropshipPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("dropship_price not between", value1, value2, "dropshipPrice");
            return (Criteria) this;
        }

        public Criteria andStoreRetentionAmountIsNull() {
            addCriterion("store_retention_amount is null");
            return (Criteria) this;
        }

        public Criteria andStoreRetentionAmountIsNotNull() {
            addCriterion("store_retention_amount is not null");
            return (Criteria) this;
        }

        public Criteria andStoreRetentionAmountEqualTo(Integer value) {
            addCriterion("store_retention_amount =", value, "storeRetentionAmount");
            return (Criteria) this;
        }

        public Criteria andStoreRetentionAmountNotEqualTo(Integer value) {
            addCriterion("store_retention_amount <>", value, "storeRetentionAmount");
            return (Criteria) this;
        }

        public Criteria andStoreRetentionAmountGreaterThan(Integer value) {
            addCriterion("store_retention_amount >", value, "storeRetentionAmount");
            return (Criteria) this;
        }

        public Criteria andStoreRetentionAmountGreaterThanOrEqualTo(Integer value) {
            addCriterion("store_retention_amount >=", value, "storeRetentionAmount");
            return (Criteria) this;
        }

        public Criteria andStoreRetentionAmountLessThan(Integer value) {
            addCriterion("store_retention_amount <", value, "storeRetentionAmount");
            return (Criteria) this;
        }

        public Criteria andStoreRetentionAmountLessThanOrEqualTo(Integer value) {
            addCriterion("store_retention_amount <=", value, "storeRetentionAmount");
            return (Criteria) this;
        }

        public Criteria andStoreRetentionAmountIn(List<Integer> values) {
            addCriterion("store_retention_amount in", values, "storeRetentionAmount");
            return (Criteria) this;
        }

        public Criteria andStoreRetentionAmountNotIn(List<Integer> values) {
            addCriterion("store_retention_amount not in", values, "storeRetentionAmount");
            return (Criteria) this;
        }

        public Criteria andStoreRetentionAmountBetween(Integer value1, Integer value2) {
            addCriterion("store_retention_amount between", value1, value2, "storeRetentionAmount");
            return (Criteria) this;
        }

        public Criteria andStoreRetentionAmountNotBetween(Integer value1, Integer value2) {
            addCriterion("store_retention_amount not between", value1, value2, "storeRetentionAmount");
            return (Criteria) this;
        }

        public Criteria andShelfStockAmountIsNull() {
            addCriterion("shelf_stock_amount is null");
            return (Criteria) this;
        }

        public Criteria andShelfStockAmountIsNotNull() {
            addCriterion("shelf_stock_amount is not null");
            return (Criteria) this;
        }

        public Criteria andShelfStockAmountEqualTo(Integer value) {
            addCriterion("shelf_stock_amount =", value, "shelfStockAmount");
            return (Criteria) this;
        }

        public Criteria andShelfStockAmountNotEqualTo(Integer value) {
            addCriterion("shelf_stock_amount <>", value, "shelfStockAmount");
            return (Criteria) this;
        }

        public Criteria andShelfStockAmountGreaterThan(Integer value) {
            addCriterion("shelf_stock_amount >", value, "shelfStockAmount");
            return (Criteria) this;
        }

        public Criteria andShelfStockAmountGreaterThanOrEqualTo(Integer value) {
            addCriterion("shelf_stock_amount >=", value, "shelfStockAmount");
            return (Criteria) this;
        }

        public Criteria andShelfStockAmountLessThan(Integer value) {
            addCriterion("shelf_stock_amount <", value, "shelfStockAmount");
            return (Criteria) this;
        }

        public Criteria andShelfStockAmountLessThanOrEqualTo(Integer value) {
            addCriterion("shelf_stock_amount <=", value, "shelfStockAmount");
            return (Criteria) this;
        }

        public Criteria andShelfStockAmountIn(List<Integer> values) {
            addCriterion("shelf_stock_amount in", values, "shelfStockAmount");
            return (Criteria) this;
        }

        public Criteria andShelfStockAmountNotIn(List<Integer> values) {
            addCriterion("shelf_stock_amount not in", values, "shelfStockAmount");
            return (Criteria) this;
        }

        public Criteria andShelfStockAmountBetween(Integer value1, Integer value2) {
            addCriterion("shelf_stock_amount between", value1, value2, "shelfStockAmount");
            return (Criteria) this;
        }

        public Criteria andShelfStockAmountNotBetween(Integer value1, Integer value2) {
            addCriterion("shelf_stock_amount not between", value1, value2, "shelfStockAmount");
            return (Criteria) this;
        }

        public Criteria andDropshipStatusIsNull() {
            addCriterion("dropship_status is null");
            return (Criteria) this;
        }

        public Criteria andDropshipStatusIsNotNull() {
            addCriterion("dropship_status is not null");
            return (Criteria) this;
        }

        public Criteria andDropshipStatusEqualTo(String value) {
            addCriterion("dropship_status =", value, "dropshipStatus");
            return (Criteria) this;
        }

        public Criteria andDropshipStatusNotEqualTo(String value) {
            addCriterion("dropship_status <>", value, "dropshipStatus");
            return (Criteria) this;
        }

        public Criteria andDropshipStatusGreaterThan(String value) {
            addCriterion("dropship_status >", value, "dropshipStatus");
            return (Criteria) this;
        }

        public Criteria andDropshipStatusGreaterThanOrEqualTo(String value) {
            addCriterion("dropship_status >=", value, "dropshipStatus");
            return (Criteria) this;
        }

        public Criteria andDropshipStatusLessThan(String value) {
            addCriterion("dropship_status <", value, "dropshipStatus");
            return (Criteria) this;
        }

        public Criteria andDropshipStatusLessThanOrEqualTo(String value) {
            addCriterion("dropship_status <=", value, "dropshipStatus");
            return (Criteria) this;
        }

        public Criteria andDropshipStatusLike(String value) {
            addCriterion("dropship_status like", value, "dropshipStatus");
            return (Criteria) this;
        }

        public Criteria andDropshipStatusNotLike(String value) {
            addCriterion("dropship_status not like", value, "dropshipStatus");
            return (Criteria) this;
        }

        public Criteria andDropshipStatusIn(List<String> values) {
            addCriterion("dropship_status in", values, "dropshipStatus");
            return (Criteria) this;
        }

        public Criteria andDropshipStatusNotIn(List<String> values) {
            addCriterion("dropship_status not in", values, "dropshipStatus");
            return (Criteria) this;
        }

        public Criteria andDropshipStatusBetween(String value1, String value2) {
            addCriterion("dropship_status between", value1, value2, "dropshipStatus");
            return (Criteria) this;
        }

        public Criteria andDropshipStatusNotBetween(String value1, String value2) {
            addCriterion("dropship_status not between", value1, value2, "dropshipStatus");
            return (Criteria) this;
        }

        public Criteria andCreatedByIsNull() {
            addCriterion("created_by is null");
            return (Criteria) this;
        }

        public Criteria andCreatedByIsNotNull() {
            addCriterion("created_by is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedByEqualTo(String value) {
            addCriterion("created_by =", value, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByNotEqualTo(String value) {
            addCriterion("created_by <>", value, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByGreaterThan(String value) {
            addCriterion("created_by >", value, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByGreaterThanOrEqualTo(String value) {
            addCriterion("created_by >=", value, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByLessThan(String value) {
            addCriterion("created_by <", value, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByLessThanOrEqualTo(String value) {
            addCriterion("created_by <=", value, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByLike(String value) {
            addCriterion("created_by like", value, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByNotLike(String value) {
            addCriterion("created_by not like", value, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByIn(List<String> values) {
            addCriterion("created_by in", values, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByNotIn(List<String> values) {
            addCriterion("created_by not in", values, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByBetween(String value1, String value2) {
            addCriterion("created_by between", value1, value2, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByNotBetween(String value1, String value2) {
            addCriterion("created_by not between", value1, value2, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCallCntIsNull() {
            addCriterion("call_cnt is null");
            return (Criteria) this;
        }

        public Criteria andCallCntIsNotNull() {
            addCriterion("call_cnt is not null");
            return (Criteria) this;
        }

        public Criteria andCallCntEqualTo(Integer value) {
            addCriterion("call_cnt =", value, "callCnt");
            return (Criteria) this;
        }

        public Criteria andCallCntNotEqualTo(Integer value) {
            addCriterion("call_cnt <>", value, "callCnt");
            return (Criteria) this;
        }

        public Criteria andCallCntGreaterThan(Integer value) {
            addCriterion("call_cnt >", value, "callCnt");
            return (Criteria) this;
        }

        public Criteria andCallCntGreaterThanOrEqualTo(Integer value) {
            addCriterion("call_cnt >=", value, "callCnt");
            return (Criteria) this;
        }

        public Criteria andCallCntLessThan(Integer value) {
            addCriterion("call_cnt <", value, "callCnt");
            return (Criteria) this;
        }

        public Criteria andCallCntLessThanOrEqualTo(Integer value) {
            addCriterion("call_cnt <=", value, "callCnt");
            return (Criteria) this;
        }

        public Criteria andCallCntIn(List<Integer> values) {
            addCriterion("call_cnt in", values, "callCnt");
            return (Criteria) this;
        }

        public Criteria andCallCntNotIn(List<Integer> values) {
            addCriterion("call_cnt not in", values, "callCnt");
            return (Criteria) this;
        }

        public Criteria andCallCntBetween(Integer value1, Integer value2) {
            addCriterion("call_cnt between", value1, value2, "callCnt");
            return (Criteria) this;
        }

        public Criteria andCallCntNotBetween(Integer value1, Integer value2) {
            addCriterion("call_cnt not between", value1, value2, "callCnt");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andStsCdIsNull() {
            addCriterion("sts_cd is null");
            return (Criteria) this;
        }

        public Criteria andStsCdIsNotNull() {
            addCriterion("sts_cd is not null");
            return (Criteria) this;
        }

        public Criteria andStsCdEqualTo(String value) {
            addCriterion("sts_cd =", value, "stsCd");
            return (Criteria) this;
        }

        public Criteria andStsCdNotEqualTo(String value) {
            addCriterion("sts_cd <>", value, "stsCd");
            return (Criteria) this;
        }

        public Criteria andStsCdGreaterThan(String value) {
            addCriterion("sts_cd >", value, "stsCd");
            return (Criteria) this;
        }

        public Criteria andStsCdGreaterThanOrEqualTo(String value) {
            addCriterion("sts_cd >=", value, "stsCd");
            return (Criteria) this;
        }

        public Criteria andStsCdLessThan(String value) {
            addCriterion("sts_cd <", value, "stsCd");
            return (Criteria) this;
        }

        public Criteria andStsCdLessThanOrEqualTo(String value) {
            addCriterion("sts_cd <=", value, "stsCd");
            return (Criteria) this;
        }

        public Criteria andStsCdLike(String value) {
            addCriterion("sts_cd like", value, "stsCd");
            return (Criteria) this;
        }

        public Criteria andStsCdNotLike(String value) {
            addCriterion("sts_cd not like", value, "stsCd");
            return (Criteria) this;
        }

        public Criteria andStsCdIn(List<String> values) {
            addCriterion("sts_cd in", values, "stsCd");
            return (Criteria) this;
        }

        public Criteria andStsCdNotIn(List<String> values) {
            addCriterion("sts_cd not in", values, "stsCd");
            return (Criteria) this;
        }

        public Criteria andStsCdBetween(String value1, String value2) {
            addCriterion("sts_cd between", value1, value2, "stsCd");
            return (Criteria) this;
        }

        public Criteria andStsCdNotBetween(String value1, String value2) {
            addCriterion("sts_cd not between", value1, value2, "stsCd");
            return (Criteria) this;
        }

        public Criteria andLastUpdateByIsNull() {
            addCriterion("last_update_by is null");
            return (Criteria) this;
        }

        public Criteria andLastUpdateByIsNotNull() {
            addCriterion("last_update_by is not null");
            return (Criteria) this;
        }

        public Criteria andLastUpdateByEqualTo(String value) {
            addCriterion("last_update_by =", value, "lastUpdateBy");
            return (Criteria) this;
        }

        public Criteria andLastUpdateByNotEqualTo(String value) {
            addCriterion("last_update_by <>", value, "lastUpdateBy");
            return (Criteria) this;
        }

        public Criteria andLastUpdateByGreaterThan(String value) {
            addCriterion("last_update_by >", value, "lastUpdateBy");
            return (Criteria) this;
        }

        public Criteria andLastUpdateByGreaterThanOrEqualTo(String value) {
            addCriterion("last_update_by >=", value, "lastUpdateBy");
            return (Criteria) this;
        }

        public Criteria andLastUpdateByLessThan(String value) {
            addCriterion("last_update_by <", value, "lastUpdateBy");
            return (Criteria) this;
        }

        public Criteria andLastUpdateByLessThanOrEqualTo(String value) {
            addCriterion("last_update_by <=", value, "lastUpdateBy");
            return (Criteria) this;
        }

        public Criteria andLastUpdateByLike(String value) {
            addCriterion("last_update_by like", value, "lastUpdateBy");
            return (Criteria) this;
        }

        public Criteria andLastUpdateByNotLike(String value) {
            addCriterion("last_update_by not like", value, "lastUpdateBy");
            return (Criteria) this;
        }

        public Criteria andLastUpdateByIn(List<String> values) {
            addCriterion("last_update_by in", values, "lastUpdateBy");
            return (Criteria) this;
        }

        public Criteria andLastUpdateByNotIn(List<String> values) {
            addCriterion("last_update_by not in", values, "lastUpdateBy");
            return (Criteria) this;
        }

        public Criteria andLastUpdateByBetween(String value1, String value2) {
            addCriterion("last_update_by between", value1, value2, "lastUpdateBy");
            return (Criteria) this;
        }

        public Criteria andLastUpdateByNotBetween(String value1, String value2) {
            addCriterion("last_update_by not between", value1, value2, "lastUpdateBy");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeIsNull() {
            addCriterion("last_update_time is null");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeIsNotNull() {
            addCriterion("last_update_time is not null");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeEqualTo(Date value) {
            addCriterion("last_update_time =", value, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeNotEqualTo(Date value) {
            addCriterion("last_update_time <>", value, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeGreaterThan(Date value) {
            addCriterion("last_update_time >", value, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("last_update_time >=", value, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeLessThan(Date value) {
            addCriterion("last_update_time <", value, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("last_update_time <=", value, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeIn(List<Date> values) {
            addCriterion("last_update_time in", values, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeNotIn(List<Date> values) {
            addCriterion("last_update_time not in", values, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("last_update_time between", value1, value2, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("last_update_time not between", value1, value2, "lastUpdateTime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}