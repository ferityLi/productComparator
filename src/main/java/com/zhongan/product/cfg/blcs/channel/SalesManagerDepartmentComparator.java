package com.zhongan.product.cfg.blcs.channel;

import com.zhongan.product.cfg.blcs.BaseComparator;
import com.zhongan.product.cfg.common.util.DateUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * @author huyong
 * @since 2021-3-2
 */
public class SalesManagerDepartmentComparator extends BaseComparator {

    public static final String TABLE_NAME = "sales_manager_department";

    public static final String INSERT = "INSERT INTO `channel_center_00`.`sales_manager_department`(`id`,`sales_manager_id`,`department_id`,`is_business_department_manager`,`is_effective`,`job_type`,`location_address`,`start_date`,`end_date`,`creator`,`gmt_created`,`modifier`,`gmt_modified`,`is_deleted`)VALUES(%1$s,'%2$s','%3$s','%4$s','%5$s','%6$s','%7$s','%8$s','%9$s','lichao003',now(),'lichao003',now(),'N');";

    public static final String UPDATE = "UPDATE `channel_center_00`.`sales_manager_department`SET `sales_manager_id` = '%2$s',`department_id` = '%3$s',`is_business_department_manager` = '%4$s',`is_effective` = '%5$s',`job_type` = '%6$s',`location_address` = '%7$s',`start_date` = '%8$s',`end_date` = '%9$s',`gmt_modified` = now(), `modifier` = 'lichao003'WHERE `id` = %1$s;";

    @Override
    protected void createInsertOrUpdateSql(String[] oldLines, String operateType) {
        String format = "";
        if (BaseComparator.INSERT.equalsIgnoreCase(operateType)) {
            format = String.format(INSERT, oldLines[1], oldLines[2], oldLines[3], oldLines[4], oldLines[5], oldLines[6],
                    oldLines[7], oldLines[8], oldLines[9]);

        } else if (BaseComparator.UPDATE.equalsIgnoreCase(operateType)) {
            format = String.format(UPDATE, oldLines[1], oldLines[2], oldLines[3], oldLines[4], oldLines[5], oldLines[6],
                    oldLines[7], oldLines[8], oldLines[9]);
        } else if (BaseComparator.IGNORE.equalsIgnoreCase(operateType)) {
            // 2.0多的数据直接 存储
            format = StringUtils.join(oldLines, ",");
        }
        String aNull1 = format.replaceAll("'null'", "null");
        String aNull = aNull1.replaceAll("'NULL'", "null");
        BaseComparator.write(TABLE_NAME, aNull, operateType);
    }

    /**
     * 经办人部门的业务主键是经办人ID+经办人部门任职起期
     *
     * @param oldLines
     * @param newLines
     * @return
     */
    @Override
    public boolean isSameBusinessKey(String[] oldLines, String[] newLines) {
        if (Long.parseLong(oldLines[1]) != Long.parseLong(newLines[1]) || !StringUtils.equals(oldLines[7], newLines[7])) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean isOldExtraData(String[] oldLines, String[] newLines) {
        if (Long.parseLong(oldLines[1]) < Long.parseLong(newLines[1])) {
            return true;
        } else if (Long.parseLong(oldLines[1]) == Long.parseLong(newLines[1])) {
            if (DateUtils.strFormatDate(oldLines[7], DateUtils.DATE_PATTERN).compareTo(DateUtils.strFormatDate(newLines[7], DateUtils.DATE_PATTERN)) < 0) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }

    }

    public static void main(String[] args) throws Exception {
        SalesManagerDepartmentComparator comparator = new SalesManagerDepartmentComparator();
        comparator.createFile(TABLE_NAME);
        comparator.comparator("sales_manager_department_relation-0301.csv", "sales_manager_department-0301.csv", 2, null, null, null);
/*        String str = "channel_center_00.sales_manager_department,230217,17,N,Y,1,,2017-06-01,2199-12-31,N";
        System.out.println(StringUtils.split(str, ",").length);
        System.out.println(str.split(",").length);*/
    }
}
