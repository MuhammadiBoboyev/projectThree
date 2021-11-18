package uz.pdp.projectthree.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentDto {
    private String firstName;
    private String lastName;
    private int groupId;
    private List<Integer> subjectsId;
}
