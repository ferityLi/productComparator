package com.zhongan.product.cfg.blcs.product;

import com.zhongan.product.cfg.blcs.BaseComparator;
import org.apache.commons.lang3.StringUtils;

/**
 * @author huyong
 * @since 2021-3-3
 */
public class SpecialAgreementComparator extends BaseComparator {

    /**
     * 特别约定的业务主键是主键ID
     *
     * @param oldLines
     * @param newLines
     * @return
     */
    @Override
    public boolean isSameBusinessKey(String[] oldLines, String[] newLines) {
        if (!StringUtils.equals(oldLines[1], newLines[1])) {
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
        SpecialAgreementComparator comparator = new SpecialAgreementComparator();
        comparator.comparator("special_agreement-v1-0302.csv", "special_agreement-v2-0302.csv",  2, null, null, null);
    }
}
