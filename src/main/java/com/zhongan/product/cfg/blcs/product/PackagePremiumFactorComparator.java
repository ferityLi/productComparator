package com.zhongan.product.cfg.blcs.product;

import com.zhongan.product.cfg.blcs.BaseComparator;
import org.apache.commons.lang3.StringUtils;

/**
 * @author huyong
 * @since 2021-3-2
 */
public class PackagePremiumFactorComparator extends BaseComparator {

    /**
     * 费率因子的业务主键是组合id+产品id+因子序号
     *
     * @param oldLines
     * @param newLines
     * @return
     */
    @Override
    public boolean isSameBusinessKey(String[] oldLines, String[] newLines) {
        if (!StringUtils.equals(oldLines[1], newLines[1]) || !StringUtils.equals(oldLines[2], newLines[2]) || !StringUtils.equals(oldLines[5], newLines[5])) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean isOldExtraData(String[] oldLines, String[] newLines) {
        if (Long.parseLong(oldLines[1]) < Long.parseLong(newLines[1])) {
            return true;
        } else if (Long.parseLong(oldLines[1]) == Long.parseLong(newLines[1])){
            if (Long.parseLong(oldLines[2]) < Long.parseLong(newLines[2])) {
                return true;
            } else if (Long.parseLong(oldLines[2]) == Long.parseLong(newLines[2])) {
                if (Long.parseLong(oldLines[5]) < Long.parseLong(newLines[5])) {
                    return true;
                } else {
                    return false;
                }
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
        PackagePremiumFactorComparator comparator = new PackagePremiumFactorComparator();
        comparator.comparator("prem_calc_relativ-0301.csv", "package_premium_factor-0301.csv",  3, null, null, null);
/*        String str = "channel_center_00.sales_manager_department,230217,17,N,Y,1,,2017-06-01,2199-12-31,N";
        System.out.println(StringUtils.split(str, ",").length);
        System.out.println(str.split(",").length);*/
    }
}
