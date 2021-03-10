package com.zhongan.product.cfg.blcs.product;

import com.zhongan.product.cfg.blcs.BaseComparator;

/**
 * 组合产品表
 * @author dailiang
 */
public class PackageProductComparator extends BaseComparator {

    /**
     * 组合产品表业务主键是 package_id、product_id
     *
     * @param oldLines
     * @param newLines
     * @return
     */
    @Override
    public boolean isSameBusinessKey(String[] oldLines, String[] newLines) {
        if (!getLong(oldLines[1]).equals(getLong(newLines[1])) || !getLong(oldLines[2]).equals(getLong(newLines[2]))) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean isOldExtraData(String[] oldLines, String[] newLines) {
        if (getLong(oldLines[1]) < getLong(newLines[1])) {
            return true;
        } else if (getLong(oldLines[1]).equals(getLong(newLines[1]))) {
            if (getLong(oldLines[2]) < getLong(newLines[2])) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private Long getLong(String str) {
        return Long.parseLong(str.substring(1, str.length() - 1));
    }

    @Override
    protected void createInsertOrUpdateSql(String[] oldLines, String oprateType) {

    }

    public static void main(String[] args) throws Exception {
        PackageProductComparator comparator = new PackageProductComparator();
        comparator.comparator("policy_product_package-v1-0307.csv", "package_product-v2-0307.csv",  1, null, null, null);
    }
}
