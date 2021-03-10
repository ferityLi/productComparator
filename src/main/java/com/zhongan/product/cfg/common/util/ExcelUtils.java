package com.zhongan.product.cfg.common.util;

import com.zhongan.product.cfg.blcs.BaseComparator;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

/**
 * @author ：dailiang
 * @since ：2021/3/9 21:06
 */

public class ExcelUtils {

    private static String oldPath = "productComparator/src/main/resources/blcs/oldcsv/";
    public static final  String filePath = "productComparator/src/main/resources/blcs/newCsv/";
    private static Logger logger = Logger.getLogger(ExcelUtils.class);

    public static void main(String[] args) throws IOException {
        createfile("package_product_liability-v2-0308.csv");
    }

    private static void createfile(String oldFilename) throws IOException {
        BufferedReader oldReader = null;
        String oldLine = null;
        BufferedReader newReader = null;
        try {
            // 先生成文件
            String fileName = oldFilename.replace(".csv", "") + "_new.csv";
            File csvFile = new File(filePath + File.separator + fileName);
            File parent = csvFile.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }
            if (csvFile.exists()) {
                csvFile.delete();
            }
            csvFile.createNewFile();
            // GB2312使正确读取分隔符","
            BufferedWriter csvWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(csvFile), "GB2312"), 1024);
            csvWriter.flush();
            csvWriter.close();

            //读取文件内容
            oldReader = new BufferedReader(new FileReader(oldPath + oldFilename));
            oldLine = oldReader.readLine();
            int i = 0;
            while (oldLine != null) {
                if (StringUtils.isBlank(oldLine)) {
                    break;
                }
                String newLine = oldLine.replace("\"", "");
                System.out.println(i++);
                FileWriter fw = new FileWriter(filePath + File.separator + fileName, true);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.newLine();
                bw.write(newLine);
                bw.close();
                fw.close();
            }

            logger.info("table：【" + oldFilename + "】去除双引号结束！");
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

}
