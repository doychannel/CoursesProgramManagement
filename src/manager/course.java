package manager;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class course implements java.io.Serializable {
    
   private String id;
    private String name;
    private String type;
    private String title;
    private Date beginD;
    private Date endD;
    private double tuition;
    private String topicID;
    public boolean status;
    private List<learner> learners;
    int size;
    public course() {
    }
    
    public course(String id, String name, String type, String title, 
            Date beginD, Date endD, double tuition, String topicID, boolean status) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.title = title;
        this.beginD = beginD;
        this.endD = endD;
        this.tuition = tuition;
        this.topicID = topicID;
        this.status = status;
        this.learners = new ArrayList<>();

    }
    public course(String id, String name, String type, String title, 
            Date beginD, Date endD, double tuition, String topicID, boolean status, int size) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.title = title;
        this.beginD = beginD;
        this.endD = endD;
        this.tuition = tuition;
        this.topicID = topicID;
        this.status = status;
        this.learners = new ArrayList<>();
        this.size = 0;
    }
    
    public int getSize(){
        return size;
    }
    public void setSize(int size){
        this.size = size;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    public boolean getStatus() {
        return status;
    }
    public String getTopicId() {
        return topicID;
    }

    
    public void setTopicId(String topicId) {
        this.topicID = topicId;
    }
    
    public String getId() {
        return id;
    }

    public void setCourseId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getBeginD() {
        return beginD;
    }

    public void setBeginD(Date beginD) {
        this.beginD = beginD;
    }

    public Date getEndD() {
        return endD;
    }

    public void setEndD(Date endD) {
        this.endD = endD;
    }

    public double getTuition() {
        return tuition;
    }

    public void setTuition(double tuition) {
        this.tuition = tuition;
    }

    public  void addLearner(learner learner) {
        learners.add(learner);
        this.size++;
        
    }
    public int getLearnerSize() {
        return learners.size();
    }
  
   public String getLearnerAll(){
       StringBuilder allLearners = new StringBuilder();
       for(learner l : learners) {
           allLearners.append(l.toString()).append("\n");
       }
       return allLearners.toString();
   }
      //getLearnerbyID
    public learner getLearner(String id) {
        for (learner l : learners) {
            if (l.getLearnerId().equalsIgnoreCase(id)) {
                return l;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return  "id=" + id + ", name=" + name + ", type=" + type + ", title=" + title + ", beginD=" + beginD
                + ", endD=" + endD + ", tuition=" + tuition + ", topicId=" + topicID + ", status=" + status + ", size= " + size + ", learners=" + getLearnerAll();
    }
}
