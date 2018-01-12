package Bi_color_tree;

import java.util.Map;
import java.util.TreeMap;


public class ArnTest  {
	
	/**
	 * Store the maximum execution time for a Task
	 */
	private final static int MAXIMUM_EXECUTION_TIME = 200;
	
	/**
	 * Store the minimum execution time for a Task
	 */
	private final static int MINIMUM_EXECUTION_TIME = 10;
	
	/**
	 * Store the Integer generator (mainly used to generate Task's execution time)
	 */
	private final ArrayListIntegerGenerator gen;
	
	/**
	 * Store the number of task that the Planificator should handle
	 */
	private final int taskToHandle;
	
	ArnTest(int taskToHandle){
		this.taskToHandle = taskToHandle;
		gen = new ArrayListIntegerGenerator(MINIMUM_EXECUTION_TIME, MAXIMUM_EXECUTION_TIME);
	}
	
	/**
	 * Build the default ArnTest (with 20 task to handle)
	 * @return the default ArnTest
	 */
	/*public static ArnTest defaultTest(){
		return new ArnTest(20);
	}*/
	
	
	public void launch() {
		
		// Instantiate the Planificator
		Planificator p = new Planificator();
		
		// Add some random generated tasks in the Planificator
		for(int i = 1; i < taskToHandle; i++){
			p.add(new Task(gen.buildObject(), "Task ID#" + i));
		}
		
		// Run the execution
		p.runAll();
	}
	
	private class Planificator {
		
		/**
		 * Store all the Task in a Red and Black Tree
		 * We use the TreeMap as Red and Black Tree class implementation
		 * See the Java official documentation #{http://docs.oracle.com/javase/6/docs/api/java/util/TreeMap.html}
		 */
		private final TreeMap<Integer, Task> map;
		
		Planificator(){
			// Init the map
			// We don't need to implement a comparator since we are using basic Integers
			map = new TreeMap<>();
		}
		
		/**
		 * Add a task to the Map
		 * It will automatically set the first Index
		 * from the task's execution time
		 * @param t the task
		 */
		void add(Task t){
			map.put(t.executionTime, t);
		}
		
		void execFirst(){
			
			// Poll the first entry with the TreeMap function
			final Map.Entry<Integer, Task> integerTaskEntry = map.pollFirstEntry();
			
			// If the value is not empty (i.e. : there are values in the map)
			if(integerTaskEntry != null){
				
				// Get the selected Task
				final Task value = integerTaskEntry.getValue();
				
				// Call the task execution
				// (@see #{{@link Task#exec()}}) for more information
				value.exec();
				
				// Automatically re-add the Task in the TreeMap
				// Incrementing it key with the execution time
				map.put(integerTaskEntry.getKey() + value.executionTime, value);
			}
		}
		
		void runAll(){
			// Run thread to execute the Planificator
			new Thread(() -> {
				// Run all the Task 30 times
				for (int i = 0; i < map.size() * 30; i++) {
					this.execFirst();
				}
			}).run();
		}
		
	}
	
	private class Task {
		
		/**
		 * Store the execution time of the task
		 * will be used in the execution simulation
		 */
		Integer executionTime;
		
		/**
		 * Store the Task name mainly for logging
		 */
		final String taskName;
		
		Task(Integer executionTime, String taskName) {
			this.executionTime = executionTime;
			this.taskName = taskName;
		}
		
		void exec(){
			try {
				
				// Make the thread sleep during the defined execution time
				Thread.sleep(this.executionTime);
				
				// Display the information for the user that the task has been executed
				System.out.println(this.taskName + " executed in " + this.executionTime);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}