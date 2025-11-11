package org.nmj.dto;

public class CategoryDto {
    private Long id;
    private String name;
    private Long createdBy;
    private String createdByUsername;
    private Long updatedBy;
    private String updatedByUsername;

    public CategoryDto(){}

    public CategoryDto(Long id, String name, Long createdBy){
        this.id=id;
        this.name=name;
        this.createdBy = createdBy;
//        this.updatedBy = updatedBy;
    }

    public CategoryDto(Long id, String name, Long createdBy, Long updatedBy){
        this.id=id;
        this.name=name;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedByUsername(String createdByUsername) {
        this.createdByUsername = createdByUsername;
    }

    public String getCreatedByUsername() {
        return createdByUsername;
    }

    public String getUpdatedByUsername() {
        return updatedByUsername;
    }

    public void setUpdatedByUsername(String updatedByUsername) {
        this.updatedByUsername = updatedByUsername;
    }

    public void setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Long getUpdatedBy() {
        return updatedBy;
    }


}
