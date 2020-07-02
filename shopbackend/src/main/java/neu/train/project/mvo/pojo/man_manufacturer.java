package neu.train.project.mvo.pojo;


import neu.train.framework.web.domain.BaseEntity;

import java.util.Date;

public class man_manufacturer extends BaseEntity {
    private int man_id;
    private int sys_user_id;
    private String name_en;
    private String name_cn;
    private String gmc_report_type;
    private String gmc_report_url;
    private String description;
    private String created_by;
    private Date creation_date;
    private String last_update_by;
    private Date last_update_date;
    private String call_cnt;
    private String remark;
    private char sts_cd;

    public man_manufacturer() {
    }

    public man_manufacturer(int man_id, int sys_user_id, String name_en, String name_cn, String gmc_report_type, String gmc_report_url, String description, String created_by, Date creation_date, String last_update_by, Date last_update_date, String call_cnt, String remark, char sts_cd) {
        this.man_id = man_id;
        this.sys_user_id = sys_user_id;
        this.name_en = name_en;
        this.name_cn = name_cn;
        this.gmc_report_type = gmc_report_type;
        this.gmc_report_url = gmc_report_url;
        this.description = description;
        this.created_by = created_by;
        this.creation_date = creation_date;
        this.last_update_by = last_update_by;
        this.last_update_date = last_update_date;
        this.call_cnt = call_cnt;
        this.remark = remark;
        this.sts_cd = sts_cd;
    }

    public int getMan_id() {
        return man_id;
    }

    public void setMan_id(int man_id) {
        this.man_id = man_id;
    }

    public int getSys_user_id() {
        return sys_user_id;
    }

    public void setSys_user_id(int sys_user_id) {
        this.sys_user_id = sys_user_id;
    }

    public String getName_en() {
        return name_en;
    }

    public void setName_en(String name_en) {
        this.name_en = name_en;
    }

    public String getName_cn() {
        return name_cn;
    }

    public void setName_cn(String name_cn) {
        this.name_cn = name_cn;
    }

    public String getGmc_report_type() {
        return gmc_report_type;
    }

    public void setGmc_report_type(String gmc_report_type) {
        this.gmc_report_type = gmc_report_type;
    }

    public String getGmc_report_url() {
        return gmc_report_url;
    }

    public void setGmc_report_url(String gmc_report_url) {
        this.gmc_report_url = gmc_report_url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public Date getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(Date creation_date) {
        this.creation_date = creation_date;
    }

    public String getLast_update_by() {
        return last_update_by;
    }

    public void setLast_update_by(String last_update_by) {
        this.last_update_by = last_update_by;
    }

    public Date getLast_update_date() {
        return last_update_date;
    }

    public void setLast_update_date(Date last_update_date) {
        this.last_update_date = last_update_date;
    }

    public String getCall_cnt() {
        return call_cnt;
    }

    public void setCall_cnt(String call_cnt) {
        this.call_cnt = call_cnt;
    }

    @Override
    public String getRemark() {
        return remark;
    }

    @Override
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public char getSts_cd() {
        return sts_cd;
    }

    public void setSts_cd(char sts_cd) {
        this.sts_cd = sts_cd;
    }

    @Override
    public String toString() {
        return "man_manufacturer{" +
                "man_id=" + man_id +
                ", sys_user_id=" + sys_user_id +
                ", name_en='" + name_en + '\'' +
                ", name_cn='" + name_cn + '\'' +
                ", gmc_report_type='" + gmc_report_type + '\'' +
                ", gmc_report_url='" + gmc_report_url + '\'' +
                ", description='" + description + '\'' +
                ", created_by='" + created_by + '\'' +
                ", creation_date=" + creation_date +
                ", last_update_by='" + last_update_by + '\'' +
                ", last_update_date=" + last_update_date +
                ", call_cnt='" + call_cnt + '\'' +
                ", remark='" + remark + '\'' +
                ", sts_cd=" + sts_cd +
                '}';
    }
}
