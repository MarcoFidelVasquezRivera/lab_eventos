package model;

public class Chair{
//+++++++++++++++++++++++++++++++++++++++++++++++++++++
//					   ATTRIBUTES
//+++++++++++++++++++++++++++++++++++++++++++++++++++++
	public final static String OPERATIVA = "operativa";
	public final static String DEFECTUOSA = "defectuosa";
	public final static String NODISPONIBLE = "no disponible";
	public final static String DISPONIBLE = "disponible";
	private String status;
	private String statusForEvent;
	private String descriptionDefective;
//+++++++++++++++++++++++++++++++++++++++++++++++++++++
//						METHODS
//+++++++++++++++++++++++++++++++++++++++++++++++++++++
	public Chair(String status, String statusForEvent){
		this.status=status;
		this.statusForEvent=statusForEvent;
	}

	public String getStatus(){
		return status;
	}

	public String getStatusForEvent(){
		return statusForEvent;
	}

	public String getDescriptionDefective(){
		return descriptionDefective;
	}

	public void setStatus(String status){
		this.status=status;
	}

	public void setStatusForEvent(String statusEvent){
		statusForEvent=statusEvent;
	}

	public void setDescriptionDefective(String descriptionDefective){
		this.descriptionDefective=descriptionDefective;
	}

}