package com.zhongan.product.cfg.blcs.product;

import com.zhongan.product.cfg.blcs.BaseComparator;

/**
 * @author huyong
 * @since 2021-3-3
 */
public class ProductComparator extends BaseComparator {

    /**
     * 条款的业务主键是主键ID
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

    @Override
    protected void createInsertOrUpdateSql(String[] oldLines, String oprateType) {

    }

    public static void main(String[] args) throws Exception {
        ProductComparator comparator = new ProductComparator();
        comparator.comparator("product_def-0302.csv", "product-0302-延后1.csv",  2, null, ",12,13,14,15,34,35,36,37,30,32,31,33,29,16,17,18,", null);
    }
}
