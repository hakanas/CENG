* Tested with;
	>javac *.java
	>java HitcoinRunner

* Additionals:

- "Guru.java": Decorater class. 
       If conman steals money more than 20000, his size expand by 10% and a crown appears at the top of them.

- "BlackHole.java": State class.
	    Conmen create a magnetic field and can quickly pull orders around themselves. If the state 
	    is changed from BlackHole to another one while an order is in the magnetic field of a 			    	
	    conman, then the order is sent to the pricePlot with random speed and position again. If an 
	    order is in more than one conman's magnetic field, the order is pulled by the first conman that
	    pass the BlackHole state.

 