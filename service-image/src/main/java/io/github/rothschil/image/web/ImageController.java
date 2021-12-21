package io.github.rothschil.image.web;

import io.github.rothschil.image.task.RunFileTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href="https://github.com/rothschil">Sam</a>
 * @date 2018/4/18 - 10:29
 * @since 1.0.0
 */
@RestController
public class ImageController {

    private static final Logger LOG = LoggerFactory.getLogger(ImageController.class);

    private RunFileTask runFileTask;

    @Autowired
    public void setRunFileTask(RunFileTask runFileTask) {
        this.runFileTask = runFileTask;
    }

    @GetMapping("/ex")
    public void excute() {
        long start = System.currentTimeMillis();
        runFileTask.run("E:\\5K Mac");
        long end = System.currentTimeMillis();
        LOG.info("耗时 cost ={} 秒", (end - start) / 1000);
    }
}
