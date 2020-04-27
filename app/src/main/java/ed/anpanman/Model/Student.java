package ed.anpanman.Model;

public class Student {
    private String id;
    private String username;
    private String imageURL;
    private String Email;
    private String Points;
    private String Grade;

    public Student(String id, String username, String imageURL, String email, String points, String grade) {
        this.id = id;
        this.username = username;
        this.imageURL = imageURL;
        Email = email;
        Points = points;
        Grade = grade;
    }

    public Student() {
    }

    public String getPoints() {
        return Points;
    }

    public void setPoints(String points) {
        Points = points;
    }

    public String getGrade() {
        return Grade;
    }

    public void setGrade(String grade) {
        Grade = grade;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImageURL() {
        return imageURL ;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
