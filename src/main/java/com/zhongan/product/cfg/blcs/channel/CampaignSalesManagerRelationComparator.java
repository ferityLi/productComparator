package com.zhongan.product.cfg.blcs.channel;

import com.zhongan.product.cfg.blcs.BaseComparator;

/**
 * 业绩归属
 * @author dailiang
 */
public class CampaignSalesManagerRelationComparator extends BaseComparator {

    /**
     * 业绩归属业务主键是主键ID
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
        CampaignSalesManagerRelationComparator comparator = new CampaignSalesManagerRelationComparator();
        comparator.comparator("campaign_sales_manager_relation-v1-0305.csv", "campaign_sales_manager_relation-v2-0305.csv",  1, null, null, null);
    }
}
