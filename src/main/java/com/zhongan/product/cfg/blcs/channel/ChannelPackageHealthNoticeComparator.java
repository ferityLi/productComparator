package com.zhongan.product.cfg.blcs.channel;

import com.zhongan.product.cfg.blcs.BaseComparator;

/**
 * 渠道组合健康告知
 * @author dailiang
 */
public class ChannelPackageHealthNoticeComparator extends BaseComparator {

    /**
     * 渠道组合健康告知的业务主键是主键ID
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
        ChannelPackageHealthNoticeComparator comparator = new ChannelPackageHealthNoticeComparator();
        comparator.comparator("product_question_def-0303.csv", "health_notice-0303.csv",  1, null, null, null);
    }
}
