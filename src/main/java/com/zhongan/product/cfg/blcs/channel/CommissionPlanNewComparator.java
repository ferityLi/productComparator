package com.zhongan.product.cfg.blcs.channel;

import com.zhongan.product.cfg.blcs.BaseComparator;

/**
 * 佣金表-新保
 * @author dailiang
 */
public class CommissionPlanNewComparator extends BaseComparator {

    /**
     * 佣金表业务主键是主键ID
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
        CommissionPlanNewComparator comparator = new CommissionPlanNewComparator();
        comparator.comparator("commission_plan_definition-v1-0307.csv", "commission_plan-v2-new-0307.csv",  1, ",4,5,", null, null);
    }
}
