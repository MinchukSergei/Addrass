package by.bsu.web.api;

import by.bsu.web.entity.UserIcon;
import by.bsu.web.repository.UserIconRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/icon")
public class IconController {

    @Autowired
    private UserIconRepository userIconRepository;

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getIconById(@PathVariable long id) {
        UserIcon userIcon = userIconRepository.findOne(id);
        return userIcon.getIconBytes();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = "multipart/form-data")
    public void setIcon(@RequestPart("file") MultipartFile file) {
        UserIcon userIcon = new UserIcon();
        try {
            userIcon.setIconBytes(file.getBytes());
            userIconRepository.save(userIcon);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
