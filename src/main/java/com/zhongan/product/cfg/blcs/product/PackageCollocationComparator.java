package com.zhongan.product.cfg.blcs.product;

import com.zhongan.product.cfg.blcs.BaseComparator;

/**
 * 产品责任互斥搭配
 * @author dailiang
 */
public class PackageCollocationComparator extends BaseComparator {

    /**
     * 产品责任互斥搭配业务主键是 package_id,product_id,is_liability_collocation,liability_id,collocation_type
     *
     * @param oldLines
     * @param newLines
     * @return
     */
    @Override
    public boolean isSameBusinessKey(String[] oldLines, String[] newLines) {
        if (Integer.parseInt(oldLines[1]) != Integer.parseInt(newLines[1]) || Integer.parseInt(oldLines[2]) != Integer.parseInt(newLines[2]) || !oldLines[3].equals(newLines[3])
                || (oldLines[4] != null && !oldLines[4].equals(newLines[4])) || !oldLines[5].equals(newLines[5]) || !oldLines[6].equals(newLines[6])) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean isOldExtraData(String[] oldLines, String[] newLines) {
        if (Integer.parseInt(oldLines[1]) < Integer.parseInt(newLines[1])) {
            return true;
        } else if (Integer.parseInt(oldLines[1]) == Integer.parseInt(newLines[1])){
            if (Integer.parseInt(oldLines[2]) < Integer.parseInt(newLines[2])) {
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
        PackageCollocationComparator comparator = new PackageCollocationComparator();
        comparator.comparator("package_prod_liab_coll-v1-0308.csv", "package_collocation-v2-0308.csv",  1, null, null, null);
    }
}
