package com.zhongan.product.cfg.blcs.product;

import com.zhongan.product.cfg.blcs.BaseComparator;

/**
 * @author huyong
 * @since 2021-3-3
 */
public class productAggregateRiskComparator extends BaseComparator {

    /**
     * 条款风险系数的业务主键是条款ID
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
        productAggregateRiskComparator comparator = new productAggregateRiskComparator();
        comparator.comparator("product_aggregation_risk-0303.csv", "product_aggregate_risk-0303.csv",  2, null, null, null);
    }
}
