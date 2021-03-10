package com.zhongan.product.cfg.blcs.channel;

import com.zhongan.product.cfg.blcs.BaseComparator;

/**
 * 共保计划表
 * @author dailiang
 */
public class ChannelPackageCoinsuranceComparator extends BaseComparator {

    /**
     * 共保计划表业务主键是主键ID
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
        ChannelPackageCoinsuranceComparator comparator = new ChannelPackageCoinsuranceComparator();
        comparator.comparator("co_insurance_project-v1-0305.csv", "channel_package_coinsurance-v2-0305.csv",  1, null, null, null);
    }
}
