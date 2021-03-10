package com.zhongan.product.cfg.blcs.product;

import com.zhongan.product.cfg.blcs.BaseComparator;

/**
 * 产品出险原因表
 */
public class ProductLossCauseComparator extends BaseComparator {

    /**
     * 产品出险原因表业务主键是主键ID
     *
     * @param oldLines
     * @param newLines
     * @return
     */
    @Override
    public boolean isSameBusinessKey(String[] oldLines, String[] newLines) {
        if (oldLines.length == 2) {
            return true;
        } else if (Long.parseLong(oldLines[1]) != Long.parseLong(newLines[1])
                || !oldLines[2].equals(newLines[2])) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean isOldExtraData(String[] oldLines, String[] newLines) {
        if (oldLines.length == 2 || Long.parseLong(oldLines[1]) < Long.parseLong(newLines[1])) {
            return true;
        } else if (Long.parseLong(oldLines[1]) == Long.parseLong(newLines[1])) {
            if (oldLines[2].compareTo(newLines[2]) < 0) {
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
        ProductLossCauseComparator comparator = new ProductLossCauseComparator();
        comparator.comparator("claim_product_loss_code-v1-0308.csv", "product_loss_cause-v2-0308.csv",  1, null, null, null);
    }
}
