package top.keng.anime.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;
import top.keng.anime.common.Constants;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class UploadUtil {
    private static final Logger logger = LoggerFactory.getLogger(UploadUtil.class);
    public static String avatarUpload(MultipartFile file, String loginName, String avatarName) {
        logger.info("visitor is changing avatar:{}", loginName);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        Random r = new Random();
        StringBuilder tempName = new StringBuilder();
        tempName.append(sdf.format(new Date())).append(r.nextInt(100)).append(loginName).append("_").append(avatarName);
        String newFileName = tempName.toString();
        avatarName = Constants.AVATAR_UPLOAD_DIC + newFileName;

        File fileDirectory1 = new File(Constants.LOCAL_DIC_AVATAR_1);
        String localPath1 = Constants.LOCAL_DIC_AVATAR_1 + newFileName;
        File destFile1 = new File(localPath1);
        File fileDirectory2 = new File(Constants.LOCAL_DIC_AVATAR_2);
        String localPath2 = Constants.LOCAL_DIC_AVATAR_2 + newFileName;
        File destFile2 = new File(localPath2);
        try {
            if (!fileDirectory2.exists() && !fileDirectory1.exists()) {
                if (!fileDirectory2.mkdir() && !fileDirectory1.mkdir()) {
                    throw new IOException("文件夹创建失败");
                }
            }
            file.transferTo(destFile1);
            file.transferTo(destFile2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return avatarName;
    }

    public static void avatarDelete(String url) throws IOException {
        int pos = url.lastIndexOf("/") + 1;
        Path p1 = Paths.get(Constants.LOCAL_DIC_AVATAR_1 + url.substring(pos));
        Path p2 = Paths.get(Constants.LOCAL_DIC_AVATAR_2 + url.substring(pos));
        Files.delete(p1);
        Files.delete(p2);
    }

    public static void pictureDelete(String url) throws IOException {
        int pos = url.lastIndexOf("/") + 1;
        Path p1 = Paths.get(Constants.LOCAL_DIC_PICTURE_1 + url.substring(pos));
        Path p2 = Paths.get(Constants.LOCAL_DIC_PICTURE_2 + url.substring(pos));
        Files.delete(p1);
        Files.delete(p2);
    }

    public static String pictureUpload(MultipartFile file, String animeName, String picName) {
        logger.info("visitor is changing avatar:{}", animeName);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        Random r = new Random();
        StringBuilder tempName = new StringBuilder();
        tempName.append(sdf.format(new Date())).append(r.nextInt(100)).append(animeName).append("_").append(picName);
        String newFileName = tempName.toString();
        picName = Constants.PICTURE_UPLOAD_DIC + newFileName;

        File fileDirectory1 = new File(Constants.LOCAL_DIC_PICTURE_1);
        String localPath1 = Constants.LOCAL_DIC_PICTURE_1 + newFileName;
        File destFile1 = new File(localPath1);
        File fileDirectory2 = new File(Constants.LOCAL_DIC_PICTURE_2);
        String localPath2 = Constants.LOCAL_DIC_PICTURE_2 + newFileName;
        File destFile2 = new File(localPath2);
        try {
            if (!fileDirectory2.exists() && !fileDirectory1.exists()) {
                if (!fileDirectory2.mkdir() && !fileDirectory1.mkdir()) {
                    throw new IOException("文件夹创建失败");
                }
            }
            file.transferTo(destFile1);
            file.transferTo(destFile2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return picName;
    }

    public static MultipartFile base64ToMultipart(String base64) {
        try {
            String[] baseStrs = base64.split(",");
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] b;
            b = decoder.decodeBuffer(baseStrs[1]);
            for(int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            return new Base64DecodedMultipartFile(b, baseStrs[0]);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
