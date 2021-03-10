package com.zhongan.product.cfg.blcs.channel;

import com.zhongan.product.cfg.blcs.BaseComparator;

/**
 * 渠道组合续保规则表
 * @author dailiang
 */
public class ChannelPackageRenewalRuleComparator extends BaseComparator {

    /**
     * 渠道组合续保规则表业务主键是主键ID
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
        ChannelPackageRenewalRuleComparator comparator = new ChannelPackageRenewalRuleComparator();
        comparator.comparator("channel_code_def-v1-0304.csv", "channel_code_gen-v2-0304.csv",  1, null, null, null);
    }
}
