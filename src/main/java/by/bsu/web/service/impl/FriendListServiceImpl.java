package by.bsu.web.service.impl;

import by.bsu.web.entity.FriendList;
import by.bsu.web.entity.UserData;
import by.bsu.web.entity.UserGroup;
import by.bsu.web.repository.FriendListRepository;
import by.bsu.web.service.FriendListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendListServiceImpl implements FriendListService {
    @Autowired
    private FriendListRepository friendListRepository;

    @Override
    public List<FriendList> findByFkUserMainNotBlackList(Long id) {
        return friendListRepository.findByFkUserMainAndBlackList(id, false);
    }

    @Override
    public List<FriendList> findByFkUserMainNotBlackListAndGroup(Long id, UserGroup group) {
        return friendListRepository.findByFkUserMainAndBlackListAndFkUserGroupEntity(id, false, group);
    }

    @Override
    public List<FriendList> findByFkUserMainBlackList(Long id) {
        return friendListRepository.findByFkUserMainAndBlackList(id, true);
    }

    @Override
    public List<FriendList> findByFkUserMain(Long id) {
        return friendListRepository.findByFkUserMain(id);
    }

    @Override
    public FriendList save(FriendList friendList) {
        return friendListRepository.save(friendList);
    }

    @Override
    public FriendList findByFkUserMainAndFkUserFriendEntity(Long main, UserData friend) {
        return friendListRepository.findByFkUserMainAndFkUserFriendEntity(main, friend);
    }

    @Override
    public void delete(Long id) {
        friendListRepository.delete(id);
    }
}
