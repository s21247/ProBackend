package com.example.probackend.group;

import com.example.probackend.User.AppUser;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "group",schema = "public")
public class GroupEntity {

    @SequenceGenerator(
            name= "group_sequence",
            sequenceName = "group_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "group_sequence"
    )
    private Long id;
    @Column(
            name = "name",
            unique = true,
            nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(
            nullable = false,
            name = "user_id",referencedColumnName = "id"
    )
    private AppUser appUser;

    public GroupEntity(String name) {
        this.name = name;
    }

    public GroupEntity() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupEntity that = (GroupEntity) o;
        return id.equals(that.id) && name.equals(that.name) && appUser.equals(that.appUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, appUser);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }
}
