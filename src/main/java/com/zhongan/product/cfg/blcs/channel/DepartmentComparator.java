package com.zhongan.product.cfg.blcs.channel;

import com.zhongan.product.cfg.blcs.BaseComparator;

/**
 * @author huyong
 * @since 2021-3-2
 */
public class DepartmentComparator extends BaseComparator {

    public static final String TABLE_NAME = "department";

    public static final String INSERT = "INSERT INTO `channel_center_00`.`department`(`id`,`department_code`,`department_name`," +
            "`department_type`,`manager_id`,`parent_id`,`start_date`,`end_date`,`department_level`,`emails`,`gmt_created`," +
            "`gmt_modified`,`creator`,`modifier`,`is_deleted`)" +
            "VALUES(%1$s,'%2$s','%3$s','%4$s','%5$s','%6$s','%7$s','%8$s','%9$s',null,now(),now(),'lichao003','lichao003','N');";

    public static final String UPDATE = "UPDATE `channel_center_00`.`department`SET `department_code` = '%2$s'," +
            "`department_name` = '%3$s',`department_type` = '%4$s',`manager_id` = '%5$s',`parent_id` = '%6$s'," +
            "`start_date` = '%7$s',`end_date` = '%8$s',`department_level` = '%9$s',`gmt_modified` =now()," +
            " `modifier` = 'lichao003'WHERE `id` = %1$s;";


    @Override
    protected void createInsertOrUpdateSql(String[] oldLines,String operateType) {
 /*       String format = "";
        if (BaseComparator.INSERT.equalsIgnoreCase(operateType)) {
            format = String.format(INSERT, oldLines[1], oldLines[2], oldLines[3], oldLines[4], oldLines[5], oldLines[6] , oldLines[7]
                    , oldLines[8], oldLines[9]);

        } else if (BaseComparator.UPDATE.equalsIgnoreCase(operateType)) {
            format = String.format(UPDATE, oldLines[1], oldLines[2], oldLines[3], oldLines[4], oldLines[5], oldLines[6], oldLines[7]
                    , oldLines[8], oldLines[9]);
        } else if (BaseComparator.IGNORE.equalsIgnoreCase(operateType)) {
            // 2.0多的数据直接 存储
            format = StringUtils.join(oldLines, ",");
        }
        String aNull1 = format.replaceAll("'null'", "null");
        String aNull = aNull1.replaceAll("'NULL'", "null");
        BaseComparator.write(TABLE_NAME, aNull, operateType);*/
    }

    /**
     * 部门的业务主键是主键ID
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
        DepartmentComparator comparator = new DepartmentComparator();
        comparator.createFile(TABLE_NAME);
        comparator.comparator("department_definition-0301.csv", "department-0301.csv", 2, null, null, null);
    }
}
