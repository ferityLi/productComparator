package com.zhongan.product.cfg.blcs.channel;

import com.zhongan.product.cfg.blcs.BaseComparator;
import org.apache.commons.lang3.StringUtils;

/**
 * 续保规则表
 * @author dailiang
 */
public class RenewalRuleComparator extends BaseComparator {
    public static final String TABLE_NAME = "partner_extra_info";

    public static final String INSERT = "INSERT INTO `channel_center_00`.`partner_extra_info`(`id`,`tax_no`,`tax_no_begin`," +
            "`tax_no_end`,`license_no`,`license_begin`,`license_end`,`org_code`,`org_code_begin`,`org_code_end`," +
            "`insurance_license_no`,`insurance_license_begin`,`insurance_license_end`,`legal`,`legal_certi_type`," +
            "`legal_certi_no`,`legal_certi_begin`,`legal_certi_end`,`user_id`,`broker_mode`,`tax_qualification`," +
            "`is_open_tax_bill`,`tax_rate`,`valid_start_date`,`valid_end_date`,`social_credit_code`,`register_province`," +
            "`register_city`,`register_province_code`,`supervision_province`,`supervision_city`,`supervision_province_code`," +
            "`register_town`,`insurance_license_type`,`insurance_license_url`,`reg_address`,`contact_address`,`contact_mobile`," +
            "`contact_email`,`register_city_code`,`register_capital_code`,`supervision_city_code`,`supervision_capital_code`," +
            "`biz_license_url`,`register_county_code`,`register_county_name`,`partner_id`,`creator`,`gmt_created`,`modifier`," +
            "`gmt_modified`,`is_deleted`)VALUES(%1$s,'%2$s','%3$s','%4$s','%5$s','%6$s','%7$s','%8$s','%9$s','%10$s','%11$s'," +
            "'%12$s','%13$s','%14$s','%15$s','%16$s','%17$s','%18$s','%19$s','%20$s','%21$s','%22$s','%23$s','%24$s','%25$s'," +
            "'%26$s','%27$s','%28$s','%29$s','%30$s','%31$s','%32$s','%33$s','%34$s','lichao003',now(),'lichao003',now(),'N');";

    public static final String UPDATE = "UPDATE `channel_center_00`.`partner_extra_info`SET`tax_no` = '%2$s'," +
            "`tax_no_begin` = '%3$s',`tax_no_end` = '%4$s',`license_no` = '%5$s',`license_begin` = '%6$s'," +
            "`license_end` = '%7$s',`org_code` = '%8$s',`org_code_begin` = '%9$s',`org_code_end` = '%10$s'," +
            "`insurance_license_no` = '%11$s',`insurance_license_begin` = '%12$s',`insurance_license_end` ='%13$s'," +
            "`legal` = '%14$s',`legal_certi_type` ='%15$s',`legal_certi_no` ='%16$s',`legal_certi_begin` = '%17$s'," +
            "`legal_certi_end` ='%18$s',`user_id` ='%19$s',`broker_mode` ='%20$s',`tax_qualification` = '%21$s'," +
            "`is_open_tax_bill` ='%22$s',`tax_rate` ='%23$s',`valid_start_date` = '%24$s',`valid_end_date` = '%25$s'," +
            "`social_credit_code` = '%26$s',`register_province` = '%27$s',`register_city` ='%28$s'," +
            "`register_province_code` = '%29$s',`supervision_province` ='%30$s',`supervision_city` ='%31$s'," +
            "`supervision_province_code` ='%32$s',`register_town` ='%33$s',`partner_id` ='%34$s',`gmt_modified` = now()," +
            "`modifier` = 'lichao003'WHERE `id` = %1$s;";
    @Override
    protected void createInsertOrUpdateSql(String[] oldLines,String operateType) {
        String format = "";
        if (BaseComparator.INSERT.equalsIgnoreCase(operateType)) {
            format = String.format(INSERT, oldLines[1], oldLines[2], oldLines[3], oldLines[4], oldLines[5], oldLines[6] , oldLines[7]
                    , oldLines[8], oldLines[9], oldLines[10], oldLines[11], oldLines[12], oldLines[13], oldLines[14], oldLines[15]
                    , oldLines[16], oldLines[17], oldLines[18], oldLines[19], oldLines[20], oldLines[21], oldLines[22]
                    , oldLines[23], oldLines[24], oldLines[25], oldLines[26], oldLines[27], oldLines[28], oldLines[29]
                    , oldLines[30], oldLines[31], oldLines[32], oldLines[33], oldLines[34]);

        } else if (BaseComparator.UPDATE.equalsIgnoreCase(operateType)) {
            format = String.format(UPDATE, oldLines[1], oldLines[2], oldLines[3], oldLines[4], oldLines[5], oldLines[6], oldLines[7]
                    , oldLines[8], oldLines[9], oldLines[10], oldLines[11], oldLines[12], oldLines[13], oldLines[14], oldLines[15]
                    , oldLines[16], oldLines[17], oldLines[18], oldLines[19], oldLines[20], oldLines[21], oldLines[22]
                    , oldLines[23], oldLines[24], oldLines[25], oldLines[26], oldLines[27], oldLines[28], oldLines[29]
                    , oldLines[30], oldLines[31], oldLines[32], oldLines[33], oldLines[34]);
        } else if (BaseComparator.IGNORE.equalsIgnoreCase(operateType)) {
            // 2.0多的数据直接 存储
            format = StringUtils.join(oldLines, ",");
        }
        String aNull1 = format.replaceAll("'null'", "null");
        String aNull = aNull1.replaceAll("'NULL'", "null");
        BaseComparator.write(TABLE_NAME, aNull, operateType);
    }

    /**
     * 续保规则表业务主键是主键ID
     *
     * @param oldLines
     * @param newLines
     * @return
     */
    @Override
    public boolean isSameBusinessKey(String[] oldLines, String[] newLines) {
        if (Long.parseLong(oldLines[1]) != Long.parseLong(newLines[1])) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean isOldExtraData(String[] oldLines, String[] newLines) {
        if (Integer.parseInt(oldLines[1]) < Integer.parseInt(newLines[1])) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) throws Exception {
        // todo 重新对比数据
        RenewalRuleComparator comparator = new RenewalRuleComparator();
        comparator.createFile(TABLE_NAME);
        comparator.comparator("product_renewal_rule-v1-0304.csv", "renewal_rule-v2-0304.csv",  1, null, null, null);
    }
}
