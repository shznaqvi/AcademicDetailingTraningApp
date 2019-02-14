package detail.acad.hassannaqvi.edu.aku.academicdetailing.model;

public class Result {

    public double correct;
    public double wrong;
    public double total;
    double percentage;

    public Result() {
    }

    public double getCorrect() {
        return correct;
    }

    public void setCorrect(double correct) {
        this.correct = correct;
    }

    public double getWrong() {
        return wrong;
    }

    public void setWrong(double wrong) {
        this.wrong = wrong;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }


}
