package com.example.probackend.group;
import com.example.probackend.User.AppUser;
import com.example.probackend.User.AppUserService;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;


@Service
public class GroupService {

    private EntityManager entityManager;
    private AppUserService userService;
    private final GroupRepository groupRepository;

    public GroupService(EntityManager entityManager, AppUserService userService, GroupRepository groupRepository) {
        this.entityManager = entityManager;
        this.userService = userService;
        this.groupRepository = groupRepository;
    }

    public String createGroup(GroupRequest request){
        boolean nameExists =
                groupRepository.findByName(request.getName())
                .isPresent();
        if(nameExists)
            throw new IllegalStateException("Name already taken!");
        var groupEntity = new GroupEntity();
        groupEntity.setName(request.getName());
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var userEntity = new AppUser();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        userEntity = (AppUser) userService.findUserByUsername(username);
        groupEntity.setAppUser(userEntity);
        entityManager.persist(groupEntity);

        return "Group created successfully";
    }


}
