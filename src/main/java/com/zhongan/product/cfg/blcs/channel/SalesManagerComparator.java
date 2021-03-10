package com.zhongan.product.cfg.blcs.channel;

import com.zhongan.product.cfg.blcs.BaseComparator;
import org.apache.commons.lang3.StringUtils;

/**
 * @author huyong
 * @since 2021-2-26
 */
public class SalesManagerComparator extends BaseComparator {


    public static final String TABLE_NAME = "channel_center";

    public static final String INSERT = "INSERT INTO `channel_center_00`.`sales_manager`(`id`,`manager_name`," +
            "`manager_no`,`job_status`,`mobile_phone`,`email`,`remark`,`domain_account`,`creator`,`gmt_created`," +
            "`modifier`,`gmt_modified`,`is_deleted`)VALUES(%1$s,'%2$s','%3$s','%4$s','%5$s','%6$s','%7$s','%8$s'," +
            "'lichao003',now(),'lichao003',now(),'N');";

    public static final String UPDATE = "UPDATE `channel_center_00`.`sales_manager`SET `manager_name` = '%2$s'," +
            "`manager_no` = '%3$s',`job_status` ='%4$s',`mobile_phone` = '%5$s',`email` = '%6$s',`remark` = '%7$s'," +
            "`domain_account` = '%8$s',`gmt_modified` = now(), `modifier` = 'lichao003' WHERE `id` = %1$s;";

    @Override
    protected void createInsertOrUpdateSql(String[] oldLines, String operateType) {
        String format = "";
        if (BaseComparator.INSERT.equalsIgnoreCase(operateType)) {
            format = String.format(INSERT, oldLines[1], oldLines[2], oldLines[3], oldLines[4], oldLines[5], oldLines[6], oldLines[7]
                    , oldLines[8]);

        } else if (BaseComparator.UPDATE.equalsIgnoreCase(operateType)) {
            format = String.format(UPDATE, oldLines[1], oldLines[2], oldLines[3], oldLines[4], oldLines[5], oldLines[6], oldLines[7]
                    , oldLines[8]);
        } else if (BaseComparator.IGNORE.equalsIgnoreCase(operateType)) {
            // 2.0多的数据直接 存储
            format = StringUtils.join(oldLines, ",");
        }
        String aNull1 = format.replaceAll("'null'", "null");
        String aNull = aNull1.replaceAll("'NULL'", "null");
        BaseComparator.write(TABLE_NAME, aNull, operateType);
    }

    /**
     * 经办人的业务主键是主键ID
     *
     * @param oldLines
     * @param newLines
     * @return
     */
    @Override
    public boolean isSameBusinessKey(String[] oldLines, String[] newLines) {
        if (Integer.parseInt(oldLines[1]) != Integer.parseInt(newLines[1])) {
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
        SalesManagerComparator comparator = new SalesManagerComparator();
        comparator.createFile(TABLE_NAME);
        comparator.comparator("sales_manager_def-0225.csv", "sales_manager-0225.csv", 2, null, null, null);
    }
}
