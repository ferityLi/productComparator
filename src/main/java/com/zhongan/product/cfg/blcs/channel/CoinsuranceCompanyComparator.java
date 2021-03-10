package com.zhongan.product.cfg.blcs.channel;

import com.zhongan.product.cfg.blcs.BaseComparator;

/**
 * 共保公司表
 * @author dailiang
 */
public class CoinsuranceCompanyComparator extends BaseComparator {

    /**
     * 共保公司表业务主键是code
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
        CoinsuranceCompanyComparator comparator = new CoinsuranceCompanyComparator();
        comparator.comparator("co_insurance_agreement-v1-0305.csv", "coinsurance_agreement-v2-0305.csv",  1, null, null, null);
    }
}
