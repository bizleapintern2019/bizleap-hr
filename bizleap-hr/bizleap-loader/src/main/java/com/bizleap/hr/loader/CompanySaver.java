package com.bizleap.hr.loader;

import java.io.IOException;
import java.util.List;
import com.bizleap.commons.domain.entity.Company;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;

public interface CompanySaver {
    void savePass1() throws ServiceUnavailableException, IOException;
    void setCompanyList(List<Company> companyList);
}
