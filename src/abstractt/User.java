package abstractt;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import ENUMS.Language;
import ENUMS.Role;

public abstract class User implements Serializable, Cloneable {
    private static final long serialVersionUID = 1L;

    protected String userId;
    protected String name;
    protected String email;
    protected String phoneNumber;
    protected Role role;
    protected Language preferredLanguage;
    protected List<String> notifications;
    private String password; 

    public User(String userId, String name, String email, String phoneNumber,String password, Role role, Language preferredLanguage) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.preferredLanguage = preferredLanguage;
        this.notifications = new ArrayList<>();
        this.password = password;
    }
    
    public User(String userId, String name, String email, String phoneNumber, String password, Role role) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.preferredLanguage = Language.RUSSIAN;
        this.notifications = new ArrayList<>();
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }
    
    public String getPassword() { 
        return password;
    }
    
    public Role getRole() {
        return role;
    }
    public String getName() {
        return this.name;
    }
    
    public void setRole(Role newRole) {
    	this.role = newRole;
    }

    public Language getPreferredLanguage() {
        return preferredLanguage;
    }

    public void setPreferredLanguage(Language preferredLanguage) {
        this.preferredLanguage = preferredLanguage;
    }

    public void addNotification(String notification) {
        notifications.add(notification);
    }

    public List<String> getNotifications() {
        return notifications;
    }

    @Override
    public String toString() {
        return String.format("User{id='%s', name='%s', email='%s', role=%s, language=%s}",
                userId, name, email, role, preferredLanguage);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId) &&
                Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, email);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public String getEmail() {
        return email;
    }

    public String getLanguage() {
        return preferredLanguage.toString();
    }
}
