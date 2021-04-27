package cat.itb.tanderapp.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class User {
    private int age;
    private String id;
    private String email;
    private String name;
//    private Calendar birth;
    private String gender;
    private String preference;
    private String description;
    private List<String> urls;
    private int ageMin;
    private int ageMax;


    public User(String id, String email, String name, Calendar birth, String gender, String preference, int ageMin, int ageMax) {
        this.id = id;
        this.email = email;
        this.name = name;
//        this.birth = birth;
        this.gender = gender;
        this.preference = preference;
        this.ageMin = ageMin;
        this.ageMax = ageMax;
//        this.age = Calendar.getInstance().get(Calendar.YEAR) - birth.get(Calendar.YEAR);
        this.description = "";
        this.urls = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public Calendar getBirth() {
//        return birth;
//    }
//
//    public void setBirth(Calendar birth) {
//        this.birth = birth;
//    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPreference() {
        return preference;
    }

    public void setPreference(String preference) {
        this.preference = preference;
    }

    public int getAgeMin() {
        return ageMin;
    }

    public void setAgeMin(int ageMin) {
        this.ageMin = ageMin;
    }

    public int getAgeMax() {
        return ageMax;
    }

    public void setAgeMax(int ageMax) {
        this.ageMax = ageMax;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }
}
