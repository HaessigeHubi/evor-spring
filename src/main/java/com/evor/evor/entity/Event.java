package com.evor.evor.entity;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "events")
    @JsonIgnore
    private Set<User> users = new HashSet<>();

    public void setId(long id) {
        this.id = id;
    }



    public Event(String eventname, String description, double lat, double lng, String address, String owner ){
        super();
        this.eventname = eventname;
        this.description = description;
        this.lat = lat;
        this.lng =lng;
        this.address = address;
        this.owner = owner;
    }


    public String getEventname() {
        return eventname;
    }

    public void setEventname(String eventname) {
        this.eventname = eventname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getMaxParticipation() {
        return maxParticipation;
    }

    public void setMaxParticipation(int maxParticipation) {
        this.maxParticipation = maxParticipation;
    }

    public int getAgeRestriction() {
        return ageRestriction;
    }

    public void setAgeRestriction(int ageRestriction) {
        this.ageRestriction = ageRestriction;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    private String eventname;
    private String description;
    @CreationTimestamp
    private Date createDate;
    private Date startDate;
    private Date endDate;

    private int maxParticipation;
    private int ageRestriction;

    private double lat;
    private double lng;

    private byte[] image;
    private String region;
    private String address;
    private String owner;


    public Event() {

    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }


    public void addUser(User user) {
        this.users.add(user);
        user.getEvents().add(this);
    }

    public void removeUsers(long userId) {
        User user = this.users.stream().filter(t -> t.getId() == userId).findFirst().orElse(null);
        if (user != null) {
            this.users.remove(user);
            user.getEvents().remove(this);
        }
    }

    @Override
    public String toString() {
        return "Events [id=" + id + ", title=" + name + ", desc=" + description + "]";
    }

}