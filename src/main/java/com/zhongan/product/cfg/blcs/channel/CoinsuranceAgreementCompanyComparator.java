package com.zhongan.product.cfg.blcs.channel;

import com.zhongan.product.cfg.blcs.BaseComparator;

/**
 * 共保公司协议关联表
 * @author dailiang
 */
public class CoinsuranceAgreementCompanyComparator extends BaseComparator {

    /**
     * 共保公司协议关联表业务主键是协议id和公司code
     *
     * @param oldLines
     * @param newLines
     * @return
     */
    @Override
    public boolean isSameBusinessKey(String[] oldLines, String[] newLines) {
        if (Long.parseLong(oldLines[1]) != Long.parseLong(newLines[1]) || !oldLines[6].equals(newLines[6])) {
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
        CoinsuranceAgreementCompanyComparator comparator = new CoinsuranceAgreementCompanyComparator();
        comparator.comparator("coinsurance_agreement_company-v1-0305.csv", "coinsurance_agreement_company-v2-0305.csv",  1, ",2,5,", null, null);
    }
}
