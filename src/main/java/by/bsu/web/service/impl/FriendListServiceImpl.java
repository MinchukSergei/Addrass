package by.bsu.web.service.impl;

import by.bsu.web.entity.FriendList;
import by.bsu.web.entity.UserData;
import by.bsu.web.repository.FriendListRepository;
import by.bsu.web.service.FriendListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FriendListServiceImpl implements FriendListService {
    @Autowired
    private FriendListRepository friendListRepository;

    @Override
    public FriendList findByPkId(Long id) {
        return friendListRepository.findOne(id);
    }

    @Override
    public FriendList save(FriendList friendList) {
        return friendListRepository.save(friendList);
    }

    @Override
    public FriendList findByFkUserMainAndFkUserFriendEntity(UserData owner, UserData friend) {
        return friendListRepository.findByFkUserMainAndFkUserFriend(owner, friend);
    }

    @Override
    public void delete(Long id) {
        friendListRepository.delete(id);
    }
}
