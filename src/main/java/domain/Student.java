package domain;


public class Student {
    // Идентификатор студента
    private Long id;
    // Фамилия
    private String lastName;
    // Имя
    private String firstName;
    // Отчество
    private String middleName;
    // Дата рождения (для простоты храним как строку)
    private String birthDate;
    // Телефон
    private String phone;
    // Email
    private String email;

    // Внешний ключ - ссылка на сущность Group
    private Long idGroup;
    // Навигационное свойство - ссылка на группу
    private Group group;

    public Student() {
    }

    public Student(String lastName, String firstName, String middleName, String birthDate, String phone, String email, Group group) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.birthDate = birthDate;
        this.phone = phone;
        this.email = email;
        this.group = group;
    }

    public Student(Long id, String lastName, String firstName, String middleName, String birthDate, String phone, String email, Long idGroup, Group group) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.birthDate = birthDate;
        this.phone = phone;
        this.email = email;
        this.idGroup = idGroup;
        this.group = group;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(Long idGroup) {
        this.idGroup = idGroup;
    }


    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public String getGroupName() {
        return group != null ? group.getName() : "Не назначена";
    }

    @Override
    public String toString() {
        return "Student {" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", groupName='" + getGroupName() + '\'' +
                '}';
    }
}