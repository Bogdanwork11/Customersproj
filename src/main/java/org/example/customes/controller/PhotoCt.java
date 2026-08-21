package org.example.customes.controller;
import org.example.customes.entity.Employees;
import org.example.customes.entity.Users;
import org.example.customes.repository.EmployeesRp;
import org.example.customes.repository.UsersRp;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/photo")
public class PhotoCt {

    private final UsersRp usersRepository;
    private final EmployeesRp employeesRepository;

    public PhotoCt(UsersRp usersRepository, EmployeesRp employeesRepository){
        this.usersRepository = usersRepository;
        this.employeesRepository = employeesRepository;
    }

    @GetMapping(value = "{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] photocheck(@PathVariable Integer id){
        Employees employee = employeesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Сотрудник не найден"));

        if (employee.getPhoto() == null){
            throw new RuntimeException("У этого сотрудника нет фото");
        }

        return employee.getPhoto();
    }

}
