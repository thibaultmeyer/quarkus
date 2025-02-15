package io.quarkus.kubernetes.deployment;

import java.util.Optional;

import io.dekorate.kubernetes.annotation.CronJobConcurrencyPolicy;
import io.dekorate.kubernetes.annotation.JobCompletionMode;
import io.dekorate.kubernetes.annotation.JobRestartPolicy;
import io.smallrye.config.WithDefault;

public interface CronJobConfig {
    /**
     * The schedule in Cron format, see <a href="https://en.wikipedia.org/wiki/Cron">Cron</a>.
     */
    Optional<String> schedule();

    /**
     * The time zone for the job schedule. The default value is the local time of the kube-controller-manager.
     */
    Optional<String> timeZone();

    /**
     * ConcurrencyPolicy describes how the job will be handled.
     */
    @WithDefault("Allow")
    CronJobConcurrencyPolicy concurrencyPolicy();

    /**
     * Deadline in seconds for starting the job if it misses scheduled time for any reason.
     * Missed jobs executions will be counted as failed ones.
     */
    Optional<Long> startingDeadlineSeconds();

    /**
     * The number of failed finished jobs to retain. The default value is 1.
     */
    Optional<Integer> failedJobsHistoryLimit();

    /**
     * The number of successful finished jobs to retain. The default value is 3.
     */
    Optional<Integer> successfulJobsHistoryLimit();

    /**
     * Specifies the maximum desired number of pods the job should run at any given time.
     */
    Optional<Integer> parallelism();

    /**
     * Specifies the desired number of successfully finished pods the job should be run with.
     */
    Optional<Integer> completions();

    /**
     * CompletionMode specifies how Pod completions are tracked.
     */
    @WithDefault("NonIndexed")
    JobCompletionMode completionMode();

    /**
     * Specifies the number of retries before marking this job failed.
     */
    Optional<Integer> backoffLimit();

    /**
     * Specifies the duration in seconds relative to the startTime that the job may be continuously active before the
     * system tries to terminate it; value must be positive integer.
     */
    Optional<Long> activeDeadlineSeconds();

    /**
     * Limits the lifetime of a Job that has finished execution (either Complete or Failed). If this
     * field is set, ttlSecondsAfterFinished after the Job finishes, it is eligible to be automatically deleted.
     */
    Optional<Integer> ttlSecondsAfterFinished();

    /**
     * Suspend specifies whether the Job controller should create Pods or not.
     */
    @WithDefault("false")
    boolean suspend();

    /**
     * Restart policy when the job container fails.
     */
    @WithDefault("OnFailure")
    JobRestartPolicy restartPolicy();
}
