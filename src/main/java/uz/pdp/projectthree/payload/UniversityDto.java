package uz.pdp.projectthree.payload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UniversityDto {//MA'LUMOTLARNI TASHISH UCHUN XIZMAT QILADI
    private String name;
    private String city;
    private String district;
    private String street;
}
