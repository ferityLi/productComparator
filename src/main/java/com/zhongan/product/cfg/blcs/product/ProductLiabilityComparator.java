package com.zhongan.product.cfg.blcs.product;

import com.zhongan.product.cfg.blcs.BaseComparator;

/**
 * 产品责任表
 * @author dailiang
 */
public class ProductLiabilityComparator extends BaseComparator {

    /**
     * 产品责任表业务主键是主键ID
     *
     * @param oldLines
     * @param newLines
     * @return
     */
    @Override
    public boolean isSameBusinessKey(String[] oldLines, String[] newLines) {
        if (!oldLines[1].equals(newLines[1]) || !oldLines[2].equals(newLines[2])) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean isOldExtraData(String[] oldLines, String[] newLines) {
        if (Long.parseLong(oldLines[1]) < Long.parseLong(newLines[1])) {
            return true;
        } else if (Long.parseLong(oldLines[1]) == Long.parseLong(newLines[1])) {
            if (oldLines[2].equals("NULL") || newLines[2].equals("NULL")) {
                return true;
            } else if (Long.parseLong(oldLines[2]) < Long.parseLong(newLines[2])) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    protected void createInsertOrUpdateSql(String[] oldLines, String oprateType) {

    }

    public static void main(String[] args) throws Exception {
        ProductLiabilityComparator comparator = new ProductLiabilityComparator();
        comparator.comparator("product_liability-v1-0307.csv", "product_liability-v2-0307.csv",  1, ",4,5,", null, null);
    }
}
