package education.model;

import com.sun.xml.internal.ws.developer.Serialization;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Serialization
    public class Student {

        private String name;
        private String surname;
        private int age;
        private String email;
        private String phone;
        private Set<Lesson> lessons;
        private Date dateOfBirth;

    }
