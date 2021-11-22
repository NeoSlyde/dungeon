package model;

import java.util.ArrayList;
import java.util.List;

import model.misc.Updatable;

// A scheduler that can be updated.
// This prevents asynchronous events from happening between updates
public class Scheduler implements Updatable {
    private double t = 0;
    private List<Task> tasks = new ArrayList<>();

    @Override
    public void update(double dt) {
        t += dt;
        runOutsteppedTasks();
    }

    public Task runAfterDelay(Runnable runnable, double ms) {
        Task task = new Task(runnable, t + ms);
        return task;
    }

    public void cancelTask(Task task) {
        if (task.wasRan)
            throw new TaskAlreadyRanException();
        tasks.remove(task);
    }

    private void runOutsteppedTasks() {
        tasks.stream().filter(task -> task.t <= t).forEach(Task::runAndRemove);
    }

    public class Task {
        private Runnable runnable;
        private double t;
        private boolean wasRan = false;

        private Task(Runnable runnable, double t) {
            this.t = t;
            this.runnable = runnable;
        }

        public boolean wasRan() {
            return wasRan;
        }

        public void runAndRemove() {
            runnable.run();
            wasRan = true;
            Scheduler.this.tasks.remove(this);
        }

    }

    public static class TaskAlreadyRanException extends RuntimeException {
    }
}
