package edu.uiuc.ise.instein.model;

/**
 * Copy from org.pitest.mutationtest.DetectionStatus
 */
public enum DetectionStatus {
    /**
     * Mutation was detected by a test
     */
    KILLED(true),

    /**
     * No test failed in the presence of the mutation
     */
    SURVIVED(false),

    /**
     * A test took a long time to run when mutation was present, might indicate an
     * that the mutation caused an infinite loop but we don't know for sure.
     */
    TIMED_OUT(true),

    /**
     * Mutation could not be loaded into the jvm. Should never happen.
     */
    NON_VIABLE(true),

    /**
     * JVM ran out of memory while processing a mutation. Might indicate that the
     * mutation increases memory usage but we don't know for sure.
     */
    MEMORY_ERROR(true),
    /**
     * Mutation not yet assessed. For internal use only.
     */
    NOT_STARTED(false),

    /**
     * Processing of mutation has begun but not yet fully assessed. For internal
     * use only.
     */
    STARTED(false),

    /**
     * Something went wrong. Don't know what but it was probably bad.
     */
    RUN_ERROR(true),

    /**
     * Mutation is not covered by any test.
     */
    NO_COVERAGE(false);

    private final boolean detected;

    DetectionStatus(final boolean detected) {
        this.detected = detected;
    }

    /**
     * Returns true if this status indicates that the mutation was distinguished
     * from the un-mutated code by the test suite, ignores the slight ambiguity of
     * some of the statuses.
     *
     * @return True if detected, false if not.
     */
    public boolean isDetected() {
        return this.detected;
    }

};