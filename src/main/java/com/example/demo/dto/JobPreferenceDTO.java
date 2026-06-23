package com.example.demo.dto;

public class JobPreferenceDTO {
           private int preferenceId;
           
           private String preferredRole;
           
           private String location;
           
           
           public JobPreferenceDTO() {
           }

		   public JobPreferenceDTO(int preferenceId, String preferredRole, String location) {
			super();
			this.preferenceId = preferenceId;
			this.preferredRole = preferredRole;
			this.location = location;
		   }

		   public int getPreferenceId() {
			   return preferenceId;
		   }

		   public void setPreferenceId(int preferenceId) {
			   this.preferenceId = preferenceId;
		   }

		   public String getPreferredRole() {
			   return preferredRole;
		   }

		   public void setPreferredRole(String preferredRole) {
			   this.preferredRole = preferredRole;
		   }

		   public String getLocation() {
			   return location;
		   }

		   public void setLocation(String location) {
			   this.location = location;
		   }
           
           
}



