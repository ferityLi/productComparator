package com.zhongan.product.cfg.blcs.product;

import com.zhongan.product.cfg.blcs.BaseComparator;
import org.apache.commons.lang3.StringUtils;

/**
 * 出现原因基表
 *
 * @author dailiang
 */
public class LossCauseComparator extends BaseComparator {

    public static final String TABLE_NAME = "loss_cause";

    public static final String INSERT = "INSERT INTO `product_center_00`.`loss_cause`(`id`,`product_category`,`loss_cause_code`," +
            "`loss_cause_name`,`creator`,`gmt_created`,`modifier`,`gmt_modified`,`is_deleted`)VALUES" +
            "(%1$s,'%2$s','%3$s','%4$s','lichao003',now(),'lichao003',now(),'N');";

    public static final String UPDATE = "UPDATE `product_center_00`.`loss_cause`SET`product_category` ='%2$s'," +
            "`loss_cause_code` = '%3$s',`loss_cause_name` = '%4$s',`modifier` ='lichao003',`gmt_modified` =now() " +
            "WHERE `id` = %1$s;";

    @Override
    protected void createInsertOrUpdateSql(String[] oldLines, String operateType) {
        String format = "";
        if (BaseComparator.INSERT.equalsIgnoreCase(operateType)) {
            format = String.format(INSERT, oldLines[1], oldLines[2], oldLines[3], oldLines[4]);

        } else if (BaseComparator.UPDATE.equalsIgnoreCase(operateType)) {
            format = String.format(UPDATE, oldLines[1], oldLines[2], oldLines[3], oldLines[4]);
        }else if (BaseComparator.IGNORE.equalsIgnoreCase(operateType)){
            // 2.0多的数据直接 存储
            format = StringUtils.join(oldLines, ",");
        }
        String aNull1 = format.replaceAll("'null'", "null");
        String aNull = aNull1.replaceAll("'NULL'", "null");
        BaseComparator.write(TABLE_NAME, aNull, operateType);
    }

    /**
     * 出现原因基表的业务主键是主键ID
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
        // 每次创建一个新的文件存储
        LossCauseComparator comparator = new LossCauseComparator();
        comparator.createFile(TABLE_NAME);
        comparator.comparator("product_loss_code-0303.csv", "loss_cause-0303.csv", 1, null, null, null);
    }
}
