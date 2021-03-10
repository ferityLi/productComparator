package com.zhongan.product.cfg.blcs.channel;

import com.zhongan.product.cfg.blcs.BaseComparator;
import org.apache.commons.lang3.StringUtils;

/**
 * 渠道定义表
 *
 * @author dailiang
 */
public class ChannelComparator extends BaseComparator {

    public static final String TABLE_NAME = "channel";

    public static final String INSERT = "INSERT INTO `channel_center_00`.`channel`" +
            "(`id`,`channel_level`,`channel_code`,`t2_code`,`channel_full_name`,`channel_short_name`,`channel_start_date`" +
            ",`channel_end_date`,`is_related_channel`,`direct_business_type`,`is_taobao_message`,`network_platform_type`" +
            ",`sales_model`,`business_department`,`campaign`,`is_termination`,`reinsur_type`,`check_valid`,`channel_area`," +
            "`is_bank`,`termination_date`,`parent_channel_id`,`creator`,`gmt_created`,`modifier`,`gmt_modified`,`is_deleted`)" +
            "VALUES(%1$s,'%2$s','%3$s','%4$s','%5$s','%6$s','%7$s','%8$s','%9$s','%10$s','%11$s','%12$s','%13$s'," +
            "'%14$s','%15$s','%16$s','%17$s','%18$s','%19$s','%20$s','%21$s','%22$s','lichao003',now(),'lichao003',now(),'N');";

    public static final String UPDATE = "UPDATE `channel_center_00`.`channel`SET `channel_level` ='%2$s',`channel_code` = '%3$s'," +
            "`t2_code` = '%4$s',`channel_full_name` ='%5$s',`channel_short_name` ='%6$s',`channel_start_date` = '%7$s'," +
            "`channel_end_date` = '%8$s',`is_related_channel` ='%9$s',`direct_business_type` ='%10$s',`is_taobao_message` = '%11$s'," +
            "`network_platform_type` = '%12$s',`sales_model` ='%13$s',`business_department` = '%14$s',`campaign` = '%15$s'," +
            "`is_termination` = '%16$s',`reinsur_type` = '%17$s',`check_valid` = '%18$s',`channel_area` = '%19$s',`is_bank` = '%20$s'," +
            "`termination_date` = '%21$s',`parent_channel_id` = '%22$s',`gmt_modified` = now(),`modifier` = 'lichao003' WHERE `id` =%1$s;";


    @Override
    protected void createInsertOrUpdateSql(String[] oldLines, String operateType) {
        String format = "";
        String is_termination = null;
        if ("1".equalsIgnoreCase(oldLines[16])) {
            is_termination = "Y";
        } else if ("0".equalsIgnoreCase(oldLines[16])) {
            is_termination = "N";
        } else {
            is_termination = oldLines[16];
        }

        String is_related_channel = null;
        if ("null".equalsIgnoreCase(oldLines[9]) || "".equalsIgnoreCase(oldLines[9])){
            is_related_channel = "N";
        }else {
            is_related_channel = oldLines[9];
        }
        String is_taobao_message = null;
        if ("null".equalsIgnoreCase(oldLines[11]) || "".equalsIgnoreCase(oldLines[11])){
            is_taobao_message = "N";
        }else {
            is_taobao_message = oldLines[11];
        }
        String reinsur_type = null;
        if ("null".equalsIgnoreCase(oldLines[17]) || "".equalsIgnoreCase(oldLines[17])){
            reinsur_type = "1";
        }else {
            reinsur_type = oldLines[17];
        }




        if (BaseComparator.INSERT.equalsIgnoreCase(operateType)) {
            format = String.format(INSERT, oldLines[1], oldLines[2], oldLines[3], oldLines[4], oldLines[5], oldLines[6], oldLines[7]
                    , oldLines[8], is_related_channel, oldLines[10], is_taobao_message, oldLines[12], oldLines[13], oldLines[14], oldLines[15]
                    , is_termination, reinsur_type, oldLines[18], oldLines[19], oldLines[20], oldLines[21], oldLines[22]);

        } else if (BaseComparator.UPDATE.equalsIgnoreCase(operateType)) {
            format = String.format(UPDATE, oldLines[1], oldLines[2], oldLines[3], oldLines[4], oldLines[5], oldLines[6], oldLines[7]
                    , oldLines[8], is_related_channel, oldLines[10], is_taobao_message, oldLines[12], oldLines[13], oldLines[14], oldLines[15]
                    , is_termination, reinsur_type, oldLines[18], oldLines[19], oldLines[20], oldLines[21], oldLines[22]);
        } else if (BaseComparator.IGNORE.equalsIgnoreCase(operateType)) {
            // 2.0多的数据直接 存储
            format = StringUtils.join(oldLines, ",");
        }
        String aNull1 = format.replaceAll("'null'", "null");
        String aNull = aNull1.replaceAll("'NULL'", "null");
        BaseComparator.write(TABLE_NAME, aNull, operateType);
    }

    /**
     * 渠道定义表业务主键是主键ID
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
        if (Long.parseLong(oldLines[1]) < Long.parseLong(newLines[1])) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) throws Exception {
        ChannelComparator comparator = new ChannelComparator();
        comparator.createFile(TABLE_NAME);
        comparator.comparator("channel_definition-v1-0303.csv", "channel-v2-0303.csv", 1, null, null, null);
    }
}
