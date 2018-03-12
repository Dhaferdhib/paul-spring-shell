package io.github.dhafiro.paul;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class TaskDao {
    private TaskRepository taskRepository;

    @Autowired
    public TaskDao(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    void addTask(String name, String description, Date plannedDate){
        taskRepository.save(new Task(name,description,plannedDate));
    }

    List<Task> getAllTasks(){
        return taskRepository.findAll();
    }

    Optional<Task> findById(Long id){
        return taskRepository.findById(id);
    }

    void update(Task task){
        taskRepository.save(task);
    }




}
