package basic.assertions;

import java.util.ArrayList;
import java.util.List;

public class SUT {
    private final String systemName;
    private boolean isVerified;
    private final List<Job> jobs = new ArrayList<>();
    private Job currentJob;

    public SUT(String systemName) {
        this.systemName = systemName;
        this.isVerified = false;
    }

    public String getSystemName() {
        return systemName;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void verify() {
        this.isVerified = true;
    }

    public void addJob(Job job) {
        jobs.add(job);
    }

    public Object[] getJobsAsArray() {
        return jobs.toArray();
    }

    public Job getCurrentJob() {
        return currentJob;
    }

    public void run() {
        if (!jobs.isEmpty()) {
            currentJob = jobs.remove(0);
            System.out.println("Running job: " + currentJob);
            return;
        }
        throw new NoJobException("실행할 작업이 없습니다!");
    }

    public void run(int jobDuration) throws InterruptedException {
        if (!jobs.isEmpty()) {
            currentJob = jobs.remove(0);
            System.out.println("Running job: " + currentJob + " lasts " + jobDuration + " milliseconds");
            Thread.sleep(jobDuration);
            return;
        }
        throw new NoJobException("실행할 작업이 없습니다!");
    }
}