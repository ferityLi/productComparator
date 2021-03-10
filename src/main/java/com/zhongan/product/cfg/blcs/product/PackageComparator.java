package com.zhongan.product.cfg.blcs.product;

import com.zhongan.product.cfg.blcs.BaseComparator;

/**
 * 组合表
 * @author dailiang
 */
public class PackageComparator extends BaseComparator {

    /**
     * 组合表业务主键是主键ID
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

    @Override
    protected void createInsertOrUpdateSql(String[] oldLines, String oprateType) {

    }

    public static void main(String[] args) throws Exception {
        PackageComparator comparator = new PackageComparator();
        comparator.comparator("product_package_def-v1-0307.csv", "package-v2-0307.csv",  1, null, null, ",7,8,");
    }
}
