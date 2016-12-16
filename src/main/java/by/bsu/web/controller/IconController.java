package by.bsu.web.controller;

import by.bsu.web.entity.UserData;
import by.bsu.web.entity.UserIcon;
import by.bsu.web.service.UserDataService;
import by.bsu.web.service.UserIconService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/icon")
public class IconController {

    @Autowired
    private UserIconService userIconService;

    @Autowired
    private SessionController sessionController;

    @Autowired
    private UserDataService userDataService;

    private HttpStatus saveImage(UserIcon userIcon, MultipartFile file, Long localContactId, UserData currentUser) {
        HttpStatus status;
        try {
            userIcon.setIconBytes(file.getBytes());
            userIcon = userIconService.saveWithRandomName(userIcon);

            if (localContactId == null) {
                currentUser.setFkUserPhoto(userIcon);
                userDataService.save(currentUser);
                status = HttpStatus.OK;
            } else {
                UserData localContact = userDataService.findByPkId(localContactId);
                if (localContact == null) {
                    status = HttpStatus.NOT_FOUND;
                } else {
                    localContact.setFkUserPhoto(userIcon);
                    status = HttpStatus.OK;
                }
            }
        } catch (IOException e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return status;
    }


    @RequestMapping(value = "/{name}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public ResponseEntity<byte[]> getIconByName(@PathVariable String name) {
        UserIcon userIcon = userIconService.findByIconName(name);

        HttpStatus httpStatus;
        byte[] imageBytes = new byte[0];
        if (userIcon == null) {
            httpStatus = HttpStatus.NOT_FOUND;
        } else {
            httpStatus = HttpStatus.OK;
            imageBytes = userIcon.getIconBytes();
        }
        return new ResponseEntity<>(imageBytes, httpStatus);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    public ResponseEntity addNewIcon(@RequestPart("icon") MultipartFile file,
                                     @PathVariable(value = "id", required = false) Long localContactId) {
        UserData currentUser = sessionController.getAuthorizedUser();
        UserIcon userIcon = new UserIcon();
        HttpStatus status = saveImage(userIcon, file, localContactId, currentUser);
        return new ResponseEntity(status);
    }

    @RequestMapping(value = "/{name}/{id}", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    public ResponseEntity editIcon(@RequestParam("icon") MultipartFile file,
                                   @PathVariable(value = "name") String name,
                                   @PathVariable(value = "id", required = false) Long localContactId) {
        UserData currentUser = sessionController.getAuthorizedUser();
        HttpStatus status;

        UserIcon currentIcon = userIconService.findByIconName(name);

        if (currentIcon == null) {
            status = HttpStatus.BAD_REQUEST;
        } else {
            if (currentIcon.getIconName().equals(UserIcon.DEFAULT_ICON_NAME)) {
                currentIcon = new UserIcon();
            }
            status = saveImage(currentIcon, file, localContactId, currentUser);
        }

        return new ResponseEntity(status);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity deleteIcon(@PathVariable("id") Long id) {
        UserIcon userIcon = userIconService.findByPkId(id);
        HttpStatus status;

        if (userIcon == null) {
            status = HttpStatus.NOT_FOUND;
        } else {
            userIconService.delete(userIcon);
            status = HttpStatus.OK;
        }
        return new ResponseEntity(status);
    }
}
