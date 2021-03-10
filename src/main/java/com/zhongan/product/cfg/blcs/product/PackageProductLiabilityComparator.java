package com.zhongan.product.cfg.blcs.product;

import com.zhongan.product.cfg.blcs.BaseComparator;

/**
 * 组合产品责任表
 * @author dailiang
 */
public class PackageProductLiabilityComparator extends BaseComparator {

    /**
     * 组合产品责任表业务主键是 package_id、product_id、liability_id
     *
     * @param oldLines
     * @param newLines
     * @return
     */
    @Override
    public boolean isSameBusinessKey(String[] oldLines, String[] newLines) {
        if (!getLong(oldLines[1]).equals(getLong(newLines[1])) || !getLong(oldLines[2]).equals(getLong(newLines[2])) || !getLong(oldLines[17]).equals(getLong(newLines[17]))) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean isOldExtraData(String[] oldLines, String[] newLines) {
        if (Integer.parseInt(oldLines[17]) < Integer.parseInt(newLines[17])) {
            return true;
        } else if (Integer.parseInt(oldLines[17]) == Integer.parseInt(newLines[17])){
            if (Integer.parseInt(oldLines[1]) < Integer.parseInt(newLines[1])) {
                return true;
            } else if (Integer.parseInt(oldLines[1]) == Integer.parseInt(newLines[1])){
                if (Integer.parseInt(oldLines[2]) < Integer.parseInt(newLines[2])) {
                    return true;
                } else {
                    return false;
                }
            }else {
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
        PackageProductLiabilityComparator comparator = new PackageProductLiabilityComparator();
        comparator.comparator("policy_product_liability-v1-0308.csv", "package_product_liability-v2-0308.csv",  1, null, null, null);
    }
}
