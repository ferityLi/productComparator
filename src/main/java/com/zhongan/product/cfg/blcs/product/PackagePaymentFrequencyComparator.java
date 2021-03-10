package com.zhongan.product.cfg.blcs.product;

import com.zhongan.product.cfg.blcs.BaseComparator;

/**
 * 组合缴费频率表
 * @author dailiang
 */
public class PackagePaymentFrequencyComparator extends BaseComparator {

    /**
     * 组合缴费频率表业务主键是主键ID
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
        PackagePaymentFrequencyComparator comparator = new PackagePaymentFrequencyComparator();
        comparator.comparator("product_pay_frequency-0302.csv", "package_payment_frequency-0302.csv",  1, ",5,7,", null, null);
    }
}
