package ed.anpanman.Model;

public class currModel
{
    byte[] Image;
    String Name;

    public currModel(byte[] image, String name) {
        Image = image;
        Name = name;
    }

    public byte[] getImage() {
        return Image;
    }

    public void setImage(byte[] image) {
        Image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
