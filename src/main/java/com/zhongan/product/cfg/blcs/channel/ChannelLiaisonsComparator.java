package com.zhongan.product.cfg.blcs.channel;

import com.zhongan.product.cfg.blcs.BaseComparator;
import org.apache.commons.lang3.StringUtils;

/**
 * 协议联络人表
 * @author dailiang
 */
public class ChannelLiaisonsComparator extends BaseComparator {

    public static final String TABLE_NAME = "channel_liaisons";

    public static final String INSERT = "INSERT INTO `channel_center_00`.`channel_liaisons`(`id`,`channel_id`," +
            "`contacts_name`,`contacts_email`,`contacts_department_name`,`gmt_created`,`gmt_modified`,`creator`," +
            "`modifier`,`is_deleted`)" +
            "VALUES(%1$s,'%5$s','%2$s','%3$s','%4$s',now(),now(),'lichao003','lichao003','N');";

    public static final String UPDATE = "UPDATE `channel_center_00`.`channel_liaisons`SET`channel_id` = '%5$s'," +
            "`contacts_name` = '%2$s',`contacts_email` = '%3$s',`contacts_department_name` ='%4$s',`gmt_modified` = now()," +
            "`modifier` = 'lichao003' WHERE `id` = %1$s;";

    @Override
    protected void createInsertOrUpdateSql(String[] oldLines,String operateType) {
        String format = "";
        if (BaseComparator.INSERT.equalsIgnoreCase(operateType)) {
            format = String.format(INSERT, oldLines[1], oldLines[2], oldLines[3], oldLines[4], oldLines[5]);

        } else if (BaseComparator.UPDATE.equalsIgnoreCase(operateType)) {
            format = String.format(UPDATE, oldLines[1], oldLines[2], oldLines[3], oldLines[4], oldLines[5]);
        } else if (BaseComparator.IGNORE.equalsIgnoreCase(operateType)) {
            // 2.0多的数据直接 存储
            format = StringUtils.join(oldLines, ",");
        }
        String aNull1 = format.replaceAll("'null'", "null");
        String aNull = aNull1.replaceAll("'NULL'", "null");
        BaseComparator.write(TABLE_NAME, aNull, operateType);
    }

    /**
     * 协议联络人表业务主键是主键ID
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
        ChannelLiaisonsComparator comparator = new ChannelLiaisonsComparator();
        comparator.createFile(TABLE_NAME);
        comparator.comparator("channel_definition_liaisons-v1-0303.csv", "channel_liaisons-v2-0303.csv",  1, null, null, null);
    }
}
