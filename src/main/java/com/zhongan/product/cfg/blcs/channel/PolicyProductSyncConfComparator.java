package com.zhongan.product.cfg.blcs.channel;

import com.zhongan.product.cfg.blcs.BaseComparator;
import org.apache.commons.lang3.StringUtils;

/**
 * 保单产品同步配置表
 *
 * @author dailiang
 */
public class PolicyProductSyncConfComparator extends BaseComparator {

    public static final String TABLE_NAME = "policy_product_sync_conf";

    public static final String INSERT = "INSERT INTO `channel_center_00`.`policy_product_sync_conf`(`id`,`package_id`," +
            "`channel_id`,`policy_sync_channel`,`effective_time`,`expire_time`,`sync_type`,`creator`,`gmt_created`," +
            "`modifier`,`gmt_modified`,`is_deleted`)VALUES(%1$s,'%2$s','%3$s','%4$s','%5$s','%6$s','%7$s','lichao003'," +
            "now(),'lichao003',now(),'N');";

    public static final String UPDATE = "UPDATE `channel_center_00`.`policy_product_sync_conf`SET`package_id` = '%2$s'," +
            "`channel_id` = '%3$s',`policy_sync_channel` = '%4$s',`effective_time` = '%5$s',`expire_time` = '%6$s'," +
            "`sync_type` ='%7$s',`gmt_modified` = now(),`modifier` = 'lichao003'WHERE `id` = %1$s;";

    @Override
    protected void createInsertOrUpdateSql(String[] oldLines, String operateType) {
        String format = "";
        if (BaseComparator.INSERT.equalsIgnoreCase(operateType)) {
            format = String.format(INSERT, oldLines[1], oldLines[2], oldLines[3], oldLines[4], oldLines[5],
                    oldLines[6], oldLines[7]);

        } else if (BaseComparator.UPDATE.equalsIgnoreCase(operateType)) {
            format = String.format(UPDATE, oldLines[1], oldLines[2], oldLines[3], oldLines[4], oldLines[5],
                    oldLines[6], oldLines[7]);
        } else if (BaseComparator.IGNORE.equalsIgnoreCase(operateType)) {
            // 2.0多的数据直接 存储
            format = StringUtils.join(oldLines, ",");
        }
        String aNull1 = format.replaceAll("'null'", "null");
        String aNull = aNull1.replaceAll("'NULL'", "null");
        BaseComparator.write(TABLE_NAME, aNull, operateType);
    }

    /**
     * 保单产品同步配置表的业务主键是主键ID
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
        PolicyProductSyncConfComparator comparator = new PolicyProductSyncConfComparator();
        comparator.createFile(TABLE_NAME);
        comparator.comparator("policy_product_sync_conf-v1-0304.csv", "policy_product_sync_conf-v2-0304.csv", 1, null, null, null);
    }
}
