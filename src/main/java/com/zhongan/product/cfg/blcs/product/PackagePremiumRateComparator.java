package com.zhongan.product.cfg.blcs.product;

import com.zhongan.product.cfg.blcs.BaseComparator;
import org.apache.commons.lang3.StringUtils;

/**
 * @author huyong
 * @since 2021-3-3
 */
public class PackagePremiumRateComparator extends BaseComparator {

    /**
     * 费率的业务主键是组合id+产品id
     *
     * @param oldLines
     * @param newLines
     * @return
     */
    @Override
    public boolean isSameBusinessKey(String[] oldLines, String[] newLines) {
        if (!StringUtils.equals(oldLines[0], newLines[0]) || !StringUtils.equals(oldLines[1], newLines[1])) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean isOldExtraData(String[] oldLines, String[] newLines) {
        if (Long.parseLong(oldLines[0]) < Long.parseLong(newLines[0])) {
            return true;
        } else if (Long.parseLong(oldLines[0]) == Long.parseLong(newLines[0])){
            if (Long.parseLong(oldLines[1]) < Long.parseLong(newLines[1])) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    protected void createInsertOrUpdateSql(String[] oldLines, String oprateType) {

    }

    public static void main(String[] args) throws Exception {
        PackagePremiumRateComparator comparator = new PackagePremiumRateComparator();
        comparator.comparator("prem_calc_rel_value-0302-延后1.csv", "package_premium_rate-0302.csv",  2, null, ",10,", null);
    }
}
