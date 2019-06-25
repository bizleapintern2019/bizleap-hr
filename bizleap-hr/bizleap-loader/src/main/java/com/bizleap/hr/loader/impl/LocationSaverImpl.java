package com.bizleap.hr.loader.impl;

import com.bizleap.commons.domain.entity.Company;
import com.bizleap.commons.domain.entity.Location;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;
import com.bizleap.commons.domain.utils.Printer;
import com.bizleap.hr.loader.CompanySaver;
import com.bizleap.hr.loader.LocationSaver;
import com.bizleap.service.CompanyService;
import com.bizleap.service.LocationService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class LocationSaverImpl implements LocationSaver {
    private static Logger logger = Logger.getLogger(LocationSaverImpl.class);
    private static Printer printer = new Printer(logger);

    @Autowired
    LocationService locationService;

    List<Location> locationList;

    public void savePass1() throws ServiceUnavailableException, IOException {
        printer.line("Saving Location: "+ getLocationList().size());
        for(Location location:getLocationList()) {
            locationService.saveLocation(location);
        }
        printer.line("Saving Completed");
    }

    public void setLocationList(List<Location> locationList) {
        this.locationList=locationList;
    }

    public List<Location> getLocationList() {
        return this.locationList;
    }
}