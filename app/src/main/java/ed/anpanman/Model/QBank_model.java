package ed.anpanman.Model;

public class QBank_model {
    private int Image;
    private String Question;
    private String Answer;

    public QBank_model() {
    }

    public QBank_model(int image, String question, String answer) {
        Image = image;
        Question = question;
        Answer = answer;
    }

    public QBank_model(String question, String answer) {
        Question = question;
        Answer = answer;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String question) {
        Question = question;
    }

    public String getAnswer() {
        return Answer;
    }

    public void setAnswer(String answer) {
        Answer = answer;
    }
}