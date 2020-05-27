package com.transport.dataservice.dataservice;

import java.util.List;
import com.transport.dataservice.model.DataServiceModel;

public interface DataServiceInterface {
	
	public List<DataServiceModel> findAllRequests();
	
	public DataServiceModel findRequestByEmpId(Integer empId);
	
	public DataServiceModel saveEmployeeRequest(DataServiceModel dataServiceModel);
	
	public boolean changeStatus();
	
	public  Boolean deleteRequest(Integer id);
	
	public void modifyBatchRequests(List<DataServiceModel> requestData);
}
