package jp.co.teruuu;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.tasklet.MethodInvokingTaskletAdapter;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.Date;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory; //(2)
    @Autowired
    private StepBuilderFactory stepBuilderFactory; //(2)

    @Autowired
    private LoggingService service;

    @Bean
    public JobLauncher jobLauncher1(JobRepository jobRepository) { //(2),(3)
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        // 同時実行数は3.それ以上はキュー待ちになる.
        taskExecutor.setCorePoolSize(3); //(4)
        // java.lang.IllegalStateException: ThreadPoolTaskExecutor not initialized
        taskExecutor.initialize(); //(5)

        SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
        jobLauncher.setJobRepository(jobRepository);
        jobLauncher.setTaskExecutor(taskExecutor);

        return jobLauncher;
    }

    @Bean
    public Job job(Step step1) { //(6)
        return jobBuilderFactory.get("batchjob")
                .incrementer(new RunIdIncrementer())
                .start(step1)
                .build();
    }

    @Bean
    public Step step1(Tasklet tasklet1) { //(6)
        return stepBuilderFactory.get("step1")
                .tasklet(tasklet1)
                .build();
    }

    @Bean
    @StepScope //(7)
    public Tasklet tasklet1( //(6)
                             @Value("#{jobParameters['id']}") Long id, //(8)
                             @Value("#{jobParameters['name']}") String name,
                             @Value("#{jobParameters['reqDate']}") Date reqDate
    ) {
        //(9)
        MethodInvokingTaskletAdapter tasklet = new MethodInvokingTaskletAdapter();
        tasklet.setTargetObject(service);
        tasklet.setTargetMethod("execute");
        tasklet.setArguments(new Object[] {id, name, reqDate});

        return tasklet;
    }
}
