package onlineshop.tools;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@EnableAsync
@Component
public class MySchedule {

    @Async
    @Scheduled(fixedDelay = 3000, initialDelay = 10000)
    public void showSomething() throws IOException {
        //this.fileTools.deleteContentOfFile(FilePaths.FILE_LOG_PATH);
        //System.out.println(ZonedDateTime.now().format(DateTimeFormatter.RFC_1123_DATE_TIME));
        //System.out.println("Log is created");
    }

}