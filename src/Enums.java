enum PhilosopherStatus {
    ASLEEP,
    EATING,
    THINKING,
    WAITING_TO_EAT,
    STARVED
}

enum ForkStatus {
    OCCUPIED,
    NOTIFYING_PHILOSOPHER,
    AVAILABLE
}

enum ForkPickUpAttemptResult {
    SUCCEED,
    FAIL
}