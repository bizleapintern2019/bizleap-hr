package com.bizleap.hr.loader;

public interface AssociationMapper {
    void setUpAssociations();
    void handleLinkageError(String message, Object source);
}
