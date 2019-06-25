package com.bizleap.hr.loader.impl;

import com.bizleap.commons.domain.entity.Company;
import com.bizleap.commons.domain.entity.Location;
import com.bizleap.commons.domain.entity.Position;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;
import com.bizleap.commons.domain.utils.Printer;
import com.bizleap.hr.loader.CompanySaver;
import com.bizleap.hr.loader.LocationSaver;
import com.bizleap.hr.loader.PositionSaver;
import com.bizleap.service.CompanyService;
import com.bizleap.service.LocationService;
import com.bizleap.service.PositionService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class PositionSaverImpl implements PositionSaver {
    private static Logger logger = Logger.getLogger(PositionSaverImpl.class);
    private static Printer printer = new Printer(logger);

    @Autowired
    PositionService positionService;

    List<Position> positionList;

    public void savePass1() throws ServiceUnavailableException, IOException {
        printer.line("Saving Position: "+ getPositionList().size());
        for(Position position:getPositionList()) {
            positionService.savePosition(position);
        }
        printer.line("Saving Completed");
    }

    public void setPositionList(List<Position> positionList) {
        this.positionList=positionList;
    }

    public List<Position> getPositionList() {
        return this.positionList;
    }
}
