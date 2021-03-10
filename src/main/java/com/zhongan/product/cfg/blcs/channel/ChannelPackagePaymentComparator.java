package com.zhongan.product.cfg.blcs.channel;

import com.zhongan.product.cfg.blcs.BaseComparator;

/**
 * 渠道组合应收应付时间表
 * @author dailiang
 */
public class ChannelPackagePaymentComparator extends BaseComparator {

    /**
     * 渠道组合应收应付时间表业务主键是主键ID
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
        ChannelPackagePaymentComparator comparator = new ChannelPackagePaymentComparator();
        comparator.comparator("channel_product_payment_config-v1-0304.csv", "channel_package_payment-v2-0304.csv",  1, null, null, null);
    }
}
