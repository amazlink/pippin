package io.github.rothschil;


import io.github.rothschil.image.task.RunFileTask;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


@DisplayName("图像处理Web")
public class ImageInfoCase extends BaseTest {


    private RunFileTask runFileTask;

    @Autowired
    public void setRunFileTask(RunFileTask runFileTask) {
        this.runFileTask = runFileTask;
    }

    @DisplayName("观察者模式-异步")
    @Test
    public void processImage(){
        runFileTask.run("E:\\5K Mac");
    }

}
