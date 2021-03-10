package com.zhongan.product.cfg.blcs.product;

import com.zhongan.product.cfg.blcs.BaseComparator;

/**
 * 组合健康告知
 * @author dailiang
 */
public class PackageHealthNoticeComparator extends BaseComparator {

    /**
     * 组合健康告知的业务主键是主键ID
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
        PackageHealthNoticeComparator comparator = new PackageHealthNoticeComparator();
        comparator.comparator("product_health_notice-v1-0308.csv", "package_health_notice-v2-0308.csv",  1, null, null, null);
    }
}
