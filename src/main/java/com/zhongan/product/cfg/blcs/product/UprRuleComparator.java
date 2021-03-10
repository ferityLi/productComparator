package com.zhongan.product.cfg.blcs.product;

import com.zhongan.product.cfg.blcs.BaseComparator;

/**
 * UPR规则表
 * @author dailiang
 */
public class UprRuleComparator extends BaseComparator {

    /**
     * UPR规则表业务主键是组合id、产品id
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
        UprRuleComparator comparator = new UprRuleComparator();
        comparator.comparator("legal_upr_accrue_rule-v1-0307.csv", "upr_rule-v1-0307.csv",  1, null, null, null);
    }
}
