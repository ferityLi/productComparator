package com.zhongan.product.cfg.blcs.channel;

import com.zhongan.product.cfg.blcs.BaseComparator;

/**
 * 佣金表-续保
 * @author dailiang
 */
public class CommissionPlanRenewComparator extends BaseComparator {

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
        CommissionPlanRenewComparator comparator = new CommissionPlanRenewComparator();
        comparator.comparator("commission_renewal_def-v1-0307.csv", "commission_plan-v2-renew-0307.csv",  1, ",4,5,", null, ",6,7,");
    }
}
