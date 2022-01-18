package education.storage;

import education.model.Lesson;
import education.util.FileUtil;

import java.util.ArrayList;
import java.util.List;

public class LessonStorage {

    private List<Lesson> lessons = new ArrayList<>();

    public void add(Lesson lesson) {
        lessons.add(lesson);
        serialize();
    }

    public void print() {
        for (Lesson lesson : lessons) {
            System.out.println(lesson);
        }
    }

    public Lesson getByName(String name) {
        for (Lesson lesson : lessons) {
            if (lesson.getName().equals(name)) {
                return lesson;
            }
        }
        return null;
    }

    public void deleteByName(Lesson lesson) {
        for (Lesson lesson1 : lessons) {
            if (lesson1.equals(lesson)) {
                lessons.remove(lesson);
            }
        }
        serialize();
    }
    public void initData() {
        List<Lesson> lessonList = FileUtil.deserializeLessons();
        if (lessonList != null) {
            lessons = lessonList;
        }
    }

    public void serialize() {
        FileUtil.serializeLessons(lessons);
    }
}

