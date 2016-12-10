package by.bsu.web.controller;

import by.bsu.web.controller.util.ErrorEntity;
import by.bsu.web.entity.FriendList;
import by.bsu.web.entity.UserColor;
import by.bsu.web.entity.UserData;
import by.bsu.web.entity.UserGroup;
import by.bsu.web.service.FriendListService;
import by.bsu.web.service.UserColorService;
import by.bsu.web.service.UserDataService;
import by.bsu.web.service.UserGroupService;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/friend")
public class FriendListController {

    @Autowired
    private UserGroupService userGroupService;

    @Autowired
    private SessionController sessionController;

    @Autowired
    private FriendListService friendListService;

    @Autowired
    private UserColorService userColorService;

    @Autowired
    private UserDataService userDataService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<String> addFriend(@RequestBody FriendList friendList) {
        UserData userData = userDataService.findByPkId(friendList.getFkUserFriendEntity().getPkId());
        UserData current = sessionController.getAuthorizedUser();
        HttpStatus result;

        if (userData != null) {
            UserGroup group = userGroupService.findByName(friendList.getFkUserGroupEntity().getName());
            UserColor color = userColorService.findByName(friendList.getFkUserColorEntity().getName());
            friendList.setFkUserGroupEntity(group);
            friendList.setFkUserFriendEntity(userData);
            friendList.setFkUserMain(current.getPkId());
            friendList.setFkUserColorEntity(color);

            FriendList exists = friendListService.findByFkUserMainAndFkUserFriendEntity(current.getPkId(), userData);
            if (exists == null) {
                friendListService.save(friendList);
                result = HttpStatus.OK;
            } else {
                result = HttpStatus.BAD_REQUEST;
            }
        } else {
            result = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<>(result);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResponseEntity<String> deleteFriendFromList(@RequestParam Long id) {
        UserData current = sessionController.getAuthorizedUser();
        UserData friend = userDataService.findByPkId(id);
        FriendList exists = friendListService.findByFkUserMainAndFkUserFriendEntity(current.getPkId(), friend);
        HttpStatus result;
        if (exists == null) {
            result = HttpStatus.BAD_REQUEST;
        } else {
            friendListService.delete(exists.getPkId());
            result = HttpStatus.OK;
        }
        return new ResponseEntity<>(result);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ResponseEntity<String> editFriend(@RequestBody FriendList friendList) {
        UserData userData = userDataService.findByPkId(friendList.getFkUserFriendEntity().getPkId());
        UserData current = sessionController.getAuthorizedUser();
        HttpStatus result;

        if (userData != null) {
            UserGroup group = userGroupService.findByName(friendList.getFkUserGroupEntity().getName());
            UserColor color = userColorService.findByName(friendList.getFkUserColorEntity().getName());
            FriendList exists = friendListService.findByFkUserMainAndFkUserFriendEntity(current.getPkId(), userData);
            exists.setFkUserColorEntity(color);
            exists.setFkUserGroupEntity(group);
            exists.setBlackList(friendList.getBlackList());

            friendListService.save(exists);
            result = HttpStatus.OK;
        } else {
            result = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(result);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public List<FriendList> getAllFriendsNBlackList() {
        UserData current = sessionController.getAuthorizedUser();
        return friendListService.findByFkUserMainNotBlackList(current.getPkId());
    }

    @RequestMapping(value = "/all/b", method = RequestMethod.GET)
    @ResponseBody
    public List<FriendList> getAllFriendBlackList() {
        UserData current = sessionController.getAuthorizedUser();
        return friendListService.findByFkUserMainBlackList(current.getPkId());
    }
}
