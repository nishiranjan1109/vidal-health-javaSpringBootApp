package dto;

public class webhookRequest {
	 private String name;
	    private String regNo;
	    private String email;

	    public webhookRequest(String name, String regNo, String email) {
	        this.name = name;
	        this.regNo = regNo;
	        this.email = email;
	    }

	    public webhookRequest() {}

}
