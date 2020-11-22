# knows




##springboot-task
###异步任务
    @Async        //告诉spring这是一个异步方法
    @EnableAsync  //开启异步注解功能 
    
###定时任务
    @Scheduled(cron = "0 * * * * SUN-SAT")
    @EnableScheduling //开启基于注解的定时任务
    
###邮件任务