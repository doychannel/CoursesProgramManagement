package manager;
import java.util.Date;

public class learner implements java.io.Serializable {
    private String id;
    private String name;
    private Date dob;
    private double scores;
  
   
   public learner() {
    }

    public learner(String id, String name, Date dob, double scores ) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.scores = scores;
    }

    
    public double getScores() {
        return scores;
    }

    public void setScores(double scores) {
        this.scores = scores;
    }

    public String getLearnerId() {
        return id;
    }

    public void setLearnerId(String id) {
        this.id = id;
    }

    public String getLearnerName() {
        return name;
    }

    public void setLearnerName(String name) {
        this.name = name;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date  dob) {
        this.dob = dob;
    }
    
   

    @Override
    public String toString() {
        return "learner{" + "id=" + id + ", name=" + name + ", dob=" + dob  + "scores=" + scores+ '}';
    }

}
