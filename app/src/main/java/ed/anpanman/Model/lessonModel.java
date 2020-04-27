package ed.anpanman.Model;

public class lessonModel {
    String Title;
   int Image;
    int No;

    public lessonModel() {
    }

    public lessonModel(String title) {
        Title = title;
    }

    public lessonModel(int no, String title) {
        Title = title;
        No=no;
    }

    public lessonModel(String title, int image) {
        Title = title;
        Image = image;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }
}

