package com.bizleap.internhr.service.resource;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ResponseBody;

import com.bizleap.commons.domain.entity.Location;
import com.bizleap.commons.domain.entity.Position;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;

public interface PositionServiceResource {
	List<Position> getAllPosition(HttpServletRequest request) throws ServiceUnavailableException;
	boolean createPosition(HttpServletRequest request,Position position);
	Position findByPositionBoId(HttpServletRequest request,String boId) throws ServiceUnavailableException;
}
