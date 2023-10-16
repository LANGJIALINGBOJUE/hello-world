package com.langjialing.helloworld.config.util;

import com.jcraft.jsch.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.*;


public class SftpUtil {

    private long count;
    /**
     * 已经连接次数
     */
    private long count1 = 0;

    private long sleepTime;

    private static final Logger logger = LoggerFactory.getLogger(SftpUtil.class);

    /**
     * 连接sftp服务器
     *
     * @return
     */
    public ChannelSftp connect(SftpConfig sftpConfig) {
        ChannelSftp sftp = null;
        try {
            JSch jsch = new JSch();
            jsch.getSession(sftpConfig.getUsername(), sftpConfig.getHostname(), sftpConfig.getPort());
            Session sshSession = jsch.getSession(sftpConfig.getUsername(), sftpConfig.getHostname(), sftpConfig.getPort());
            logger.info("Session created ... UserName=" + sftpConfig.getUsername() + ";host=" + sftpConfig.getHostname() + ";port=" + sftpConfig.getPort());
            sshSession.setPassword(sftpConfig.getPassword());
            Properties sshConfig = new Properties();
            sshConfig.put("StrictHostKeyChecking", "no");
            sshSession.setConfig(sshConfig);
            sshSession.connect();
            logger.info("Session connected ...");
            logger.info("Opening Channel ...");
            Channel channel = sshSession.openChannel("sftp");
            channel.connect();
            sftp = (ChannelSftp) channel;
            logger.info("登录成功");
        } catch (Exception e) {
            try {
                count1 += 1;
                if (count == count1) {
                    throw new RuntimeException(e);
                }
                Thread.sleep(sleepTime);
                logger.info("重新连接....");
                connect(sftpConfig);
            } catch (InterruptedException e1) {
                throw new RuntimeException(e1);
            }
        }
        return sftp;
    }

    /**
     * 上传文件。
     *
     * @param directory  上传的目录
     * @param uploadFile 要上传的文件
     * @param sftpConfig
     */
    public void upload(String directory, String uploadFile, SftpConfig sftpConfig) {
        ChannelSftp sftp = connect(sftpConfig);
        try {
            sftp.cd(directory);
        } catch (SftpException e) {
            try {
                sftp.mkdir(directory);
                sftp.cd(directory);
            } catch (SftpException e1) {
                throw new RuntimeException("ftp创建文件路径失败" + directory);
            }
        }
        File file = new File(uploadFile);
        InputStream inputStream=null;
        try {
            inputStream = new FileInputStream(file);
            sftp.put(inputStream, file.getName());
        } catch (Exception e) {
            throw new RuntimeException("sftp异常" + e);
        } finally {
            disConnect(sftp);
            closeStream(inputStream,null);
        }
    }

    /**
     * 同时上传多个文件。
     * @param directory 上传的目录
     * @param uploadFiles 要上传的文件
     * @param sftpConfig
     */
    public void uploadMultipleFiles(String directory, List<String> uploadFiles, SftpConfig sftpConfig) {
        ChannelSftp sftp = connect(sftpConfig);
        try {
            sftp.cd(directory);
        } catch (SftpException e) {
            try {
                sftp.mkdir(directory);
                sftp.cd(directory);
            } catch (SftpException e1) {
                throw new RuntimeException("ftp创建文件路径失败" + directory);
            }
        }

        for (String uploadFile : uploadFiles) {
            File file = new File(uploadFile);
            if (file.exists()) {
                try (InputStream inputStream = new FileInputStream(file)) {
                    sftp.put(inputStream, file.getName());
                } catch (Exception e) {
                    throw new RuntimeException("sftp异常" + e);
                }
            } else {
                throw new RuntimeException("文件不存在: " + uploadFile);
            }
        }

        disConnect(sftp);
    }

    /**
     * 上传文件夹。
     * @param directory 上传的目录
     * @param localDirectory 要上传的文件
     * @param sftpConfig
     */
    public void uploadDirectory(String directory, String localDirectory, SftpConfig sftpConfig) {
        ChannelSftp sftp = connect(sftpConfig);
        try {
            sftp.cd(directory);
        } catch (SftpException e) {
            try {
                sftp.mkdir(directory);
                sftp.cd(directory);
            } catch (SftpException e1) {
                throw new RuntimeException("ftp创建文件路径失败" + directory);
            }
        }

        File localDir = new File(localDirectory);
        if (localDir.isDirectory()) {
            uploadDirectoryRecursively(sftp, localDir);
        }

        disConnect(sftp);
    }

    /**
     * 上传文件。如果文件夹内含多级下级文件夹，则递归创建。
     * @param sftp
     * @param localDir 本地文件夹。
     */
    private void uploadDirectoryRecursively(ChannelSftp sftp, File localDir) {
        for (File file : Objects.requireNonNull(localDir.listFiles())) {
            if (file.isDirectory()) {
                String newRemotePath = file.getName() + "/";
                try {
                    sftp.mkdir(newRemotePath);
                    sftp.cd(newRemotePath);
                    uploadDirectoryRecursively(sftp, file);
                    sftp.cd("..");
                } catch (SftpException e) {
                    logger.info("ftp创建文件路径失败:" + e.getMessage());
                    throw new RuntimeException("ftp创建文件路径失败:" + newRemotePath);
                }
            } else if (file.isFile()) {
                try (InputStream inputStream = new FileInputStream(file)) {
                    sftp.put(inputStream, file.getName());
                } catch (Exception e) {
                    throw new RuntimeException("sftp异常" + e);
                }
            }
        }
    }


    /**
     * 下载文件。
     *
     * @param directory    下载目录
     * @param downloadFile 下载的文件
     * @param saveFile     存在本地的路径
     * @param sftpConfig
     */
    public void download(String directory, String downloadFile, String saveFile, SftpConfig sftpConfig) {
        OutputStream output = null;
        try {
            File localDirFile = new File(saveFile);
            // 判断本地目录是否存在，不存在需要新建各级目录
            if (!localDirFile.exists()) {
                localDirFile.mkdirs();
            }
            if (logger.isInfoEnabled()) {
                logger.info("开始获取远程文件:[{}]---->[{}]", new Object[]{directory, saveFile});
            }
            ChannelSftp sftp = connect(sftpConfig);
            sftp.cd(directory);
            if (logger.isInfoEnabled()) {
                logger.info("打开远程文件:[{}]", new Object[]{directory});
            }
            output = new FileOutputStream(new File(saveFile.concat(File.separator).concat(downloadFile)));
            sftp.get(downloadFile, output);
            if (logger.isInfoEnabled()) {
                logger.info("文件下载成功");
            }
            disConnect(sftp);
        } catch (Exception e) {
            if (logger.isInfoEnabled()) {
                logger.info("文件下载出现异常，[{}]", e);
            }
            throw new RuntimeException("文件下载出现异常，[{}]", e);
        } finally {
            closeStream(null,output);
        }
    }

    /**
     * 下载远程文件夹下的所有文件。
     * @param remoteFilePath 要下载的文件路径。
     * @param localDirPath 本地保存位置。
     * @throws Exception
     */
    public void downloadDirFile(String remoteFilePath, String localDirPath, SftpConfig sftpConfig) throws Exception {
        File localDirFile = new File(localDirPath);
        // 判断本地目录是否存在，不存在需要新建各级目录
        if (!localDirFile.exists()) {
            localDirFile.mkdirs();
        }
        if (logger.isInfoEnabled()) {
            logger.info("sftp文件服务器文件夹[{}],下载到本地目录[{}]", new Object[]{remoteFilePath, localDirFile});
        }
        ChannelSftp channelSftp = connect(sftpConfig);
        Vector<ChannelSftp.LsEntry> lsEntries = channelSftp.ls(remoteFilePath);
        if (logger.isInfoEnabled()) {
            logger.info("远程目录下的文件为[{}]", lsEntries);
        }
        for (ChannelSftp.LsEntry entry : lsEntries) {
            String fileName = entry.getFilename();
            if (checkFileName(fileName)) {
                continue;
            }
            String remoteFileName = getRemoteFilePath(remoteFilePath, fileName);
            channelSftp.get(remoteFileName, localDirPath);
        }
        disConnect(channelSftp);
    }

    /**
     * 下载远程文件夹下的所有文件（如果文件夹包含子文件夹，则递归下载）。
     * @param remoteDirPath 要下载的目录路径。
     * @param localDirPath  本地保存位置。
     * @param sftpConfig
     * @throws Exception
     */
    public void downloadDirectory(String remoteDirPath, String localDirPath, SftpConfig sftpConfig) throws Exception {
        File localDirFile = new File(localDirPath);
        if (!localDirFile.exists()) {
            localDirFile.mkdirs();
        }

        if (logger.isInfoEnabled()) {
            logger.info("SFTP文件服务器文件夹[{}],下载到本地目录[{}]", remoteDirPath, localDirFile);
        }

        ChannelSftp channelSftp = connect(sftpConfig);
        downloadRemoteDirectory(channelSftp, remoteDirPath, localDirPath);
        disConnect(channelSftp);
    }

    /**
     * 下载文件夹（如果文件夹含多级文件夹，则递归下载）。
     * @param channelSftp
     * @param remoteDirPath 要下载的目录路径。
     * @param localDirPath 本地保存位置。
     * @throws SftpException
     */
    private void downloadRemoteDirectory(ChannelSftp channelSftp, String remoteDirPath, String localDirPath) throws SftpException {
        Vector<ChannelSftp.LsEntry> lsEntries = channelSftp.ls(remoteDirPath);

        for (ChannelSftp.LsEntry entry : lsEntries) {
            String fileName = entry.getFilename();
            if (checkFileName(fileName)) {
                continue;
            }
            String remoteFilePath = remoteDirPath + "/" + fileName;
            String localFilePath = localDirPath + File.separator + fileName;

            if (entry.getAttrs().isDir()) {
                // 如果是目录，递归下载子目录
                File localDirFile = new File(localFilePath);
                if (!localDirFile.exists()) {
                    localDirFile.mkdirs();
                }
                downloadRemoteDirectory(channelSftp, remoteFilePath, localFilePath);
            } else {
                // 如果是文件，下载文件
                channelSftp.get(remoteFilePath, localFilePath);
            }
        }
    }


    /**
     * 关闭流
     * @param outputStream
     */
    private void closeStream(InputStream inputStream,OutputStream outputStream) {
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(inputStream != null){
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean checkFileName(String fileName) {
        if (".".equals(fileName) || "..".equals(fileName)) {
            return true;
        }
        return false;
    }

    private String getRemoteFilePath(String remoteFilePath, String fileName) {
        if (remoteFilePath.endsWith("/")) {
            return remoteFilePath.concat(fileName);
        } else {
            return remoteFilePath.concat("/").concat(fileName);
        }
    }

    /**
     * 删除文件
     *
     * @param directory  要删除文件所在目录
     * @param deleteFile 要删除的文件
     * @param sftp
     */
    public void delete(String directory, String deleteFile, ChannelSftp sftp) {
        try {
            sftp.cd(directory);
            sftp.rm(deleteFile);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 列出目录下的文件
     *
     * @param directory  要列出的目录
     * @param sftpConfig
     * @return
     * @throws SftpException
     */
    public List<String> listFiles(String directory, SftpConfig sftpConfig) throws SftpException, UnsupportedEncodingException {
        ChannelSftp sftp = connect(sftpConfig);
        List fileNameList = new ArrayList();
        try {
            sftp.cd(directory);
        } catch (SftpException e) {
            return fileNameList;
        }
        sftp.setFilenameEncoding("UTF-8");
        Vector<?> vector = sftp.ls(directory);
        for (int i = 0; i < vector.size(); i++) {
            if (vector.get(i) instanceof ChannelSftp.LsEntry) {
                ChannelSftp.LsEntry lsEntry = (ChannelSftp.LsEntry) vector.get(i);
                String fileName = lsEntry.getFilename();
                if (".".equals(fileName) || "..".equals(fileName)) {
                    continue;
                }
                fileNameList.add(fileName);
            }
        }
        disConnect(sftp);
        return fileNameList;
    }

    /**
     * 断掉连接
     */
    public void disConnect(ChannelSftp sftp) {
        try {
            sftp.disconnect();
            sftp.getSession().disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public SftpUtil(long count, long sleepTime) {
        this.count = count;
        this.sleepTime = sleepTime;
    }

    public SftpUtil() {

    }
}
