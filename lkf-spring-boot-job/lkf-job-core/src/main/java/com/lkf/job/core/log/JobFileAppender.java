package com.lkf.job.core.log;

import com.lkf.job.core.biz.model.LogResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/10/22 0022.
 */
public class JobFileAppender {
    private static Logger logger = LoggerFactory.getLogger(JobFileAppender.class);
    /*父子线程之间变量共享*/
    public static final InheritableThreadLocal<String> contextHolder = new InheritableThreadLocal<>();
    public static String logPath = "/data/applogs/lkf-job/jobhandler/";

    /**
     * log filename: filePath/yyyy-MM-dd/10025.log
     *
     * @param triggerDate 触发事件
     * @param logId       日志id
     * @return
     */
    public static String buildLogFileName(Date triggerDate, int logId) {
        File filePath = new File(logPath);
        /*日志路径不存在，创建路径*/
        if (!filePath.exists())
            filePath.mkdirs();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String currentFormat = simpleDateFormat.format(new Date());

        /*filePath/yyyy-MM-dd*/
        File fileDatePath = new File(filePath, currentFormat);
        if (!fileDatePath.exists())
            fileDatePath.mkdirs();

        /*filePath/yyyy-MM-dd/10025.log*/
        String logFileName = simpleDateFormat.format(triggerDate).concat("/").concat(String.valueOf(logId)).concat(".log");
        return logFileName;
    }

    public static void appendLog2File(String logFileName, String appendLog) {
        if (appendLog == null)
            appendLog = "";
        appendLog += "\r\n";

        if (logFileName == null || logFileName.trim().length() == 0)
            return;
        File logFile = new File(logPath, logFileName);

        if (!logFile.exists()) {
            try {
                logFile.createNewFile();
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
                return;
            }
        }

        // append file content
        try {
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(logFile, true);
                fos.write(appendLog.getBytes("utf-8"));
                fos.flush();
            } finally {
                if (fos != null) {
                    try {
                        fos.close();
                    } catch (IOException e) {
                        logger.error(e.getMessage(), e);
                    }
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * 读取日志文件
     *
     * @param logFileName 日志文件名
     * @param fromLineNum 日志读取开始行号
     * @return log content
     */
    public static LogResult readLog(String logFileName, int fromLineNum) {

        // valid log file
        if (logFileName == null || logFileName.trim().length() == 0) {
            return new LogResult(fromLineNum, 0, "readLog fail, logFile not found", true);
        }
        File logFile = new File(logPath, logFileName);

        if (!logFile.exists()) {
            return new LogResult(fromLineNum, 0, "readLog fail, logFile not exists", true);
        }

        // read file
        StringBuffer logContentBuffer = new StringBuffer();
        int toLineNum = 0;
        LineNumberReader reader = null;
        try {
            reader = new LineNumberReader(new InputStreamReader(new FileInputStream(logFile), "utf-8"));
            String line = null;

            while ((line = reader.readLine()) != null) {
                toLineNum = reader.getLineNumber();        // [from, to], start as 1
                if (toLineNum >= fromLineNum) {
                    logContentBuffer.append(line).append("\n");
                }
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }

        // result
        LogResult logResult = new LogResult(fromLineNum, toLineNum, logContentBuffer.toString(), false);
        return logResult;
    }

    /**
     * read log data
     *
     * @param logFile 日志文件地址
     * @return log content
     */
    public static String readLines(File logFile) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(logFile), "utf-8"));
            if (null != reader) {
                StringBuilder sb = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null) {
                    sb.append(line).append("\n");
                }
                return sb.toString();
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }
        return null;
    }
}
