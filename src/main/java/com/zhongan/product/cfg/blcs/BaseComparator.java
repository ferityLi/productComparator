package com.zhongan.product.cfg.blcs;

import com.zhongan.product.cfg.common.util.DateUtils;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

/**
 * @author huyong
 * @date 2020-1-2
 */
public abstract class BaseComparator {
    private String oldPath = "productComparator/src/main/resources/blcs/v1/";
    private String newPath = "productComparator/src/main/resources/blcs/v2/";
    public static final  String filePath = "productComparator/src/main/resources/blcs/modifySql";
    public static final String INSERT = "insert";
    public static final String UPDATE  = "update";
    public static final String IGNORE = "ignore";

    private String[] oldTitles;
    private String[] newTitles;

    private Logger logger = Logger.getLogger(BaseComparator.class);

    /**
     * 比对两个文件中数据是否匹配
     * 两个文件中的数据是根据业务主键排序了，相同的数据应该对应在文件中的行数一致
     *
     * @param oldFilename  1.0系统的数据文件
     * @param newFilenames 2.0系统的数据文件
     * @param startIndex 开始校验的列数
     * @param doubleIndex 处理double类型数据
     * @param skipIndex 跳过不处理的数据
     * @param dateIndex 出里日期数据
     * @throws IOException
     */
    public void comparator(String oldFilename, String newFilenames, int startIndex, String doubleIndex, String skipIndex, String dateIndex) throws IOException {
        BufferedReader oldReader = null;
        String oldLine = null;
        BufferedReader newReader = null;
        String newLine = null;
        try {
            //读取文件内容
            oldReader = new BufferedReader(new FileReader(oldPath + oldFilename));
            newReader = new BufferedReader(new FileReader(newPath + newFilenames));
            // 过滤首行标题
            oldTitles = oldReader.readLine().split(",");
            newTitles = newReader.readLine().split(",");
            oldLine = oldReader.readLine();
            newLine = newReader.readLine();
            logger.info("table：【" + oldFilename + "】开始比对！");
            while (oldLine != null || newLine != null) {
                if (StringUtils.isBlank(oldLine) || StringUtils.isBlank(newLine)) {
                    logger.error("table：【" + oldFilename + "】存在多余数据！【1.0数据：" + oldLine + "】【2.0数据：" + newLine + "】");
                    if (StringUtils.isNotBlank(oldLine)) {
                        String[] oldLines = oldLine.split(",");
                        createInsertOrUpdateSql(oldLines,INSERT);
                        oldLine = oldReader.readLine();
                    }
                    if (StringUtils.isNotBlank(newLine)) {
                        String[] newLines = newLine.split(",");
                        createInsertOrUpdateSql(newLines,IGNORE);
                        newLine = newReader.readLine();
                    }
                    continue;
                }

                String[] oldLines = oldLine.split(",");
                String[] newLines = newLine.split(",");
                if (oldLines.length <=1 && newLines.length <=1) {
                    oldLine = oldReader.readLine();
                    newLine = newReader.readLine();
                    continue;
                }
                while (!this.isSameBusinessKey(oldLines, newLines)) {
                    if (this.isOldExtraData(oldLines, newLines)) {
                        logger.error("table：【" + oldFilename + "】【1.0有一条数据未同步到2.0系统，对应的数据：" + oldLine + "】【与其比对的2.0数据是：" + newLine + "】");
                        createInsertOrUpdateSql(oldLines,INSERT);
                        oldLine = oldReader.readLine();
                        if (StringUtils.isBlank(oldLine)) {
                            break;
                        }
                        oldLines = oldLine.split(",");
                    } else {
                        logger.warn("table：【" + oldFilename + "】【2.0有一条数据是从2.0系统创建，不是从1.0同步过来的，对应的2.0数据：" + newLine + "】【与其比对的1.0数据是：" + oldLine + "】");
                        createInsertOrUpdateSql(oldLines,IGNORE);
                        newLine = newReader.readLine();
                        if (StringUtils.isBlank(newLine)) {
                            break;
                        }
                        newLines = newLine.split(",");
                    }
                    continue;
                }
                if (StringUtils.isBlank(oldLine) || StringUtils.isBlank(newLine)) {
                    logger.error("table：【" + oldFilename + "】存在多余数据！【1.0数据：" + oldLine + "】【2.0数据：" + newLine + "】");
                    if (StringUtils.isNotBlank(oldLine)) {
                        createInsertOrUpdateSql(oldLines,INSERT);
                        oldLine = oldReader.readLine();
                    }
                    if (StringUtils.isNotBlank(newLine)) {
                        createInsertOrUpdateSql(newLines,IGNORE);
                        newLine = newReader.readLine();
                    }
                    continue;
                }
                if (!this.isSameBusinessData(oldLines, newLines, startIndex, doubleIndex, skipIndex, dateIndex)) {
                    createInsertOrUpdateSql(oldLines,UPDATE);
                    logger.error("table：【" + oldFilename + "】【1.0同步到2.0系统的数据有差异，对应的1.0数据为：" + oldLine + "】【与其比对的2.0数据是：" + newLine + "】");
                }
                oldLine = oldReader.readLine();
                newLine = newReader.readLine();
            }
            logger.info("table：【" + oldFilename + "】比对结束！");
        } catch (Exception e) {
            //在命令行打印异常信息在程序中出错的位置及原因。
            e.printStackTrace();
        } finally {
            if (oldReader != null) {
                oldReader.close();
            }
            if (newReader != null) {
                newReader.close();
            }
        }
    }

    /**
     * 判断两行数据的业务主键是否一致
     *
     * @param oldLines
     * @param newLines
     * @return
     */
    public abstract boolean isSameBusinessKey(String[] oldLines, String[] newLines);

    /**
     * 判断是否1.0有多余的数据：业务主键谁小即有多余的数据
     * 判断依据：如果1.0的业务主键的数值要小于2.0的，则返回true；否则返回false；
     *
     * @param oldLines
     * @param newLines
     * @return
     */
    public abstract boolean isOldExtraData(String[] oldLines, String[] newLines);

    protected abstract void createInsertOrUpdateSql(String[] oldLines,String oprateType);

    /**
     * 判断两行数据是否一致
     *
     * @param oldLines
     * @param newLines
     * @param newLines
     * @param doubleIndex double类型数据的列索引，前后要带字符","；格式：",5,8,"
     * @param skipIndex 跳过的索引，不比对，格式：",5,8,"
     * @return
     */
    public boolean isSameBusinessData(String[] oldLines, String[] newLines, int startIndex, String doubleIndex, String skipIndex, String dateIndex) {
        if (oldLines.length != newLines.length) {
            return false;
        }
        for (int i = startIndex; i < oldLines.length; i++) {
            if ((StringUtils.isBlank(oldLines[i].trim()) || StringUtils.equals(oldLines[i], "NULL") || StringUtils.equals("\"\"", oldLines[i]))
                    && (StringUtils.isBlank(newLines[i].trim()) || StringUtils.equals(newLines[i], "NULL") || StringUtils.equals("\"\"", newLines[i]))) {
                continue;
            }
            if (StringUtils.isNotBlank(skipIndex) && skipIndex.contains("," + i + ",")) {
                continue;
            }
            if (StringUtils.isNotBlank(doubleIndex) || StringUtils.isNotBlank(dateIndex)) {
                if (StringUtils.isNotBlank(doubleIndex) && doubleIndex.contains("," + i + ",")) {
                    if (Double.parseDouble(oldLines[i]) != Double.parseDouble(newLines[i])) {
                        logger.warn("**********差异数据索引：【" + i + "】1.0对应的字段名：【" + oldTitles[i] + "】数据：【" + oldLines[i] + "】2.0对应的字段名：" + newTitles[i] + "】数据：【" + newLines[i] + "】");
                        return false;
                    }
                }
                if (StringUtils.isNotBlank(dateIndex) && dateIndex.contains("," + i + ",")) {
                    if ("NULL".equals(oldLines[i]) && "NULL".equals(newLines[i])) {
                        if (DateUtils.strFormatDate(oldLines[i], DateUtils.DATE_PATTERN).compareTo(DateUtils.strFormatDate(newLines[i], DateUtils.DATE_PATTERN)) != 0) {
                            logger.warn("**********差异数据索引：【" + i + "】1.0对应的字段名：【" + oldTitles[i] + "】数据：【" + oldLines[i] + "】2.0对应的字段名：" + newTitles[i] + "】数据：【" + newLines[i] + "】");
                            return false;
                        }
                    } else if (!StringUtils.equals(oldLines[i], newLines[i])) {
                        logger.warn("**********差异数据索引：【" + i + "】1.0对应的字段名：【" + oldTitles[i] + "】数据：【" + oldLines[i] + "】2.0对应的字段名：" + newTitles[i] + "】数据：【" + newLines[i] + "】");
                        return false;
                    }
                }
            } else {
                if (!StringUtils.equals(oldLines[i], newLines[i])) {
                    logger.warn("**********差异数据索引：【" + i + "】1.0对应的字段名：【" + oldTitles[i] + "】数据：【" + oldLines[i] + "】2.0对应的字段名：" + newTitles[i] + "】数据：【" + newLines[i] + "】");
                    return false;
                }
            }
        }
        return true;
    }

    protected static void write(String tableName, String format, String operateType) {
        FileWriter fw = null;
        try {
            if ("insert".equalsIgnoreCase(operateType)) {
                fw = new FileWriter(BaseComparator.filePath + File.separator + tableName + File.separator + operateType + File.separator + "tst_dml_" + tableName + ".sql", true);
            } else if ("update".equalsIgnoreCase(operateType)) {
                fw = new FileWriter(BaseComparator.filePath + File.separator + tableName + File.separator + operateType + File.separator + "tst_dml_" + tableName + ".sql", true);
            }else if ("ignore".equalsIgnoreCase(operateType)){
                // 2.0多的数据直接 存储
                fw = new FileWriter(BaseComparator.filePath + File.separator + tableName + File.separator + operateType + File.separator + "ignore.txt", true);
            }
            BufferedWriter bw = new BufferedWriter(fw);
            bw.newLine();
            bw.write(format);
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void createFile(String lossCause) throws IOException {
        //创建新增脚本
        File insert=new File(filePath+ File.separator +lossCause+File.separator+"insert"+File.separator+"tst_dml_"+lossCause+".sql");
        File fileParent = insert.getParentFile();
        if(!fileParent.exists()){
            fileParent.mkdirs();
        }
        if (!insert.exists()){
            insert.createNewFile();
        }
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(insert);
            fileWriter.write("-- 新产品缺失老产品的配置数据脚本");
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 创建更新脚本
        File update=new File(filePath+ File.separator +lossCause+File.separator+"update"+File.separator+"tst_dml_"+lossCause+".sql");
        File parentFile = update.getParentFile();
        if(!parentFile.exists()){
            parentFile.mkdirs();
        }
        if(!update.exists()){
            update.createNewFile();
        }
        try {
            fileWriter = new FileWriter(update);
            fileWriter.write("-- 新产品需按照老产品值进行更新的数据脚本");
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 2.0多的数据存储文件
        File ignore=new File(filePath+ File.separator +lossCause+File.separator+"ignore"+File.separator+"ignore.txt");
        File ignoreParentFile = ignore.getParentFile();
        if(!ignoreParentFile.exists()){
            ignoreParentFile.mkdirs();
        }
        if(!ignore.exists()){
            ignore.createNewFile();
        }
        try {
            fileWriter = new FileWriter(ignore);
            fileWriter.write("-- 新产品多余老产品的数据");
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
