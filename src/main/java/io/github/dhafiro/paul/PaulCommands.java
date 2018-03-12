package io.github.dhafiro.paul;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@ShellComponent
public class PaulCommands {
    private TaskDao taskDao;

    @Autowired
    public PaulCommands(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    @ShellMethod("List all tasks")
    public List<Task> list(){
        return taskDao.getAllTasks();
    }


    @ShellMethod(value="Add a task", prefix = "-")
    public String add(String name,String description, String date){
        Date date1;
        try {
            date1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return "Your date is not correct ... try to match this pattern : yyyy-MM-dd HH:mm:ss";
        }
        taskDao.addTask(name,description,date1);
        return "Task added";
    }


    @ShellMethod(value="mark task as done")
    public String markDone(Long id){
        Optional<Task> task = taskDao.findById(id);
        if(task.isPresent()){
            task.get().setDone(true);
            taskDao.update(task.get());
            return "Marked as done";
        }
        return "Task not found with #id: "+id;
    }
}
