package com.zhongan.product.cfg.blcs.channel;

import com.zhongan.product.cfg.blcs.BaseComparator;

/**
 * 渠道组合特约
 * @author dailiang
 */
public class ChannelPackageSpecialAgreementComparator extends BaseComparator {

    /**
     * 渠道组合特约业务主键是主键ID
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
        ChannelPackageSpecialAgreementComparator comparator = new ChannelPackageSpecialAgreementComparator();
        comparator.comparator("channel_package_special_agreement-v1-0307.csv", "channel_package_special_agreement-v2-0307.csv",  1, null, null, null);
    }
}
