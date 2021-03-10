package com.zhongan.product.cfg.blcs.channel;

import com.zhongan.product.cfg.blcs.BaseComparator;

/**
 * 共保附件表
 * @author dailiang
 */
public class CoinsuranceAgreementAttachmentComparator extends BaseComparator {

    /**
     * 共保附件表业务主键是主键ID
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
        CoinsuranceAgreementAttachmentComparator comparator = new CoinsuranceAgreementAttachmentComparator();
        comparator.comparator("product_attachment-v1-0305.csv", "coinsurance_agreement_attachment-v2-0305.csv",  1, null, null, null);
    }
}
