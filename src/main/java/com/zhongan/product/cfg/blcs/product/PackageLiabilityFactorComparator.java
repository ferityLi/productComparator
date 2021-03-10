package com.zhongan.product.cfg.blcs.product;

import com.zhongan.product.cfg.blcs.BaseComparator;

/**
 * 责任因子表
 * @author dailiang
 */
public class PackageLiabilityFactorComparator extends BaseComparator {

    /**
     * 责任因子表业务主键是id
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
        PackageLiabilityFactorComparator comparator = new PackageLiabilityFactorComparator();
        comparator.comparator("product_loss_code-0303.csv", "loss_cause-0303.csv",  1, null, null, null);
    }
}
