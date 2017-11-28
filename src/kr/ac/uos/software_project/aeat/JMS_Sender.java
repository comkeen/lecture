package kr.ac.uos.software_project.aeat;

public class JMS_Sender {

	public JMS_Sender() {
		System.out.println("create " + this.getClass().getName());
	}
	
	public void sendCapToGateway(String capMessage, AlertGateway alertGateway) {
		alertGateway.recevieCap(capMessage);
	}
}
