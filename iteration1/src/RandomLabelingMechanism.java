import java.util.ArrayList;
import java.util.Date;

/* RandomLabelingMechanism is kind of labelingMechanism which assignment process handled randomly, 
   inherits from LabelingMechanism class. Implemented Singleton Pattern. */  
public class RandomLabelingMechanism extends LabelingMechanism
{
	private static RandomLabelingMechanism randomLabelingMechanism;
    
    // No arg constructor.
	private RandomLabelingMechanism() {
		
	 }
    
    // getInstance method for singleton pattern.
	public static synchronized RandomLabelingMechanism getInstance() { 

	    if (randomLabelingMechanism == null) {

	    	randomLabelingMechanism = new RandomLabelingMechanism();
	    }

	    return randomLabelingMechanism;
      }
      
	/* Method below assigns labels to an instance by maximum of maxLabelPerInstance.
       then returns to assignmentList in dataset. */
    public void assign(Dataset dataset, Instance instance,User user) {

        // Getting a random value between 1 and max labels per instance.
        int maxLabelRandom = (int) (1 + (Math.random() * dataset.getMaxLabelPerInstance()));
        
        // Creates a local label arraylist to store labels to assign.
        ArrayList<Label> labels = new ArrayList<Label>(); 

        // Chooses a random label from classLabel arraylist in dataset.
        for (int j = 0; j < maxLabelRandom; j++) {
            Label getRandomLabel = dataset.getClassLabels().get((int) ((Math.random() * dataset.getClassLabels().size())));
            labels.add(getRandomLabel);
            
            // Print the created assignment to the log file.
            Logger.getInstance().print(new Date(),
                    "[Assignment] INFO user id:" + user.getUserID() + " " + user.getUserName() + " tagged instance id:"
                            + instance.getInstanceID() + " " + "with class label " + getRandomLabel.getLabelID() + ":"
                            + getRandomLabel.getLabelName() + " " + " instance: \"" + instance.getContent() + "\"");
        }

        // Returns to assignmentList in dataset.
        Assignment assignment = new Assignment(instance, user, labels);
        dataset.addAssignment(assignment);

    }
	
}
