package com.bizleap.hr.loader.impl;

import com.bizleap.commons.domain.entity.Location;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;
import com.bizleap.hr.loader.LocationSaver;
import com.bizleap.service.LocationService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

//@Author: Soe Min Thein
@Service
public class LocationSaverImpl implements LocationSaver {
    private static Logger logger = Logger.getLogger(LocationSaverImpl.class);

    @Autowired
    private LocationService locationService;

    private List<Location> locationList;
    
    public void setLocationList(List<Location> locationList) {
        this.locationList = locationList;
    }

    public List<Location> getLocationList() {
        return this.locationList;
    }
    
    public void savePass1() throws ServiceUnavailableException, IOException {
        logger.info("Saving Location: " + getLocationList().size());
        for(Location location : getLocationList()) {
            locationService.saveLocation(location);
        }
        logger.info("Saving Completed");
    }
}