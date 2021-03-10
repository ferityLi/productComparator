package com.zhongan.product.cfg.blcs.product;

import com.zhongan.product.cfg.blcs.BaseComparator;
import org.apache.commons.lang3.StringUtils;

/**
 * @author huyong
 * @since 2021-3-3
 */
public class SpecialAgreementCompileComparator extends BaseComparator {

    /**
     * 特别约定-责任数据的业务主键是主键ID
     *
     * @param oldLines
     * @param newLines
     * @return
     */
    @Override
    public boolean isSameBusinessKey(String[] oldLines, String[] newLines) {
        if (!StringUtils.equals(oldLines[0], newLines[0])) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean isOldExtraData(String[] oldLines, String[] newLines) {
        if (Long.parseLong(oldLines[0]) < Long.parseLong(newLines[0])) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void createInsertOrUpdateSql(String[] oldLines, String oprateType) {

    }

    public static void main(String[] args) throws Exception {
        SpecialAgreementCompileComparator comparator = new SpecialAgreementCompileComparator();
        comparator.comparator("special_agreement-v1-L-0302.csv", "special_agreement_compatible-0302.csv",  1, null, null, null);
    }
}
