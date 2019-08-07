package com.lms.ls.rest.util;

import com.lms.ls.rest.model.client.LibraryRestResponse;
import com.lms.ls.rest.model.client.RequestMetaData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;

public class LibraryServiceUtil {
    private LibraryServiceUtil() {}

    public static ResponseEntity<Object> returnResponse(Object body, HttpStatus status) {
        LibraryRestResponse<Object> response = new LibraryRestResponse<>();
        response.setBody(body);
        RequestMetaData metaData = new RequestMetaData();
        metaData.setRequestedBy("APPLICATION");
        metaData.setRequestTimestamp(new Date());
        response.setMetaData(metaData);
        response.setFinalStatus(status.isError() ? "FAILURE" : "Success");
        if(body instanceof List) {
            response.setRecordReturned(((List)body).size());
        } else {
            response.setRecordReturned(1);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
