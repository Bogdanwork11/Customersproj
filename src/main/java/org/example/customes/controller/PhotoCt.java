package org.example.customes.controller;
import org.example.customes.entity.Users;
import org.example.customes.repository.UsersRp;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/photo")
public class PhotoCt {

    private final UsersRp usersRepository;

    public PhotoCt(UsersRp usersRepository){
        this.usersRepository = usersRepository;
    }

    @GetMapping(value = "{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] photocheck(@PathVariable Integer id){
        Users user = usersRepository.findById(id).orElse(null);

        if (user == null){
            throw new RuntimeException("Увы не получилось найти пользователя");
        }else {
            return user.getPhoto();
        }
    }

}
