package com.learning.basics.controller;

import com.learning.basics.models.LeadInfo;
import com.learning.basics.models.ResponseObjectStatus;
import com.learning.basics.database.AttendanceDataRepository;
import com.learning.basics.database.LeadInfoRepository;
import com.learning.basics.models.AttendanceData;
import com.learning.basics.models.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequestMapping("/api")
public class AppController {

    public static final String LEAD_ID = "lead_id";
    public static final String FAILURE = "failure";
    public static final String SUCCESS = "success";
    public static final String LEAD_ID_IS_NULL = "lead_id is null";

    @Autowired
    private LeadInfoRepository leadInfoRepository;

    @Autowired
    private AttendanceDataRepository attendanceDataRepository;

    @GetMapping("/")
    public String dummyGet(){
        return ("<h1>Hello Spring</h1>");
    }

    @GetMapping("/admin")
    public String adminGet(){
        return ("<h1>Hello Admin</h1>");
    }

    @GetMapping("/user")
    public String userGet(){
        return ("<h1>Hello User</h1>");
    }

    /**
     * This controller method is used to fetch the Lead Info details from the stored Data
     * based on the lead_id sent in the parameter
     *
     * @param lead_id which is the unique Id of the lead
     * @return Response based on given lead id
     */
    @GetMapping("/leads/{lead_id}")
    public ResponseEntity<?> getLeadInfo(@PathVariable(LEAD_ID) Integer lead_id) {
        if (null == lead_id) {
            return getResponseEntityForNullLeadId();
        }
        LeadInfo leadInfo = leadInfoRepository.getOne(lead_id);
        if (null == leadInfo) {
            return new ResponseEntity<>(new LeadInfo(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(leadInfo, HttpStatus.OK);
    }

    /**
     * This method is used to add a new lead into the DB based on the data sent in the request body
     *
     * @param lead_info which contains the lead details
     * @return the outcome of the save
     */
    @PostMapping("/leads/")
    public ResponseEntity<?> addLeadInfo(@RequestBody LeadInfo lead_info) {
        try {
            lead_info.setStatus(Status.Created);
            leadInfoRepository.save(lead_info);
            return new ResponseEntity<>(lead_info, HttpStatus.CREATED);
        } catch (Throwable ex) {
            ResponseObjectStatus responseObjectStatus = new ResponseObjectStatus();
            responseObjectStatus.setStatus(FAILURE);
            responseObjectStatus.setReason(ex.getMessage());
            return new ResponseEntity<>(responseObjectStatus, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * This method is used to update the existing lead details
     *
     * @param lead_id which is used as an identifier for the specific lead to be updated
     * @param lead_info which contains the new updated details of the lead
     * @return the outcome of the update
     */
    @PutMapping("/leads/{lead_id}")
    public ResponseEntity<?> updateLeadInfo(@PathVariable(LEAD_ID) Integer lead_id, @RequestBody LeadInfo lead_info){
        ResponseObjectStatus responseObjectStatus = new ResponseObjectStatus();

        LeadInfo leadInfoFromDB = getUpdatedLeadInfo(lead_id, lead_info);

        try{
            leadInfoRepository.save(leadInfoFromDB);
            responseObjectStatus.setStatus(SUCCESS);
        }
        catch(Throwable ex){
            responseObjectStatus.setStatus(FAILURE);
            responseObjectStatus.setReason(ex.getMessage());
            return new ResponseEntity<>(responseObjectStatus, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(responseObjectStatus, HttpStatus.ACCEPTED);
    }

    private LeadInfo getUpdatedLeadInfo(Integer lead_id, LeadInfo lead_info) {
        LeadInfo leadInfoFromDB = leadInfoRepository.getOne(lead_id);
        leadInfoFromDB.setFirst_name(lead_info.getFirst_name());
        leadInfoFromDB.setLast_name(lead_info.getLast_name());
        leadInfoFromDB.setMobile(lead_info.getMobile());
        leadInfoFromDB.setEmail(lead_info.getEmail());
        leadInfoFromDB.setLocation_type(lead_info.getLocation_type());
        leadInfoFromDB.setLocation_string(lead_info.getLocation_string());
        return leadInfoFromDB;
    }

    /**
     * This method is used to delete the lead with given lead_id
     *
     * @param lead_id which is used as an identifier for the specific lead to be deleted
     * @return the outcome of the delete operation
     */
    @DeleteMapping("/leads/{lead_id}")
    public ResponseEntity<?> removeLeadInfo(@PathVariable(LEAD_ID) Integer lead_id){
        if (null == lead_id) {
            return getResponseEntityForNullLeadId();
        }
        ResponseObjectStatus responseObjectStatus = new ResponseObjectStatus();
        try{
            leadInfoRepository.deleteById(lead_id);
            responseObjectStatus.setStatus(SUCCESS);
        }
        catch(Throwable ex){
            responseObjectStatus.setStatus(FAILURE);
            responseObjectStatus.setReason(ex.getMessage());
            return new ResponseEntity<>(responseObjectStatus, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(responseObjectStatus, HttpStatus.OK);
    }

    /**
     * This method is used to mark the lead's communication status
     *
     * @param lead_id which is used as an identifier for the specific lead to be marked
     * @param lead_info which contains the new updated communication status of the lead
     * @return the outcome of the marking process
     */
    @PutMapping("/mark_lead/{lead_id}")
    public ResponseEntity<?> markLeadInfo(@PathVariable(LEAD_ID) Integer lead_id, @RequestBody LeadInfo lead_info){
        if (null == lead_id || null==lead_info.getCommunication()) {
            return getResponseEntityForNullLeadId();
        }

        LeadInfo leadInfoFromDB = leadInfoRepository.getOne(lead_id);
        leadInfoFromDB.setCommunication(lead_info.getCommunication());
        leadInfoFromDB.setStatus(Status.Contacted);
        leadInfoRepository.save(leadInfoFromDB);
        return new ResponseEntity<>(leadInfoFromDB, HttpStatus.ACCEPTED);
    }

    private ResponseEntity<?> getResponseEntityForNullLeadId() {
        ResponseObjectStatus responseObjectStatus = new ResponseObjectStatus();
        responseObjectStatus.setStatus(FAILURE);
        responseObjectStatus.setReason(LEAD_ID_IS_NULL);
        return new ResponseEntity<>(responseObjectStatus, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/attendance/")
    public ResponseEntity<?> addAttendence(@RequestBody AttendanceData attendanceData) {
        attendanceDataRepository.save(attendanceData);
        return new ResponseEntity<>(attendanceData, HttpStatus.CREATED);
    }




}
