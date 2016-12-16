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

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity addFriendByLogin(@RequestBody FriendList friendList) {
        UserData currentUser = sessionController.getAuthorizedUser();
        HttpStatus status;

        UserData friend = userDataService.findByUserLogin(friendList.getFkUserFriend().getUserLogin());

        if (friend == null) {
            status = HttpStatus.NOT_FOUND;
        } else {
            UserData fetchedUser = userDataService.findUserDateByIdAndFetchFriendList(currentUser.getPkId());
            Set<FriendList> friends = new HashSet<>();

            if (fetchedUser != null) {
                friends = fetchedUser.getFriends();
            }

            FriendList oldFriendList = friendListService.findByFkUserMainAndFkUserFriendEntity(currentUser, friend);

            if (friends.contains(oldFriendList)) {
                status = HttpStatus.BAD_REQUEST;
            } else {
                UserColor color = null;
                if (friendList.getFkUserColor() != null) {
                    color = userColorService.findByName(friendList.getFkUserColor().getName());
                }
                UserGroup group = null;
                if (friendList.getFkUserGroup() != null) {
                    group = userGroupService.findByName(friendList.getFkUserGroup().getName());
                }
                friendList.setFkUserMain(currentUser);
                friendList.setFkUserColor(color);
                friendList.setFkUserGroup(group);
                friendList.setFkUserFriend(friend);
                friends.add(friendList);
                currentUser.setFriends(friends);
                userDataService.save(currentUser);
                status = HttpStatus.OK;
            }
        }

        return new ResponseEntity(status);
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity editFriend(@RequestBody FriendList friendList) {
        UserData currentUser = sessionController.getAuthorizedUser();
        HttpStatus status;

        UserData fetchedUser = userDataService.findUserDateByIdAndFetchFriendList(currentUser.getPkId());
        Set<FriendList> friends = new HashSet<>();
        if (fetchedUser != null) {
            friends = fetchedUser.getFriends();
        }

        FriendList oldFriendList = friendListService.findByPkId(friendList.getPkId());
        if (friends.contains(oldFriendList)) {
            friends.remove(oldFriendList);
            UserColor color = null;
            if (friendList.getFkUserColor() != null) {
                color = userColorService.findByName(friendList.getFkUserColor().getName());
            }
            UserGroup group = null;
            if (friendList.getFkUserGroup() != null) {
                group = userGroupService.findByName(friendList.getFkUserGroup().getName());
            }
            oldFriendList.setFkUserColor(color);
            oldFriendList.setFkUserGroup(group);
            oldFriendList.setBlackList(friendList.getBlackList());
            friends.add(oldFriendList);
            currentUser.setFriends(friends);
            userDataService.save(currentUser);
            status = HttpStatus.OK;
        } else {
            status = HttpStatus.FORBIDDEN;
        }

        return new ResponseEntity(status);
    }

}
