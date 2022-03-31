
package it.tss.blogapp.entity;

import it.tss.blogapp.boundary.UsersResource;
import java.util.ArrayList;
import java.util.List;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.ws.rs.core.UriBuilder;

@Entity
@Table(name = "user")
public class User extends BaseEntity {

    @JsonbProperty(value = "first_name")
    @NotBlank
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @JsonbProperty(value = "last_name")
    @NotBlank
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NotBlank
    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank
    @Size(min = 4)
    @Column(nullable = false)
    private String pwd;
    
    @JsonbTransient
    @OneToMany(mappedBy = "author", fetch = FetchType.EAGER)
    private List<Post> post = new ArrayList<>();

    public JsonObject toJsonSlice() {

        return Json.createObjectBuilder()
                .add("id", this.id)
                .add("link", UriBuilder.fromResource(UsersResource.class)
                        .path(UsersResource.class,"find")
                        .build(this.id).toString())
                .build();
    }

    /*
    getter setter
     */
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Post> getPost() {
        return post;
    }

    public void setPost(List<Post> post) {
        this.post = post;
    }
    
    @JsonbTransient
    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", pwd=" + pwd + '}';
    }

}
