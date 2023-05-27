package com.zerobase.fastlms.util;

import com.zerobase.fastlms.banner.type.FileManage;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class FileUtil {
    private Map<FileManage, String> fileInfo = new HashMap<>();

    /**
     * @return "b0882ec9bca5468ea5b6963c538858ea.jfif"
     */
    public String getSaveFileName() {
        return fileInfo.get(FileManage.SAVE_NAME);
    }

    /**
     * @return "image01.jfif"
     */
    public String getRealFileName() {
        return fileInfo.get(FileManage.REAL_NAME);
    }

    /**
     * @return "C:\Users\fastlms\files/2023/05/27/"
     */
    public String getSaveFilePath() {
        return fileInfo.get(FileManage.ABSOLUTE_PATH);
    }

    /**
     * @return "/files/2023/05/27/"
     */
    public String getUrlFilePath() {
        return fileInfo.get(FileManage.URL_PATH);
    }



    public FileUtil save(MultipartFile file) {
        // "/Users/kyutaepark/Documents/sources/zerobase/fastlms/files";
        String baseLocalPath = "C:\\Users\\xh\\Documents\\zb-learning-management-system\\fastlms\\src\\main\\resources\\static\\files";
        String baseUrlPath = "/files";

        if (file != null) {
            String originalFilename = file.getOriginalFilename();

            String[] arrFilename = getNewSaveFile(baseLocalPath, baseUrlPath, originalFilename);

            fileInfo.put(FileManage.ABSOLUTE_PATH_AND_SAVE_NAME, arrFilename[0]);
            fileInfo.put(FileManage.URL_PATH_AND_SAVE_NAME, arrFilename[1]);

            try {
                File newFile = new File(arrFilename[0]);
                FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(newFile));
            } catch (IOException e) {
                log.info("############################ - 1");
                log.info(e.getMessage());
            }
        }
        return this;
    }

    public String[] getNewSaveFile(String baseLocalPath, String baseUrlPath, String originalFilename) {

        LocalDate now = LocalDate.now();

        String[] dirs = {
                String.format("%s/%d/", baseLocalPath,now.getYear()),
                String.format("%s/%d/%02d/", baseLocalPath, now.getYear(),now.getMonthValue()),
                String.format("%s/%d/%02d/%02d/", baseLocalPath, now.getYear(), now.getMonthValue(), now.getDayOfMonth())};

        String urlDir = String.format("%s/%d/%02d/%02d/", baseUrlPath, now.getYear(), now.getMonthValue(), now.getDayOfMonth());

        fileInfo.put(FileManage.ABSOLUTE_PATH, dirs[2]);
        fileInfo.put(FileManage.URL_PATH, urlDir);

        for(String dir : dirs) {
            File file = new File(dir);
            if (!file.isDirectory()) {
                file.mkdir();
            }
        }

        String fileExtension = "";
        if (originalFilename != null) {
            int dotPos = originalFilename.lastIndexOf(".");
            if (dotPos > -1) {
                fileExtension = originalFilename.substring(dotPos + 1);
            }

            fileInfo.put(FileManage.REAL_NAME, originalFilename);
        }

        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        String newFilename = String.format("%s%s", dirs[2], uuid);
        String newUrlFilename = String.format("%s%s", urlDir, uuid);
        if (fileExtension.length() > 0) {
            newFilename += "." + fileExtension;
            newUrlFilename += "." + fileExtension;
        }

        fileInfo.put(FileManage.SAVE_NAME, uuid + "." + fileExtension);

        return new String[]{newFilename, newUrlFilename};
    }
}
