package education.model;

import com.sun.xml.internal.ws.developer.Serialization;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Serialization
public class Lesson {

    private String name;
    private String duration;
    private String lecturerName;
    private double price;
    private Student student;

}
