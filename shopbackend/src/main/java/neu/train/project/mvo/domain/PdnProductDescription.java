package neu.train.project.mvo.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

public class PdnProductDescription {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pdn_product_description.pdn_id
     *
     * @mbg.generated
     */
    private Integer pdnId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pdn_product_description.pro_id
     *
     * @mbg.generated
     */
    private Integer proId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pdn_product_description.type_cd
     *
     * @mbg.generated
     */
    private String typeCd;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pdn_product_description.platform_type
     *
     * @mbg.generated
     */
    private String platformType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pdn_product_description.created_by
     *
     * @mbg.generated
     */
    private String createdBy;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pdn_product_description.create_time
     *
     * @mbg.generated
     */
    private LocalDateTime createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pdn_product_description.last_update_by
     *
     * @mbg.generated
     */
    private String lastUpdateBy;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pdn_product_description.last_update_time
     *
     * @mbg.generated
     */
    private LocalDateTime lastUpdateTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pdn_product_description.call_cnt
     *
     * @mbg.generated
     */
    private Integer callCnt;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pdn_product_description.sts_cd
     *
     * @mbg.generated
     */
    private String stsCd;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pdn_product_description.descrition
     *
     * @mbg.generated
     */
    private String descrition;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pdn_product_description.pdn_id
     *
     * @return the value of pdn_product_description.pdn_id
     *
     * @mbg.generated
     */
    public Integer getPdnId() {
        return pdnId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pdn_product_description.pdn_id
     *
     * @param pdnId the value for pdn_product_description.pdn_id
     *
     * @mbg.generated
     */
    public void setPdnId(Integer pdnId) {
        this.pdnId = pdnId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pdn_product_description.pro_id
     *
     * @return the value of pdn_product_description.pro_id
     *
     * @mbg.generated
     */
    public Integer getProId() {
        return proId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pdn_product_description.pro_id
     *
     * @param proId the value for pdn_product_description.pro_id
     *
     * @mbg.generated
     */
    public void setProId(Integer proId) {
        this.proId = proId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pdn_product_description.type_cd
     *
     * @return the value of pdn_product_description.type_cd
     *
     * @mbg.generated
     */
    public String getTypeCd() {
        return typeCd;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pdn_product_description.type_cd
     *
     * @param typeCd the value for pdn_product_description.type_cd
     *
     * @mbg.generated
     */
    public void setTypeCd(String typeCd) {
        this.typeCd = typeCd;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pdn_product_description.platform_type
     *
     * @return the value of pdn_product_description.platform_type
     *
     * @mbg.generated
     */
    public String getPlatformType() {
        return platformType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pdn_product_description.platform_type
     *
     * @param platformType the value for pdn_product_description.platform_type
     *
     * @mbg.generated
     */
    public void setPlatformType(String platformType) {
        this.platformType = platformType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pdn_product_description.created_by
     *
     * @return the value of pdn_product_description.created_by
     *
     * @mbg.generated
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pdn_product_description.created_by
     *
     * @param createdBy the value for pdn_product_description.created_by
     *
     * @mbg.generated
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pdn_product_description.create_time
     *
     * @return the value of pdn_product_description.create_time
     *
     * @mbg.generated
     */
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pdn_product_description.create_time
     *
     * @param createTime the value for pdn_product_description.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pdn_product_description.last_update_by
     *
     * @return the value of pdn_product_description.last_update_by
     *
     * @mbg.generated
     */
    public String getLastUpdateBy() {
        return lastUpdateBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pdn_product_description.last_update_by
     *
     * @param lastUpdateBy the value for pdn_product_description.last_update_by
     *
     * @mbg.generated
     */
    public void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pdn_product_description.last_update_time
     *
     * @return the value of pdn_product_description.last_update_time
     *
     * @mbg.generated
     */
    public LocalDateTime getLastUpdateTime() {
        return lastUpdateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pdn_product_description.last_update_time
     *
     * @param lastUpdateTime the value for pdn_product_description.last_update_time
     *
     * @mbg.generated
     */
    public void setLastUpdateTime(LocalDateTime lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pdn_product_description.call_cnt
     *
     * @return the value of pdn_product_description.call_cnt
     *
     * @mbg.generated
     */
    public Integer getCallCnt() {
        return callCnt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pdn_product_description.call_cnt
     *
     * @param callCnt the value for pdn_product_description.call_cnt
     *
     * @mbg.generated
     */
    public void setCallCnt(Integer callCnt) {
        this.callCnt = callCnt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pdn_product_description.sts_cd
     *
     * @return the value of pdn_product_description.sts_cd
     *
     * @mbg.generated
     */
    public String getStsCd() {
        return stsCd;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pdn_product_description.sts_cd
     *
     * @param stsCd the value for pdn_product_description.sts_cd
     *
     * @mbg.generated
     */
    public void setStsCd(String stsCd) {
        this.stsCd = stsCd;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pdn_product_description.descrition
     *
     * @return the value of pdn_product_description.descrition
     *
     * @mbg.generated
     */
    public String getDescrition() {
        return descrition;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pdn_product_description.descrition
     *
     * @param descrition the value for pdn_product_description.descrition
     *
     * @mbg.generated
     */
    public void setDescrition(String descrition) {
        this.descrition = descrition;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pdn_product_description
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", pdnId=").append(pdnId);
        sb.append(", proId=").append(proId);
        sb.append(", typeCd=").append(typeCd);
        sb.append(", platformType=").append(platformType);
        sb.append(", createdBy=").append(createdBy);
        sb.append(", createTime=").append(createTime);
        sb.append(", lastUpdateBy=").append(lastUpdateBy);
        sb.append(", lastUpdateTime=").append(lastUpdateTime);
        sb.append(", callCnt=").append(callCnt);
        sb.append(", stsCd=").append(stsCd);
        sb.append(", descrition=").append(descrition);
        sb.append("]");
        return sb.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pdn_product_description
     *
     * @mbg.generated
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        PdnProductDescription other = (PdnProductDescription) that;
        return (this.getPdnId() == null ? other.getPdnId() == null : this.getPdnId().equals(other.getPdnId()))
            && (this.getProId() == null ? other.getProId() == null : this.getProId().equals(other.getProId()))
            && (this.getTypeCd() == null ? other.getTypeCd() == null : this.getTypeCd().equals(other.getTypeCd()))
            && (this.getPlatformType() == null ? other.getPlatformType() == null : this.getPlatformType().equals(other.getPlatformType()))
            && (this.getCreatedBy() == null ? other.getCreatedBy() == null : this.getCreatedBy().equals(other.getCreatedBy()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getLastUpdateBy() == null ? other.getLastUpdateBy() == null : this.getLastUpdateBy().equals(other.getLastUpdateBy()))
            && (this.getLastUpdateTime() == null ? other.getLastUpdateTime() == null : this.getLastUpdateTime().equals(other.getLastUpdateTime()))
            && (this.getCallCnt() == null ? other.getCallCnt() == null : this.getCallCnt().equals(other.getCallCnt()))
            && (this.getStsCd() == null ? other.getStsCd() == null : this.getStsCd().equals(other.getStsCd()))
            && (this.getDescrition() == null ? other.getDescrition() == null : this.getDescrition().equals(other.getDescrition()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pdn_product_description
     *
     * @mbg.generated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getPdnId() == null) ? 0 : getPdnId().hashCode());
        result = prime * result + ((getProId() == null) ? 0 : getProId().hashCode());
        result = prime * result + ((getTypeCd() == null) ? 0 : getTypeCd().hashCode());
        result = prime * result + ((getPlatformType() == null) ? 0 : getPlatformType().hashCode());
        result = prime * result + ((getCreatedBy() == null) ? 0 : getCreatedBy().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getLastUpdateBy() == null) ? 0 : getLastUpdateBy().hashCode());
        result = prime * result + ((getLastUpdateTime() == null) ? 0 : getLastUpdateTime().hashCode());
        result = prime * result + ((getCallCnt() == null) ? 0 : getCallCnt().hashCode());
        result = prime * result + ((getStsCd() == null) ? 0 : getStsCd().hashCode());
        result = prime * result + ((getDescrition() == null) ? 0 : getDescrition().hashCode());
        return result;
    }

    /**
     * This enum was generated by MyBatis Generator.
     * This enum corresponds to the database table pdn_product_description
     *
     * @mbg.generated
     */
    public enum Column {
        pdnId("pdn_id", "pdnId", "INTEGER", false),
        proId("pro_id", "proId", "INTEGER", false),
        typeCd("type_cd", "typeCd", "VARCHAR", false),
        platformType("platform_type", "platformType", "VARCHAR", false),
        createdBy("created_by", "createdBy", "VARCHAR", false),
        createTime("create_time", "createTime", "TIMESTAMP", false),
        lastUpdateBy("last_update_by", "lastUpdateBy", "VARCHAR", false),
        lastUpdateTime("last_update_time", "lastUpdateTime", "TIMESTAMP", false),
        callCnt("call_cnt", "callCnt", "INTEGER", false),
        stsCd("sts_cd", "stsCd", "CHAR", false),
        descrition("descrition", "descrition", "LONGVARCHAR", false);

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table pdn_product_description
         *
         * @mbg.generated
         */
        private static final String BEGINNING_DELIMITER = "`";

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table pdn_product_description
         *
         * @mbg.generated
         */
        private static final String ENDING_DELIMITER = "`";

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table pdn_product_description
         *
         * @mbg.generated
         */
        private final String column;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table pdn_product_description
         *
         * @mbg.generated
         */
        private final boolean isColumnNameDelimited;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table pdn_product_description
         *
         * @mbg.generated
         */
        private final String javaProperty;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table pdn_product_description
         *
         * @mbg.generated
         */
        private final String jdbcType;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table pdn_product_description
         *
         * @mbg.generated
         */
        public String value() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table pdn_product_description
         *
         * @mbg.generated
         */
        public String getValue() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table pdn_product_description
         *
         * @mbg.generated
         */
        public String getJavaProperty() {
            return this.javaProperty;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table pdn_product_description
         *
         * @mbg.generated
         */
        public String getJdbcType() {
            return this.jdbcType;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table pdn_product_description
         *
         * @mbg.generated
         */
        Column(String column, String javaProperty, String jdbcType, boolean isColumnNameDelimited) {
            this.column = column;
            this.javaProperty = javaProperty;
            this.jdbcType = jdbcType;
            this.isColumnNameDelimited = isColumnNameDelimited;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table pdn_product_description
         *
         * @mbg.generated
         */
        public String desc() {
            return this.getEscapedColumnName() + " DESC";
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table pdn_product_description
         *
         * @mbg.generated
         */
        public String asc() {
            return this.getEscapedColumnName() + " ASC";
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table pdn_product_description
         *
         * @mbg.generated
         */
        public static Column[] excludes(Column ... excludes) {
            ArrayList<Column> columns = new ArrayList<>(Arrays.asList(Column.values()));
            if (excludes != null && excludes.length > 0) {
                columns.removeAll(new ArrayList<>(Arrays.asList(excludes)));
            }
            return columns.toArray(new Column[]{});
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table pdn_product_description
         *
         * @mbg.generated
         */
        public String getEscapedColumnName() {
            if (this.isColumnNameDelimited) {
                return new StringBuilder().append(BEGINNING_DELIMITER).append(this.column).append(ENDING_DELIMITER).toString();
            } else {
                return this.column;
            }
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table pdn_product_description
         *
         * @mbg.generated
         */
        public String getAliasedEscapedColumnName() {
            return this.getEscapedColumnName();
        }
    }
}