/**
 * @projectName: aladdin
 * @package: com.trouvaille.aladdin.controller
 * @className: CommonController
 * @author: trouvaille_william
 * @description: 文件控制类
 * @date: 2022/8/3 16:36
 * @version: 1.0
 */
package com.trouvaille.aladdin.controller;


import com.trouvaille.aladdin.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/common")
@Slf4j
public class CommonController {

    @Value("${aladdin.path}")
    private String basePath;


    /**
     * @param file:
     * @return R<String>
     * @author William_Trouvaille
     * @description 文件上传类
     * @date 2022/08/03 16:40
     */
    @PostMapping("/upload")
    public R<String> upload(final MultipartFile file) {
        boolean mkdirs = true;
        //file是一个临时文件，需要转存到指定位置，否则本次请求完成后临时文件会删除
        CommonController.log.info(file.toString());

        //原始文件名
        final String originalFilename = file.getOriginalFilename();//abc.jpg
        assert originalFilename != null;
        final String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));

        //使用UUID重新生成文件名，防止文件名称重复造成文件覆盖
        final String fileName = UUID.randomUUID() + suffix;

        //创建一个目录对象
        final File dir = new File(basePath);
        //判断当前目录是否存在
        //目录不存在，需要创建
        if (!dir.exists()) {
            mkdirs = dir.mkdirs();
        }

        try {
            //将临时文件转存到指定位置
            file.transferTo(new File(basePath + fileName));
        } catch (final IOException e) {
            e.printStackTrace();
        }
        return R.flag(mkdirs, fileName, "文件上传失败!");
    }


    /**
     * @param name:
     * @param response:
     * @return void
     * @author William_Trouvaille
     * @description 文件下载类
     * @date 2022/08/03 16:40
     */
    @GetMapping("/download")
    public void download(final String name, final HttpServletResponse response) {

        try {
            //输入流，通过输入流读取文件内容
            final FileInputStream fileInputStream = new FileInputStream(new File(basePath + name));

            //输出流，通过输出流将文件写回浏览器
            final ServletOutputStream outputStream = response.getOutputStream();

            response.setContentType("image/jpeg");

            int len;
            byte[] bytes = new byte[1024];
            while ((len = fileInputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, len);
                outputStream.flush();
            }

            //关闭资源
            outputStream.close();
            fileInputStream.close();
        } catch (final Exception e) {
            e.printStackTrace();
        }
        CommonController.log.info("文件下载成功!");

    }
}
