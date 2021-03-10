package com.zhongan.product.cfg.blcs.channel;

import com.zhongan.product.cfg.blcs.BaseComparator;

/**
 * 上下架操作记录表
 * @author dailiang
 */
public class ChannelPackageOperationComparator extends BaseComparator {

    /**
     * 上下架操作记录表业务主键是主键ID
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
        ChannelPackageOperationComparator comparator = new ChannelPackageOperationComparator();
        comparator.comparator("product_effective_operation-v1-0304.csv", "channel_package_operation-v2-0304.csv",  1, null, null, null);
    }
}
