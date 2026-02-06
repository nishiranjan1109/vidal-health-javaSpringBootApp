package startup;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import service.HiringService;

@Component
public class Runner implements CommandLineRunner {

    private final HiringService hiringService;

    public Runner(HiringService hiringService) {
        this.hiringService = hiringService;
    }

    @Override
    public void run(String... args) {
        hiringService.executeHiringTask();
    }
}
