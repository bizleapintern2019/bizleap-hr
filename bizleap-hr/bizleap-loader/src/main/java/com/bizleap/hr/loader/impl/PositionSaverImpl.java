package com.bizleap.hr.loader.impl;

import com.bizleap.commons.domain.entity.Position;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;
import com.bizleap.hr.loader.PositionSaver;
import com.bizleap.service.PositionService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.List;

// @Author: Soe Min Thein
@Service
public class PositionSaverImpl implements PositionSaver {
	
    private static Logger logger = Logger.getLogger(PositionSaverImpl.class);

    @Autowired
    private PositionService positionService;

    private List<Position> positionList;

    public void setPositionList(List<Position> positionList) {
        this.positionList=positionList;
    }

    public List<Position> getPositionList() {
        return this.positionList;
    }
    
    public void savePass1() throws ServiceUnavailableException, IOException {
        logger.info("Saving Position: "+ getPositionList().size());
        for(Position position:getPositionList()) {
            positionService.savePosition(position);
        }
        logger.info("Saving Completed");
    }
}